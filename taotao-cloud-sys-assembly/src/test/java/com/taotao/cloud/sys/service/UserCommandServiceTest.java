package com.taotao.cloud.sys.service;

import com.taotao.boot.data.datasource.wrapper.TransactionSynchronizationWrapper;
import com.taotao.boot.ddd.model.event.EventDispatcher;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.application.dto.user.command.AssignRolesCommand;
import com.taotao.cloud.sys.application.service.commad.impl.UserCommandServiceImpl;
import com.taotao.cloud.sys.domain.aggregate.RoleAgg;
import com.taotao.cloud.sys.domain.aggregate.UserAgg;
import com.taotao.cloud.sys.domain.repository.RoleDomainRepository;
import com.taotao.cloud.sys.domain.repository.UserDomainRepository;
import com.taotao.cloud.sys.domain.service.UserDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * UserCommandService 应用层服务测试
 *
 * <p>使用 Mockito 纯单元测试模式（无需 Spring 容器），验证 {@link UserCommandServiceImpl#assignRoles}
 * 的核心编排逻辑：加载聚合 → 校验 → 调用领域服务 → 保存 → 分发事件。
 */
@ExtendWith(MockitoExtension.class)
class UserCommandServiceTest {

	@Mock
	private UserDomainRepository userDomainRepository;

	@Mock
	private RoleDomainRepository roleDomainRepository;

	@Mock
	private UserDomainService userDomainService;

	@Mock
	private TransactionSynchronizationWrapper txSynchronizationWrapper;

	@Mock
	private EventDispatcher eventDispatcher;

	@InjectMocks
	private UserCommandServiceImpl userService;

	@Captor
	private ArgumentCaptor<Runnable> afterCommitCaptor;

	// ==================== assignRoles() 正常流程 ====================

	@Test
	void shouldAssignRolesSuccessfully() {
		// Arrange
		AssignRolesCommand cmd = new AssignRolesCommand(1L, List.of(10L, 20L));
		UserAgg mockUser = new UserAgg();
		RoleAgg role1 = new RoleAgg();
		role1.setId(BizId.fromValue(10L));
		RoleAgg role2 = new RoleAgg();
		role2.setId(BizId.fromValue(20L));

		when(userDomainRepository.findUsingIdCol(1L, Boolean.TRUE)).thenReturn(mockUser);
		when(roleDomainRepository.findAssignableRoles(any())).thenReturn(List.of(role1, role2));

		// Act
		userService.assignRoles(cmd);

		// Assert - 领域服务被正确调用
		verify(userDomainService).assignRoles(mockUser, List.of(role1, role2));

		// Assert - 保存被调用
		verify(userDomainRepository).save(mockUser, Boolean.TRUE);

		// Assert - 注册了 afterCommit 回调
		verify(txSynchronizationWrapper).afterCommit(afterCommitCaptor.capture());
	}

	// ==================== assignRoles() 角色不存在 ====================

	@Test
	void shouldThrowWhenRequestedRoleDoesNotExist() {
		// Arrange
		AssignRolesCommand cmd = new AssignRolesCommand(1L, List.of(10L, 99L));
		UserAgg mockUser = new UserAgg();
		RoleAgg role1 = new RoleAgg();
		role1.setId(BizId.fromValue(10L));

		when(userDomainRepository.findUsingIdCol(1L, Boolean.TRUE)).thenReturn(mockUser);
		// Only role 10L is returned; 99L is missing
		when(roleDomainRepository.findAssignableRoles(any())).thenReturn(List.of(role1));

		// Act & Assert
		assertThatThrownBy(() -> userService.assignRoles(cmd))
			.isInstanceOf(IllegalArgumentException.class);

		// 不满足前置校验，后续调用不应发生
		verify(userDomainService, never()).assignRoles(any(), any());
		verify(userDomainRepository, never()).save(any(), anyBoolean());
	}

	// ==================== assignRoles() afterCommit 分发事件 ====================

	@Test
	void shouldDispatchEventsAfterCommit() {
		// Arrange
		AssignRolesCommand cmd = new AssignRolesCommand(1L, List.of(10L));
		UserAgg mockUser = new UserAgg();
		RoleAgg role1 = new RoleAgg();
		role1.setId(BizId.fromValue(10L));

		when(userDomainRepository.findUsingIdCol(1L, Boolean.TRUE)).thenReturn(mockUser);
		when(roleDomainRepository.findAssignableRoles(any())).thenReturn(List.of(role1));

		// Act
		userService.assignRoles(cmd);

		// Capture the afterCommit callback and execute it manually
		verify(txSynchronizationWrapper).afterCommit(afterCommitCaptor.capture());
		afterCommitCaptor.getValue().run();

		// Assert - 事件在事务提交后被分发
		verify(eventDispatcher).dispatchEvents(mockUser);
	}
}
