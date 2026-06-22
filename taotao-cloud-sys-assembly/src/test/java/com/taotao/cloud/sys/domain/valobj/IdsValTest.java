package com.taotao.cloud.sys.domain.valobj;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * IdsVal 值对象测试
 *
 * <p>测试 {@link IdsVal} 的业务规则：ID 列表的清洗、包含判断、交集运算、不可变性。
 */
class IdsValTest {

	// ==================== of() 静态工厂方法 ====================

	@Test
	void shouldCreateEmptyWhenNullInput() {
		IdsVal val = IdsVal.of(null);
		assertThat(val.isEmpty()).isTrue();
	}

	@Test
	void shouldCreateEmptyWhenEmptyInput() {
		IdsVal val = IdsVal.of(Collections.emptyList());
		assertThat(val.isEmpty()).isTrue();
	}

	@Test
	void shouldFilterNullValues() {
		IdsVal val = IdsVal.of(Arrays.asList(1L, null, 2L));
		assertThat(val.deptIds()).containsExactly(1L, 2L);
	}

	@Test
	void shouldRemoveDuplicates() {
		IdsVal val = IdsVal.of(Arrays.asList(1L, 2L, 1L, 3L));
		assertThat(val.deptIds()).containsExactly(1L, 2L, 3L);
	}

	@Test
	void shouldSortAscending() {
		IdsVal val = IdsVal.of(Arrays.asList(3L, 1L, 2L));
		assertThat(val.deptIds()).containsExactly(1L, 2L, 3L);
	}

	// ==================== empty() ====================

	@Test
	void emptyShouldReturnEmptyInstance() {
		IdsVal val = IdsVal.empty();
		assertThat(val.isEmpty()).isTrue();
		assertThat(val.deptIds()).isEmpty();
	}

	// ==================== contains() ====================

	@Test
	void shouldContainExistingId() {
		IdsVal val = IdsVal.of(Arrays.asList(1L, 2L, 3L));
		assertThat(val.contains(2L)).isTrue();
	}

	@Test
	void shouldNotContainNonExistingId() {
		IdsVal val = IdsVal.of(Arrays.asList(1L, 2L));
		assertThat(val.contains(99L)).isFalse();
	}

	// ==================== isEmpty() ====================

	@Test
	void shouldBeEmptyWhenNoIds() {
		assertThat(IdsVal.empty().isEmpty()).isTrue();
	}

	@Test
	void shouldNotBeEmptyWhenHasIds() {
		assertThat(IdsVal.of(List.of(1L)).isEmpty()).isFalse();
	}

	// ==================== intersect() ====================

	@Test
	void shouldReturnEmptyWhenIntersectWithEmpty() {
		IdsVal a = IdsVal.of(Arrays.asList(1L, 2L));
		IdsVal b = IdsVal.empty();
		assertThat(a.intersect(b).isEmpty()).isTrue();
	}

	@Test
	void shouldReturnCommonIds() {
		IdsVal a = IdsVal.of(Arrays.asList(1L, 2L, 3L, 4L));
		IdsVal b = IdsVal.of(Arrays.asList(3L, 4L, 5L));
		assertThat(a.intersect(b).deptIds()).containsExactly(3L, 4L);
	}

	@Test
	void shouldReturnEmptyWhenNoCommonIds() {
		IdsVal a = IdsVal.of(Arrays.asList(1L, 2L));
		IdsVal b = IdsVal.of(Arrays.asList(3L, 4L));
		assertThat(a.intersect(b).isEmpty()).isTrue();
	}

	// ==================== immutability ====================

	@Test
	void returnedListShouldBeUnmodifiable() {
		IdsVal val = IdsVal.of(List.of(1L, 2L));
		List<Long> list = val.deptIds();
		assertThat(list).isUnmodifiable();
	}

	@Test
	void externalMutationShouldNotAffectValueObject() {
		List<Long> mutableInput = new java.util.ArrayList<>(List.of(1L, 2L));
		IdsVal val = IdsVal.of(mutableInput);
		mutableInput.add(3L);
		assertThat(val.deptIds()).containsExactly(1L, 2L);
	}

	// ==================== toList() ====================

	@Test
	void toListShouldReturnIds() {
		IdsVal val = IdsVal.of(Arrays.asList(2L, 1L));
		assertThat(val.toList()).containsExactly(1L, 2L);
	}
}
