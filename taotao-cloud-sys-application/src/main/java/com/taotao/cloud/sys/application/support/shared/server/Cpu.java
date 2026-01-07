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

package com.taotao.cloud.sys.application.support.shared.server;

/**
 * CPU相关信息
 *
 */
public class Cpu {

    /** 核心数 */
    private int cpuNum;

    /** CPU总的使用率 */
    private double total;

    /** CPU系统使用率 */
    private double sys;

    /** CPU用户使用率 */
    private double used;

    /** CPU当前等待率 */
    private double wait;

    /** CPU当前空闲率 */
    private double free;

    public int getCpuNum() {

        return cpuNum;
    }

    public void setCpuNum(int cpuNum) {

        this.cpuNum = cpuNum;
    }

    public double getTotal() {

        return total * 100;
    }

    public void setTotal(double total) {

        this.total = total;
    }

    public double getSys() {

        return sys / total * 100;
    }

    public void setSys(double sys) {

        this.sys = sys;
    }

    public double getUsed() {

        return used / total * 100;
    }

    public void setUsed(double used) {

        this.used = used;
    }

    public double getWait() {

        return wait / total * 100;
    }

    public void setWait(double wait) {

        this.wait = wait;
    }

    public double getFree() {

        return free / total * 100;
    }

    public void setFree(double free) {

        this.free = free;
    }
}
