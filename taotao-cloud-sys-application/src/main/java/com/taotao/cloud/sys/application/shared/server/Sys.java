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
 * 主机系统相关信息实体类系统相关信息
 *
 * @since 2022/5/21 16:13
 */
public class Sys {

    /** 服务器名称 */
    private String computerName;

    /** 服务器Ip */
    private String computerIp;

    /** 项目路径 */
    private String userDir;

    /** 操作系统 */
    private String osName;

    /** 系统架构 */
    private String osArch;











    /**
     * 获取ComputerName
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getComputerName() {

        return computerName;
    }











    /**
     * 设置ComputerName
     *
     * @param computerName computerName
     * @return 无返回值
     * @since 2022.03
     */

    public void setComputerName(String computerName) {

        this.computerName = computerName;
    }











    /**
     * 获取ComputerIp
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getComputerIp() {

        return computerIp;
    }











    /**
     * 设置ComputerIp
     *
     * @param computerIp computerIp
     * @return 无返回值
     * @since 2022.03
     */

    public void setComputerIp(String computerIp) {

        this.computerIp = computerIp;
    }











    /**
     * 获取UserDir
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getUserDir() {

        return userDir;
    }











    /**
     * 设置UserDir
     *
     * @param userDir userDir
     * @return 无返回值
     * @since 2022.03
     */

    public void setUserDir(String userDir) {

        this.userDir = userDir;
    }











    /**
     * 获取OsName
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getOsName() {

        return osName;
    }











    /**
     * 设置OsName
     *
     * @param osName osName
     * @return 无返回值
     * @since 2022.03
     */

    public void setOsName(String osName) {

        this.osName = osName;
    }











    /**
     * 获取OsArch
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getOsArch() {

        return osArch;
    }











    /**
     * 设置OsArch
     *
     * @param osArch osArch
     * @return 无返回值
     * @since 2022.03
     */

    public void setOsArch(String osArch) {

        this.osArch = osArch;
    }
}
