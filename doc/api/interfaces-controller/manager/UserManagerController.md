# 平台管理端-用户API — 接口详细设计

## 基本信息

| 项 | 值 |
| --- | --- |
| Controller | `AdminUserController` |
| 源码文件 | `taotao-cloud-sys-interfaces\src\main\java\com\taotao\cloud\sys\interfaces\controller\manager\UserManagerController.java` |
| 请求前缀 | `/manager/sys/user` |
| 接口数量 | 1 |

## 接口列表

| 请求方式 | 接口路径 | 说明 | 认证 |
| --- | --- | --- | --- |
| POST | `/manager/sys/user/assign-roles` | assignRoles | @NotAuth |

---

## 接口详情

### assignRoles

| 项 | 值 |
| --- | --- |
| 接口路径 | `/manager/sys/user/assign-roles` |
| 请求方式 | `POST` |
| 方法签名 | `Result<Void> assignRoles(...)` |
| 认证要求 | 免认证 @NotAuth |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| assignUserRolesCommand | `AssignRolesCommand` | @Valid, @RequestBody |

**`AssignRolesCommand` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| userId | Long | 用户id | 必填，@NotNull |
| roleIds | List<Long> | 角色id列表 | 必填，@NotEmpty |

> 附加方法：getBizIdRoleIds(): Set<BizId> - 角色id列表转领域 BizId 集合；hasRoleIds(): boolean - 角色id列表是否非空

#### 出参
返回类型：`Result<Void>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.10 |
| requestId | String | 请求id | traceId 或生成的 id |
| data | T | 数据对象 | 业务数据 |

#### 接口逻辑

- 入参校验：@Valid @RequestBody AssignRolesCommand（userId 必填、roleIds 非空）
- 免鉴权：@NotAuth（原 @PreAuthorize 权限注解被注释）
- 调用 UserCommandService.assignRoles(assignUserRolesCommand)
- Service 逻辑（UserCommandServiceImpl）：
-   ① 取出 userId 与 roleIds（转 Set<BizId>）
-   ② 事务内（transactionalWrapper.doInTransaction）
-   ③ userDomainRepository.findUsingIdCol(userId, true) 加载用户聚合
-   ④ roleDomainRepository.findAssignableRoles(roleIds) 查询可分配角色
-   ⑤ userDomainService.assignRoles(userAgg, assignableRoles, roleIds) 领域服务分配角色
-   ⑥ userDomainRepository.save(userAgg, true) 保存聚合
-   ⑦ afterCommit 后 eventDispatcher.dispatchEvents(userAgg) 分发领域事件
-   ⑧ 记录日志"角色分配成功"
- 返回 Result.success()

---
