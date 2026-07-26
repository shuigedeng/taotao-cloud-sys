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

package com.taotao.cloud.sys.domain.aggregate;

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.ddd.model.domain.AggregateRoot;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.domain.entity.DictItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典聚合根
 * <p>封装字典相关的业务规则和一致性边界，包含字典项集合</p>
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@ToString

@Schema(name = "Dict", description = "字典聚合根")
public class DictAgg extends AggregateRoot<BizId> {

	@Schema(name = "name", description = "字典名称")
	private String name;

	private BizId pid;

	@Schema(name = "description", description = "字典描述")
	private String description;

	@Schema(name = "sort", description = "排序")
	private Integer sort;

	private List<DictItem> dictItems = new ArrayList<>();











	/**
	 * 校验名称
	 *
	 * @param count 数量
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void checkName(long count) {
		if (count > 0) {
			throw new BusinessException("部门名称已存在，请重新填写");
		}
	}











	/**
	 * 校验
	 *
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void checkIdAndPid() {
		if (id.equals(pid)) {
			throw new BusinessException("上级部门不能为当前部门");
		}
	}
}
