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

package com.taotao.cloud.sys.api.rpc.dto.query;

import com.taotao.boot.common.model.ddd.types.Query;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 菜单查询对象
 *
 * @param id id
 * @param name 菜单名称
 * @param type 菜单类型 1：目录 2：菜单 3：按钮
 * @param perms 权限标识
 * @param path 前端path / 即跳转路由
 * @param component 菜单组件
 * @param parentId 父菜单ID
 * @param icon 图标
 * @param keepAlive 是否缓存页面: 0:否 1:是 (默认值0)
 * @param hidden 是否隐藏路由菜单: 0否,1是（默认值0）
 * @param alwaysShow 聚合路由 0否,1是（默认值0）
 * @param redirect 重定向
 * @param isFrame 是否为外链 0否,1是（默认值0）
 * @param sortNum 排序值
 * @param createTime 创建时间
 * @param lastModifiedTime 最后修改时间
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 15:27:42
 */
@RecordBuilder
public record FileRpcQuery(Long id, String name, int type, String perms, String path, String component, long parentId,
						   String icon, boolean keepAlive, boolean hidden, boolean alwaysShow, String redirect,
						   boolean isFrame, int sortNum, LocalDateTime createTime, LocalDateTime lastModifiedTime) implements
	Query {

	@Serial
	private static final long serialVersionUID = 5126530068827085130L;

}
