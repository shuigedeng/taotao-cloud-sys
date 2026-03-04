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

package com.taotao.cloud.sys.application.dto.own.resource.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;

/**
 * 菜单DTO
 *
 * @author shuigedeng
 * @since 2020/6/15 11:00
 */
@RecordBuilder
@Schema(description = "菜单DTO")
public record ResourceResult(@Schema(description = "菜单DTO") Integer menuId,
							 @Schema(description = "菜单名称") String name,
							 @Schema(description = "菜单权限") String perms,
							 @Schema(description = "菜单路径") String path,
							 @Schema(description = "菜单isFrame") Boolean isFrame,
							 @Schema(description = "父菜单id") Integer parentId,
							 @Schema(description = "菜单组件名称") String component,
							 @Schema(description = "菜单icon") String icon,
							 @Schema(description = "菜单排序") Integer sort,
							 @Schema(description = "菜单类型") Integer type,
							 @Schema(description = "菜单删除标识") String delFlag,
							 @Schema(description = "菜单keepAlive") Boolean keepAlive,
							 @Schema(description = "菜单是否隐藏") Boolean hidden,
							 @Schema(description = "菜单是否一直展示") Boolean alwaysShow,
							 @Schema(description = "菜单redirect") String redirect) implements MarkerResult {

	@Serial
	private static final long serialVersionUID = -4132785717179910025L;

}
