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

package com.taotao.cloud.sys.infrastructure.persistent.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.boot.common.enums.UserObjectEnum;
import com.taotao.boot.data.mybatis.mybatisplus.base.mapper.MpSuperMapper;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.UserRelationPO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

/**
 * IUserRoleMapper
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2021/10/13 22:50
 */
public interface UserRelationMapper extends MpSuperMapper<UserRelationPO, Long> {

	default List<UserRelationPO> selectByUserId( Long userId, UserObjectEnum userObjectEnum ) {
		LambdaQueryWrapper<UserRelationPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(UserRelationPO::getUserId, userId);
		lambdaQueryWrapper.eq(UserRelationPO::getObjectType, userObjectEnum.name());
		return selectList(lambdaQueryWrapper);
	}

	default void deleteByUserId( Long userId, UserObjectEnum userObjectEnum ){
		LambdaQueryWrapper<UserRelationPO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(UserRelationPO::getUserId, userId);
		lambdaQueryWrapper.eq(UserRelationPO::getObjectType, userObjectEnum.name());
		delete(lambdaQueryWrapper);
	}

	;
}
