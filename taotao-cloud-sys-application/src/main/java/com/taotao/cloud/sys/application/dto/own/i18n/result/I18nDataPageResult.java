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

package com.taotao.cloud.sys.application.dto.own.i18n.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * 国际化信息分页视图对象
 *
 * @param id ID
 * @param languageTag 语言标签
 * @param code 国际化标识
 * @param message 文本值，可以使用 { } 加角标，作为占位符
 * @param remarks 备注
 * @param createTime 创建时间
 * @param updateTime 修改时间
 */
@RecordBuilder
@Schema(title = "国际化信息分页视图对象")
public record I18nDataPageResult(@Schema(title = "ID") Integer id, @Schema(title = "语言标签") String languageTag,
								 @Schema(title = "国际化标识") String code,
								 @Schema(title = "文本值，可以使用 { } 加角标，作为占位符") String message,
								 @Schema(title = "备注") String remarks,
								 @Schema(title = "创建时间") LocalDateTime createTime,
								 @Schema(title = "修改时间") LocalDateTime updateTime) implements MarkerResult {

	private static final long serialVersionUID = 1L;

}
