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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.config;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/** 物流公司设置 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(name = LogisticsConfigPO.TABLE_NAME)
@TableName(LogisticsConfigPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = LogisticsConfigPO.TABLE_NAME)
public class LogisticsConfigPO extends BaseSuperEntity<LogisticsConfigPO, Long> {

    public static final String TABLE_NAME = "ttc_logistics_config";

    /** 物流公司名称 */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null COMMENT '物流公司名称'")
    private String name;

    /** 物流公司code */
    @Column(name = "`code`", columnDefinition = "varchar(255) not null COMMENT '物流公司code'")
    private String code;

    /** 物流公司联系人 */
    @Column(name = "`contact_name`", columnDefinition = "varchar(32) not null COMMENT '物流公司联系人'")
    private String contactName;

    /** 物流公司联系电话 */
    @Column(name = "`contact_mobile`", columnDefinition = "varchar(32) not null COMMENT '物流公司联系电话'")
    private String contactMobile;

    /** 支持电子面单 */
    @Column(name = "`stand_by`", columnDefinition = "varchar(255) not null COMMENT '支持电子面单'")
    private String standBy;

    /** 物流公司电子面单表单 */
    @Column(name = "`form_items`", columnDefinition = "varchar(255) not null COMMENT '物流公司电子面单表单'")
    private String formItems;

    /** 禁用状态 OPEN：开启，CLOSE：禁用 */
    @Column(
            name = "`disabled`",
            columnDefinition = "varchar(12) not null COMMENT '禁用状态 OPEN：开启，CLOSE：禁用'")
    private String disabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getStandBy() {
        return standBy;
    }

    public void setStandBy(String standBy) {
        this.standBy = standBy;
    }

    public String getFormItems() {
        return formItems;
    }

    public void setFormItems(String formItems) {
        this.formItems = formItems;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
