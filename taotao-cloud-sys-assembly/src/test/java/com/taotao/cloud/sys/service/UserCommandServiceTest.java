package com.taotao.cloud.sys.service;

import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.application.dto.own.user.command.UserAssignRolesCommand;
import com.taotao.cloud.sys.application.service.commad.UserCommandService;
import com.taotao.cloud.sys.application.service.commad.impl.UserCommandServiceImpl;
import com.taotao.cloud.sys.domain.aggregate.UserAgg;
import com.taotao.cloud.sys.domain.repository.UserDomainRepository;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.UserPO;
import com.taotao.cloud.sys.infrastructure.persistent.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

// 1. 不需要 @SpringBootTest，不需要启动容器！
// 使用 Mockito 的扩展来初始化 Mock 对象
@ExtendWith(MockitoExtension.class)
class UserCommandServiceTest {

	// 2. @Mock: 创建一个假的 UserRepository，它是空的，需要你喂数据
	@Mock
	private UserDomainRepository userRepository;

	// 3. @InjectMocks: 创建 UserService 实例，并把上面的 userRepository 塞进去
	@InjectMocks
	private UserCommandServiceImpl userService;

	@Test
	void shouldFindUserById() {
		// --- Arrange (准备) ---
		UserAgg mockUser = new UserAgg();

		// 告诉 Mock 对象：当有人调用 findById(1L) 时，返回 mockUser
		when(userRepository.findById(BizId.fromValue(1L), true)).thenReturn(mockUser);
//		when(userRepository.save(mockUser)).thenReturn(null);

		// --- Act (执行) ---
		userService.assignRoles (new UserAssignRolesCommand(1L, new ArrayList<>()));

		// --- Assert (断言) ---
//		assertThat(result.getName()).isEqualTo("Alice");

		// --- Verify (验证) ---
		// 验证 userRepository.findById 是否真的被调用了一次
		verify(userRepository, times(1)).findById(BizId.fromValue(1L));
	}
}
