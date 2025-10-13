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

package com.taotao.cloud.sys.interfaces.feign;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.idempotent.annotation.Idempotent;
import com.taotao.boot.ratelimit.ratelimitguava.GuavaLimit;
import com.taotao.boot.ratelimit.ratelimitguava.Limit;
import com.taotao.boot.security.spring.annotation.NotAuth;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.ApiController;
import com.taotao.cloud.sys.api.feign.DictApi;
import com.taotao.cloud.sys.api.feign.request.DictQueryApiRequest;
import com.taotao.cloud.sys.api.feign.response.DictApiResponse;
import com.taotao.cloud.sys.application.service.commad.DictCommandService;
import com.yomahub.tlog.core.annotation.TLogAspect;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 为远程客户端提供粗粒度的调用接口
 */
@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class DictApiController extends ApiController implements DictApi {

	private final DictCommandService dictCommandService;

	@Override
	@NotAuth
	@Idempotent(perFix = "findByCode")
	@Limit(key = "limitTest", period = 10, count = 3)
	@SentinelResource("findByCode")
	public Response<DictApiResponse> findByCode(
		@Validated @RequestBody Request<DictQueryApiRequest> dictQueryApiRequest) {
		if ("sd".equals(dictQueryApiRequest.getBizNo())) {
			throw new BusinessException("我出错了");
			// try {
			//	Thread.sleep(100000000000L);
			// } catch (InterruptedException e) {
			//	throw new RuntimeException(e);
			// }
		}
		//		DictPO dictPo = dictService.findByCode(code);
		//		return DictAssembler.INSTANCE.convert(dictPo);
		return Response.from(new DictApiResponse());
	}

	@Override
	@Operation(summary = "test", description = "test")
	@RequestLogger
	@NotAuth
	@Idempotent(perFix = "test")
	@TLogAspect(
		value = {"code"},
		pattern = "{{}}",
		joint = ",",
		str = "nihao")
	@Limit(key = "limitTest", period = 10, count = 3)
	@GuavaLimit
	@SentinelResource("test")
	public Response<DictApiResponse> test(
		Request<DictQueryApiRequest> dictQueryApiRequest) {
		LogUtils.info("sldfkslfdjalsdfkjalsfdjl");
		//		Dict dict = service().findByCode(id);
		//
		//		Future<Dict> asyncByCode = service().findAsyncByCode(id);
		//
		//		Dict dict1;
		//		try {
		//			dict1 = asyncByCode.get();
		//		} catch (InterruptedException | ExecutionException e) {
		//			throw new RuntimeException(e);
		//		}
		//
		//		LogUtils.info("我在等待你");

		return null;
		// return IDictMapStruct.INSTANCE.dictToFeignDictRes(dict);
	}
}
