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

package com.taotao.cloud.sys.api.internal.query;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.common.model.result.Result;
import com.taotao.cloud.sys.api.internal.dto.query.SettingApiQuery;
import com.taotao.cloud.sys.api.internal.dto.response.setting.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 远程调用后台配置模块
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2022-03-25 14:09:48
 */
@HttpExchange
public interface SettingQueryApi {

    /**
     * 通过key获取配置
     *
     * @param key key
     * @return 配置信息
     * @since 2022-03-25 14:10:22
     */
    @PostExchange("/internal/sys/tools/setting")
	Response<SettingApiResponse> query(@RequestBody Request<SettingApiQuery> request) ;

    @PostExchange("/internal/sys/tools/setting/base")
	Response<BaseSettingApiResponse> querySetting(@RequestBody Request<SettingApiQuery> request);

    /**
     * 获得商品设置
     *
     * @param name 名字
     * @return {@link Result }<{@link GoodsSettingApiResponse }>
     * @since 2022-04-25 16:47:40
     */
    @PostExchange("/internal/sys/tools/setting/goods")
	Response<GoodsSettingApiResponse> queryGoodsSetting(@RequestBody Request<SettingApiQuery> request);

    @PostExchange("/internal/sys/tools/setting/order")
	Response<OrderSettingApiResponse> queryOrderSetting(@RequestBody Request<SettingApiQuery> request);

    @PostExchange("/internal/sys/tools/setting/experience")
	Response<ExperienceSettingApiResponse> queryExperienceSetting(@RequestBody Request<SettingApiQuery> request);

    @PostExchange("/internal/sys/tools/setting/point")
	Response<PointSettingApiResponse> queryPointSetting(@RequestBody Request<SettingApiQuery> request);

    @PostExchange("/internal/sys/tools/setting/qq/connect")
	Response<QQConnectSettingApiResponse> queryQQConnectSetting(@RequestBody Request<SettingApiQuery> request);

    @PostExchange("/internal/sys/tools/setting/wechat/connect")
	Response<WechatConnectSettingApiResponse> queryWechatConnectSetting(@RequestBody Request<SettingApiQuery> request);

    @PostExchange("/internal/sys/tools/setting/seckill")
	Response<SeckillSettingApiResponse> querySeckillSetting(@RequestBody Request<SettingApiQuery> request);

    @PostExchange("/internal/sys/tools/setting/ali")
	Response< AlipayPaymentSettingApiResponse> queryAlipayPaymentSetting(@RequestBody Request<SettingApiQuery> request);

    @PostExchange("/internal/sys/tools/setting/wechat")
	Response<WechatPaymentSettingApiResponse> queryWechatPaymentSetting(@RequestBody Request<SettingApiQuery> request);
}
