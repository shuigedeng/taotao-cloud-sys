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
import com.taotao.cloud.sys.api.client.response.setting.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * 远程调用后台配置模块
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2022-03-25 14:09:48
 */
@HttpExchange(
	value = ServiceNameConstants.TAOTAO_CLOUD_SYS)
public interface SettingApi {

    /**
     * 通过key获取配置
     *
     * @param key key
     * @return 配置信息
     * @since 2022-03-25 14:10:22
     */
    @GetExchange("/sys/feign/tools/setting")
    SettingApiResponse get(@RequestParam(value = "key") String key);

    @GetExchange("/sys/feign/tools/setting/base")
    BaseSettingApiResponse getBaseSetting(@RequestParam(value = "name") String name);

    /**
     * 获得商品设置
     *
     * @param name 名字
     * @return {@link Result }<{@link GoodsSettingApiResponse }>
     * @since 2022-04-25 16:47:40
     */
    @GetExchange("/sys/feign/tools/setting/goods")
    GoodsSettingApiResponse getGoodsSetting(@RequestParam(value = "name") String name);

    @GetExchange("/sys/feign/tools/setting/order")
    OrderSettingApiResponse getOrderSetting(@RequestParam(value = "name") String name);

    @GetExchange("/sys/feign/tools/setting/experience")
    ExperienceSettingApiResponse getExperienceSetting(@RequestParam(value = "name") String name);

    @GetExchange("/sys/feign/tools/setting/point")
    PointSettingApiResponse getPointSetting(@RequestParam(value = "name") String name);

    @GetExchange("/sys/feign/tools/setting/qq/connect")
    QQConnectSettingApiResponse getQQConnectSetting(@RequestParam(value = "name") String name);

    @GetExchange("/sys/feign/tools/setting/wechat/connect")
    WechatConnectSettingApiResponse getWechatConnectSetting(
            @RequestParam(value = "name") String name);

    @GetExchange("/sys/feign/tools/setting/seckill")
    SeckillSettingApiResponse getSeckillSetting(@RequestParam(value = "name") String name);

    @GetExchange("/sys/feign/tools/setting/ali")
    AlipayPaymentSettingApiResponse getAlipayPaymentSetting(
            @RequestParam(value = "name") String name);

    @GetExchange("/sys/feign/tools/setting/wechat")
    WechatPaymentSettingApiResponse getWechatPaymentSetting(
            @RequestParam(value = "name") String name);
}
