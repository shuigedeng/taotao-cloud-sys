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

package com.taotao.cloud.sys.facade.controller.manager;

import com.taotao.boot.webagg.controller.BusinessController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taotao.boot.webagg.controller.BusinessController;

/**
 * @author shuigedeng
 * @since 2021/9/7
 */
@Tag(name = "登录日志")
@RestController
@RequestMapping("/log/login")
@RequiredArgsConstructor
public class LoginLogController extends BusinessController {
//    private final LoginLogService loginLogService;
//
//    @Operation(summary = "分页")
//    @PostMapping("/add")
//    public Result<Boolean> add(@RequestBody LoginLogParam loginLogParam) {
//        loginLogService.add(loginLogParam);
//        return Result.success(true);
//    }
//
//    @Operation(summary = "分页")
//    @GetMapping("/page")
//    public Result<PageResult<LoginLogDto>> page(LoginLogParam loginLogParam) {
//        return Result.success(loginLogService.page(loginLogParam));
//    }
//
//    @Operation(summary = "获取")
//    @GetMapping("/findById")
//    public Result<LoginLogDto> findById(Long id) {
//        return Result.success(loginLogService.findById(id));
//    }
}
