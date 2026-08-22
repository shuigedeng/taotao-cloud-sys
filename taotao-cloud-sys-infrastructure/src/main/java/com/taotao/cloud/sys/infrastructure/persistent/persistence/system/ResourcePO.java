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
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 菜单表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:08:15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

@Entity
@Table(name = ResourcePO.TABLE_NAME)
@TableName(ResourcePO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = ResourcePO.TABLE_NAME)
public class ResourcePO extends BasePO<ResourcePO> {

    public static final String TABLE_NAME = "ttc_resource";

    /** 菜单标题 */
    @Column(name = "`name`", columnDefinition = "varchar(32) not null comment '菜单名称'")
    private String name;

    /** 权限标识 */
    @Column(name = "`permission`", columnDefinition = "varchar(255) comment '权限标识'")
    private String permission;

    /** 前端path / 即跳转路由 */
    @Column(name = "`path`", columnDefinition = "varchar(255) comment '前端path / 即跳转路由'")
    private String path;

    /** 菜单组件 */
    @Column(name = "`component`", columnDefinition = "varchar(255) comment '菜单组件'")
    private String component;

    /** 父菜单ID */
    @Column(name = "`parent_id`", columnDefinition = "bigint not null default 0 comment '父菜单ID'")
    private Long parentId;

    /** 图标 */
    @Column(name = "`icon`", columnDefinition = "varchar(255) comment '图标'")
    private String icon;

    /** 排序值 */
    @Column(name = "`sort_num`", columnDefinition = "int(11) not null default 0 comment '排序值'")
    private Integer sortNum;

    /** 是否缓存页面: 0:否 1:是 (默认值0) */
    @Column(
            name = "`keep_alive`",
            columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 comment '是否缓存页面: 0:否 1:是 (默认值0)'")
    private Boolean keepAlive;

    /**
     * 菜单类型 1:目录 2:菜单 3：资源(分页查询操作、操作按钮、删除按钮、查询按钮、等等) 资源 (包括分页、各种按钮、删除 等等 对应的是请求路径如：/api/menu/find)
     *
     */
    @Column(name = "`type`", columnDefinition = "int not null comment '菜单类型 (1:目录 2:菜单 3：资源)'")
    private Integer type;

    /** url请求Id (type=3 时, 此id有值) */
    @Column(
            name = "`request_path_id`",
            columnDefinition = "bigint null comment 'url请求Id (type=3时, 此id有值)'")
    private Long requestPathId;

    /** 是否隐藏路由菜单: 0否,1是（默认值0） */
    @Column(
            name = "`hidden`",
            columnDefinition = "boolean DEFAULT false comment '是否隐藏路由菜单: 0否,1是（默认值0)'")
    private Boolean hidden;

    /** 重定向 */
    @Column(name = "`redirect`", columnDefinition = "varchar(255) comment '重定向'")
    private String redirect;

    /** 是否为外链 */
    @Column(name = "`target`", columnDefinition = "varchar(32) comment '是否为外链'")
    private String target;

    /** 租户id */
    @Column(name = "`tenant_id`", columnDefinition = "varchar(32) COMMENT '租户id'")
    private String tenantId;

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
     * @since 2022.03
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取权限
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置权限
     *
     * @param permission 权限
     * @since 2022.03
     */
    public void setPermission(String permission) {
        this.permission = permission;
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
     * @since 2022.03
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取Component
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getComponent() {
        return component;
    }

    /**
     * 设置Component
     *
     * @param component component
     * @since 2022.03
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 获取父级ID
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级ID
     *
     * @param parentId 父级ID
     * @since 2022.03
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取图标
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     * @since 2022.03
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取排序号
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * 设置排序号
     *
     * @param sortNum 排序号
     * @since 2022.03
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * 获取KeepAlive
     *
     * @return 是否成功
     * @since 2022.03
     */
    public Boolean getKeepAlive() {
        return keepAlive;
    }

    /**
     * 设置KeepAlive
     *
     * @param keepAlive keepAlive
     * @since 2022.03
     */
    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
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
     * @since 2022.03
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取请求路径ID
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getRequestPathId() {
        return requestPathId;
    }

    /**
     * 设置请求路径ID
     *
     * @param requestPathId 请求路径ID
     * @since 2022.03
     */
    public void setRequestPathId(Long requestPathId) {
        this.requestPathId = requestPathId;
    }

    /**
     * 获取Hidden
     *
     * @return 是否成功
     * @since 2022.03
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * 设置Hidden
     *
     * @param hidden hidden
     * @since 2022.03
     */
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * 获取Redirect
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * 设置Redirect
     *
     * @param redirect redirect
     * @since 2022.03
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    /**
     * 获取目标
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置目标
     *
     * @param target 目标
     * @since 2022.03
     */
    public void setTarget(String target) {
        this.target = target;
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
        ResourcePO resource = (ResourcePO) o;
        return getId() != null && Objects.equals(getId(), resource.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
