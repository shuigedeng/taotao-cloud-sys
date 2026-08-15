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
import com.taotao.boot.webagg.entity.BasePO;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

/**
 * 组织信息表
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
@Table(name = OrgPO.TABLE_NAME)
@TableName(value = OrgPO.TABLE_NAME, autoResultMap = true)
@org.springframework.data.relational.core.mapping.Table(name = OrgPO.TABLE_NAME)
public class OrgPO extends BasePO<OrgPO> {

    public static final String TABLE_NAME = "ttc_org";

    /** 部门名称 */
    @Column(name = "`name`", columnDefinition = "varchar(32) not null comment '公司名称'")
    private String name;

    /** 上级公司id */
    @Column(name = "`parent_id`", columnDefinition = "int not null default 0 comment '上级公司id'")
    private Long parentId;

    /** 备注 */
    @Column(name = "`remark`", columnDefinition = "varchar(255) comment '备注'")
    private String remark;

    /**
     * 备注
     * 设置了ResultMap为`mybatis-plus_Person`后就可以拿到正确的值. @ResultMap("mybatis-plus_Person") @Select("SELECT
     * * FROM person WHERE id=#{id}") Person selectOneById(int id);
     *
     * //pgsql json定义语句
     * @Column(name = "`jsonb_content`", columnDefinition = "jsonb")
     */
    //@Type(value = JsonType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @TableField(typeHandler = JacksonListTypeHandler.class)
    @Column(name = "`id_tree`", columnDefinition = "json null comment 'id树'")
    private List<String> idTree;

    /** 当前深度 */
    @Column(name = "`depth`", columnDefinition = "int not null default 0 comment '当前深度 已1开始'")
    private Integer depth;

    /** 排序值 */
    @Column(name = "`sort_num`", columnDefinition = "int not null default 0 comment '排序值'")
    private Integer sortNum;

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
     * @return 无返回值
     * @since 2022.03
     */
    public void setName(String name) {
        this.name = name;
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
     * @return 无返回值
     * @since 2022.03
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public List<String> getIdTree() {
        return idTree;
    }

    /**
     * 设置IdTree
     *
     * @param idTree idTree
     * @return 无返回值
     * @since 2022.03
     */
    public void setIdTree(List<String> idTree) {
        this.idTree = idTree;
    }

    /**
     * 获取部门h
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getDepth() {
        return depth;
    }

    /**
     * 设置部门h
     *
     * @param depth 部门h
     * @return 无返回值
     * @since 2022.03
     */
    public void setDepth(Integer depth) {
        this.depth = depth;
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
     * @return 无返回值
     * @since 2022.03
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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
        OrgPO dept = (OrgPO) o;
        return getId() != null && Objects.equals(getId(), dept.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
