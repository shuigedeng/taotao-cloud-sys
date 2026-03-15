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

package com.taotao.cloud.sys.domain.valobj;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;

/**
 * DeptVal
 *
 * @param path 如 "1,2,5"
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public record IdPathVal(String path) {

	// 静态工厂方法（推荐）
	public static IdPathVal of( String path ) {
		if (path == null || path.trim().isEmpty()) {
			return new IdPathVal("");
		}
		// 可加校验：只允许数字和逗号
		if (!path.matches("[0-9,]+")) {
			throw new IllegalArgumentException("Invalid id path: " + path);
		}
		return new IdPathVal(path.trim());
	}

	public static IdPathVal of( List<Long> ids){
		return of(StrUtil.join(StrPool.COMMA, ids));
	}

	public List<Long> ids(){
		return Convert.toList(Long.class, StrUtil.splitToLong(this.path,StrPool.COMMA)) ;
	}

	// 业务方法：获取父路径
	public IdPathVal parent() {
		if (path.isEmpty() || !path.contains(",")) {
			return new IdPathVal("");
		}
		int lastComma = path.lastIndexOf(",");
		return new IdPathVal(path.substring(0, lastComma));
	}

	// 业务方法：是否为根节点
	public boolean isRoot() {
		return !path.contains(",");
	}

	// 业务方法：是否包含某 ID（用于权限判断）
	public boolean contains( Long targetId ) {
		if (targetId == null || path.isEmpty()) {
			return false;
		}
		return Arrays.stream(path.split(","))
			.anyMatch(id -> id.equals(targetId.toString()));
	}

	// 获取原始字符串（用于持久化）
	public String getValue() {
		return path;
	}
}
