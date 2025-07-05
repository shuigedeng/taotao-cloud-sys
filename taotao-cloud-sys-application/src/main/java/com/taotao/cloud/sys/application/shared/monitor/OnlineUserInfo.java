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

import lombok.*;

/**
 * 当前在线会话
 */
@Setter
@Getter
@ToString
public class OnlineUserInfo {

    /**
     * 会话编号
     */
    private String tokenId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地址
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录时间
     */
    private Long loginTime;

    //    public OnlineUserInfo(LoginUser user) {
    //        if (user == null) {
    //            return;
    //        }
    //        this.setTokenId(user.getCachedKey());
    //        this.tokenId = user.getCachedKey();
    //        this.userName = user.getUsername();
    //        this.ipaddr = user.getLoginInfo().getIpAddress();
    //        this.loginLocation = user.getLoginInfo().getLocation();
    //        this.browser = user.getLoginInfo().getBrowser();
    //        this.os = user.getLoginInfo().getOperationSystem();
    //        this.loginTime = user.getLoginTime();
    //
    //        SysDeptEntity deptEntity = CacheCenter.deptCache.get(user.getDeptId() + "");
    //
    //        if (deptEntity != null) {
    //            this.deptName = deptEntity.getDeptName();
    //        }
    //    }

}
