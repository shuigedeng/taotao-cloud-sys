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

import cn.hutool.core.collection.CollectionUtil;
import com.taotao.boot.common.enums.UserObjectEnum;
import com.taotao.boot.common.support.asserts.BusinessAssert;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.domain.aggregate.UserAgg;
import com.taotao.cloud.sys.domain.repository.UserDomainRepository;
import com.taotao.cloud.sys.infrastructure.assembler.UserInfraAssembler;
import com.taotao.cloud.sys.infrastructure.persistent.mapper.UserMapper;
import com.taotao.cloud.sys.infrastructure.persistent.mapper.UserRelationMapper;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.UserPO;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.UserRelationPO;
import com.taotao.cloud.sys.infrastructure.persistent.repository.UserRelationRepository;
import com.taotao.cloud.sys.infrastructure.persistent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.executor.BatchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DictDomainRepositoryImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Service
@RequiredArgsConstructor
public class UserDomainRepositoryImpl implements UserDomainRepository {

	private final UserInfraAssembler userAssembler;
	private final UserMapper userMapper;
	private final UserRelationMapper userRelationMapper;
	private final UserRepository userRepository;
	private final UserRelationRepository userRelationRepository;

	@Override
	public UserAgg findUsingIdCol( Long id, boolean withLock ) {
		UserPO userPo = userMapper.selectByIdForUpdate(id, withLock);

		BusinessAssert.notNull(userPo, "id:{}用户不存在", id);

		UserAgg userAgg = userAssembler.toAgg(userPo);

		fillRoleIds(userAgg);

		return userAgg;
	}

	@Override
	public int save( UserAgg userAgg, boolean skipNull ) {

		UserPO userPo = userAssembler.toPo(userAgg);
		int num = save(userPo, userMapper, skipNull);

		BusinessAssert.isTrue(num > 0, "保存用户失败");
		BusinessAssert.notNull(userPo.getId(), "用户ID不能为空");

		userAgg.ifRoleIdModified(this::replaceUserRoles);

		return num;
	}

	private void fillRoleIds( UserAgg userAgg ) {
		List<UserRelationPO> userRelationPo = userRelationMapper.selectByUserId(userAgg.id().id(), UserObjectEnum.ROLE);

		List<BizId> roleIds = userRelationPo.stream()
			.map(UserRelationPO::getObjectId)
			.map(BizId::fromValue).toList();

		userAgg.setRoleIds(roleIds);
	}

	/**
	 * 替换用户的所有角色关联（先删后增）
	 */
	private void replaceUserRoles( UserAgg userAgg ) {

		Long userId = userAgg.id().id();
		List<BizId> roleIds = CollectionUtil.emptyIfNull(userAgg.getRoleIds());

		int deleteCount = userRelationMapper.deleteByUserId(userAgg.id().id(), UserObjectEnum.ROLE);
		LogUtils.debug("删除用户{}的旧角色关系{}条", userId, deleteCount);

		if (roleIds.isEmpty()) {
			return;
		}

		List<UserRelationPO> userRelationPos = roleIds.stream()
			.map(roleId -> new UserRelationPO(userAgg.id().id(),roleId.id(),UserObjectEnum.ROLE.name()))
			.collect(Collectors.toList());

		int insertCount = saves(userRelationPos, userRelationMapper, false);

		LogUtils.debug("为用户{}添加角色关系{}条", userId, insertCount);

		BusinessAssert.isTrue(insertCount == roleIds.size(),
			"用户角色关系保存失败，预期插入{}条，实际插入{}条",
			userRelationPos.size(), insertCount);

		LogUtils.info("用户{}角色关系同步完成", userId);
	}
}
