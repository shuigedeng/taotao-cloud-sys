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

package com.taotao.cloud.sys.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.boot.webagg.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.sys.application.dto.dept.clientobject.DeptTreeVO;
import com.taotao.cloud.sys.application.service.DeptService;
import com.taotao.cloud.sys.infrastructure.persistent.mapper.DeptMapper;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.DeptPO;
import com.taotao.cloud.sys.infrastructure.persistent.repository.cls.DeptRepository;
import com.taotao.cloud.sys.infrastructure.persistent.repository.inf.IDeptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * DeptServiceImpl
 *
 * @author shuigedeng
 * @since 2020-10-16 15:54:05
 * @since 1.0
 */
@Service
public class DeptServiceImpl
	implements DeptService {

//	@Override
//	public List<DeptTreeVO> tree() {
//		LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
//		queryWrapper.orderByDesc(Dept::getSortNum);
//		List<Dept> list = list(queryWrapper);
//
//		return DeptConvert.INSTANCE.convertTree(list)
//			.stream()
//			.filter(Objects::nonNull)
//			.peek(e -> {
//				e.setKey(e.getId());
//				e.setValue(e.getId());
//				e.setTitle(e.getName());
//			})
//			.toList();
//	}
}
