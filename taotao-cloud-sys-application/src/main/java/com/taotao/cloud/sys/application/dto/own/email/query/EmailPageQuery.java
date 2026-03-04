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

package com.taotao.cloud.sys.application.dto.own.email.query;

import com.taotao.boot.common.model.ValidationGroups;
import com.taotao.boot.common.model.ddd.query.PageQuery;
import com.taotao.boot.common.model.ddd.types.Query;

import java.util.List;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * EmailVo
 *
 * @param tos 收件人，支持多个收件人
 * @author shuigedeng
 * @version 2022.03
 * @since 2022-03-22 09:25:30
 */
@RecordBuilder
public record EmailPageQuery(
	@Schema(description = "分页") @NotNull(message = "分页参数不能为空!", groups = ValidationGroups.Update.class) PageQuery page,
	List<String> tos, String subject, String content) implements Query {

}
