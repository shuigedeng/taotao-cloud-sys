package com.taotao.cloud.sys.controller;

import com.taotao.boot.common.model.result.Result;
import com.taotao.cloud.sys.interfaces.controller.manager.DictManagerController;
import com.taotao.cloud.sys.TaoTaoCloudSysApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DictManagerController 控制器测试
 *
 * <p>验证 {@link DictManagerController#queryForUpdate()} 返回正常结果。
 */
class DictManagerControllerTest extends TaoTaoCloudSysApplicationTests {

	@Autowired
	private DictManagerController controller;

	@Test
	void queryForUpdateShouldReturnSuccess() {
		Result<Void> result = controller.queryForUpdate();
		assertThat(result).isNotNull();
	}
}
