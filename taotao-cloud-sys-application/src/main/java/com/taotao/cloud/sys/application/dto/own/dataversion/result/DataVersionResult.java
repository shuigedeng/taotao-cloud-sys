package com.taotao.cloud.sys.application.dto.own.dataversion.result;

import com.taotao.boot.common.model.ddd.types.MarkerResult;
import io.soabase.recordbuilder.core.RecordBuilder;

/**
 * DataVersionResult
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@RecordBuilder
public record DataVersionResult() implements MarkerResult {}
