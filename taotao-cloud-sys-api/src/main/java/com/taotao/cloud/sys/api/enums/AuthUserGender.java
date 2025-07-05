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

package com.taotao.cloud.sys.api.enums;

import com.taotao.boot.common.utils.lang.StringUtils;
import java.util.Arrays;

public enum AuthUserGender {
    MALE("1", "男"),
    FEMALE("0", "女"),
    UNKNOWN("-1", "未知");

    private String code;
    private String desc;

    public static AuthUserGender getRealGender(String originalGender) {
        if (null != originalGender && !UNKNOWN.getCode().equals(originalGender)) {
            String[] males = new String[] {"m", "男", "1", "male"};
            return Arrays.asList(males).contains(originalGender.toLowerCase()) ? MALE : FEMALE;
        } else {
            return UNKNOWN;
        }
    }

    public static AuthUserGender getWechatRealGender(String originalGender) {
        return !StringUtils.isEmpty(originalGender) && !"0".equals(originalGender)
                ? getRealGender(originalGender)
                : UNKNOWN;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    private AuthUserGender(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
