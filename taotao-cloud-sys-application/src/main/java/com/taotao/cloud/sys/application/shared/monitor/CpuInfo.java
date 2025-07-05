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

import java.math.BigDecimal;
import lombok.*;
import org.dromara.hutool.core.math.NumberUtil;

/**
 * CPU相关信息
 */
@Setter
@Getter
@ToString
public class CpuInfo {

    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;

    public double getTotal() {
        return NumberUtil.round(total * 100, 2).doubleValue();
    }

    public BigDecimal getSys() {
        return NumberUtil.div(sys * 100, total, 2);
    }

    public BigDecimal getUsed() {
        return NumberUtil.div(used * 100, total, 2);
    }

    public BigDecimal getWait() {
        return NumberUtil.div(wait * 100, total, 2);
    }

    public BigDecimal getFree() {
        return NumberUtil.div(free * 100, total, 2);
    }
}
