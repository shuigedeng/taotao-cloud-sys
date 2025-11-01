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

package com.taotao.cloud.sys.application.shared.server;


import static cn.hutool.core.date.DatePattern.NORM_DATETIME_FORMAT;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * JVM相关信息
 *
 * @since 2022/5/21 15:41
 */
public class Jvm {

    /** 当前JVM占用的内存总数(M) */
    private double total;

    /** JVM最大可用内存总数(M) */
    private double max;

    /** JVM空闲内存(M) */
    private double free;

    /** JDK版本 */
    private String version;

    /** JDK路径 */
    private String home;

    public double getTotal() {

        return total / (1024 * 1024);
    }

    public void setTotal(double total) {

        this.total = total;
    }

    public double getMax() {

        return max / (1024 * 1024);
    }

    public void setMax(double max) {

        this.max = max;
    }

    public double getFree() {

        return free / (1024 * 1024);
    }

    public void setFree(double free) {

        this.free = free;
    }

    public double getUsed() {

        return (total - free) / (1024 * 1024);
    }

    public double getUsage() {

        return (total - free) / total * 100;
    }

    /** 获取JDK名称 */
    public String getName() {

        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public String getVersion() {

        return version;
    }

    public void setVersion(String version) {

        this.version = version;
    }

    public String getHome() {

        return home;
    }

    public void setHome(String home) {

        this.home = home;
    }

    /** JDK启动时间 */
    public String getStartTime() {
        return DateUtil.format(
                new Date(ManagementFactory.getRuntimeMXBean().getStartTime()),
                NORM_DATETIME_FORMAT);
    }

    /** JDK运行时间 */
    public String getRunTime() {
        return String.valueOf(
                DateUtil.between(
                        new Date(ManagementFactory.getRuntimeMXBean().getStartTime()),
                        new Date(),
                        DateUnit.MINUTE));
    }
}
