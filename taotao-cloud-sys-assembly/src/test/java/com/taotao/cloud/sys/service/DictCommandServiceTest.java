package com.taotao.cloud.sys.service;

import com.taotao.cloud.sys.application.service.command.DictCommandService;
import com.taotao.cloud.sys.application.service.command.impl.DictCommandServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DictCommandService 应用层服务测试
 *
 * <p>验证 {@link DictCommandServiceImpl} 符合 {@link DictCommandService} 接口契约。
 */
class DictCommandServiceTest {

	@Test
	void serviceShouldImplementInterface() {
		DictCommandService service = new DictCommandServiceImpl();
		assertThat(service).isInstanceOf(DictCommandService.class);
	}
}
