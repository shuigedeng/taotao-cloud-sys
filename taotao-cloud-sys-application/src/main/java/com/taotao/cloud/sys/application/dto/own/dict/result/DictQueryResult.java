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

package com.taotao.cloud.sys.application.dto.own.dict.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 字典查询对象
 *
 * @author shuigedeng
 * @since 2020/5/14 10:44
 */
@RecordBuilder
@Schema(description = "字典查询对象")
public record DictQueryResult(@Schema(description = "id") Long id, @Schema(description = "字典名称") String dictName,
							  @Schema(description = "字典编码") String dictCode,
							  @Schema(description = "描述") String description,
							  @Schema(description = "排序值") Integer dictSort,
							  @Schema(description = "备注信息") String remark,
							  @Schema(description = "创建时间") LocalDateTime createTime,
							  @Schema(description = "最后修改时间") LocalDateTime lastModifiedTime) implements
	MarkerResult {

	@Serial
	private static final long serialVersionUID = -4132785717179910025L;
}
