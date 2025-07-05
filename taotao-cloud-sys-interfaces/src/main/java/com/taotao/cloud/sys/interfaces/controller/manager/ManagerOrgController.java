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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端-部门管理API
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-10-09 14:19:11
 */
@Validated
@RestController
@RequestMapping("/sys/manager/org")
@Tag(name = "管理端-部门管理API", description = "管理端-部门管理API")
public class ManagerOrgController extends BusinessController {
    //
    //    @Operation(summary = "获取部门树", description = "获取部门树")
    //    @RequestLogger
    //    @PreAuthorize("hasAuthority('dept:tree:data')")
    //    @GetMapping("/tree")
    //    public Result<List<DeptTreeVO>> tree() {
    //        return Result.success(ForestNodeMerger.merge(service().tree()));
    //    }
}
