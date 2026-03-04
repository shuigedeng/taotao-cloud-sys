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

package com.taotao.cloud.sys.application.dto.own.dept.command;

import com.taotao.boot.common.model.ddd.types.Command;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;

/**
 * 部门添加对象
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2022-03-23 08:50:16
 */
@RecordBuilder
@Schema(description = "部门添加对象")
public record DeptSaveCommand(
	@Schema(description = "部门id") Integer deptId,
	@Schema(description = "部门名称") String name, @Schema(description = "上级部门id") Integer parentId,
	@Schema(description = "排序") Integer sort, @Schema(description = "备注") String remark) implements Command {

	@Serial
	private static final long serialVersionUID = -4132785717179910025L;

}
