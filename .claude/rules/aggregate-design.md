# 聚合设计规范

## 聚合识别原则

### 1. 聚合边界
- 聚合内修改必须在一个事务中完成
- 聚合间使用事件驱动最终一致性
- 聚合根命名以 `Agg` 后缀（`DictAgg`, `UserAgg`, `DeptAgg`）

### 2. 跨聚合引用
- 跨聚合通过 ID 引用（Long/Long 值），非对象引用

```java
// ✅ 正确：跨聚合用 ID
public class DictAgg {
    private Long parentId;  // 父级字典 ID
    private List<DictItem> items;  // 聚合内实体用对象引用
}

// ❌ 错误：跨聚合用对象引用
public class DictAgg {
    private DictAgg parent;  // 不应直接引用其他聚合根
}
```

### 3. 小聚合原则
- 一个聚合根只包含必要的实体，通常 1-3 个
- 避免加载过多数据

### 4. 行为方法（非贫血模型）
```java
public class DictAgg {
    // 命令方法：有业务语义
    public void addItem(DictItem item) { ... }
    public void disable() { ... }
    public void updateName(String name) { ... }

    // 禁止 setter 风格
    // public void setStatus(Integer status) { ... }
}
```

### 5. 工厂方法
```java
public class DictAgg {
    // 无参构造（JPA 要求），protected
    protected DictAgg() {}

    // 静态工厂方法
    public static DictAgg create(String dictName, String dictCode) {
        DictAgg agg = new DictAgg();
        agg.dictName = dictName;
        agg.dictCode = dictCode;
        agg.status = EnabledEnum.ENABLED;
        agg.registerEvent(new DictCreatedEvent(agg.id));
        return agg;
    }
}
```

### 6. 不变性维护
```java
public class DictAgg {
    public void addItem(DictItem item) {
        // 不变性：字典必须启用才能添加条目
        if (!isEnabled()) {
            throw new DomainException("已禁用的字典不能添加条目");
        }
        // 不变性：编码唯一
        if (hasItemCode(item.getItemCode())) {
            throw new DomainException("字典条目编码已存在");
        }
        items.add(item);
    }
}
```
