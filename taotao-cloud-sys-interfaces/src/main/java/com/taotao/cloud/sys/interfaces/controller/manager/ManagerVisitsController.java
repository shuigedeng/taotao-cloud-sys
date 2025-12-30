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
 * VisitsController
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-11 16:26:45
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "工具管理端-访问记录管理API", description = "工具管理端-访问记录管理API")
@RequestMapping("/sys/tools/visits")
public class ManagerVisitsController extends BusinessController {

    //    private final IVisitsService visitsService;
    //
    //    @Operation(summary = "创建访问记录", description = "创建访问记录")
    //    @RequestLogger("创建访问记录")
    //    @PreAuthorize("@el.check('admin','timing:list')")
    //    @PostMapping
    //    public Result<Boolean> create() {
    //        visitsService.count(RequestUtils.getHttpServletRequest());
    //        return Result.success(true);
    //    }
    //
    //    @Operation(summary = "查询访问记录", description = "查询访问记录")
    //    @RequestLogger("查询访问记录")
    //    @PreAuthorize("@el.check('admin','timing:list')")
    //    @GetMapping
    //    public Result<Object> get() {
    //        return Result.success(visitsService.get());
    //    }
    //
    //    @Operation(summary = "查询图表数据", description = "查询图表数据")
    //    @RequestLogger("查询图表数据")
    //    @PreAuthorize("@el.check('admin','timing:list')")
    //    @GetMapping(value = "/chartData")
    //    public Result<Object> getChartData() {
    //        return Result.success(visitsService.getChartData());
    //    }
}
