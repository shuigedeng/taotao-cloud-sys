---
description: DDD 代码审查 — 检查领域模型、架构合规、代码质量
parameters:
  - name: scope
    type: string
    enum: [controller, service, domain, all]
    default: all
---

# 代码审查命令

## 审查范围：{{scope}}

### 检查清单

#### DDD 合规
- [ ] 聚合根命名以 Agg 后缀
- [ ] 值对象不可变（final、无 setter）
- [ ] 跨聚合通过 ID 引用
- [ ] 领域事件在聚合内 registerEvent()

#### 架构合规
- [ ] domain 无外部依赖（不依赖 Spring、数据库）
- [ ] Controller 不含业务逻辑
- [ ] Application Service 不含业务规则判断
- [ ] 依赖方向正确

#### 代码质量
- [ ] 无 N+1 查询
- [ ] 方法长度 ≤ 50 行
- [ ] 无空 catch 块

## 输出格式
```markdown
### 🔴 严重
### 🟡 警告
### 🟢 建议
### ✅ 通过项
```
