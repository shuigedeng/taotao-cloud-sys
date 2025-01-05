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

package com.taotao.cloud.sys.infrastructure.persistent.persistence.app;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 系统应用表
 *
 * @author
 * @since 2022-09-12
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = AppPO.TABLE_NAME)
@TableName(AppPO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = AppPO.TABLE_NAME)
public class AppPO extends BaseSuperEntity<AppPO, Long> {

    public static final String TABLE_NAME = "tt_app";

    /** 应用名称 */
    private String name;

    /** 应用编码 */
    private String code;

    /** 图标 */
    private String icon;

    /** 排序 */
    private Integer sort;
}
