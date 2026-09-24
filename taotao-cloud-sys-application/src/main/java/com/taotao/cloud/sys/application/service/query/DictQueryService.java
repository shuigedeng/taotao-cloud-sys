package com.taotao.cloud.sys.application.service.query;

import com.taotao.boot.ddd.model.application.service.QueryService;
import com.taotao.cloud.sys.application.dto.dict.result.DictQueryResult;

public interface DictQueryService extends QueryService {

    void queryForUpdate();

	DictQueryResult queryByCode(String code);
}
