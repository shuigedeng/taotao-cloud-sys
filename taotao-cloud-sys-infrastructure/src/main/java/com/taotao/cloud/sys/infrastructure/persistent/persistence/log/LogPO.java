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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.log;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.data.mybatis.interceptor.encrypt.annotation.EncryptField;
import com.taotao.boot.data.mybatis.mybatisplus.interceptor.datachanage.annotation.DataVersionLog;
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
 * 日志表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-15 09:25:26
 */
@DataVersionLog(title = "系统日志变更数据")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(fluent = true)
@Entity
@Table(name = LogPO.TABLE_NAME)
@TableName(LogPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = LogPO.TABLE_NAME)
public class LogPO extends BaseSuperEntity<LogPO, Long> {

    public static final String TABLE_NAME = "tt_log";

    /** 请求日志id */
    @Column(name = "`trace_id`", columnDefinition = "varchar(64) null comment '请求日志id'")
    private String traceId;

    /** 服务名称 */
    @Column(name = "`application_name`", columnDefinition = "varchar(128) null comment '服务名称'")
    private String applicationName;

    /** 操作人ID */
    @Column(name = "`username`", columnDefinition = "varchar(64) null comment '操作人'")
    private String username;

    /** 操作人ID */
    @Column(name = "`user_id`", columnDefinition = "varchar(128) null comment '操作人ID'")
    private String userId;

    /** 客户端ID */
    @Column(name = "`client_id`", columnDefinition = "varchar(128) null comment '客户端ID'")
    private String clientId;

    /** 操作描述 */
    @Column(name = "`description`", columnDefinition = "varchar(1024) null comment '操作描述'")
    private String description;

    /** 操作IP */
    @Column(name = "`ip`", columnDefinition = "varchar(128) null comment '操作IP'")
    private String ip;

    /** 操作类型 1 操作记录 2异常记录 */
    @Column(name = "`operate_type`", columnDefinition = "int null comment '操作类型 1 操作记录 2异常记录'")
    private Integer operateType;

    /** 请求类型（1查询/获取，2添加，3修改，4删除） */
    @Column(name = "`request_type`", columnDefinition = "int null comment '请求类型（1查询/获取，2添加，3修改，4删除）'")
    private Integer requestType;

    /** 请求方法名称 */
    @Column(name = "`method_name`", columnDefinition = "varchar(128) null comment '请求方法名称'")
    private String methodName;

    /** 请求方式 */
    @Column(name = "`method`", columnDefinition = "varchar(128) null comment '请求方式'")
    private String method;

    /** 请求url */
    @Column(name = "`url`", columnDefinition = "varchar(256) null comment '请求url'")
    private String url;

    /** 方法参数 */
    @Column(name = "`args`", columnDefinition = "varchar(1024) null comment '方法参数'")
    private String args;

    /** 请求参数 */
    @Column(name = "`params`", columnDefinition = "varchar(1024) null comment '请求参数'")
    private String params;

    /** 请求头 */
    @Column(name = "`headers`", columnDefinition = "text null comment '请求头'")
    private String headers;

    /** 类路径 */
    @Column(name = "`classpath`", columnDefinition = "text null comment '类路径'")
    private String classpath;

    /** 开始时间 */
    @Column(name = "`start_time`", columnDefinition = "bigint null comment '开始时间'")
    private Long startTime;

    /** 完成时间 */
    @Column(name = "`end_time`", columnDefinition = "bigint null comment '完成时间'")
    private Long endTime;

    /** 消耗时间 */
    @Column(name = "`consuming_time`", columnDefinition = "bigint null comment '消耗时间'")
    private Long consumingTime;

    /** 异常详情信息 堆栈信息 */
    @Column(name = "`ex_detail`", columnDefinition = "text null comment '异常详情信息 堆栈信息'")
    private String exDetail;

    /** 异常描述 e.getMessage */
    @Column(name = "`ex_desc`", columnDefinition = "text null comment ' 异常描述 e.getMessage'")
    private String exDesc;

    /** 租户id */
    @Column(name = "`tenant_id`", columnDefinition = "varchar(64) null comment '租户id'")
    private String tenantId;

    /** 来源 */
    @Column(name = "`source`", columnDefinition = "varchar(256) null comment '来源'")
    private String source;

    /** 记录时间 */
    @Column(name = "`ctime`", columnDefinition = "varchar(128) null comment '记录时间'")
    private String ctime;

    /** 返回值 */
    @Column(name = "`result`", columnDefinition = "text null comment '返回值'")
    private String result;

    /** 天 */
    @Column(name = "`logday`", columnDefinition = "varchar(64) null comment '天'")
    private String logday;

    /** 操作地点 */
    @Column(name = "`location`", columnDefinition = "varchar(1024) null comment '操作地点'")
    private String location;

    /** 操作系统 */
    @Column(name = "`os`", columnDefinition = "text null comment '操作系统'")
    private String os;

    /** 浏览器 */
    @Column(name = "`browser`", columnDefinition = "text null comment '浏览器'")
    @EncryptField
    private String browser;


	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getConsumingTime() {
		return consumingTime;
	}

	public void setConsumingTime(Long consumingTime) {
		this.consumingTime = consumingTime;
	}

	public String getExDetail() {
		return exDetail;
	}

	public void setExDetail(String exDetail) {
		this.exDetail = exDetail;
	}

	public String getExDesc() {
		return exDesc;
	}

	public void setExDesc(String exDesc) {
		this.exDesc = exDesc;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLogday() {
		return logday;
	}

	public void setLogday(String logday) {
		this.logday = logday;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        LogPO log = (LogPO) o;
        return getId() != null && Objects.equals(getId(), log.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
