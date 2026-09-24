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

package com.taotao.cloud.sys.interfaces.controller.inner;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.common.support.info.ApiInfo;
import com.taotao.boot.common.support.info.Create;
import com.taotao.boot.common.support.info.Update;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.idempotent.annotation.Idempotent;
import com.taotao.boot.ratelimit.ratelimitguava.GuavaLimit;
import com.taotao.boot.ratelimit.ratelimitguava.Limit;
import com.taotao.boot.security.spring.annotation.NotAuth;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.InnerController;
import com.taotao.cloud.sys.api.inner.command.DictCommandApi;
import com.taotao.cloud.sys.api.inner.dto.command.CreateDictApiCommad;
import com.taotao.cloud.sys.api.inner.dto.query.DictApiQuery;
import com.taotao.cloud.sys.api.inner.dto.response.DictApiResponse;
import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
import com.taotao.cloud.sys.application.assembler.DictAppAssembler;
import com.taotao.cloud.sys.application.dto.dict.result.DictQueryResult;
import com.taotao.cloud.sys.application.service.command.DictCommandService;
//import com.yomahub.tlog.core.annotation.TLogAspect;
import com.taotao.cloud.sys.application.service.query.DictQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_07;
import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_08;

/**
 * 为远程客户端提供粗粒度的调用接口
 */
@RequiredArgsConstructor
@RestController
@RequestMapping
@Tag(name = "内部端-字典API", description = "内部端-字典API")
public class InnerDictController extends InnerController implements DictCommandApi, DictQueryApi {

	private final DictCommandService dictCommandService;
	private final DictQueryService dictQueryService;
	private final DictAppAssembler dictAppAssembler;

	@ApiInfo(
		create = @Create(version = V2022_07, date = "2022-07-01 17:11:55"),
		update = {
			@Update(
				version = V2022_07,
				content = "主要修改了配置信息的接口查询",
				date = "2022-07-01 17:11:55"),
			@Update(
				version = V2022_08,
				content = "主要修改了配置信息的接口查询08",
				date = "2022-07-01 17:11:55")
		})
	@Override
	@Operation(summary = "添加部门", description = "添加部门")
	@RequestLogger
	@Idempotent(perFix = "findByCode")
	@Limit(key = "limitTest", period = 10, count = 3)
	@SentinelResource("findByCode")
	public Response<DictApiResponse> create(@Valid @RequestBody Request<CreateDictApiCommad> request ) {
		if ("sd".equals(request.getBizNo())) {
			throw new BusinessException("我出错了");
			// try {
			//	Thread.sleep(100000000000L);
			// } catch (InterruptedException e) {
			//	throw new RuntimeException(e);
			// }
		}
		LogUtils.info("xxxxxxxxxxxxxxxxxxxxx");
		//		DictPO dictPo = dictService.findByCode(code);
		//		return DictAssembler.INSTANCE.convert(dictPo);
		return Response.from(null);
	}



	@ApiInfo(
		create = @Create(version = V2022_07, date = "2022-07-01 17:11:55"),
		update = {
			@Update(
				version = V2022_07,
				content = "主要修改了配置信息的接口查询",
				date = "2022-07-01 17:11:55"),
			@Update(
				version = V2022_08,
				content = "主要修改了配置信息的接口查询08",
				date = "2022-07-01 17:11:55")
		})
	@Operation(summary = "根据code查询", description = "根据code查询")
	@Override
	@RequestLogger
	@NotAuth
	public Response<DictApiResponse> queryByCode(@Valid @RequestBody Request<DictApiQuery> request ) {
		DictApiQuery order = request.getOrder();
		DictQueryResult result = dictQueryService.queryByCode(order.code());
		return Response.from(dictAppAssembler.toResponse(result));
	}


}
