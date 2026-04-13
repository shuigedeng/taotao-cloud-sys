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

package com.taotao.cloud.sys.application.dto.own.dataversion.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 *
 * @author shuigedeng
 * @since 2022/1/10
 */
@RecordBuilder
@Schema(title = "数据版本日志")
public record CreateDataVersionLogCommand(@Schema(description = "主键") Long id,
                                          @Schema(description = "表名称") String tableName,
                                          @Schema(description = "数据名称") String dataName,
                                          @Schema(description = "数据主键") String dataId,
                                          @Schema(description = "数据内容") String dataContent,
                                          @Schema(description = "本次变动的数据内容") Object changeContent,
                                          @Schema(description = "数据版本") Integer version,
                                          @Schema(description = "创建者ID") Long creator,
                                          @Schema(description = "创建时间") LocalDateTime createTime)implements Command {

}
