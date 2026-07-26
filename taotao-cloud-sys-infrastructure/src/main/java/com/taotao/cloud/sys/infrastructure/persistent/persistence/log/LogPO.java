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
import com.taotao.boot.webagg.entity.BasePO;
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
import org.hibernate.Hibernate;

/**
 * 日志表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-15 09:25:26
 */
@Setter
@Getter
@DataVersionLog(title = "系统日志变更数据")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

@Entity
@Table(name = LogPO.TABLE_NAME)
@TableName(LogPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = LogPO.TABLE_NAME)
public class LogPO extends BasePO<LogPO> {

    public static final String TABLE_NAME = "ttc_log";

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
    @Column(
            name = "`request_type`",
            columnDefinition = "int null comment '请求类型（1查询/获取，2添加，3修改，4删除）'")
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











    /**
     * 获取TraceId
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getTraceId() {
        return traceId;
    }











    /**
     * 设置TraceId
     *
     * @param traceId traceId
     * @return 无返回值
     * @since 2022.03
     */

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }











    /**
     * 获取ApplicationName
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getApplicationName() {
        return applicationName;
    }











    /**
     * 设置ApplicationName
     *
     * @param applicationName applicationName
     * @return 无返回值
     * @since 2022.03
     */

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }











    /**
     * 获取用户名
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getUsername() {
        return username;
    }











    /**
     * 设置用户名
     *
     * @param username 用户名
     * @return 无返回值
     * @since 2022.03
     */

    public void setUsername(String username) {
        this.username = username;
    }











    /**
     * 获取用户ID
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getUserId() {
        return userId;
    }











    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     * @return 无返回值
     * @since 2022.03
     */

    public void setUserId(String userId) {
        this.userId = userId;
    }











    /**
     * 获取ClientId
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getClientId() {
        return clientId;
    }











    /**
     * 设置ClientId
     *
     * @param clientId clientId
     * @return 无返回值
     * @since 2022.03
     */

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
     * @return 无返回值
     * @since 2022.03
     */

    public void setDescription(String description) {
        this.description = description;
    }











    /**
     * 获取Ip
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getIp() {
        return ip;
    }











    /**
     * 设置Ip
     *
     * @param ip ip
     * @return 无返回值
     * @since 2022.03
     */

    public void setIp(String ip) {
        this.ip = ip;
    }











    /**
     * 获取OperateType
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Integer getOperateType() {
        return operateType;
    }











    /**
     * 设置OperateType
     *
     * @param operateType operateType
     * @return 无返回值
     * @since 2022.03
     */

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }











    /**
     * 获取请求类型
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Integer getRequestType() {
        return requestType;
    }











    /**
     * 设置请求类型
     *
     * @param requestType 请求类型
     * @return 无返回值
     * @since 2022.03
     */

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }











    /**
     * 获取MethodName
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getMethodName() {
        return methodName;
    }











    /**
     * 设置MethodName
     *
     * @param methodName methodName
     * @return 无返回值
     * @since 2022.03
     */

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }











    /**
     * 获取Method
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getMethod() {
        return method;
    }











    /**
     * 设置Method
     *
     * @param method method
     * @return 无返回值
     * @since 2022.03
     */

    public void setMethod(String method) {
        this.method = method;
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
     * 获取Args
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getArgs() {
        return args;
    }











    /**
     * 设置Args
     *
     * @param args args
     * @return 无返回值
     * @since 2022.03
     */

    public void setArgs(String args) {
        this.args = args;
    }











    /**
     * 获取参数列表
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getParams() {
        return params;
    }











    /**
     * 设置参数列表
     *
     * @param params 参数列表
     * @return 无返回值
     * @since 2022.03
     */

    public void setParams(String params) {
        this.params = params;
    }











    /**
     * 获取Headers
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getHeaders() {
        return headers;
    }











    /**
     * 设置Headers
     *
     * @param headers headers
     * @return 无返回值
     * @since 2022.03
     */

    public void setHeaders(String headers) {
        this.headers = headers;
    }











    /**
     * 获取Classpath
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getClasspath() {
        return classpath;
    }











    /**
     * 设置Classpath
     *
     * @param classpath classpath
     * @return 无返回值
     * @since 2022.03
     */

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }











    /**
     * 获取开始时间
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getStartTime() {
        return startTime;
    }











    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     * @return 无返回值
     * @since 2022.03
     */

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }











    /**
     * 获取结束时间
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getEndTime() {
        return endTime;
    }











    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     * @return 无返回值
     * @since 2022.03
     */

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }











    /**
     * 获取ConsumingTime
     *
     * @return 结果数量
     * @since 2022.03
     */

    public Long getConsumingTime() {
        return consumingTime;
    }











    /**
     * 设置ConsumingTime
     *
     * @param consumingTime consumingTime
     * @return 无返回值
     * @since 2022.03
     */

    public void setConsumingTime(Long consumingTime) {
        this.consumingTime = consumingTime;
    }











    /**
     * 获取ExDetail
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getExDetail() {
        return exDetail;
    }











    /**
     * 设置ExDetail
     *
     * @param exDetail exDetail
     * @return 无返回值
     * @since 2022.03
     */

    public void setExDetail(String exDetail) {
        this.exDetail = exDetail;
    }











    /**
     * 获取ExDesc
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getExDesc() {
        return exDesc;
    }











    /**
     * 设置ExDesc
     *
     * @param exDesc exDesc
     * @return 无返回值
     * @since 2022.03
     */

    public void setExDesc(String exDesc) {
        this.exDesc = exDesc;
    }











    /**
     * 获取租户ID
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getTenantId() {
        return tenantId;
    }











    /**
     * 设置租户ID
     *
     * @param tenantId 租户ID
     * @return 无返回值
     * @since 2022.03
     */

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }











    /**
     * 获取来源
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getSource() {
        return source;
    }











    /**
     * 设置来源
     *
     * @param source 来源
     * @return 无返回值
     * @since 2022.03
     */

    public void setSource(String source) {
        this.source = source;
    }











    /**
     * 获取Ctime
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getCtime() {
        return ctime;
    }











    /**
     * 设置Ctime
     *
     * @param ctime ctime
     * @return 无返回值
     * @since 2022.03
     */

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }











    /**
     * 获取结果
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getResult() {
        return result;
    }











    /**
     * 设置结果
     *
     * @param result 结果
     * @return 无返回值
     * @since 2022.03
     */

    public void setResult(String result) {
        this.result = result;
    }











    /**
     * 获取日志day
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getLogday() {
        return logday;
    }











    /**
     * 设置日志day
     *
     * @param logday 日志day
     * @return 无返回值
     * @since 2022.03
     */

    public void setLogday(String logday) {
        this.logday = logday;
    }











    /**
     * 获取Location
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getLocation() {
        return location;
    }











    /**
     * 设置Location
     *
     * @param location location
     * @return 无返回值
     * @since 2022.03
     */

    public void setLocation(String location) {
        this.location = location;
    }











    /**
     * 获取Os
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getOs() {
        return os;
    }











    /**
     * 设置Os
     *
     * @param os os
     * @return 无返回值
     * @since 2022.03
     */

    public void setOs(String os) {
        this.os = os;
    }











    /**
     * 获取Browser
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getBrowser() {
        return browser;
    }











    /**
     * 设置Browser
     *
     * @param browser browser
     * @return 无返回值
     * @since 2022.03
     */

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
