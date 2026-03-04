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

package com.taotao.cloud.sys.application.dto.own.i18n.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 国际化信息传输对象
 *
 * @param languageTag 语言标签
 * @param code 唯一标识 = 业务:关键词
 * @param message 文本值，可以使用 { } 加角标，作为占位符
 * @param remarks 备注
 */
@RecordBuilder
@Schema(title = "国际化信息传输对象")
public record I18nDataCommand(@Schema(title = "语言标签") String languageTag,
							  @Schema(title = "唯一标识 = 业务:关键词") String code,
							  @Schema(title = "文本值，可以使用 { } 加角标，作为占位符") String message,
							  @Schema(title = "备注") String remarks) implements Command {

	private static final long serialVersionUID = 1L;

}
