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
 * 权限资源表(url请求)
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:52:30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

@Entity
@Table(
	name = RequestPathPO.TABLE_NAME,
	uniqueConstraints = {
		@UniqueConstraint(name = "uniq_code", columnNames = "code"),
	},
	indexes = {
		@Index(name = "idx_create_time", columnList = "create_time"),
	})
@TableName(RequestPathPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = RequestPathPO.TABLE_NAME)
public class RequestPathPO extends BasePO<RequestPathPO> {

    public static final String TABLE_NAME = "ttc_request_path";

    /** 权限标识 (controller类#方法#请求方式) ManagerUserController#page#post */
    @Column(name = "`code`", columnDefinition = "varchar(255) not null comment '权限标识'")
    private String code;

    /** 权限名称 (获取用户分页详情) */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null comment '权限名称'")
    private String name;

    /** 分组名称 (用户管理) */
    @Column(name = "`group_name`", columnDefinition = "varchar(255) not null comment '分组名称'")
    private String groupName;

    /** 请求类型 (post) */
    @Column(name = "`request_type`", columnDefinition = "varchar(255) not null comment '请求类型'")
    private String requestType;

    /** 请求路径 (/api/sys/admin/user) */
    @Column(name = "`path`", columnDefinition = "varchar(1024) not null comment '请求路径'")
    private String path;

    /** 启用鉴权 */
    @Column(name = "`enable`", columnDefinition = "boolean not null default true comment '启用鉴权'")
    private boolean enable;

    /** 是否通过系统生成的权限 */
    @Column(
            name = "`generate`",
            columnDefinition = "boolean not null default false comment '是否通过系统生成的权限'")
    private boolean generate;

    /** 描述 */
    @Column(name = "`remark`", columnDefinition = "varchar(1024) null comment '描述'")
    private String remark;

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
     * 获取GroupName
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置GroupName
     *
     * @param groupName groupName
     * @return 无返回值
     * @since 2022.03
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取请求类型
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * 设置请求类型
     *
     * @param requestType 请求类型
     * @return 无返回值
     * @since 2022.03
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * 获取路径
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     * @return 无返回值
     * @since 2022.03
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 判断Enable
     *
     * @return 是否成功
     * @since 2022.03
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * 设置Enable
     *
     * @param enable enable
     * @return 无返回值
     * @since 2022.03
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 判断Generate
     *
     * @return 是否成功
     * @since 2022.03
     */
    public boolean isGenerate() {
        return generate;
    }

    /**
     * 设置Generate
     *
     * @param generate generate
     * @return 无返回值
     * @since 2022.03
     */
    public void setGenerate(boolean generate) {
        this.generate = generate;
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
        RequestPathPO position = (RequestPathPO) o;
        return getId() != null && Objects.equals(getId(), position.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
