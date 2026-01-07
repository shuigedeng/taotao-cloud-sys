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

package com.taotao.cloud.sys.interfaces.inner;

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
import com.taotao.boot.webagg.controller.InnerController;
import com.taotao.cloud.sys.api.inner.command.DictCommandApi;
import com.taotao.cloud.sys.api.inner.dto.request.DictQueryApiRequest;
import com.taotao.cloud.sys.api.inner.dto.response.DictApiResponse;
import com.taotao.cloud.sys.api.inner.query.DictQueryApi;
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
@RequiredArgsConstructor
@RestController
@RequestMapping
public class DictCommandApiController extends InnerController implements DictCommandApi {

	private final DictCommandService dictCommandService;

	@Override
	public Response<DictApiResponse> findByCode( Request<DictQueryApiRequest> dictQueryApiRequest ) {
		return null;
	}

	@Override
	public Response<DictApiResponse> test( Request<DictQueryApiRequest> dictQueryApiRequest ) {
		return null;
	}
}
