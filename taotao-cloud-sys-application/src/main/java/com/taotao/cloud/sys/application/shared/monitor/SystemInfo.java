package com.taotao.cloud.sys.application.shared.monitor;

import lombok.*;

/**
 * 系统相关信息
 */
@Setter
@Getter
@ToString
public class SystemInfo {

    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;

}
