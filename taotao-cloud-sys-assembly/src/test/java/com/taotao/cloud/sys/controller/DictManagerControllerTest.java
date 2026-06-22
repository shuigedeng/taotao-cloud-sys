package com.taotao.cloud.sys.controller;

import com.taotao.cloud.sys.TaoTaoCloudSysApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * DictManagerController 控制器测试
 *
 * <p>集成测试：验证 {@code GET /manager/sys/dict/query/for-update} 接口的行为。
 */
@AutoConfigureMockMvc
class DictManagerControllerTest extends TaoTaoCloudSysApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void queryForUpdateShouldReturnSuccess() throws Exception {
		mockMvc.perform(get("/manager/sys/dict/query/for-update"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.code").value(200))
			.andExpect(jsonPath("$.data").isEmpty());
	}
}
