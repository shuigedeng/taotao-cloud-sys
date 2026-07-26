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











    /**
     * 获取CpuNum
     *
     * @return 结果数量
     * @since 2022.03
     */

    public int getCpuNum() {

        return cpuNum;
    }











    /**
     * 设置CpuNum
     *
     * @param cpuNum cpuNum
     * @return 无返回值
     * @since 2022.03
     */

    public void setCpuNum(int cpuNum) {

        this.cpuNum = cpuNum;
    }











    /**
     * 获取总计
     *
     * @return double
     * @since 2022.03
     */

    public double getTotal() {

        return total * 100;
    }











    /**
     * 设置总计
     *
     * @param total 总计
     * @return 无返回值
     * @since 2022.03
     */

    public void setTotal(double total) {

        this.total = total;
    }











    /**
     * 获取Sys
     *
     * @return double
     * @since 2022.03
     */

    public double getSys() {

        return sys / total * 100;
    }











    /**
     * 设置Sys
     *
     * @param sys sys
     * @return 无返回值
     * @since 2022.03
     */

    public void setSys(double sys) {

        this.sys = sys;
    }











    /**
     * 获取Used
     *
     * @return double
     * @since 2022.03
     */

    public double getUsed() {

        return used / total * 100;
    }











    /**
     * 设置Used
     *
     * @param used used
     * @return 无返回值
     * @since 2022.03
     */

    public void setUsed(double used) {

        this.used = used;
    }











    /**
     * 获取Wait
     *
     * @return double
     * @since 2022.03
     */

    public double getWait() {

        return wait / total * 100;
    }











    /**
     * 设置Wait
     *
     * @param wait wait
     * @return 无返回值
     * @since 2022.03
     */

    public void setWait(double wait) {

        this.wait = wait;
    }











    /**
     * 获取Free
     *
     * @return double
     * @since 2022.03
     */

    public double getFree() {

        return free / total * 100;
    }











    /**
     * 设置Free
     *
     * @param free free
     * @return 无返回值
     * @since 2022.03
     */

    public void setFree(double free) {

        this.free = free;
    }
}
