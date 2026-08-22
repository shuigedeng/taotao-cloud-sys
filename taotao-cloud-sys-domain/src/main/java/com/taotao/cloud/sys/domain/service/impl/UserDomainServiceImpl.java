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

import cn.hutool.core.collection.CollUtil;
import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.common.support.asserts.BusinessAssert;
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
	public void assignRoles( UserAgg userAgg, List<RoleAgg> assignableRoles, Set<BizId> requestedRoleIds ) {
		BusinessAssert.isTrue(userAgg != null, "用户不能为空");
		BusinessAssert.isTrue(CollUtil.isNotEmpty(assignableRoles), "至少分配一个角色");

		List<BizId> assignableRoleIds = assignableRoles.stream().map(RoleAgg::id).collect(Collectors.toList());

		validateRolesExist(requestedRoleIds, assignableRoleIds);

		userAgg.assignRoles(assignableRoleIds);
	}

	/**
	 * 验证角色是否存在
	 *
	 * @param requestedRoleIds 请求的角色ID列表
	 * @param assignableRoleIds 可分配角色ID列表
	 * @since 2022.03
	 */
	private void validateRolesExist( Set<BizId> requestedRoleIds, List<BizId> assignableRoleIds ) {
		Set<BizId> assignableRoleIdsCopy = new HashSet<>(assignableRoleIds);
		Set<BizId> requestedRoleIdsCopy = new HashSet<>(requestedRoleIds);

		requestedRoleIdsCopy.removeAll(assignableRoleIdsCopy);

		BusinessAssert.isTrue(requestedRoleIdsCopy.isEmpty(), "角色不存在或不可分配: {}", requestedRoleIdsCopy);
	}
}
