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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.data.mybatis.mybatisplus.handler.typehandler.JacksonListTypeHandler;
import com.taotao.boot.data.mybatis.mybatisplus.interceptor.datascope.dataPermission.enums.DataScopeEnum;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

/**
 * 数据权限表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:10:22
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(
	name = DataScopePO.TABLE_NAME,
	uniqueConstraints = {
		@UniqueConstraint(name = "uniq_code", columnNames = "code"),
	},
	indexes = {
		@Index(name = "idx_create_date", columnList = "create_date"),
	})
@TableName(value = DataScopePO.TABLE_NAME, autoResultMap = true)
@org.springframework.data.relational.core.mapping.Table(name = DataScopePO.TABLE_NAME)
public class DataScopePO extends BaseSuperEntity<DataScopePO, Long> {

    public static final String TABLE_NAME = "ttc_data_scope";

    /** 编码 */
    @Column(name = "`code`", columnDefinition = "varchar(255) not null comment '编码'")
    private String code;

    /** 名称 */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null comment '名称'")
    private String name;

    /**
     * 数据范围类型
     *
     * @see DataScopeEnum
     */
    @Column(name = "`type`", columnDefinition = "int not null comment '数据范围类型'")
    private Integer type;

    /** 备注 */
    @Column(name = "`remark`", columnDefinition = "varchar(1024) null comment '备注'")
    private String remark;

    /** 组织id列表 */
    @Type(value = JsonType.class)
    @TableField(typeHandler = JacksonListTypeHandler.class)
    @Column(name = "`org_ids`", columnDefinition = "json null comment '组织id列表'")
    private List<Long> orgIds;

    /** 部门id */
    @Type(value = JsonType.class)
    @TableField(typeHandler = JacksonListTypeHandler.class)
    @Column(name = "`dept_ids`", columnDefinition = "json null comment '部门id列表'")
    private List<Long> deptIds;

    /** 用户id */
    @Type(value = JsonType.class)
    @TableField(typeHandler = JacksonListTypeHandler.class)
    @Column(name = "`user_ids`", columnDefinition = "json null comment '用户id列表'")
    private List<Long> userIds;

    /** 租户id */
    @Column(name = "`tenant_id`", columnDefinition = "varchar(32) COMMENT '租户id'")
    private String tenantId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Long> orgIds) {
        this.orgIds = orgIds;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        DataScopePO dept = (DataScopePO) o;
        return getId() != null && Objects.equals(getId(), dept.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
