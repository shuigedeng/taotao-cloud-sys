# 值对象设计规范

## 核心特性

### 1. 不可变性
```java
public final class IdsVal {
    private final List<Long> ids;

    public IdsVal(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new DomainException("ID列表不能为空");
        }
        this.ids = Collections.unmodifiableList(ids);
    }

    public List<Long> getIds() {
        return ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdsVal that)) return false;
        return Objects.equals(ids, that.ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ids);
    }
}
```

### 2. 自验证
值对象在构造时必须验证自身有效性：
```java
public class Money {
    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("金额不能为负数");
        }
        if (currency == null || currency.isBlank()) {
            throw new DomainException("货币不能为空");
        }
        this.amount = amount;
        this.currency = currency;
    }
}
```

### 3. 业务行为
值对象可以包含业务行为（但不能引用仓储或外部服务）：
```java
public class IdPathVal {
    private final String path;

    public IdPathVal(String path) {
        if (path == null || path.isBlank()) {
            throw new DomainException("路径不能为空");
        }
        this.path = path;
    }

    public boolean isRoot() {
        return "0".equals(path);
    }

    public String parentPath() {
        int lastIndex = path.lastIndexOf(",");
        return lastIndex > 0 ? path.substring(0, lastIndex) : "0";
    }

    public String childPath(Long childId) {
        return path + "," + childId;
    }
}
```

### 4. 位置
- 值对象统一放在 `domain/valobj/` 包下
- 使用 Java `record` 或 `final class` 实现
