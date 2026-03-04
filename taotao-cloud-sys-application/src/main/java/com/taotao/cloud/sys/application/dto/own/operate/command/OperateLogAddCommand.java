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

package com.taotao.cloud.sys.application.dto.own.operate.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author shuigedeng
 * @since 2021/8/12
 */
@RecordBuilder
@Schema(description = "操作日志")
public record OperateLogAddCommand(@Schema(description = "操作模块") String title,
								   @Schema(description = "操作人员id") Long operateId,
								   @Schema(description = "操作人员账号") String username,
								   @Schema(description = "业务类型") String businessType,
								   @Schema(description = "请求方法") String method,
								   @Schema(description = "请求方式") String requestMethod,
								   @Schema(description = "请求url") String operateUrl,
								   @Schema(description = "操作ip") String operateIp,
								   @Schema(description = "操作地点") String operateLocation,
								   @Schema(description = "请求参数") String operateParam,
								   @Schema(description = "返回参数") String operateReturn,
								   @Schema(description = "操作状态（0正常 1异常）") Boolean success,
								   @Schema(description = "错误消息") String errorMsg,
								   @Schema(description = "操作时间") LocalDateTime operateTime) implements Command {

}
