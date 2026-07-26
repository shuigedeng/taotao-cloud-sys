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
 * 文件日志表
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
@Table(name = FileLogPO.TABLE_NAME)
@TableName(FileLogPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = FileLogPO.TABLE_NAME)
public class FileLogPO extends BasePO<FileLogPO> {

    public static final String TABLE_NAME = "ttc_file_log";

    /** 业务ID */
    @Column(name = "`biz_id`", columnDefinition = "bigint comment '业务ID'")
    private Long bizId;

    /**
     * 业务类型
     *
     * @see BizType
     */
    @Column(name = "`biz_type`", columnDefinition = "varchar(32) not null comment '业务类型'")
    private String bizType;

    /**
     * 数据类型
     *
     * @see DataType {IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}
     */
    @Column(name = "`data_type`", columnDefinition = "varchar(32) not null comment '数据类型'")
    private String dataType;

    /** 原始文件名 */
    @Column(
            name = "`original_file_name`",
            columnDefinition = "varchar(255) not null comment '原始文件名'")
    private String originalFileName;

    /** 文件访问链接 */
    @Column(name = "`url`", columnDefinition = "varchar(255) not null comment '文件访问链接'")
    private String url;

    /** 文件md5值 */
    @Column(name = "`file_md5`", columnDefinition = "varchar(255) not null comment '文件md5值'")
    private String fileMd5;

    /** 文件上传类型 取上传文件的值 */
    @Column(name = "`context_type`", columnDefinition = "varchar(255) not null comment '文件上传类型'")
    private String contextType;

    /** 唯一文件名 */
    @Column(name = "`filename`", columnDefinition = "varchar(255) not null comment '唯一文件名'")
    private String filename;

    /** 后缀(没有.) */
    @Column(name = "`ext`", columnDefinition = "varchar(64) not null comment '后缀'")
    private String ext;

    /** 大小 */
    @Column(name = "`size`", columnDefinition = "bigint not null comment '大小'")
    private Long size;











    /**
     * 获取BizId
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getBizId() {
        return bizId;
    }











    /**
     * 设置BizId
     *
     * @param bizId bizId
     * @return 无返回值
     * @since 2022.03
     */

    public void setBizId(Long bizId) {
        this.bizId = bizId;
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
     * 获取OriginalFileName
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getOriginalFileName() {
        return originalFileName;
    }











    /**
     * 设置OriginalFileName
     *
     * @param originalFileName originalFileName
     * @return 无返回值
     * @since 2022.03
     */

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
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
     * 获取文件md5
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getFileMd5() {
        return fileMd5;
    }











    /**
     * 设置文件md5
     *
     * @param fileMd5 文件md5
     * @return 无返回值
     * @since 2022.03
     */

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
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
     * 获取文件名称
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getFilename() {
        return filename;
    }











    /**
     * 设置文件名称
     *
     * @param filename 文件名称
     * @return 无返回值
     * @since 2022.03
     */

    public void setFilename(String filename) {
        this.filename = filename;
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
     * 获取尺寸
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getSize() {
        return size;
    }











    /**
     * 设置尺寸
     *
     * @param size 尺寸
     * @return 无返回值
     * @since 2022.03
     */

    public void setSize(Long size) {
        this.size = size;
    }
}
