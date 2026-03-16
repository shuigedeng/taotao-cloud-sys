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

package com.taotao.cloud.sys.domain.entity;

import com.taotao.boot.ddd.model.domain.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * DeptEntity
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Setter
@Getter
@ToString

@Schema(name = "Dept", description = "部门")
public class DictItem implements Entity {
	private String dictCode;

	/** 字典项文本 */
	private String itemText;

	/** 字典项值 */
	private String itemValue;

	/** 描述 */
	private String description;

	/** 状态 0不启用 1启用 */
	private Integer status;

	/** 排序值 */
	private Integer sortNum;
}
