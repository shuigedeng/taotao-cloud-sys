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

package com.taotao.cloud.sys.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 代码生成相关配置
 *
 * @author shuigedeng
 * @version 2022.09
 * @since 2022-09-22 09:41:54
 */
@Component
@ConfigurationProperties(prefix = "sys.gen")
@EnableConfigurationProperties({GenProperties.class})
public class GenProperties {
    /** 作者 */
    public static String author;

    /** 生成包路径 */
    public static String packageName;

    /** 自动去除表前缀，默认是false */
    public static boolean autoRemovePre;

    /** 表前缀(类名不会包含表前缀) */
    public static String tablePrefix;

    /**
     * 获取Author
     *
     * @return 字符串
     * @since 2022.03
     */
    public static String getAuthor() {
        return author;
    }

    /**
     * 设置Author
     *
     * @param author author
     * @since 2022.03
     */
    public void setAuthor(String author) {
        GenProperties.author = author;
    }

    /**
     * 获取PackageName
     *
     * @return 字符串
     * @since 2022.03
     */
    public static String getPackageName() {
        return packageName;
    }

    /**
     * 设置PackageName
     *
     * @param packageName packageName
     * @since 2022.03
     */
    public void setPackageName(String packageName) {
        GenProperties.packageName = packageName;
    }

    /**
     * 获取AutoRemovePre
     *
     * @return 是否成功
     * @since 2022.03
     */
    public static boolean getAutoRemovePre() {
        return autoRemovePre;
    }

    /**
     * 设置AutoRemovePre
     *
     * @param autoRemovePre autoRemovePre
     * @since 2022.03
     */
    public void setAutoRemovePre(boolean autoRemovePre) {
        GenProperties.autoRemovePre = autoRemovePre;
    }

    /**
     * 获取表Prefix
     *
     * @return 字符串
     * @since 2022.03
     */
    public static String getTablePrefix() {
        return tablePrefix;
    }

    /**
     * 设置表Prefix
     *
     * @param tablePrefix 表Prefix
     * @since 2022.03
     */
    public void setTablePrefix(String tablePrefix) {
        GenProperties.tablePrefix = tablePrefix;
    }
}
