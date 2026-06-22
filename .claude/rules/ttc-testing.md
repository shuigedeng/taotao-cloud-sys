# 测试规范

## 分层测试策略

### 单元测试（domain 层）
- 纯 POJO 测试，无 Spring 上下文
- 测试聚合根行为、值对象验证、领域事件
- 使用 JUnit 5 + AssertJ

```java
@Test
void shouldCreateDictWithItems() {
    DictAgg dict = DictAgg.create("性别", "gender");
    dict.addItem(new DictItem("0", "未知"));
    dict.addItem(new DictItem("1", "男"));
    dict.addItem(new DictItem("2", "女"));

    assertThat(dict.getItems()).hasSize(3);
    assertThat(dict.getDomainEvents())
        .hasAtLeastOneOfType(DictCreatedEvent.class);
}
```

### 集成测试（application 层）
- 使用 `@SpringBootTest`
- 测试完整的用例编排（Application Service → Repository → DB）
- 集成测试放在 `assembly/src/test/`

### 代码质量门禁
- JaCoCo 覆盖率：行覆盖率 ≥ 80%
- Checkstyle + SpotBugs + PMD + Spotless 无违规
- 禁止在测试中使用 `@DirtiesContext`

## 命令
```bash
# 运行所有测试
./gradlew test

# 运行指定模块测试
./gradlew :taotao-cloud-sys-domain:test
./gradlew :taotao-cloud-sys-infrastructure:test

# 带覆盖率
./gradlew test jacocoTestReport
```
