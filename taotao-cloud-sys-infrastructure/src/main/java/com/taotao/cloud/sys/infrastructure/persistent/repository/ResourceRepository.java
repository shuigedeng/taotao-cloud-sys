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

package com.taotao.cloud.sys.infrastructure.persistent.repository;

import com.taotao.boot.data.jpa.base.repository.JpaExtendRepository;
import com.taotao.boot.data.jpa.base.repository.JpaSuperRepository;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.DeptPO;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.system.ResourcePO;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * CompanyMapper
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2021/10/13 22:50
 */
public interface ResourceRepository extends JpaSuperRepository<ResourcePO, Long>, JpaExtendRepository<ResourcePO, Long> {

    public List<ResourcePO> searchByComponent(String component);

    default List<Long> selectByComponent(String component) {
        List<ResourcePO> resources = searchByComponent(component);
        return Optional.ofNullable(resources).stream()
                .filter(Objects::nonNull)
                .map(e -> e.get(0).getId())
                .toList();
    }
}
