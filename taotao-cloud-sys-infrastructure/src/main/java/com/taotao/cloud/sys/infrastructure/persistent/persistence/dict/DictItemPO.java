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
 * 字典子项表 // @SQLDelete(sql = "update ttc_dict_item set del_flag = 1 where id = ?") // @Where(clause
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

@Entity
@Table(name = DictItemPO.TABLE_NAME)
@TableName(DictItemPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = DictItemPO.TABLE_NAME)
public class DictItemPO extends BasePO<DictItemPO> {

    public static final String TABLE_NAME = "ttc_dict_item";

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

    /**
     * 获取字典ID
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getDictId() {
        return dictId;
    }

    /**
     * 设置字典ID
     *
     * @param dictId 字典ID
     * @since 2022.03
     */
    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    /**
     * 获取项文本
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getItemText() {
        return itemText;
    }

    /**
     * 设置项文本
     *
     * @param itemText 项文本
     * @since 2022.03
     */
    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    /**
     * 获取项值
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 设置项值
     *
     * @param itemValue 项值
     * @since 2022.03
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    /**
     * 获取描述
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     * @since 2022.03
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取状态
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     * @since 2022.03
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * @since 2022.03
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}
