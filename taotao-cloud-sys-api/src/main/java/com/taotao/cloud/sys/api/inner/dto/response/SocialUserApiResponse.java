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

package com.taotao.cloud.sys.api.inner.dto.response;

import com.taotao.boot.common.model.ddd.types.MarkerResponse;
import com.taotao.cloud.sys.api.enums.AuthUserGender;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>社会化登录用户 </p>
 *
 * @param uuid JustAuth中的关键词 以下内容了解后，将会使你更容易地上手JustAuth。
 * <p>
 * source JustAuth支持的第三方平台，比如：GITHUB、GITEE等 uuid 一般为第三方平台的用户ID。以下几个平台需特别注意： 钉钉、抖音：uuid 为用户的 unionid
 * 微信公众平台登录、京东、酷家乐、美团：uuid 为用户的 openId 微信开放平台登录、QQ：uuid 为用户的 openId，平台支持获取unionid， unionid 在 AuthToken
 * 中（如果支持），在登录完成后，可以通过 response.getData().getToken().getUnionId() 获取 Google：uuid 为用户的
 * sub，sub为Google的所有账户体系中用户唯一的身份标识符，详见：OpenID Connect (opens new window) 注：建议通过uuid +
 * source的方式唯一确定一个用户，这样可以解决用户身份归属的问题。因为 单个用户ID 在某一平台中是唯一的，但不能保证在所有平台中都是唯一的。
 * @param gender 性别
 */
@RecordBuilder
public record SocialUserApiResponse(Long id, @Schema(title = "社会用户ID") String socialId,
									@Schema(title = "用户第三方系统的唯一id", description = "在调用方集成该组件时，可以用uuid + source唯一确定一个用") String uuid,
									@Schema(title = "用户名") String userName,
									@Schema(title = "用户昵称") String nickName,
									@Schema(title = "用户头像") String avatar, @Schema(title = "用户网址") String blog,
									@Schema(title = "所在公司") String company, @Schema(title = "位置") String location,
									@Schema(title = "用户邮箱") String email, @Schema(title = "用户邮箱") String remark,
									@Schema(title = "性别") AuthUserGender gender,
									@Schema(title = "第三方用户来源") String source,
									@Schema(title = "用户的授权令牌") String accessToken,
									@Schema(title = "第三方用户的授权令牌的有效期", description = "部分平台可能没有") Integer expireIn,
									@Schema(title = "刷新令牌", description = "部分平台可能没有") String refreshToken,
									@Schema(title = "第三方用户的刷新令牌的有效期", description = "部分平台可能没有") Integer refreshTokenExpireIn,
									@Schema(title = "第三方用户授予的权限", description = "部分平台可能没有") String scope,
									@Schema(title = "个别平台的授权信息", description = "部分平台可能没有") String tokenType,
									@Schema(title = "第三方用户的 ID", description = "部分平台可能没有") String uid,
									@Schema(title = "第三方用户的 open id", description = "部分平台可能没有") String openId,
									@Schema(title = "个别平台的授权信息", description = "部分平台可能没有") String accessCode,
									@Schema(title = "第三方用户的 union id", description = "部分平台可能没有") String unionId,
									@Schema(title = "小程序Appid", description = "部分平台可能没有") String appId,
									@Schema(title = "手机号码", description = "部分平台可能没有") String phoneNumber) implements
	MarkerResponse {

}
