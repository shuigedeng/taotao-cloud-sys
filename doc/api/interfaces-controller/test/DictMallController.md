# 移动端-字典API — 接口详细设计

## 基本信息

| 项 | 值 |
| --- | --- |
| Controller | `DictMallController` |
| 源码文件 | `taotao-cloud-sys-interfaces\src\main\java\com\taotao\cloud\sys\interfaces\controller\test\DictMallController.java` |
| 请求前缀 | `/sys/mall/dict` |
| 接口数量 | 2 |

## 接口列表

| 请求方式 | 接口路径 | 说明 | 认证 |
| --- | --- | --- | --- |
| GET | `/sys/mall/dict/testMybatisQueryStructure` | 测试mybatis sql | @NotAuth |
| POST | `/sys/mall/dict/testMybatisQueryStructuredddd` | 测试mybatis sqldddddd | @NotAuth |

---

## 接口详情

### 测试mybatis sql

| 项 | 值 |
| --- | --- |
| 接口路径 | `/sys/mall/dict/testMybatisQueryStructure` |
| 请求方式 | `GET` |
| 方法签名 | `Result<List<String>> testMybatisQueryStructure(...)` |
| 认证要求 | 免认证 @NotAuth |

#### 入参
无（无入参）

#### 出参
返回类型：`Result<List<String>>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.09 |
| requestId | String | 请求id | traceId 或生成的 id |
| data | T | 数据对象 | 业务数据 |

**data 泛型 `List<String>` 字段明细：**

> 对象 `List<String>` 字段见源码

#### 接口逻辑

- 免鉴权：@NotAuth
- 记录日志 LogUtils.info("asdfasdffffff")
- taskExecutor.execute(...) 异步任务执行（演示线程池）
- 调用 gRPC 服务：dictGrpcServiceStub.findByCode(DictGrpcQuery) 并打印结果
- 返回 Result.success(空 List<String>)

---

### 测试mybatis sqldddddd

| 项 | 值 |
| --- | --- |
| 接口路径 | `/sys/mall/dict/testMybatisQueryStructuredddd` |
| 请求方式 | `POST` |
| 方法签名 | `Result<DictQueryResult> testMybatisQueryStructuredddd(...)` |
| 认证要求 | 免认证 @NotAuth |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| dictQuery | `DictQuery` | @RequestBody |

**`DictQuery` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| dictName | String | 字典名称 | 必填，@NotBlank；@Size(max=10) |
| dictCode | String | 字典编码 | 必填，@NotBlank；@Size(max=10) |
| description | String | 描述 | - |
| dictSort | Integer | 排序值 | - |
| remark | String | 备注信息 | - |

#### 出参
返回类型：`Result<DictQueryResult>`

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| status | String | 状态码 | SUCCESS / FAILURE / PROCESSING / UNKNOWN |
| code | String | 状态值 | 如 0000000 |
| message | String | 消息描述 | 默认"请求成功" |
| timestamp | long | 请求时间 | 毫秒时间戳 |
| version | String | 版本号 | 如 2026.09 |
| requestId | String | 请求id | traceId 或生成的 id |
| data | T | 数据对象 | 业务数据 |

**data 泛型 `DictQueryResult` 字段明细：**

| 字段 | 类型 | 说明 | 约束/备注 |
| --- | --- | --- | --- |
| id | Long | id | - |
| dictName | String | 字典名称 | - |
| dictCode | String | 字典编码 | - |
| description | String | 描述 | - |
| dictSort | Integer | 排序值 | - |
| remark | String | 备注信息 | - |
| createTime | LocalDateTime | 创建时间 | - |
| lastModifiedTime | LocalDateTime | 最后修改时间 | - |

#### 接口逻辑

- 免鉴权：@NotAuth
- 入参：@RequestBody DictQuery dictQuery（dictName/dictCode 必填）
- 返回 Result.success(DictQueryResultBuilder.builder().build())，即空的 DictQueryResult

---
