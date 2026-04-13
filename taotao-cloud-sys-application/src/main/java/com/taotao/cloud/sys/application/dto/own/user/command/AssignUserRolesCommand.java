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

package com.taotao.cloud.sys.application.dto.own.user.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.List;

/**
 * 为管理员分配角色命令
 *
 * @author shuigedeng
 * @since 2020/5/14 10:44
 */
@RecordBuilder
@Schema(description = "为管理员分配角色命令")
public record AssignUserRolesCommand(
	@Schema(description = "用户id") @NotNull(message = "用户id不能为空") Long userId,
	@Schema(description = "角色id列表") @NotEmpty(message = "角色id列表不能为空") List<Long> roleIds)
	implements Command {

	@Serial
	private static final long serialVersionUID = -4132785717179910025L;

}
