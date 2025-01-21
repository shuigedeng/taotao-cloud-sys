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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.SuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * 角色-菜单第三方表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:07:31
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = RoleResourcePO.TABLE_NAME)
@TableName(RoleResourcePO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = RoleResourcePO.TABLE_NAME)
public class RoleResourcePO extends SuperEntity<RoleResourcePO, Long> {

    public static final String TABLE_NAME = "tt_role_resource";

    /** 角色ID */
    @Column(name = "role_id", columnDefinition = "bigint not null comment '角色ID'")
    private Long roleId;

    /** 菜单ID */
    @Column(name = "resource_id", columnDefinition = "bigint not null comment '菜单ID'")
    private Long resourceId;

    @Accessors(fluent = true)
    public RoleResourcePO(Long id, Long roleId, Long resourceId) {
        super(id);
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        RoleResourcePO roleResource = (RoleResourcePO) o;
        return getId() != null && Objects.equals(getId(), roleResource.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
