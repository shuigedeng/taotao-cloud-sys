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
import com.taotao.boot.data.mybatis.mybatisplus.interceptor.datascope.dataPermission.enums.DataScopeEnum;
import com.taotao.boot.webagg.entity.BasePO;
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

@Entity
@Table(
	name = DataScopePO.TABLE_NAME,
	uniqueConstraints = {
		@UniqueConstraint(name = "uniq_code", columnNames = "code"),
	},
	indexes = {
		@Index(name = "idx_create_time", columnList = "create_time"),
	})
@TableName(value = DataScopePO.TABLE_NAME, autoResultMap = true)
@org.springframework.data.relational.core.mapping.Table(name = DataScopePO.TABLE_NAME)
public class DataScopePO extends BasePO<DataScopePO> {

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
    //@Type(value = JsonType.class)
    //@TableField(typeHandler = JacksonListTypeHandler.class)
    @Column(name = "`org_ids`", columnDefinition = "varchar(4096) null comment '组织id列表'")
    private String orgIds;

    /** 部门id */
    //@Type(value = JsonType.class)
    //@TableField(typeHandler = JacksonListTypeHandler.class)
    @Column(name = "`dept_ids`", columnDefinition = "varchar(4096) null comment '部门id列表'")
    private String deptIds;

    /** 用户id */
    //@Type(value = JsonType.class)
    //@TableField(typeHandler = JacksonListTypeHandler.class)
    @Column(name = "`user_ids`", columnDefinition = "varchar(4096) null comment '用户id列表'")
    private String userIds;

    /** 租户id */
    @Column(name = "`tenant_id`", columnDefinition = "varchar(32) COMMENT '租户id'")
    private String tenantId;

    /**
     * 获取编码
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code 编码
     * @return 无返回值
     * @since 2022.03
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取名称
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     * @return 无返回值
     * @since 2022.03
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类型
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     * @return 无返回值
     * @since 2022.03
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取备注
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     * @return 无返回值
     * @since 2022.03
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取组织IDs
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getOrgIds() {
        return orgIds;
    }

    /**
     * 设置组织IDs
     *
     * @param orgIds 组织IDs
     * @return 无返回值
     * @since 2022.03
     */
    public void setOrgIds( String orgIds) {
        this.orgIds = orgIds;
    }

    /**
     * 获取部门IDs
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getDeptIds() {
        return deptIds;
    }

    /**
     * 设置部门IDs
     *
     * @param deptIds 部门IDs
     * @return 无返回值
     * @since 2022.03
     */
    public void setDeptIds( String deptIds) {
        this.deptIds = deptIds;
    }

    /**
     * 获取用户IDs
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getUserIds() {
        return userIds;
    }

    /**
     * 设置用户IDs
     *
     * @param userIds 用户IDs
     * @return 无返回值
     * @since 2022.03
     */
    public void setUserIds( String userIds) {
        this.userIds = userIds;
    }

    /**
     * 获取租户ID
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置租户ID
     *
     * @param tenantId 租户ID
     * @return 无返回值
     * @since 2022.03
     */
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
