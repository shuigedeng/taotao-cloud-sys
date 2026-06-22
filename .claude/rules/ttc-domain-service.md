# 领域服务设计规范

## 何时使用领域服务

### 适用场景
1. **跨聚合的业务逻辑** — 协调多个聚合的操作
2. **无状态的计算服务** — 不持有状态，只提供行为
3. **外部领域概念** — 不属于任何单一聚合的业务逻辑

### 不适用场景
1. **应属于聚合根的行为**（如 `Order.calculateTotal()`）
2. **纯粹的技术性操作**（如调用 Mapper）
3. **应用层的用例编排**（应由 Application Service 负责）

## 实现规范

### 1. 无状态设计
```java
@DomainService
@Service
public class UserDomainServiceImpl implements UserDomainService {
    private final DeptDomainRepository deptDomainRepository;
    private final RoleDomainRepository roleDomainRepository;

    // 方法不修改自身状态
    public void assignUserRoles(Long userId, List<Long> roleIds) {
        for (Long roleId : roleIds) {
            RoleAgg role = roleDomainRepository.findById(roleId)
                .orElseThrow(() -> new DomainException("角色不存在: " + roleId));
            role.assignUser(userId);
            roleDomainRepository.save(role);
        }
    }
}
```

### 2. 业务语义明确
```java
@DomainService
public interface DeptDomainService {
    void moveDept(Long deptId, Long newParentId);
    boolean isDeptInHierarchy(Long ancestorId, Long deptId);
}
```

### 3. 命名
- 接口：`{Biz}DomainService`（如 `UserDomainService`）
- 实现：`{Biz}DomainServiceImpl`（如 `UserDomainServiceImpl`）
- 统一放在 `domain/service/` 和 `domain/service/impl/`
