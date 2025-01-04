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

package com.taotao.cloud.sys.application.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: OAuth2 合规性配置参数 </p>
 *
 *
 * @since : 2022/7/7 0:16
 */
@ConfigurationProperties(prefix = TestProperties.PREFIX)
public class TestProperties {

    public static final String PREFIX = "test";

    /**
     * 开启登录失败限制
     */
    private Integer signInFailureLimited = 0;

    public Integer getSignInFailureLimited() {
        return signInFailureLimited;
    }

    public void setSignInFailureLimited(Integer signInFailureLimited) {
        this.signInFailureLimited = signInFailureLimited;
    }
}
