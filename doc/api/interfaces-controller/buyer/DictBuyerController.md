# 买家端-字典API — 接口详细设计

## 基本信息

| 项 | 值 |
| --- | --- |
| Controller | `DictBuyerController` |
| 源码文件 | `taotao-cloud-sys-interfaces\src\main\java\com\taotao\cloud\sys\interfaces\controller\buyer\DictBuyerController.java` |
| 请求前缀 | `/buyer/sys/dict` |
| 接口数量 | 2 |

## 接口列表

| 请求方式 | 接口路径 | 说明 | 认证 |
| --- | --- | --- | --- |
| GET | `/buyer/sys/dict/query/type` | 通过code查询所有字典列表 | @NotAuth |
| GET | `/buyer/sys/dict/query/code` | 通过code查询所有字典列表 | - |

---

## 接口详情

### 通过code查询所有字典列表

| 项 | 值 |
| --- | --- |
| 接口路径 | `/buyer/sys/dict/query/type` |
| 请求方式 | `GET` |
| 方法签名 | `Result<Void> add(...)` |
| 认证要求 | 免认证 @NotAuth |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| type | `String` | @RequestParam |

#### 出参
返回类型：`Result<Void>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.09 |
| requestId | String | 请求id | traceId 或生成的 id |
| data | T | 数据对象 | 业务数据 |

#### 接口逻辑

- 入参校验：@RequestParam String type（必填）
- 记录日志 LogUtils.info("type:xxxxxxxxx:{}", type)，打印入参 type
- 直接返回 Result.success()（业务逻辑已注释，仅保留日志演示）

---

### 通过code查询所有字典列表

| 项 | 值 |
| --- | --- |
| 接口路径 | `/buyer/sys/dict/query/code` |
| 请求方式 | `GET` |
| 方法签名 | `Result<Boolean> testCode(...)` |
| 认证要求 | 需登录/权限 |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| code | `String` | @RequestParam |

#### 出参
返回类型：`Result<Boolean>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.09 |
| requestId | String | 请求id | traceId 或生成的 id |
| data | T | 数据对象 | 业务数据 |

**data 泛型 `Boolean` 字段明细：**

> 对象 `Boolean` 字段见源码

#### 接口逻辑

- 入参校验：@RequestParam String code（必填）
- 原业务逻辑（按 code 查询字典、发送 Pulsar 消息）已被注释
- 当前方法直接返回 null（未实现），实际调用会得到空响应体

---
