package com.taotao.cloud.sys.domain.aggregate;

import com.taotao.boot.ddd.model.val.BizId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * RoleAgg 聚合根测试
 *
 * <p>测试 {@link RoleAgg} 的业务规则：创建、权限管理、状态判断。
 */
class RoleAggTest {

	// ==================== create() ====================

	@Test
	void shouldCreateRoleWithInitialState() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "系统管理角色");

		assertThat(role.getCode()).isEqualTo("ROLE_ADMIN");
		assertThat(role.getName()).isEqualTo("管理员");
		assertThat(role.getDescription()).isEqualTo("系统管理角色");
		assertThat(role.getPermissionIds()).isEmpty();
		assertThat(role.isEnabled()).isTrue();
	}

	// ==================== updateNameOrDesc() ====================

	@Test
	void shouldUpdateNameAndDescription() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "描述");
		role.updateNameOrDesc("超级管理员", "新描述");

		assertThat(role.getName()).isEqualTo("超级管理员");
		assertThat(role.getDescription()).isEqualTo("新描述");
	}

	@Test
	void shouldSkipEmptyUpdate() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "描述");
		role.updateNameOrDesc("", null);

		assertThat(role.getName()).isEqualTo("管理员");
		assertThat(role.getDescription()).isEqualTo("描述");
	}

	// ==================== addPermission() ====================

	@Test
	void shouldAddPermission() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.addPermission("PERM_USER_VIEW");

		assertThat(role.hasPermission("PERM_USER_VIEW")).isTrue();
	}

	@Test
	void shouldNotAddDuplicatePermission() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.addPermission("PERM_USER_VIEW");
		role.addPermission("PERM_USER_VIEW");

		assertThat(role.getPermissionIds()).hasSize(1);
	}

	// ==================== addPermissions() ====================

	@Test
	void shouldAddMultiplePermissions() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.addPermissions(List.of("PERM_USER_VIEW", "PERM_USER_EDIT"));

		assertThat(role.getPermissionIds()).hasSize(2);
	}

	@Test
	void shouldFilterBlankPermissions() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.addPermissions(List.of("PERM_USER_VIEW", "", " ", null));

		assertThat(role.getPermissionIds()).hasSize(1);
	}

	@Test
	void shouldHandleNullPermissionListGracefully() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.addPermissions(null);

		assertThat(role.getPermissionIds()).isEmpty();
	}

	// ==================== removePermission() ====================

	@Test
	void shouldRemovePermission() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.addPermission("PERM_USER_VIEW");
		role.removePermission("PERM_USER_VIEW");

		assertThat(role.hasPermission("PERM_USER_VIEW")).isFalse();
	}

	@Test
	void shouldIgnoreRemoveNullPermission() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.removePermission(null);
		// no exception expected
	}

	// ==================== removePermissions() ====================

	@Test
	void shouldRemoveMultiplePermissions() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.addPermissions(List.of("P1", "P2", "P3"));
		role.removePermissions(List.of("P1", "P3"));

		assertThat(role.getPermissionIds()).containsExactly("P2");
	}

	// ==================== clearPermissions() ====================

	@Test
	void shouldClearAllPermissions() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		role.addPermissions(List.of("P1", "P2"));
		role.clearPermissions();

		assertThat(role.getPermissionIds()).isEmpty();
	}

	// ==================== isEnabled() ====================

	@Test
	void newRoleShouldBeEnabled() {
		RoleAgg role = RoleAgg.create("ROLE_ADMIN", "管理员", "");
		assertThat(role.isEnabled()).isTrue();
	}
}
