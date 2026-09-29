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
import com.taotao.boot.common.model.response.BatchResponse;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.sys.api.internal.dto.query.RoleApiQuery;
import com.taotao.cloud.sys.api.internal.dto.response.RoleQueryApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

/**
 * 远程调用后台角色模块
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2022-03-25 14:09:31
 */
@HttpExchange
public interface RoleQueryApi {

    /**
     * 根据用户id获取角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     * @since 2020/10/21 15:13
     */
    @PostExchange("/internal/sys/role/info")
	Response<BatchResponse<RoleQueryApiResponse>> query(@RequestBody Request<RoleApiQuery> request);
}
