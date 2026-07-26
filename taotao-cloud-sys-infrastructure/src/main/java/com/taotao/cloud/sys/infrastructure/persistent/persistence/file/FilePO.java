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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.file;

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
 * 文件表
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/11/12 15:33
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

@Entity
@Table(name = FilePO.TABLE_NAME)
@TableName(FilePO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = FilePO.TABLE_NAME)
public class FilePO extends BasePO<FilePO> {

    public static final String TABLE_NAME = "ttc_file";

    /** 创建人 */
    @Column(name = "`create_name`", columnDefinition = "varchar(255) not null comment '创建人'")
    private String createName;

    /** 业务类型 供应商上传图片 供应商上传附件 商品上传图片 */
    @Column(name = "`biz_type`", columnDefinition = "varchar(255) not null comment '业务类型 '")
    private String bizType;

    /** 数据类型 {IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他} */
    @Column(name = "`data_type`", columnDefinition = "varchar(255) not null comment '数据类型'")
    private String dataType;

    /** 原始文件名 */
    @Column(name = "`original`", columnDefinition = "varchar(255) not null comment '原始文件名'")
    private String original;

    /** 文件访问链接 */
    @Column(name = "`url`", columnDefinition = "varchar(255) not null comment '文件访问链接'")
    private String url;

    /** 文件md5值 */
    @Column(name = "`md5`", columnDefinition = "varchar(255) not null comment '文件md5值'")
    private String md5;

    /** 文件上传类型 取上传文件的值 */
    @Column(name = "`type`", columnDefinition = "varchar(255) not null comment '文件上传类型'")
    private String type;

    /** 文件上传类型 取上传文件的值 */
    @Column(name = "`context_type`", columnDefinition = "varchar(255) not null comment '文件上传类型'")
    private String contextType;

    /** 唯一文件名 */
    @Column(name = "`name`", columnDefinition = "varchar(255) not null comment '唯一文件名'")
    private String name;

    /** 后缀(没有.) */
    @Column(name = "`ext`", columnDefinition = "varchar(255) not null comment '后缀'")
    private String ext;

    /** 大小 */
    @Column(name = "`length`", columnDefinition = "bigint null comment '大小'")
    private Long length;

    /**
     * 获取CreateName
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置CreateName
     *
     * @param createName createName
     * @return 无返回值
     * @since 2022.03
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 获取BizType
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * 设置BizType
     *
     * @param bizType bizType
     * @return 无返回值
     * @since 2022.03
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * 获取数据类型
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置数据类型
     *
     * @param dataType 数据类型
     * @return 无返回值
     * @since 2022.03
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取Original
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 设置Original
     *
     * @param original original
     * @return 无返回值
     * @since 2022.03
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * 获取URL
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     *
     * @param url URL
     * @return 无返回值
     * @since 2022.03
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取md5
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 设置md5
     *
     * @param md5 md5
     * @return 无返回值
     * @since 2022.03
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 获取类型
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     * @return 无返回值
     * @since 2022.03
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取ContextType
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getContextType() {
        return contextType;
    }

    /**
     * 设置ContextType
     *
     * @param contextType contextType
     * @return 无返回值
     * @since 2022.03
     */
    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

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
     * 获取Ext
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getExt() {
        return ext;
    }

    /**
     * 设置Ext
     *
     * @param ext ext
     * @return 无返回值
     * @since 2022.03
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * 获取长度
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Long getLength() {
        return length;
    }

    /**
     * 设置长度
     *
     * @param length 长度
     * @return 无返回值
     * @since 2022.03
     */
    public void setLength(Long length) {
        this.length = length;
    }
}
