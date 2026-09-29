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

package com.taotao.cloud.sys.api.internal.query;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.common.support.info.ApiInfo;
import com.taotao.boot.common.support.info.Create;
import com.taotao.boot.common.support.info.Update;
import com.taotao.cloud.sys.api.internal.dto.query.FileApiQuery;
import com.taotao.cloud.sys.api.internal.dto.response.FileApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_07;
import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_08;

/**
 * 文件查询 API
 * <p>提供文件相关的查询操作接口（远程调用）</p>
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@HttpExchange
public interface FileQueryApi {

    /**
     * 字典列表code查询
     *
     * @param code 代码
     * @return {@link FileApiResponse }
     * @since 2022-06-29 21:40:21
     */
    @PostExchange("/internal/file/file/code")
	Response<FileApiResponse> query(@RequestBody Request<FileApiQuery> request);
}
