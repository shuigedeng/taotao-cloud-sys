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

package com.taotao.cloud.sys.api.rpc.dto.response;

import com.taotao.boot.common.model.ddd.types.MarkerResponse;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * CompanyBO
 *
 * @param tenantId 租户id
 * @param tenantSecret 租户密钥
 * @param name 公司名称
 * @param fullName 企业全称
 * @param creditCode 信用代码
 * @param email 邮箱
 * @param username 联系人
 * @param phone 联系人手机号
 * @param address 联系人地址
 * @param domain 请求域名
 * @param webSite 公司网址
 * @param regionInfo 所在地区
 * @param type 公司类型
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-19 20:47:05
 */
@RecordBuilder
@Schema(description = "公司查询Response")
public record CompanyRpcResponse(
	@Schema(description = "租户id") String tenantId,
	@Schema(description = "租户密钥") String tenantSecret,
	@Schema(description = "公司名称") String name,
	@Schema(description = "企业全称") String fullName,
	@Schema(description = "信用代码") String creditCode,
	@Schema(description = "邮箱") String email,
	@Schema(description = "联系人") String username,
	@Schema(description = "联系人手机号") String phone,
	@Schema(description = "联系人地址") String address,
	@Schema(description = "请求域名") String domain,
	@Schema(description = "公司网址") String webSite,
	@Schema(description = "所在地区") String regionInfo,
	@Schema(description = "公司类型") Integer type)
	implements MarkerResponse {

	@Serial
	private static final long serialVersionUID = 5126530068827085130L;
}
