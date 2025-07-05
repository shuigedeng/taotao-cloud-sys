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
import com.taotao.boot.common.enums.UserObjectEnum;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import com.taotao.boot.webagg.entity.SuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 用户-角色第三方表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:04:45
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(
        name = UserRelationPO.TABLE_NAME,
        uniqueConstraints = {
            @UniqueConstraint(name = "uniq_goods_no", columnNames = "goods_no"),
        },
        indexes = {
            @Index(name = "idx_create_date", columnList = "create_date"),
        })
@TableName(UserRelationPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = UserRelationPO.TABLE_NAME)
public class UserRelationPO extends BaseSuperEntity<UserRelationPO, Long> {

    public static final String TABLE_NAME = "ttc_user_relation";

    /** 用户ID */
    @Column(name = "`user_id`", columnDefinition = "bigint not null comment '用户ID'")
    private Long userId;

    /**
     * 对象类型 org dept position role dataScope
     *
     * @see UserObjectEnum
     */
    @Column(name = "`object_type`", columnDefinition = "varchar(255) not null comment '对象类型'")
    private String objectType;

    /** 对象id orgId deptId positionId roleId dataScopeId */
    @Column(name = "`object_id`", columnDefinition = "bigint not null comment '对象id'")
    private Long objectId;

    /** 排序值 */
    @Column(name = "`sort_code`", columnDefinition = "int null comment '排序值'")
    private Integer sortCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        UserRelationPO userRole = (UserRelationPO) o;
        return getId() != null && Objects.equals(getId(), userRole.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
