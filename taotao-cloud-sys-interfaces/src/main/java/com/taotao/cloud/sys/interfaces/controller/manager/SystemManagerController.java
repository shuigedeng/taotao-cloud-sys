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
import com.taotao.cloud.sys.application.service.commad.SystemCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SystemController
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-03-02 15:55:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "平台管理端-systemAPI", description = "平台管理端-systemAPI")
@RequestMapping("/manager/sys/system")
public class SystemManagerController extends BusinessController {

    private final SystemCommandService systemService;

    /// **
    // * 下载公钥
    // */
    // @GetMapping("/download/publicKey")
    // public ResponseEntity downloadPublicKey() throws IOException {
    //    final File file = systemService.publicKeyFile();
    //    HttpHeaders headers = new HttpHeaders();
    //    headers.setContentDispositionFormData("attachment", "id_rsa.pub");
    //    headers.add("fileName","id_rsa.pub");
    //    headers.add("Access-Control-Expose-Headers", "fileName");
    //    headers.add("Access-Control-Expose-Headers", "Content-Disposition");
    //    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    //
    //    final FileSystemResource fileSystemResource = new FileSystemResource(file);
    //
    //    ResponseEntity<Resource> body = ResponseEntity.ok()
    //            .headers(headers)
    //            .contentLength(fileSystemResource.contentLength())
    //            .body(fileSystemResource);
    //    return body;
    // }
    //
    /// **
    // * 当前版本
    // */
    // @GetMapping
    // public String current(){
    //	return versionService.currentVersion().toString();
    // }
    //
    /// **
    // * 当前版本详细信息
    // */
    // @GetMapping("/detail")
    // public Version detail(){
    //	return versionService.currentVersion();
    // }
}
