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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.i18n;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
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

/**
 * 国际化信息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(name = I18nDataPO.TABLE_NAME)
@TableName(I18nDataPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = I18nDataPO.TABLE_NAME)
public class I18nDataPO extends BaseSuperEntity<I18nDataPO, Long> {

    public static final String TABLE_NAME = "ttc_i18n_data";

    /**
     * 语言标签
     */
    @Column(name = "`language_tag`", columnDefinition = "varchar(255) not null comment '语言标签'")
    private String languageTag;

    /**
     * 国际化标识
     */
    @Column(name = "`code`", columnDefinition = "varchar(255) not null comment '国际化标识'")
    private String code;

    /**
     * 文本值，可以使用 { } 加角标，作为占位符
     */
    @Column(
            name = "`message`",
            columnDefinition = "varchar(255) not null comment '文本值，可以使用 { } 加角标，作为占位符'")
    private String message;

    /**
     * 备注
     */
    @Column(name = "`remarks`", columnDefinition = "varchar(255) not null comment '备注'")
    private String remarks;

    public String getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        I18nDataPO i18nData = (I18nDataPO) o;
        return languageTag.equals(i18nData.languageTag) && code.equals(i18nData.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageTag, code);
    }
}
