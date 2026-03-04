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

package com.taotao.cloud.sys.application.dto.own.dict.query;

import com.taotao.boot.common.model.ddd.types.Query;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DictQuery
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@RecordBuilder
public record DictQuery(
	@Schema(description = "字典名称") @NotBlank(message = "字典名称不能为空") @Size(max = 10, message = "字典名称不能超过10个字符") String dictName,
	@Schema(description = "字典编码") @NotBlank(message = "字典编码不能为空") @Size(max = 10, message = "字典编码不能超过10个字符") String dictCode,
	@Schema(description = "描述") String description, @Schema(description = "排序值") Integer dictSort,
	@Schema(description = "备注信息") String remark)implements Query {

}
