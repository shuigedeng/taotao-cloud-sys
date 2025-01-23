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
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

/** 短信签名 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(name = SmsSignPO.TABLE_NAME)
@TableName(SmsSignPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = SmsSignPO.TABLE_NAME)
public class SmsSignPO extends BaseSuperEntity<SmsSignPO, Long> {

    public static final String TABLE_NAME = "tt_tt_sms_sign";

    @Column(name = "`sign_name`", columnDefinition = "varchar(2000) not null comment '签名名称'")
    private String signName;

    @Column(name = "`sign_source`", columnDefinition = "varchar(2000) not null comment '签名来源'")
    private Integer signSource;

    @Column(name = "`remark`", columnDefinition = "varchar(2000) not null comment '短信签名申请说明'")
    private String remark;

    @Column(name = "`business_license`", columnDefinition = "varchar(2000) not null comment '营业执照'")
    private String businessLicense;

    @Column(name = "`license`", columnDefinition = "varchar(2000) not null comment '授权委托书'")
    private String license;

    @Column(
            name = "sign_status",
            columnDefinition = "int not null default 0 comment '签名审核状态  0：审核中。"
                    + "     * 1：审核通过。"
                    + "     * 2：审核失败，请在返回参数Reason中查看审核失败原因。'")
    private Integer signStatus;

    @Column(name = "`reason`", columnDefinition = "varchar(2000) not null comment '审核备注'")
    private String reason;

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public Integer getSignSource() {
		return signSource;
	}

	public void setSignSource(Integer signSource) {
		this.signSource = signSource;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Integer getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
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
        SmsSignPO smsSignPO = (SmsSignPO) o;
        return getId() != null && Objects.equals(getId(), smsSignPO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
