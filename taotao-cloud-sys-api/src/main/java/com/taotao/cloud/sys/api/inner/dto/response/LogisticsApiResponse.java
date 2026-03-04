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
import io.soabase.recordbuilder.core.RecordBuilder;

import java.io.Serial;

/**
 * LogisticsVO
 *
 * @param name 物流公司名称
 * @param code 物流公司code
 * @param contactName 物流公司联系人
 * @param contactMobile 物流公司联系电话
 * @param standBy 支持电子面单
 * @param formItems 物流公司电子面单表单
 * @param disabled 禁用状态 OPEN：开启，CLOSE：禁用
 * @author shuigedeng
 * @version 2022.03
 * @since 2021/12/20 14:06
 */
@RecordBuilder
public record LogisticsApiResponse(Long id, String name, String code, String contactName, String contactMobile,
								   String standBy, String formItems, String disabled)implements MarkerResponse {

	@Serial
	private static final long serialVersionUID = -4132785717179910025L;

}
