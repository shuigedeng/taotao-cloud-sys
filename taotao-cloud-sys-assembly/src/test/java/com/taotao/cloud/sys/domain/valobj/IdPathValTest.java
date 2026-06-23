package com.taotao.cloud.sys.domain.valobj;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * IdPathVal 值对象测试
 *
 * <p>测试 {@link IdPathVal} 的业务规则：路径解析、父子关系、包含判断、合法性校验。
 */
class IdPathValTest {

	// ==================== of(String) 静态工厂方法 ====================

	@Test
	void shouldCreateEmptyWhenNullPath() {
		IdPathVal val = IdPathVal.of(null);
		assertThat(val.path()).isEmpty();
	}

	@Test
	void shouldCreateEmptyWhenBlankPath() {
		IdPathVal val = IdPathVal.of("  ");
		assertThat(val.path()).isEmpty();
	}

	@Test
	void shouldParseValidPath() {
		IdPathVal val = IdPathVal.of("1,2,5");
		assertThat(val.path()).isEqualTo("1,2,5");
	}

	@ParameterizedTest
	@ValueSource(strings = {"abc", "1,2,a"})
	void shouldThrowOnInvalidPath(String invalidPath) {
		assertThatThrownBy(() -> IdPathVal.of(invalidPath))
			.isInstanceOf(IllegalArgumentException.class);
	}

	// ==================== of(List<Long>) ====================

	@Test
	void shouldCreateFromList() {
		IdPathVal val = IdPathVal.of(List.of(1L, 2L, 5L));
		assertThat(val.path()).isEqualTo("1,2,5");
	}

	@Test
	void shouldCreateEmptyFromEmptyList() {
		IdPathVal val = IdPathVal.of(List.of());
		assertThat(val.path()).isEmpty();
	}

	// ==================== ids() ====================

	@Test
	void shouldConvertPathToIds() {
		IdPathVal val = IdPathVal.of("1,2,5");
		assertThat(val.ids()).containsExactly(1L, 2L, 5L);
	}

	@Test
	void shouldReturnEmptyListForEmptyPath() {
		IdPathVal val = IdPathVal.of("");
		assertThat(val.ids()).isEmpty();
	}

	// ==================== parent() ====================

	@Test
	void shouldReturnEmptyParentForSingleId() {
		IdPathVal val = IdPathVal.of("5");
		assertThat(val.parent().path()).isEmpty();
	}

	@Test
	void shouldReturnParentPath() {
		IdPathVal val = IdPathVal.of("1,2,5");
		assertThat(val.parent().path()).isEqualTo("1,2");
	}

	@Test
	void shouldReturnEmptyParentForEmptyPath() {
		IdPathVal val = IdPathVal.of("");
		assertThat(val.parent().path()).isEmpty();
	}

	// ==================== isRoot() ====================

	@Test
	void singleIdShouldBeRoot() {
		IdPathVal val = IdPathVal.of("5");
		assertThat(val.isRoot()).isTrue();
	}

	@Test
	void multipleIdsShouldNotBeRoot() {
		IdPathVal val = IdPathVal.of("1,5");
		assertThat(val.isRoot()).isFalse();
	}

	// ==================== contains() ====================

	@Test
	void shouldContainExistingTargetId() {
		IdPathVal val = IdPathVal.of("1,2,5");
		assertThat(val.contains(2L)).isTrue();
	}

	@Test
	void shouldNotContainNonExistingTargetId() {
		IdPathVal val = IdPathVal.of("1,2,5");
		assertThat(val.contains(99L)).isFalse();
	}

	@Test
	void shouldReturnFalseForNullTargetId() {
		IdPathVal val = IdPathVal.of("1,2,5");
		assertThat(val.contains(null)).isFalse();
	}

	@Test
	void emptyPathShouldNotContainAnything() {
		IdPathVal val = IdPathVal.of("");
		assertThat(val.contains(1L)).isFalse();
	}

	// ==================== getValue() ====================

	@Test
	void getValueShouldReturnRawPath() {
		IdPathVal val = IdPathVal.of("1,2,5");
		assertThat(val.getValue()).isEqualTo("1,2,5");
	}
}
