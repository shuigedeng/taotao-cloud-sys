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

package com.taotao.cloud.sys.application.dto.own.resource.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.io.Serial;

import org.hibernate.validator.constraints.Length;

/**
 * 菜单更新对象
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 15:26:19
 */
@RecordBuilder
@Schema(description = "菜单更新对象")
public record ResourceUpdateCommand(
	@Schema(description = "菜单名称") @NotBlank(message = "菜单名称不能超过为空") @Length(max = 20, message = "菜单名称不能超过20个字符") String name,
	@Schema(description = "菜单类型 1：目录 2：菜单 3：按钮") @NotBlank(message = "菜单类型不能超过为空") Byte type,
	@Schema(description = "权限标识") String perms, @Schema(description = "前端path / 即跳转路由") String path,
	@Schema(description = "菜单组件") String component, @Schema(description = "父菜单ID") Long parentId,
	@Schema(description = "图标") String icon,
	@Schema(description = "是否缓存页面: 0:否 1:是 (默认值0)") Boolean keepAlive,
	@Schema(description = "是否隐藏路由菜单: 0否;1是（默认值0）") Boolean hidden,
	@Schema(description = "聚合路由 0否;1是（默认值0）") Boolean alwaysShow,
	@Schema(description = "重定向") String redirect,
	@Schema(description = "是否为外链 0否;1是（默认值0）") Boolean isFrame,
	@Schema(description = "排序值") Integer sortNum) implements Command {

	@Serial
	private static final long serialVersionUID = -4132785717179910025L;

}
