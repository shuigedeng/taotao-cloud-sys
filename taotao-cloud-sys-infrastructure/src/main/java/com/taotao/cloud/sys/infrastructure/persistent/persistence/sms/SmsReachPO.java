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

/** 短信任务
 * @author 57222*/
@Entity
@Table(name = SmsReachPO.TABLE_NAME)
@TableName(SmsReachPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = SmsReachPO.TABLE_NAME)
public class SmsReachPO extends BaseSuperEntity<SmsReachPO, Long> {

    public static final String TABLE_NAME = "ttc_sms_reach";

    @Column(name = "sign_name", columnDefinition = "varchar(2000) not null comment '签名名称'")
    private String signName;

    @Column(name = "sms_name", columnDefinition = "varchar(2000) not null comment '模板名称'")
    private String smsName;

    @Column(name = "message_code", columnDefinition = "varchar(2000) not null comment '消息CODE'")
    private String messageCode;

    @Column(name = "context", columnDefinition = "varchar(2000) not null comment '消息内容'")
    private String context;

    @Column(name = "sms_range", columnDefinition = "varchar(2000) not null comment '接收人 1:全部会员，2：选择会员 '")
    private String smsRange;

    @Column(name = "num", columnDefinition = "varchar(2000) not null comment '预计发送条数'")
    private String num;

}
