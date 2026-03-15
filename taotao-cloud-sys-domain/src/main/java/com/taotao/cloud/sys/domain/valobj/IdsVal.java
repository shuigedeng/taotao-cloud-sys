package com.taotao.cloud.sys.domain.valobj;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record IdsVal(List<Long> deptIds) {

	// 静态工厂方法（带校验）
	public static IdsVal of( List<Long> deptIds ) {
		if (deptIds == null || deptIds.isEmpty()) {
			return new IdsVal(Collections.emptyList());
		}

		// 过滤 null、去重、排序（根据业务需求）
		List<Long> cleaned = deptIds.stream()
			.filter(Objects::nonNull)
			.distinct()
			.sorted()
			.collect(Collectors.toList());

		return new IdsVal(cleaned);
	}

	// 空作用域
	public static IdsVal empty() {
		return new IdsVal(Collections.emptyList());
	}

	// 业务方法：是否包含某部门
	public boolean contains( Long deptId ) {
		return deptIds.contains(deptId);
	}

	// 业务方法：是否为空
	public boolean isEmpty() {
		return deptIds.isEmpty();
	}

	// 业务方法：交集（用于权限计算）
	public IdsVal intersect( IdsVal other ) {
		if (this.isEmpty() || other.isEmpty()) {
			return empty();
		}
		List<Long> common = this.deptIds.stream()
			.filter(other.deptIds::contains)
			.collect(Collectors.toList());
		return of(common);
	}

	// 获取不可变列表（防止外部修改）
	@Override
	public List<Long> deptIds() {
		return Collections.unmodifiableList(deptIds);
	}

	// 用于持久化（MyBatis/JPA 可直接使用）
	public List<Long> toList() {
		return deptIds();
	}
}
