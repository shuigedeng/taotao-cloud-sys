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

package com.taotao.cloud.sys.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.sensitive.SensitiveWordPO;

/**
 * 敏感词业务层
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2022-03-25 14:33:20
 */
public interface SensitiveWordService extends CommandService {

    /**
     * 重新写入缓存
     *
     * @since 2022-03-25 14:33:25
     */
    void resetCache();
}
