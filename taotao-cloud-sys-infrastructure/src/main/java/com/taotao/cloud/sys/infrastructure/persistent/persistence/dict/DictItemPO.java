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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.dict;

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

/**
 * 字典子项表 // @SQLDelete(sql = "update tt_dict_item set del_flag = 1 where id = ?") // @Where(clause
 * ="del_flag = 1")
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:09:21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(name = DictItemPO.TABLE_NAME)
@TableName(DictItemPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = DictItemPO.TABLE_NAME)
public class DictItemPO extends BaseSuperEntity<DictItemPO, Long> {

    public static final String TABLE_NAME = "tt_dict_item";

    /**
     * 字典id
     *
     * @see DictPO
     */
    @Column(name = "`dict_id`", columnDefinition = "bigint not null comment '字典id'")
    private Long dictId;

    /** 字典项文本 */
    @Column(name = "`item_text`", columnDefinition = "varchar(2000) not null comment '字典项文本'")
    private String itemText;

    /** 字典项值 */
    @Column(name = "`item_value`", columnDefinition = "varchar(2000) not null comment '字典项文本'")
    private String itemValue;

    /** 描述 */
    @Column(name = "`description`", columnDefinition = "varchar(255) comment '描述'")
    private String description;

    /** 状态 0不启用 1启用 */
    @Column(name = "`status`", columnDefinition = "int NOT NULL DEFAULT 1 comment ' 状态 0不启用 1启用'")
    private Integer status;

    /** 排序值 */
    @Column(name = "`sort_num`", columnDefinition = "int not null default 1 comment '排序值'")
    private Integer sortNum;

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getItemText() {
		return itemText;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
}
