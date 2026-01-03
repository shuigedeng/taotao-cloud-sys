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

import com.taotao.cloud.sys.domain.entity.DeptEntity;
import com.taotao.cloud.sys.domain.repository.DeptDomainRepository;
import com.taotao.cloud.sys.infrastructure.persistent.mapper.DeptMapper;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.DeptPO;
import com.taotao.cloud.sys.infrastructure.persistent.repository.DeptRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * DeptDomainRepositoryImpl
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
@Service
@RequiredArgsConstructor
public class DeptDomainRepositoryImpl implements DeptDomainRepository {
	private final DeptMapper deptMapper;
	private final DeptRepository deptRepository;

    @Override
    public void create(DeptEntity dept) {
		deptMapper.insert((DeptPO) null);
	}

    @Override
    public void modify(DeptEntity dept) {}

    @Override
    public void remove(Long[] ids) {}

	@Override
	public void findById( Long id ) {
		Optional<DeptPO> byId = deptRepository.findById(id);
		System.out.println(byId.orElse(null));
		DeptPO deptPO = deptMapper.selectById(id);
		System.out.println(deptPO);
	}
}
