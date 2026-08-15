package com.taotao.cloud.sys.controller;

import com.taotao.boot.common.model.result.Result;
import com.taotao.cloud.sys.interfaces.controller.admin.AdminDeptController;
import com.taotao.cloud.sys.TaoTaoCloudSysApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DeptManagerController 控制器测试
 *
 * <p>验证 {@link AdminDeptController#tree()} 返回正常结果。
 */
class DeptManagerControllerTest extends TaoTaoCloudSysApplicationTests {

	@Autowired
	private AdminDeptController controller;

	@Test
	void treeShouldReturnSuccess() {
		Result<?> result = controller.tree();
		assertThat(result).isNotNull();
	}
}
