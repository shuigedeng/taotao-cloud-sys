package com.taotao.cloud.sys.domain.aggregate;

import com.taotao.boot.common.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * DeptAgg 聚合根测试
 *
 * <p>测试 {@link DeptAgg} 的业务规则：名称唯一性校验、父子节点自引用校验。
 */
class DeptAggTest {

	// ==================== checkName() ====================

	@Test
	void shouldPassWhenNameIsUnique() {
		DeptAgg dept = new DeptAgg();
		dept.setId(1L);
		dept.checkName(0);
		// no exception expected
	}

	@Test
	void shouldThrowWhenNameAlreadyExists() {
		DeptAgg dept = new DeptAgg();
		dept.checkName(0); // fine
		assertThatThrownBy(() -> dept.checkName(1))
			.isInstanceOf(BusinessException.class)
			.hasMessageContaining("部门名称已存在");
	}

	// ==================== checkIdAndPid() ====================

	@Test
	void shouldPassWhenIdIsDifferentFromPid() {
		DeptAgg dept = new DeptAgg();
		dept.setId(1L);
		dept.setPid(2L);
		dept.checkIdAndPid();
		// no exception expected
	}

	@Test
	void shouldThrowWhenIdEqualsPid() {
		DeptAgg dept = new DeptAgg();
		dept.setId(1L);
		dept.setPid(1L);

		assertThatThrownBy(dept::checkIdAndPid)
			.isInstanceOf(BusinessException.class)
			.hasMessageContaining("上级部门不能为当前部门");
	}
}
