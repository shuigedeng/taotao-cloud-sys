# 本地开发配置（个人覆盖，不提交）

## 开发环境
- **IDE**: IntelliJ IDEA 2026.1+
- **JDK**: graalvm-jdk-25
- **建模**: PlantUML / Miro

## 个人偏好
- 测试驱动：先写领域层单元测试
- 代码生成：Lombok + MapStruct + Record Builder 三件套
- 调试时开启 SQL 日志（`logging.level.sql=DEBUG`）
- 编码规范遵循项目 Checkstyle + Spotless

## 本地工具配置
```yaml
ddd:
  event-storming:
    output: docs/event-storming/
  aggregate:
    max-size: 10
```
