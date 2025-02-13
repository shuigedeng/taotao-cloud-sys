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

package com.taotao.cloud.sys.facade.controller.mall;

import com.taotao.boot.common.model.Result;
import com.taotao.boot.security.spring.annotation.NotAuth;
import com.taotao.boot.webagg.controller.BusinessController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taotao.boot.webagg.controller.BusinessController;

import java.util.ArrayList;
import java.util.List;

/**
 * 移动端-字典API
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-10-09 14:24:19
 */
@Validated
@RestController
@RequestMapping("/sys/mall/dict")
@Tag(name = "移动端-字典API", description = "移动端-字典API")
public class MallDictController extends BusinessController {

	@NotAuth
	@Operation(summary = "测试mybatis sql", description = "测试mybatis sql")
	@GetMapping("/testMybatisQueryStructure")
	public Result<List<String>> testMybatisQueryStructure() {
		return Result.success(new ArrayList<>());
	}
}
