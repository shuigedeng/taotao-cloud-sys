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

package com.taotao.cloud.sys.interfaces.controller.manager;

import com.taotao.boot.webagg.controller.BusinessController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台管理端-地区API
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-10-09 15:01:59
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/manager/sys/region")
@Tag(name = "平台管理端-地区API", description = "平台管理端-地区API")
public class RegionManagerController extends BusinessController {

    //    @Operation(summary = "根据父id查询地区数据", description = "根据父id查询地区数据")
    //    @Parameters({
    //            @Parameter(name = "parentId", description = "父id", required = true, example =
    // "1111", in = ParameterIn.PATH)
    //    })
    //    @RequestLogger
    //    @GetMapping("/parentId/{parentId}")
    //    @NotAuth
    //    // @PreAuthorize("hasAuthority('sys:region:info:parentId')")
    //    public Result<List<RegionParentVO>> queryRegionByParentId(@NotBlank(message = "父id不能为空")
    // @PathVariable(name = "parentId") Long parentId) {
    //        List<RegionParentVO> result = service().queryRegionByParentId(parentId);
    //        return Result.success(result);
    //    }
    //
    //    @Operation(summary = "树形结构查询", description = "树形结构查询")
    //    @Parameters({
    //            @Parameter(name = "parentId", description = "父id", required = true, example =
    // "1"),
    //            @Parameter(name = "depth", description = "深度", example = "1024"),
    //    })
    //    @RequestLogger
    //    @GetMapping(value = "/tree")
    //    @NotAuth
    //    // @PreAuthorize("hasAuthority('sys:region:info:true')")
    //    public Result<List<RegionParentVO>> tree(@NotNull(message = "父id不能为空")
    // @RequestParam(required = false, defaultValue = "1") Long parentId,
    //                                             @RequestParam(required = false, defaultValue =
    // "1024") Integer depth) {
    //        List<RegionParentVO> result = service().tree(parentId, depth);
    //        return Result.success(result);
    //    }
    //
    //    @Operation(summary = "另一种树形结构查询", description = "另一种树形结构查询")
    //    @RequestLogger
    //    @GetMapping(value = "/other/tree")
    //    @PreAuthorize("hasAuthority('sys:region:info:true')")
    //    public Result<List<RegionTreeVO>> treeOther() {
    //        List<RegionTreeVO> result = service().treeOther();
    //        return Result.success(ForestNodeMerger.merge(result));
    //    }
}
