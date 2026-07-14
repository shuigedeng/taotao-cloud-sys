---
description: 生成 OpenAPI / Swagger 文档
---

# API 文档生成

## 执行步骤

### 1. 启动服务
```bash
./gradlew :taotao-cloud-sys-assembly:bootRun --args='--spring.profiles.active=dev'
```

### 2. 访问文档
- Knife4j UI：`http://localhost:8080/doc.html`
- OpenAPI JSON：`http://localhost:8080/v3/api-docs`

### 3. 检查完整性
- Controller 是否有 `@Tag` 注解
- 接口方法是否有 `@Operation` 注解
- DTO 字段是否有 `@Schema` 注解
