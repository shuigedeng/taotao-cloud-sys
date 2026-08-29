# 平台管理端-字典API — 接口详细设计

## 基本信息

| 项 | 值 |
| --- | --- |
| Controller | `AdminDictController` |
| 源码文件 | `taotao-cloud-sys-interfaces\src\main\java\com\taotao\cloud\sys\interfaces\controller\manager\DictManagerController.java` |
| 请求前缀 | `/manager/sys/dict` |
| 接口数量 | 1 |

## 接口列表

| 请求方式 | 接口路径 | 说明 | 认证 |
| --- | --- | --- | --- |
| GET | `/manager/sys/dict/query/for-update` | queryForUpdate | @NotAuth |

---

## 接口详情

### queryForUpdate

| 项 | 值 |
| --- | --- |
| 接口路径 | `/manager/sys/dict/query/for-update` |
| 请求方式 | `GET` |
| 方法签名 | `Result<Void> queryForUpdate(...)` |
| 认证要求 | 免认证 @NotAuth |

#### 入参
无（无入参）

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

- 免鉴权：@NotAuth
- 调用 DictQueryService.queryForUpdate()
- 链路：DictQueryServiceImpl -> DictQueryRepository.queryForUpdate()（数据库行锁更新演示）
- 返回 Result.success()

---
