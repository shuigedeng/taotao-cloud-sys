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
import cn.hutool.core.util.StrUtil;
import com.taotao.boot.common.enums.UserObjectEnum;
import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.ddd.model.val.BizId;
import com.taotao.cloud.sys.domain.aggregate.UserAgg;
import com.taotao.cloud.sys.domain.repository.UserDomainRepository;
import com.taotao.cloud.sys.infrastructure.assembler.UserAssembler;
import com.taotao.cloud.sys.infrastructure.persistent.mapper.UserMapper;
import com.taotao.cloud.sys.infrastructure.persistent.mapper.UserRelationMapper;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.UserPO;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.UserRelationPO;
import com.taotao.cloud.sys.infrastructure.persistent.repository.UserRelationRepository;
import com.taotao.cloud.sys.infrastructure.persistent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final UserAssembler userAssembler;
	private final UserRelationRepository userRelationRepository;
	private final UserRelationMapper userRelationMapper;

	@Override
	public UserAgg findById( BizId bizId ) {
		return findById(bizId, Boolean.FALSE);
	}

	@Override
	public UserAgg findById( BizId bizId, boolean withLock ) {
		UserPO userPo;
		if (withLock) {
			userPo = userMapper.selectByIdForUpdate(bizId.id());
		} else {
			userPo = userMapper.selectById(bizId.id());
		}

		if (userPo == null) {
			throw new BusinessException(StrUtil.format("id:{}用户不存在", bizId.id()));
		}
		UserAgg userAgg = userAssembler.toAgg(userPo);

		fillRoleIds(userAgg);

		return userAgg;
	}

	@Override
	public Long save( UserAgg userAgg ) {

		UserPO userPo = userAssembler.toPo(userAgg);
		userMapper.insertOrUpdate(userPo);

		syncUserRoleRelation(userAgg);

		return 1L;
	}

	private void fillRoleIds( UserAgg userAgg ) {
		List<UserRelationPO> userRelationPo = userRelationMapper.selectByUserId(userAgg.id().id(), UserObjectEnum.ROLE);
		List<BizId> roleIds = userRelationPo.stream().map(UserRelationPO::objectId).map(BizId::fromValue).toList();
		userAgg.setRoleIds(roleIds);
	}

	private void syncUserRoleRelation( UserAgg userAgg ) {
		userRelationMapper.deleteByUserId(userAgg.id().id(), UserObjectEnum.ROLE);
		List<UserRelationPO> userRelationPos = userAgg.getRoleIds().stream()
			.map(roleId -> {
				UserRelationPO po = new UserRelationPO();
				po.setUserId(userAgg.id().id());
				po.setObjectId(roleId.id());
				po.setObjectType(UserObjectEnum.ROLE.getValue());
				return po;
			})
			.collect(Collectors.toList());
		if (CollectionUtil.isNotEmpty(userRelationPos)) {
			userRelationMapper.batchInsert(userRelationPos);
		}
	}
}
