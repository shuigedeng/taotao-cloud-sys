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

package com.taotao.cloud.sys.application.dto.own.dictitem.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 字典项查询对象
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 15:32:25
 */
@RecordBuilder
@Schema(description = "字典项查询对象")
public record DictItemQueryResult(@Schema(description = "id") Long id, @Schema(description = "字典id") Long dictId,
								  @Schema(description = "字典项文本") String itemText,
								  @Schema(description = "字典项值") String itemValue,
								  @Schema(description = "描述") String description,
								  @Schema(description = "状态(1不启用 2启用)") Integer status,
								  @Schema(description = "创建时间") LocalDateTime createTime,
								  @Schema(description = "最后修改时间") LocalDateTime lastModifiedTime) implements
	MarkerResult {

	@Serial
	private static final long serialVersionUID = -4132785717179910025L;

}
