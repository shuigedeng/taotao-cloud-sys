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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.config;

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

/**
 * 邮件配置表
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
@Table(name = EmailConfigPO.TABLE_NAME)
@TableName(EmailConfigPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = EmailConfigPO.TABLE_NAME)
public class EmailConfigPO extends BaseSuperEntity<EmailConfigPO, Long> {

    public static final String TABLE_NAME = "tt_email_config";

    /** 收件人 */
    @Column(name = "`from_user`", columnDefinition = "varchar(64) not null comment '收件人'")
    private String fromUser;

    /** 邮件服务器SMTP地址 */
    @Column(name = "`host`", columnDefinition = "varchar(64) not null comment '邮件服务器SMTP地址'")
    private String host;

    /** 密码 */
    @Column(name = "`pass`", columnDefinition = "varchar(64) not null comment '密码'")
    private String pass;

    /** 端口 */
    @Column(name = "`port`", columnDefinition = "varchar(64) not null comment '端口'")
    private String port;

    /** 发件者用户名 */
    @Column(name = "`user`", columnDefinition = "varchar(64) not null comment '发件者用户名'")
    private String user;

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
