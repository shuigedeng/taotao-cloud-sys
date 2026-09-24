package com.taotao.cloud.sys.application.service.query.impl;

import com.taotao.cloud.sys.application.adapter.DictQueryPort;
import com.taotao.cloud.sys.application.dto.dict.result.DictQueryResult;
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
	private final DictQueryPort dictQueryPort;

	@Override
	public void queryForUpdate() {
		dictQueryPort.queryForUpdate();
	}

	@Override
	public DictQueryResult queryByCode(String code) {
		return dictQueryPort.queryByCode(code);
	}
}
