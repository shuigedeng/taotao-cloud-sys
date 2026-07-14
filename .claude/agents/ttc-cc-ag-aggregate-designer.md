---
name: ttc-cc-ag-aggregate-designer
description: 聚合设计专家，负责设计 DDD 聚合根
tools:
  - read
  - write
  - edit
---

# 聚合设计代理

## 设计流程

### 1. 识别聚合边界
根据业务一致性要求划分聚合：
- 什么操作必须在一个事务内完成？
- 什么可以异步最终一致？

### 2. 设计聚合根
```java
// 聚合根命名以 Agg 后缀
public class DictAgg {
    private Long id;
    private String dictName;
    private String dictCode;
    private EnabledEnum status;
    private String remark;
    private List<DictItem> items;  // 聚合内实体

    // 工厂方法
    public static DictAgg create(String dictName, String dictCode) { ... }

    // 行为方法
    public void addItem(DictItem item) { ... }
    public void disable() { ... }
}
```

### 3. 设计仓储接口（在 domain 层）
```java
public interface DictDomainRepository {
    Optional<DictAgg> findById(Long id);
    DictAgg save(DictAgg dict);
    void deleteById(Long id);
    boolean existsByDictCode(String dictCode);
}
```

### 4. sys 领域已知聚合清单
| 聚合 | 实体 | 说明 |
|------|------|------|
| DictAgg | DictItem | 字典 |
| UserAgg | — | 用户 |
| RoleAgg | — | 角色 |
| PermissionAgg | — | 权限 |
| DeptAgg | — | 部门 |
| OrgAgg | — | 组织 |
| PositionAgg | — | 岗位 |
| FileAgg | FileLogAgg | 文件 |
