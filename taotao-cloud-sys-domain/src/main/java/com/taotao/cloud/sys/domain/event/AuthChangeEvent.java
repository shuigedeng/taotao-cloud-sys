package com.taotao.cloud.sys.domain.event;

import com.taotao.boot.ddd.model.domain.event.DomainEvent;
import com.taotao.boot.ddd.model.val.BizId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AuthChangeEvent 类
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2026/3/14
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@Schema(name = "AuthChangeEvent", description = "操作日志事件")
public class AuthChangeEvent extends DomainEvent<BizId> {

	private final List<BizId> roleIds;

	public AuthChangeEvent(BizId aggregateId, List<BizId> roleIds) {
		this.setAggregateId(Objects.requireNonNull(aggregateId));
		this.roleIds = new ArrayList<>(roleIds); // 防御拷贝
	}
}
