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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.sensitive;

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
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * 敏感词实体
 *
 * @author shuigedeng
 */

@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = false)
@Entity
@Table(name = SensitiveWordPO.TABLE_NAME)
@TableName(SensitiveWordPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = SensitiveWordPO.TABLE_NAME)
public class SensitiveWordPO extends BaseSuperEntity<SensitiveWordPO, Long> {

    public static final String TABLE_NAME = "tt_sensitive_words";

    /** 敏感词名称 */
    @Column(name = "sensitive_word", columnDefinition = "varchar(255) not null default '' comment '敏感词名称'")
    private String sensitiveWord;

//    @Accessors(fluent = true)
//    public SensitiveWord(
//            Long id,
//            LocalDateTime createTime,
//            Long createBy,
//            LocalDateTime updateTime,
//            Long updateBy,
//            Integer version,
//            Boolean delFlag,
//            String sensitiveWord) {
//        super(id, createTime, createBy, updateTime, updateBy, version, delFlag);
//        this.sensitiveWord = sensitiveWord;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        SensitiveWordPO that = (SensitiveWordPO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
