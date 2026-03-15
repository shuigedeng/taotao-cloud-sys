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
    private RoleDomainRepository roleDomainRepository;

	@Override
	public void assignRoles( UserAgg userAgg, List<Long> roleIds ) {
		if (roleIds == null || roleIds.isEmpty()) {
			throw new BusinessException("角色列表不能为空");
		}

		Set<BizId> requestedRoleIds = roleIds.stream()
			.map(BizId::fromValue)
			.collect(Collectors.toSet());

		List<RoleAgg> assignableRoles = roleDomainRepository.findAssignableRoles(requestedRoleIds);

		// 校验是否所有请求的角色都存在且可分配
		Set<BizId> foundIds = assignableRoles.stream()
			.map(RoleAgg::id)
			.collect(Collectors.toSet());

		Set<BizId> missing = new HashSet<>(requestedRoleIds);
		missing.removeAll(foundIds);
		if (!missing.isEmpty()) {
			throw new BusinessException("角色不存在或不可分配: " + missing);
		}

		for (RoleAgg role : assignableRoles) {
			if (!role.isEnabled()) {
				throw new IllegalStateException("Repository 返回了非启用角色: " + role.getId());
			}
		}

		userAgg.assignRoles(new ArrayList<>(requestedRoleIds));
	}
}
