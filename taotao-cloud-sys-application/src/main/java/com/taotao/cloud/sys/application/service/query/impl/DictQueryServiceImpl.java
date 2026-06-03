package com.taotao.cloud.sys.application.service.query.impl;

import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.sys.application.repository.DictQueryRepository;
import com.taotao.cloud.sys.application.service.query.AppQueryService;
import com.taotao.cloud.sys.application.service.query.DictQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * AppQueryServiceImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Service
@RequiredArgsConstructor
public class DictQueryServiceImpl implements DictQueryService {
private final DictQueryRepository dictQueryRepository;
	@Override
	public void queryForUpdate() {
		dictQueryRepository.queryForUpdate();
	}
}
