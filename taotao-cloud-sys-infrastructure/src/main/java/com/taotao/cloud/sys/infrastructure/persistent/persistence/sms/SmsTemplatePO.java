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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.sms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
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

import java.time.LocalDateTime;
import java.util.Objects;

/** 短信模板 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = SmsTemplatePO.TABLE_NAME)
@TableName(SmsTemplatePO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = SmsTemplatePO.TABLE_NAME)
public class SmsTemplatePO extends BaseSuperEntity<SmsTemplatePO, Long> {

    public static final String TABLE_NAME = "tt_tt_sms_template";

    @Column(name = "template_name", columnDefinition = "varchar(2000) not null comment '模板名称'")
    private String templateName;

    @Column(name = "template_type", columnDefinition = "varchar(2000) not null comment '短信类型'")
    private Integer templateType;

    @Column(name = "remark", columnDefinition = "varchar(2000) not null comment '短信模板申请说明'")
    private String remark;

    @Column(name = "template_content", columnDefinition = "varchar(2000) not null comment '模板内容'")
    private String templateContent;

    @Column(
            name = "template_status",
            columnDefinition = "int not null default 0 comment '模板审核状态  0：审核中。"
                    + "     * 1：审核通过。"
                    + "     * 2：审核失败，请在返回参数Reason中查看审核失败原因。'")
    private Integer templateStatus;

    @Column(name = "template_code", columnDefinition = "varchar(2000) not null comment '短信模板CODE'")
    private String templateCode;

    @Column(name = "reason", columnDefinition = "varchar(2000) not null comment '审核备注'")
    private String reason;

    @Builder
    public SmsTemplatePO(
            Long id,
            LocalDateTime createTime,
            Long createBy,
            LocalDateTime updateTime,
            Long updateBy,
            Integer version,
            Boolean delFlag,
            String templateName,
            Integer templateType,
            String remark,
            String templateContent,
            Integer templateStatus,
            String templateCode,
            String reason) {
        super(id, createTime, createBy, updateTime, updateBy, version, delFlag);
        this.templateName = templateName;
        this.templateType = templateType;
        this.remark = remark;
        this.templateContent = templateContent;
        this.templateStatus = templateStatus;
        this.templateCode = templateCode;
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        SmsTemplatePO that = (SmsTemplatePO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
