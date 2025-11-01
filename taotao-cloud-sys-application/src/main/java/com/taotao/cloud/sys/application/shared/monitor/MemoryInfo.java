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

import cn.hutool.core.util.NumberUtil;
import com.taotao.boot.common.constant.CommonConstants;
import java.math.BigDecimal;
import lombok.*;

/**
 * 內存相关信息
 */
@Setter
@Getter
@ToString
public class MemoryInfo {

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(NumberUtil.div(total, CommonConstants.GB, 2));
    }

    public BigDecimal getUsed() {
        return BigDecimal.valueOf(NumberUtil.div(used, CommonConstants.GB, 2));
    }

    public BigDecimal getFree() {
        return BigDecimal.valueOf(NumberUtil.div(free, CommonConstants.GB, 2));
    }

    public BigDecimal getUsage() {
        return BigDecimal.valueOf(NumberUtil.div(used * 100, total, 2));
    }
}
