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

package com.taotao.cloud.sys.application.shared.monitor;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.taotao.boot.common.constant.CommonConstants;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import lombok.*;

/**
 * JVM相关信息
 */
@Setter
@Getter
@ToString
public class JvmInfo {

    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(NumberUtil.div(total, CommonConstants.MB, 2));
    }

    public BigDecimal getMax() {
        return BigDecimal.valueOf(NumberUtil.div(max, CommonConstants.MB, 2));
    }

    public BigDecimal getFree() {
        return BigDecimal.valueOf(NumberUtil.div(free, CommonConstants.MB, 2));
    }

    public BigDecimal getUsed() {
        return BigDecimal.valueOf(NumberUtil.div(total - free, CommonConstants.MB, 2));
    }

    public BigDecimal getUsage() {
        return BigDecimal.valueOf(NumberUtil.div((total - free) * 100, total, 2));
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        return DateUtil.format(
                DateUtil.date(ManagementFactory.getRuntimeMXBean().getStartTime()),
                DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * JDK运行时间
     */
//    public String getRunTime() {
//        return DateUtil.formatBetween(
//                DateUtil.date(ManagementFactory.getRuntimeMXBean().getStartTime()), DateUtil.now());
//    }

    /**
     * 运行参数
     */
    public String getInputArgs() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString();
    }
}
