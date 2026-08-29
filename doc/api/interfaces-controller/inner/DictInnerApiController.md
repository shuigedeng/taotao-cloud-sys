# 内部端-字典API — 接口详细设计

## 基本信息

| 项 | 值 |
| --- | --- |
| Controller | `InnerDictController` |
| 源码文件 | `taotao-cloud-sys-interfaces\src\main\java\com\taotao\cloud\sys\interfaces\controller\inner\DictInnerApiController.java` |
| 请求前缀 | `(无类级前缀)` |
| 实现接口 | `DictCommandApi`, `DictQueryApi` |
| 接口数量 | 4 |

## 接口列表

| 请求方式 | 接口路径 | 说明 | 认证 |
| --- | --- | --- | --- |
| POST | `/inner/sys/dict/command/save` | 添加部门 | - |
| POST | `/inner/sys/dict/command/test` | 测试部门 | @NotAuth |
| POST | `/inner/sys/dict/query/code` | 根据code查询 | - |
| POST | `/inner/sys/dict/query/test` | 测试测试 | - |

---

## 接口详情

### 添加部门

| 项 | 值 |
| --- | --- |
| 接口路径 | `/inner/sys/dict/command/save` |
| 请求方式 | `POST` |
| 方法签名 | `Response<DictQueryApiResponse> create(...)` |
| 认证要求 | 需登录/权限 |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| dictQueryApiRequest | `Request<DictApiQuery>` | @Validated, @RequestBody |

**`Request` 包装字段：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| version | String | 版本号 | 如 2026.09 |
| requestNo | String | 请求No | 自动生成 |
| orderNo | String | 订单No | 必填 @NotBlank |
| bizNo | String | 业务No | 必填 @NotBlank |
| order | T | 请求参数 | 业务请求体，必填 @NotNull |

**请求体 `DictApiQuery` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| code | String | 字典编码（Schema 注释为"租户id"） | - |
| id | Long | id（Schema 注释为"租户密钥"） | - |

#### 出参
返回类型：`Response<DictQueryApiResponse>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.09 |
| responseNo | String | 响应No | 自动生成 |
| result | T | 数据对象 | 业务结果 |

**result 泛型 `DictQueryApiResponse` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| id | Long | id | - |
| dictName | String | 字典名称 | - |
| dictCode | String | 字典编码 | - |
| description | String | 描述 | - |
| sortNum | Integer | 排序值 | - |
| remark | String | 备注信息 | - |

#### 接口逻辑

- 入参校验：@Validated @RequestBody Request<DictApiQuery>（bizNo/orderNo/order 必填）
- 幂等控制：@Idempotent(perFix="findByCode")；限流：@Limit(period=10, count=3)；Sentinel：@SentinelResource("findByCode")
- 业务校验：若 dictQueryApiRequest.getBizNo() == "sd"，抛出 BusinessException("我出错了")
- 记录日志 LogUtils.info("xxxxxxxxxxxxxxxxxxxxx")
- 返回 Response.from(DictQueryApiResponseBuilder.builder().build())，即空的 DictQueryApiResponse（查询逻辑被注释）

---

### 测试部门

| 项 | 值 |
| --- | --- |
| 接口路径 | `/inner/sys/dict/command/test` |
| 请求方式 | `POST` |
| 方法签名 | `Response<DictQueryApiResponse> test(...)` |
| 认证要求 | 免认证 @NotAuth |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| dictQueryApiRequest | `Request<DictApiQuery>` | @Validated, @RequestBody |

**`Request` 包装字段：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| version | String | 版本号 | 如 2026.09 |
| requestNo | String | 请求No | 自动生成 |
| orderNo | String | 订单No | 必填 @NotBlank |
| bizNo | String | 业务No | 必填 @NotBlank |
| order | T | 请求参数 | 业务请求体，必填 @NotNull |

**请求体 `DictApiQuery` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| code | String | 字典编码（Schema 注释为"租户id"） | - |
| id | Long | id（Schema 注释为"租户密钥"） | - |

#### 出参
返回类型：`Response<DictQueryApiResponse>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.09 |
| responseNo | String | 响应No | 自动生成 |
| result | T | 数据对象 | 业务结果 |

