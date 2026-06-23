package com.taotao.cloud.sys.domain.aggregate;

import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.common.enums.UserStatusEnum;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserAgg 聚合根测试
 *
 * <p>测试 {@link UserAgg} 的业务规则：创建、状态变更、角色管理、事件发布。
 */
class UserAggTest {

	// ==================== create() ====================

	@Test
	void shouldCreateUserWithInitialState() {
		UserAgg user = UserAgg.create("13800138000");

		assertThat(user.getMobile()).isEqualTo("13800138000");
		assertThat(user.getStatus()).isEqualTo(UserStatusEnum.NORMAL);
		assertThat(user.getRoleIds()).isEmpty();
	}

	// ==================== updateInfo() ====================

	@Test
	void shouldUpdateRealNameAndMobile() {
		UserAgg user = UserAgg.create("13800138000");
		user.updateInfo("张三", "13900139000");

		assertThat(user.getRealName()).isEqualTo("张三");
		assertThat(user.getMobile()).isEqualTo("13900139000");
	}

	@Test
	void shouldSkipUpdateWhenNameIsBlank() {
		UserAgg user = UserAgg.create("13800138000");
		user.updateInfo("  ", "13900139000");

		assertThat(user.getMobile()).isEqualTo("13900139000");
	}

	// ==================== enable() / freeze() ====================

	@Test
	void shouldEnableUser() {
		UserAgg user = UserAgg.create("13800138000");
		user.freeze();
		user.enable();

		assertThat(user.isNormal()).isTrue();
	}

	@Test
	void shouldFreezeUser() {
		UserAgg user = UserAgg.create("13800138000");
		user.freeze();

		assertThat(user.isNormal()).isFalse();
	}

	// ==================== addRole() ====================

	@Test
	void shouldAddRole() {
		UserAgg user = UserAgg.create("13800138000");
		user.addRole(1L);

		assertThat(user.hasRole(BizId.fromValue(1L))).isTrue();
		assertThat(user.isRoleIdModified()).isTrue();
	}

	@Test
	void shouldNotAddDuplicateRole() {
		UserAgg user = UserAgg.create("13800138000");
		user.addRole(1L);
		user.addRole(1L);

		assertThat(user.getRoleIds()).hasSize(1);
	}

	@Test
	void shouldIgnoreNullRole() {
		UserAgg user = UserAgg.create("13800138000");
		user.addRole(null);

		assertThat(user.getRoleIds()).isEmpty();
	}

	// ==================== addRoles() ====================

	@Test
	void shouldAddMultipleRoles() {
		UserAgg user = UserAgg.create("13800138000");
		user.addRoles(List.of(1L, 2L, 3L));

		assertThat(user.getRoleIds()).hasSize(3);
	}

	// ==================== removeRoles() ====================

	@Test
	void shouldRemoveRoles() {
		UserAgg user = UserAgg.create("13800138000");
		user.addRoles(List.of(1L, 2L, 3L));
		user.removeRoles(List.of(BizId.fromValue(2L)));

		assertThat(user.hasRole(BizId.fromValue(2L))).isFalse();
		assertThat(user.getRoleIds()).hasSize(2);
	}

	// ==================== clearRoles() ====================

	@Test
	void shouldClearAllRoles() {
		UserAgg user = UserAgg.create("13800138000");
		user.addRoles(List.of(1L, 2L));
		user.clearRoles();

		assertThat(user.getRoleIds()).isEmpty();
		assertThat(user.isRoleIdModified()).isTrue();
	}

	// ==================== status checks ====================

	@Test
	void newUserShouldBeNormalAndAvailable() {
		UserAgg user = UserAgg.create("13800138000");
		assertThat(user.isNormal()).isTrue();
		assertThat(user.isAvailable()).isTrue();
		assertThat(user.isDeleted()).isFalse();
	}

	// ==================== updateLastLoginTime() ====================

	@Test
	void shouldUpdateLastLoginTime() {
		UserAgg user = UserAgg.create("13800138000");
		user.updateLastLoginTime();

		assertThat(user.getLastLoginTime()).isNotNull();
	}
}
