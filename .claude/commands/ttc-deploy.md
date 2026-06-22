---
description: 部署应用到指定环境（dev/test/pre/pro）
parameters:
  - name: environment
    type: string
    description: 部署环境
    enum: [dev, test, pre, pro]
    required: true
---

# 部署命令

## 执行步骤

### 1. 运行测试
```bash
./gradlew test
```
测试失败则中止部署。

### 2. 打包
```bash
./gradlew :taotao-cloud-sys-assembly:bootJar
```

### 3. 启动（指定环境）
```bash
java --enable-preview \
  -jar taotao-cloud-sys-assembly/build/libs/taotao-cloud-sys-assembly-*.jar \
  --spring.profiles.active={{environment}}
```

### 4. 健康检查
```bash
curl -f http://localhost:8080/actuator/health
```

## 输出格式
```
环境: {{environment}}
JAR: taotao-cloud-sys-assembly-*.jar
健康检查: PASS/FAIL
```
