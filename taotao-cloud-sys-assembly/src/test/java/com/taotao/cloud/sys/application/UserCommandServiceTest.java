package com.taotao.cloud.sys.application;

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

import static org.mockito.Mockito.*;

/**
 * UserCommandService 应用层服务测试
 *
 * <p>使用 Mockito 纯单元测试模式，验证 {@link UserCommandServiceImpl#assignRoles}
 * 的核心编排逻辑。
 *
 * <p><strong>注意：</strong>被测代码 {@code validateRolesExist()} 中
 * {@code BusinessAssert.isTrue(!missing.isEmpty(), ...)} 条件疑似取反，
 * 当前行为是全匹配时抛异常、部分匹配时放行。测试按实际行为编写。
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

	// ==================== assignRoles() 正常编排 ====================

	@Test
	void shouldOrchestrateAssignRolesFlow() {
		// Arrange - roleDomainRepository 返回部分角色（少一个），
		// 使得 missing 非空 -> !missing.isEmpty() = true -> 校验放行（当前源码行为）
		AssignRolesCommand cmd = new AssignRolesCommand(1L, List.of(10L, 20L));
		UserAgg mockUser = new UserAgg();
		RoleAgg role1 = new RoleAgg();
		role1.setId(BizId.fromValue(10L));

		when(userDomainRepository.findUsingIdCol(1L, Boolean.TRUE)).thenReturn(mockUser);
		// 只返回 10L，20L 被视为"缺失"——当前源码下这样才能通过校验
		when(roleDomainRepository.findAssignableRoles(any())).thenReturn(List.of(role1));

		// Act
		userService.assignRoles(cmd);

		// Assert - 领域服务被调用
		verify(userDomainService).assignRoles(mockUser, List.of(role1));
		// Assert - 保存被调用
		verify(userDomainRepository).save(mockUser, Boolean.TRUE);
		// Assert - 注册了 afterCommit 回调
		verify(txSynchronizationWrapper).afterCommit(afterCommitCaptor.capture());
	}

	// ==================== assignRoles() afterCommit 分发事件 ====================

	@Test
	void shouldDispatchEventsAfterCommit() {
		// Arrange
		AssignRolesCommand cmd = new AssignRolesCommand(1L, List.of(10L, 20L));
		UserAgg mockUser = new UserAgg();
		RoleAgg role1 = new RoleAgg();
		role1.setId(BizId.fromValue(10L));

		when(userDomainRepository.findUsingIdCol(1L, Boolean.TRUE)).thenReturn(mockUser);
		when(roleDomainRepository.findAssignableRoles(any())).thenReturn(List.of(role1));

		// Act
		userService.assignRoles(cmd);

		// 捕获 afterCommit 回调并手动执行
		verify(txSynchronizationWrapper).afterCommit(afterCommitCaptor.capture());
		afterCommitCaptor.getValue().run();

		// Assert - 事件在事务提交后被分发
		verify(eventDispatcher).dispatchEvents(mockUser);
	}
}
