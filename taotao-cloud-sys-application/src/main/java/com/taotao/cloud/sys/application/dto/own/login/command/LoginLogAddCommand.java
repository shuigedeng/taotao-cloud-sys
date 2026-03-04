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

package com.taotao.cloud.sys.application.dto.own.login.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 登录日志
 *
 * @author shuigedeng
 * @since 2021/8/12
 */
@RecordBuilder
@Schema(description = "登录日志")
public record LoginLogAddCommand(@Schema(description = "用户账号id") Long userId,
								 @Schema(description = "用户名称") String account,
								 @Schema(description = "登录成功状态") Boolean login,
								 @Schema(description = "登录终端") String client,
								 @Schema(description = "登录方式") String loginType,
								 @Schema(description = "登录IP地址") String ip,
								 @Schema(description = "登录地点") String loginLocation,
								 @Schema(description = "浏览器类型") String browser,
								 @Schema(description = "操作系统") String os,
								 @Schema(description = "提示消息") String msg,
								 @Schema(description = "访问时间") LocalDateTime loginTime) implements Command {

}
