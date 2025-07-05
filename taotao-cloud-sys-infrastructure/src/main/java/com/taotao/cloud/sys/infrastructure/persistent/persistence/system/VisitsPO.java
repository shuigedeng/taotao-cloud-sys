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
import com.taotao.boot.webagg.entity.BaseSuperEntity;
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
 * pv 与 ip 统计
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
        name = VisitsPO.TABLE_NAME,
        uniqueConstraints = {
            @UniqueConstraint(name = "uniq_goods_no", columnNames = "goods_no"),
        },
        indexes = {
            @Index(name = "idx_create_date", columnList = "create_date"),
        })
@TableName(VisitsPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = VisitsPO.TABLE_NAME)
public class VisitsPO extends BaseSuperEntity<VisitsPO, Long> {

    public static final String TABLE_NAME = "ttc_visits";

    @Column(name = "`date`", columnDefinition = "varchar(64) not null comment '日期'")
    private String date;

    @Column(name = "`pv_counts`", columnDefinition = "bigint not null default 0 comment 'pv'")
    private Long pvCounts;

    @Column(name = "`ip_counts`", columnDefinition = "bigint not null default 0 comment 'ip'")
    private Long ipCounts;

    @Column(name = "`week_day`", columnDefinition = "varchar(64) not null comment '天'")
    private String weekDay;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getPvCounts() {
        return pvCounts;
    }

    public void setPvCounts(Long pvCounts) {
        this.pvCounts = pvCounts;
    }

    public Long getIpCounts() {
        return ipCounts;
    }

    public void setIpCounts(Long ipCounts) {
        this.ipCounts = ipCounts;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        VisitsPO visitsPO = (VisitsPO) o;
        return getId() != null && Objects.equals(getId(), visitsPO.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