**result 泛型 `DictQueryApiResponse` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| id | Long | id | - |
| dictName | String | 字典名称 | - |
| dictCode | String | 字典编码 | - |
| description | String | 描述 | - |
| sortNum | Integer | 排序值 | - |
| remark | String | 备注信息 | - |

#### 接口逻辑

- 入参校验：@Validated @RequestBody Request<DictApiQuery>
- 免鉴权：@NotAuth；幂等：@Idempotent(perFix="test")；限流：@Limit + @GuavaLimit；Sentinel：@SentinelResource("test")
- 记录日志 LogUtils.info("sldfkslfdjalsdfkjalsfdjl")
- 原查询/异步调用逻辑被注释，当前直接返回 null

---

### 根据code查询

| 项 | 值 |
| --- | --- |
| 接口路径 | `/inner/sys/dict/query/code` |
| 请求方式 | `POST` |
| 方法签名 | `Response<DictQueryApiResponse> queryByCode(...)` |
| 认证要求 | 需登录/权限 |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| dictQueryApiRequest | `Request<DictApiQuery>` | @Validated, @RequestBody |

**`Request` 包装字段：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| version | String | 版本号 | 如 2026.09 |
| requestNo | String | 请求No | 自动生成 |
| orderNo | String | 订单No | 必填 @NotBlank |
| bizNo | String | 业务No | 必填 @NotBlank |
| order | T | 请求参数 | 业务请求体，必填 @NotNull |

**请求体 `DictApiQuery` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| code | String | 字典编码（Schema 注释为"租户id"） | - |
| id | Long | id（Schema 注释为"租户密钥"） | - |

#### 出参
返回类型：`Response<DictQueryApiResponse>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.09 |
| responseNo | String | 响应No | 自动生成 |
| result | T | 数据对象 | 业务结果 |

**result 泛型 `DictQueryApiResponse` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| id | Long | id | - |
| dictName | String | 字典名称 | - |
| dictCode | String | 字典编码 | - |
| description | String | 描述 | - |
| sortNum | Integer | 排序值 | - |
| remark | String | 备注信息 | - |

#### 接口逻辑

- 入参校验：@Validated @RequestBody Request<DictApiQuery>
- 当前未实现，直接返回 null

---

### 测试测试

| 项 | 值 |
| --- | --- |
| 接口路径 | `/inner/sys/dict/query/test` |
| 请求方式 | `POST` |
| 方法签名 | `Response<DictQueryApiResponse> queryTest(...)` |
| 认证要求 | 需登录/权限 |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| dictQueryApiRequest | `Request<DictApiQuery>` | @Validated, @RequestBody |

**`Request` 包装字段：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| version | String | 版本号 | 如 2026.09 |
| requestNo | String | 请求No | 自动生成 |
| orderNo | String | 订单No | 必填 @NotBlank |
| bizNo | String | 业务No | 必填 @NotBlank |
| order | T | 请求参数 | 业务请求体，必填 @NotNull |

**请求体 `DictApiQuery` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| code | String | 字典编码（Schema 注释为"租户id"） | - |
| id | Long | id（Schema 注释为"租户密钥"） | - |

#### 出参
返回类型：`Response<DictQueryApiResponse>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.09 |
| responseNo | String | 响应No | 自动生成 |
| result | T | 数据对象 | 业务结果 |

**result 泛型 `DictQueryApiResponse` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| id | Long | id | - |
| dictName | String | 字典名称 | - |
| dictCode | String | 字典编码 | - |
| description | String | 描述 | - |
| sortNum | Integer | 排序值 | - |
| remark | String | 备注信息 | - |

#### 接口逻辑

- 入参校验：@Validated @RequestBody Request<DictApiQuery>
- 当前未实现，直接返回 null

---
