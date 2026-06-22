package com.taotao.cloud.sys.domain.aggregate;

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.ddd.model.val.BizId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * DictAgg 聚合根测试
 *
 * <p>测试 {@link DictAgg} 的业务规则：名称唯一性校验、父子节点自引用校验。
 */
class DictAggTest {

	// ==================== checkName() ====================

	@Test
	void shouldPassWhenNameIsUnique() {
		DictAgg dict = new DictAgg();
		dict.checkName(0); // count=0 means no duplicate
		// no exception expected
	}

	@Test
	void shouldThrowWhenNameAlreadyExists() {
		DictAgg dict = new DictAgg();
		assertThatThrownBy(() -> dict.checkName(1))
			.isInstanceOf(BusinessException.class)
			.hasMessageContaining("部门名称已存在");
	}

	// ==================== checkIdAndPid() ====================

	@Test
	void shouldPassWhenIdIsDifferentFromPid() {
		DictAgg dict = new DictAgg();
		dict.setId(BizId.fromValue(1L));
		dict.setPid(BizId.fromValue(2L));
		dict.checkIdAndPid();
		// no exception expected
	}

	@Test
	void shouldThrowWhenIdEqualsPid() {
		BizId sameId = BizId.fromValue(1L);
		DictAgg dict = new DictAgg();
		dict.setId(sameId);
		dict.setPid(sameId);

		assertThatThrownBy(dict::checkIdAndPid)
			.isInstanceOf(BusinessException.class)
			.hasMessageContaining("上级部门不能为当前部门");
	}
}
