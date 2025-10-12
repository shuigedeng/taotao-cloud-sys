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

package com.taotao.cloud.sys.application.service.commad.impl;

import com.taotao.cloud.sys.application.service.commad.RoleCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * RoleServiceImpl
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 20:46:26
 */
@Service
@AllArgsConstructor
public class RoleCommandServiceImpl implements RoleCommandService {

    //    private static final QRole SYS_ROLE = QRole.role;
    //
    //    private final IRoleResourceService roleResourceService;
    //
    //    @Override
    //    public Boolean existRoleByCode(String code) {
    //        BooleanExpression predicate = SYS_ROLE.code.eq(code);
    //        return cr().exists(predicate);
    //    }
    //
    //    @Override
    //    @Transactional(rollbackFor = Exception.class)
    //    public Boolean saveRoleMenus(Long roleId, Set<Long> menuIds) {
    //        return roleResourceService.saveRoleMenu(roleId, menuIds);
    //    }
    //
    //    @Override
    //    public List<RoleBO> findAllRoles() {
    //        List<Role> roles = ir().findAll();
    //        return RoleConvert.INSTANCE.convertListBO(roles);
    //    }
    //
    //    @Override
    //    public List<RoleBO> findRoleByUserIds(Set<Long> userIds) {
    //        // List<Role> roles = cr().findRoleByUserIds(userIds);
    //        List<Role> roles = new ArrayList<>();
    //        return RoleConvert.INSTANCE.convertListBO(roles);
    //    }
    //
    //    @Override
    //    public List<RoleBO> findRoleByCodes(Set<String> codes) {
    //        // List<Role> roles = cr().findRoleByCodes(codes);
    //        List<Role> roles = new ArrayList<>();
    //        return RoleConvert.INSTANCE.convertListBO(roles);
    //    }
}
