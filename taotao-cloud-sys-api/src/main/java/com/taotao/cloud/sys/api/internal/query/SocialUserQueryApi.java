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
import com.taotao.cloud.sys.api.internal.dto.query.SocialUserApiQuery;
import com.taotao.cloud.sys.api.internal.dto.response.SocialUserApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_07;
import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_08;

/**
 * 远程调用后台用户模块
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@HttpExchange
public interface SocialUserQueryApi {

    /**
     * 添加用户信息
     *
     * @param socialUserApiResponse 用户名称
     * @return 用户信息
     * @since 2020/10/21 15:06
     */
    @PostExchange(value = "/internal/sys/social-user")
	Response<SocialUserApiResponse> query(@RequestBody Request<SocialUserApiQuery> request);

    /**
     * 获取用户信息
     *
     * @param username 用户名称
     * @return 用户信息
     * @since 2020/10/21 15:06
     */
    @PostExchange(value = "/internal/sys/social-user/info/username")
	Response<SocialUserApiResponse> queryUsername(@RequestBody Request<SocialUserApiQuery> request);

    /**
     * 通过第三方查询用户包括角色权限等
     *
     * @param providerId     providerId
     * @param providerUserId providerUserId
     * @return 系统用户信息
     * @since 2020/4/29 17:47
     */

    @PostExchange(value = "/internal/sys/social-user/info/social")
	Response<SocialUserApiResponse> querySocial(@RequestBody Request<SocialUserApiQuery> request);

    /**
     * 通过用户名查询用户包括角色权限等
     *
     * @param nicknameOrUserNameOrPhoneOrEmail 用户名
     * @return 系统用户信息
     * @since 2020/4/29 17:48
     */
    @PostExchange(value = "/internal/sys/social-user/info/security")
	Response<SocialUserApiResponse> querySysSecurityUser(@RequestBody Request<SocialUserApiQuery> request);
}
