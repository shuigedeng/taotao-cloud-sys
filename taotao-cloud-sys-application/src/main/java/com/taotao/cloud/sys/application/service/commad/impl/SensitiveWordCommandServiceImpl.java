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

import com.taotao.cloud.sys.application.service.commad.SensitiveWordCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** 敏感词业务层实现 */
@Service
@RequiredArgsConstructor
public class SensitiveWordCommandServiceImpl implements SensitiveWordCommandService {

    @Override
    public void resetCache() {}

    //    private final RedisRepository redisRepository;
    //
    //    @Override
    //    public void resetCache() {
    //        List<SensitiveWord> sensitiveWordsList = this.list();
    //        if (sensitiveWordsList == null || sensitiveWordsList.isEmpty()) {
    //            return;
    //        }
    //		new SensitiveWord()
    //			.setSensitiveWord("afd")
    //			.setCreateBy(1L)
    //			.setDelFlag(false)
    //			.setId(1L);
    //        List<String> sensitiveWords =
    //                sensitiveWordsList
    //					.stream()
    //					.map(SensitiveWord::getSensitiveWord)
    //					.toList();
    //
    //        redisRepository.set(RedisConstant.SENSITIVE_WORDS_KEY, sensitiveWords);
    //    }
}
