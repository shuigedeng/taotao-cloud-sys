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

package com.taotao.cloud.sys.domain.service.impl;

import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.domain.aggregate.RoleAgg;
import com.taotao.cloud.sys.domain.aggregate.UserAgg;
import com.taotao.cloud.sys.domain.repository.RoleDomainRepository;
import com.taotao.cloud.sys.domain.repository.UserDomainRepository;
import com.taotao.cloud.sys.domain.service.UserDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DeptDomainServiceImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Service
@AllArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private UserDomainRepository userDomainRepository;

	@Override
	public void assignRoles( UserAgg userAgg, List<RoleAgg> assignableRoles ) {
		// 只处理用户聚合内部的业务规则
		// 例如：检查用户状态、角色冲突、数量限制等

		if (userAgg.isDeleted()) {
			throw new BusinessException("已删除的用户不能分配角色");
		}

		if (assignableRoles.isEmpty()) {
			throw new BusinessException("至少分配一个角色");
		}

		// 将角色ID列表传递给用户聚合
		List<BizId> roleIds = assignableRoles.stream()
			.map(RoleAgg::id)
			.collect(Collectors.toList());

		userAgg.assignRoles(roleIds);  // 用户聚合内部处理角色分配逻辑
	}
}
