package com.taotao.cloud.sys.application.dto.i18n.clientobject;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 国际化信息 查询对象
 */
@Setter
@Getter
@ToString
@Schema(title = "国际化信息查询对象")
public class I18nDataCO {

	private static final long serialVersionUID = 1L;

	@Parameter(description = "国际化标识")
	private String code;

	@Parameter(description = "文本信息")
	private String message;

	@Parameter(description = "语言标签")
	private String languageTag;

}
