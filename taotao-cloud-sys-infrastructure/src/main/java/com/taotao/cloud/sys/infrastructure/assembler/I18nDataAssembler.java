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

package com.taotao.cloud.sys.infrastructure.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 国际化信息模型转换器
 */
@Mapper
public interface I18nDataAssembler {

    I18nDataAssembler INSTANCE = Mappers.getMapper(I18nDataAssembler.class);

    //	/**
    //	 * i18nMessage 转 I18nData
    //	 * @param i18nMessage 国际化消息
    //	 * @return I18nData 国际化信息实体
    //	 */
    //	I18nData messageToPo(I18nMessage i18nMessage);
    //
    //	/**
    //	 * PO 转 PageVO
    //	 * @param i18nData 国际化信息
    //	 * @return I18nDataPageVO 国际化信息PageVO
    //	 */
    //	I18nDataPageVO poToPageVo(I18nData i18nData);
    //
    //	/**
    //	 * PO 转 DTI
    //	 * @param i18nData 国际化信息
    //	 * @return I18nDataDTO 国际化信息DTO
    //	 */
    //	I18nDataDTO poToDto(I18nData i18nData);
    //
    //	/**
    //	 * PO 转 ExcelVO
    //	 * @param i18nData 国际化信息
    //	 * @return I18nDataExcelVO 国际化信息ExcelVO
    //	 */
    //	I18nDataExcelVO poToExcelVo(I18nData i18nData);
    //
    //	/**
    //	 * ExcelVO转 PO
    //	 * @param i18nDataExcelVO 国际化信息ExcelVO
    //	 * @return I18nData 国际化信息
    //	 */
    //	I18nData excelVoToPo(I18nDataExcelVO i18nDataExcelVO);

}
