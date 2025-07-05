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

package com.taotao.cloud.sys.application.dto.i18n.cmmond;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.*;

/**
 * 国际化信息传输对象
 */
@Setter
@Getter
@ToString
@Schema(title = "国际化信息传输对象")
public class I18nDataCreateCmd {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识 = 业务:关键词
     */
    @NotEmpty(message = "{i18nMessage.code}：{}")
    @Schema(title = "唯一标识 = 业务:关键词")
    private String code;

    /**
     * 语言文本列表
     */
    @Valid
    @NotNull(message = "{i18nData.languageTexts}: {}")
    @Size(min = 1, message = "{i18nData.languageTexts}: {}")
    @Schema(title = "语言文本列表")
    private List<LanguageText> languageTexts;

    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remarks;

    /**
     * 语言文本
     */
    @Setter
    @Getter
    @ToString
    @Schema(title = "语言文本信息")
    public static class LanguageText {

        /**
         * 语言标签
         */
        @NotEmpty(message = "{i18nMessage.languageTag}：{}")
        @Schema(title = "语言标签")
        private String languageTag;

        /**
         * 文本值，可以使用 { } 加角标，作为占位符
         */
        @NotEmpty(message = "{i18nMessage.message}：{}")
        @Schema(title = "文本值，可以使用 { } 加角标，作为占位符")
        private String message;
    }
}
