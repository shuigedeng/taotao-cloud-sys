# 平台管理端-TestRediscontroller — 接口详细设计

## 基本信息

| 项 | 值 |
| --- | --- |
| Controller | `AdminTestRedisController` |
| 源码文件 | `taotao-cloud-sys-interfaces\src\main\java\com\taotao\cloud\sys\interfaces\controller\manager\TestRedisManagerController.java` |
| 请求前缀 | `(无类级前缀)` |
| 接口数量 | 3 |

## 接口列表

| 请求方式 | 接口路径 | 说明 | 认证 |
| --- | --- | --- | --- |
| GET | `/user/{id}` | getUserById | - |
| GET | `/getUserById1/{id}` | getUserById1 | - |
| GET | `/getUserById2/{id}` | getUserById2 | - |

---

## 接口详情

### getUserById

| 项 | 值 |
| --- | --- |
| 接口路径 | `/user/{id}` |
| 请求方式 | `GET` |
| 方法签名 | `User getUserById(...)` |
| 认证要求 | 需登录/权限 |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| id | `Long` | @PathVariable |

#### 出参
返回类型：`User`

> 对象 `User`（内部静态类（测试用，无字段））：无字段

#### 接口逻辑

- Redis 缓存穿透示例
- ① 布隆过滤器判断（已注释）：BloomFilterUtil.mightContain(id) 不存在则直接返回 null
- ② 查询缓存：redisTemplate.opsForValue().get("user_" + id)
- ③ 缓存未命中则查数据库（已注释），命中后写缓存 300 秒
- ④ 查询结果为空时记录请求并加入布隆过滤器（已注释）
- ⑤ 返回 user（当前数据库查询被注释，恒为 null）

---

### getUserById1

| 项 | 值 |
| --- | --- |
| 接口路径 | `/getUserById1/{id}` |
| 请求方式 | `GET` |
| 方法签名 | `User getUserById1(...)` |
| 认证要求 | 需登录/权限 |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| id | `Long` | @PathVariable |

#### 出参
返回类型：`User`

> 对象 `User`（内部静态类（测试用，无字段））：无字段

#### 接口逻辑

- Redis 缓存击穿示例
- ① 查询缓存 "user_" + id
- ② 缓存未命中则加分布式锁：setIfAbsent("lock_user_" + id, uuid, 60秒)
- ③ 加锁成功则查数据库（已注释），命中后写缓存 300 秒
- ④ finally 中释放锁：锁值一致时 delete(lockKey)
- ⑤ 返回 user（当前数据库查询被注释，恒为 null）

---

### getUserById2

| 项 | 值 |
| --- | --- |
| 接口路径 | `/getUserById2/{id}` |
| 请求方式 | `GET` |
| 方法签名 | `User getUserById2(...)` |
| 认证要求 | 需登录/权限 |

#### 入参
| 参数 | 类型 | 注解 |
| --- | --- | --- |
| id | `Long` | @PathVariable |

#### 出参
返回类型：`User`

> 对象 `User`（内部静态类（测试用，无字段））：无字段

#### 接口逻辑

- Redis 缓存雪崩示例
- 声明 @Cacheable(value="userCache", key="#id") 使用 Spring 缓存（二级缓存演示）
- 原 Ehcache + Redis 多级缓存逻辑被注释
- 当前直接返回 null

---
