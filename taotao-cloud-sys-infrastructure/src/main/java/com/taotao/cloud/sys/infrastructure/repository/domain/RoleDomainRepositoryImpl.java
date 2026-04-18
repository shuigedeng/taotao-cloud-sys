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

package com.taotao.cloud.sys.infrastructure.repository.domain;

import cn.hutool.core.collection.CollUtil;
import com.taotao.boot.common.support.asserts.BusinessAssert;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.domain.aggregate.RoleAgg;
import com.taotao.cloud.sys.domain.repository.RoleDomainRepository;
import com.taotao.cloud.sys.infrastructure.assembler.RoleInfraAssembler;
import com.taotao.cloud.sys.infrastructure.persistent.mapper.RoleMapper;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.RolePO;
import com.taotao.cloud.sys.infrastructure.persistent.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * DictDomainRepositoryImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Service
@RequiredArgsConstructor
public class RoleDomainRepositoryImpl implements RoleDomainRepository {

	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;
	private final RoleInfraAssembler roleAssembler;

	@Override
	public List<RoleAgg> findAssignableRoles( Set<BizId> requestedRoleIds ) {

		BusinessAssert.isTrue(CollUtil.isNotEmpty(requestedRoleIds), "角色列表不能为空");

		List<Long> ids = requestedRoleIds.stream().map(BizId::id).toList();

		List<RolePO> rolePos = roleMapper.selectByIds(ids);

		BusinessAssert.isTrue(CollUtil.isNotEmpty(rolePos), "ids:{}角色不存在", ids);

		return roleAssembler.toAggs(rolePos);
	}
}
