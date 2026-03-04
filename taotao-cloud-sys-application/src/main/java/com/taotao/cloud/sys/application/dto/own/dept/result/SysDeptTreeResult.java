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

package com.taotao.cloud.sys.application.dto.own.dept.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author shuigedeng
 * @since 2020/5/14 10:44
 */
@RecordBuilder
@Schema(description = "部门树VO")
public record SysDeptTreeResult(@Schema(description = "对应SysDepart中的id字段,前端数据树中的key") Integer key,
								@Schema(description = "对应SysDepart中的id字段;前端数据树中的value") String value,
								@Schema(description = "对应depart_name字段;前端数据树中的title") String title,
								@Schema(description = "部门主键ID") Long deptId,
								@Schema(description = "部门名称") String name,
								@Schema(description = "上级部门") Long parentId,
								@Schema(description = "排序") Integer sort, @Schema(description = "备注") String remark,
								@Schema(description = "创建时间") LocalDateTime createTime,
								@Schema(description = "修改时间") LocalDateTime updateTime,
								@Schema(description = "是否删除  -1：已删除  0：正常") Boolean delFlag,
								@Schema(description = "上级部门") String parentName,
								@Schema(description = "等级") Integer level,
								@Schema(description = "children") List<SysDeptTreeResult> children) implements
	MarkerResult {

	@Serial
	private static final long serialVersionUID = -4132785717179910025L;

}
