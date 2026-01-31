/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.sys.service;

import com.taotao.boot.test.junitperf.core.annotation.TtcTest;
import com.taotao.boot.test.junitperf.core.report.impl.HtmlReporter;
import com.taotao.cloud.sys.TaoTaoCloudSysApplicationTests;
import com.taotao.cloud.sys.application.dto.own.dept.result.DeptTreeResult;
import com.taotao.cloud.sys.application.service.commad.DeptCommandService;
import java.util.List;

import com.taotao.cloud.sys.domain.repository.DeptDomainRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BrandQueryServiceTest
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
public class BrandQueryServiceTest extends TaoTaoCloudSysApplicationTests {

    @Autowired private DeptCommandService deptService;
	@Autowired
	private DeptCommandService deptCommandService;
	@Autowired
	private DeptDomainRepository deptDomainRepository;
    //	@Test
    @TtcTest(duration = 1000, reporter = HtmlReporter.class)
    public void treeTest() throws InterruptedException {

        List<DeptTreeResult> tree = deptService.tree();
        System.out.println("asdfasdfsadfsadf");
    }

	@Test
	public void findById(){
		deptDomainRepository.findById(1L);
	}
}
