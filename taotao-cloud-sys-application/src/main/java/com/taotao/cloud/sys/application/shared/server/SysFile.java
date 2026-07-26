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
 * 系统文件相关信息系统文件相关信息实体类
 *
 * @since 2022/5/21 15:39
 */
public class SysFile {

    /** 盘符路径 */
    private String dirName;

    /** 盘符类型 */
    private String sysTypeName;

    /** 文件类型 */
    private String typeName;

    /** 总大小 */
    private String total;

    /** 剩余大小 */
    private String free;

    /** 已经使用量 */
    private String used;

    /** 资源的使用率 */
    private double usage;











    /**
     * 获取DirName
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getDirName() {

        return dirName;
    }











    /**
     * 设置DirName
     *
     * @param dirName dirName
     * @return 无返回值
     * @since 2022.03
     */

    public void setDirName(String dirName) {

        this.dirName = dirName;
    }











    /**
     * 获取SysTypeName
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getSysTypeName() {

        return sysTypeName;
    }











    /**
     * 设置SysTypeName
     *
     * @param sysTypeName sysTypeName
     * @return 无返回值
     * @since 2022.03
     */

    public void setSysTypeName(String sysTypeName) {

        this.sysTypeName = sysTypeName;
    }











    /**
     * 获取类型名称
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getTypeName() {

        return typeName;
    }











    /**
     * 设置类型名称
     *
     * @param typeName 类型名称
     * @return 无返回值
     * @since 2022.03
     */

    public void setTypeName(String typeName) {

        this.typeName = typeName;
    }











    /**
     * 获取总计
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getTotal() {

        return total;
    }











    /**
     * 设置总计
     *
     * @param total 总计
     * @return 无返回值
     * @since 2022.03
     */

    public void setTotal(String total) {

        this.total = total;
    }











    /**
     * 获取Free
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getFree() {

        return free;
    }











    /**
     * 设置Free
     *
     * @param free free
     * @return 无返回值
     * @since 2022.03
     */

    public void setFree(String free) {

        this.free = free;
    }











    /**
     * 获取Used
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getUsed() {

        return used;
    }











    /**
     * 设置Used
     *
     * @param used used
     * @return 无返回值
     * @since 2022.03
     */

    public void setUsed(String used) {

        this.used = used;
    }











    /**
     * 获取Usage
     *
     * @return double
     * @since 2022.03
     */

    public double getUsage() {

        return usage;
    }











    /**
     * 设置Usage
     *
     * @param usage usage
     * @return 无返回值
     * @since 2022.03
     */

    public void setUsage(double usage) {

        this.usage = usage;
    }
}
