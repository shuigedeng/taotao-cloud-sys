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

package com.taotao.cloud.sys.application.service.impl;

import com.taotao.boot.common.enums.ResultEnum;
import com.taotao.boot.common.exception.BusinessException;
import com.taotao.boot.webagg.service.impl.BaseSuperServiceImpl;
import com.taotao.cloud.sys.application.service.LogisticsService;
import com.taotao.cloud.sys.infrastructure.persistent.mapper.LogisticsMapper;
import com.taotao.cloud.sys.infrastructure.persistent.persistence.config.LogisticsConfigPO;
import com.taotao.cloud.sys.infrastructure.persistent.repository.cls.LogisticsRepository;
import com.taotao.cloud.sys.infrastructure.persistent.repository.inf.ILogisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/11/13 10:00
 */
@Service
@AllArgsConstructor
public class LogisticsServiceImpl
        implements LogisticsService {

	@Override
	public LogisticsConfigPO findLogisticsById(Long id) {
		return null;
	}

//    @Override
//    public LogisticsConfig findLogisticsById(Long id) {
//        Optional<LogisticsConfig> optionalExpressCompany = ir().findById(id);
//        return optionalExpressCompany.orElseThrow(() -> new BusinessException(ResultEnum.FILE_NOT_EXIST));
//    }
}
