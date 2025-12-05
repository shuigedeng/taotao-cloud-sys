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

package com.taotao.cloud.sys.api.client;

import com.taotao.boot.common.constant.ServiceNameConstants;
import com.taotao.boot.common.model.result.Result;
import com.taotao.cloud.sys.api.client.response.LogisticsApiResponse;
import com.taotao.cloud.sys.api.client.response.TracesApiResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

/**
 * 远程调用物流公司模块
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2022-03-25 14:09:10
 */
@HttpExchange(
	value = ServiceNameConstants.TAOTAO_CLOUD_SYS)
public interface LogisticsApi {

    /**
     * 通过id
     *
     * @param logisticsId 物流id
     * @return {@link Result }<{@link LogisticsApiResponse }>
     * @since 2022-04-25 16:47:29
     */
    @GetExchange("/sys/feign/logistic/codes")
    LogisticsApiResponse getById(@RequestParam(value = "logisticsId") Long logisticsId);

    /**
     * 得到物流
     *
     * @param logisticsId 物流id
     * @param logisticsNo 物流编码
     * @return {@link Result }<{@link TracesApiResponse }>
     * @since 2022-04-25 16:47:32
     */
    @GetExchange("/sys/feign/logistic/info")
    TracesApiResponse getLogistic(
            @RequestParam(value = "logisticsId") Long logisticsId,
            @RequestParam(value = "logisticsNo") String logisticsNo);

    @GetExchange("/sys/feign/logistic/list")
    List<LogisticsApiResponse> list();
}
