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
import java.util.List;

/**
 * 菜单
 *
 * @author shuigedeng
 * @since 2020/5/14 10:44
 */
@RecordBuilder
@Schema(description = "菜单VO")
public record MenuResult(@Schema(description = "菜单名称") String name, @Schema(description = "菜单路径") String path,
						 @Schema(description = "菜单redirect") String redirect,
						 @Schema(description = "菜单组件名称") String component,
						 @Schema(description = "菜单alwaysShow") Boolean alwaysShow,
						 @Schema(description = "菜单meta") MenuMetaResult meta,
						 @Schema(description = "菜单children") List<MenuResult> children) implements MarkerResult {

	@Serial
	private static final long serialVersionUID = -5853343562172855421L;

}
