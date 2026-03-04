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

package com.taotao.cloud.sys.application.dto.own.region.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.List;

/**
 * 地区VO
 *
 * @param level 地区级别（1:省份province,2:市city,3:区县district,4:街道street） "行政区划级别" + "country:国家" +
 * "province:省份（直辖市会在province和city显示）" + "city:市（直辖市会在province和city显示）" + "district:区县" + "street:街道"
 */
@RecordBuilder
@Schema(description = "地区VO")
public record RegionResult(@Schema(description = "id") Long id, @Schema(description = "地区父节点") Long parentId,
						   @Schema(description = "地区编码") String code, @Schema(description = "地区名称") String name,
						   @Schema(description = "地区级别") String level,
						   @Schema(description = "城市编码") String cityCode,
						   @Schema(description = "城市中心经度") String lng,
						   @Schema(description = "城市中心纬度") String lat,
						   @Schema(description = "行政地区路径,类似：1，2，3") String path,
						   @Schema(description = "排序") Integer orderNum,
						   @Schema(description = "子信息") List<RegionResult> children) implements MarkerResult {

	@Serial
	private static final long serialVersionUID = 5126530068827085130L;

}
