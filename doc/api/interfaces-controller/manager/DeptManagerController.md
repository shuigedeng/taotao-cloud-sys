# 平台管理端-部门API — 接口详细设计

## 基本信息

| 项 | 值 |
| --- | --- |
| Controller | `AdminDeptController` |
| 源码文件 | `taotao-cloud-sys-interfaces\src\main\java\com\taotao\cloud\sys\interfaces\controller\manager\DeptManagerController.java` |
| 请求前缀 | `/manager/sys/dept` |
| 接口数量 | 1 |

## 接口列表

| 请求方式 | 接口路径 | 说明 | 认证 |
| --- | --- | --- | --- |
| GET | `/manager/sys/dept/query/tree` | 获取部门树 | - |

---

## 接口详情

### 获取部门树

| 项 | 值 |
| --- | --- |
| 接口路径 | `/manager/sys/dept/query/tree` |
| 请求方式 | `GET` |
| 方法签名 | `Result<List<DeptTreeResult>> tree(...)` |
| 认证要求 | 需登录/权限 |

#### 入参
无（无入参）

#### 出参
返回类型：`Result<List<DeptTreeResult>>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.10 |
| requestId | String | 请求id | traceId 或生成的 id |
| data | T | 数据对象 | 业务数据 |

**data 泛型 `List<DeptTreeResult>` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| id | Long | 主键ID | 继承自 MapperNode |
| parentId | Long | 父节点ID | 继承自 MapperNode |
| children | List<INode> | 子孙节点 | 继承自 MapperNode |
| hasChildren | Boolean | 是否有子孙节点 | 继承自 MapperNode |
| name | String | 部门名称 | - |
| sort | Integer | 排序 | 继承自 MapperNode |
| isDeleted | String | 是否删除 | - |
| tenantId | Long | 租户ID | - |
| remark | String | 备注 | - |
| createTime | LocalDateTime | 创建时间 | - |
| updateTime | LocalDateTime | 修改时间 | - |

#### 接口逻辑

- 调用 DeptCommandService.tree() 查询全部部门（当前实现返回空列表）
- 使用 ForestNodeMerger.merge(...) 将部门列表合并为森林结构（树形）
- 返回 Result.success(deptTreeResults) 包装部门树

---
