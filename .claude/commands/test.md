---
description: 运行测试并生成 JaCoCo 覆盖率报告
parameters:
  - name: module
    type: string
    description: 测试模块（domain/application/infrastructure/interfaces）
    required: false
  - name: coverage
    type: boolean
    default: true
---

# 测试执行命令

## 执行步骤

### 1. 运行测试
```bash
./gradlew test
```

如果指定了模块：
```bash
./gradlew :taotao-cloud-sys-{{module}}:test
```

### 2. 生成覆盖率报告
{% if coverage %}
```bash
./gradlew jacocoTestReport
```
报告位置：`build/reports/jacoco/test/html/index.html`
{% endif %}

### 3. 输出格式
```
总测试数: {{total}}
通过: {{passed}}
失败: {{failed}}
跳过: {{skipped}}
覆盖率: {{coverage}}%
```
