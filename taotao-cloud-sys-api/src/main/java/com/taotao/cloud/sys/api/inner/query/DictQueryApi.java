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

package com.taotao.cloud.sys.api.inner.query;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.sys.api.inner.dto.query.DictApiQuery;
import com.taotao.cloud.sys.api.inner.dto.response.DictApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;


/**
 * 字典查询 API
 * <p>提供字典相关的查询操作接口（远程调用）</p>
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@HttpExchange
//@HttpExchange(url = "http://taotao-cloud-sys")
public interface DictQueryApi {

	/**
	 * 字典列表code查询
	 *
	 * @param request 代码
	 * @return {@link DictApiResponse }
	 * @since 2022-06-29 21:40:21
	 */
	@PostExchange("/inner/sys/dict/query/code")
	Response<DictApiResponse> queryByCode(@RequestBody Request<DictApiQuery> request);
}
