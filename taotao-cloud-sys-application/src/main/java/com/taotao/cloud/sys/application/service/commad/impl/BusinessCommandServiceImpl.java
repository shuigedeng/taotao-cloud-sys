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

package com.taotao.cloud.sys.application.service.commad.impl;

import com.taotao.cloud.sys.application.service.commad.BussinessCommandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.core.context.RootContext;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

// https://blog.csdn.net/weixin_46209120/article/details/132223385
@Service
@Slf4j
public class BusinessCommandServiceImpl implements BussinessCommandService {

    //    @Autowired
    //    private AccountFeignService accountFeignService;
    //    @Autowired
    //    private StorageFeignService storageFeignService;
    //    @Autowired
    //    private OrderService orderService;

    @Override
    @GlobalTransactional(name = "createOrder", rollbackFor = Exception.class)
    public boolean saveOrder() {
        log.info("=============用户下单=================");
        log.info("当前 XID: {}", RootContext.getXID());

        //        //获取全局唯一订单号
        //        Long orderId = UUIDGenerator.generateUUID();
        //        //阶段一： 创建订单
        //        Order order = orderService.prepareSaveOrder(orderVo,orderId);
        //        //扣减库存
        //        storageFeignService.deduct(orderVo.getCommodityCode(), orderVo.getCount());
        //        //扣减余额
        //        accountFeignService.debit(orderVo.getUserId(), orderVo.getMoney());
        return true;
    }
}
