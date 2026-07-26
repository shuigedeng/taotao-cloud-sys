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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 岗位表
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
@Table(name = PositionPO.TABLE_NAME)
@TableName(PositionPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = PositionPO.TABLE_NAME)
public class PositionPO extends BasePO<PositionPO> {

    public static final String TABLE_NAME = "ttc_position";

    /** 岗位名称 */
    @Column(name = "`name`", columnDefinition = "varchar(32) not null comment '岗位名称'")
    private String name;

    /** 部门id */
    @Column(name = "`dept_id`", columnDefinition = "bigint not null comment '部门id'")
    private Long deptId;

    /** 公司id */
    @Column(name = "`org_id`", columnDefinition = "bigint not null comment '组织id'")
    private Long orgId;

    /** 备注 */
    @Column(name = "`remark`", columnDefinition = "varchar(255) comment '备注'")
    private String remark;

    /** 排序值 */
    @Column(name = "`sort_num`", columnDefinition = "int(11) not null default 0 comment '排序值'")
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
     * 获取部门ID
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     * @return 无返回值
     * @since 2022.03
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取组织ID
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 设置组织ID
     *
     * @param orgId 组织ID
     * @return 无返回值
     * @since 2022.03
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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
}
