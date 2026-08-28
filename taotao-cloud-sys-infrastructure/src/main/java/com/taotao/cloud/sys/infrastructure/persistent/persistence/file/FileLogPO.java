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
}
