# 架构规范 — DDD 六边形架构

## 分层职责与依赖方向

```
api  ←  interfaces  ←  application  →  facade
                          ↓
                     domain  ←  infrastructure
```

### domain 层（核心）
- **职责**：纯业务逻辑，聚合根/实体/值对象/领域事件/仓储接口/领域服务
- **禁止**：依赖 Spring、数据库、任何框架注解
- **允许依赖**：JDK 标准库、项目 common 模块（枚举/常量）
- **包路径**：`com.taotao.cloud.sys.domain.{aggregate|entity|valobj|event|repository|service}`

### application 层（编排）
- **职责**：用例编排、事务管理、DTO 转换、事件发布
- **命令服务**：`service/commad/` — `@Transactional` 写操作
- **查询服务**：`service/query/` — `@Transactional(readOnly = true)` 读操作
- **禁止**：业务规则判断、直接调用 Mapper/Repository
- **查询仓储**：在 `repository/` 包定义接口，infrastructure 层实现

### infrastructure 层（实现）
- **职责**：持久化实现、消息收发、事件订阅、技术配置
- **领域仓储实现**：`repository/domain/` — 实现 domain 层的仓储接口
- **查询仓储实现**：`repository/aplication/` — 实现 application 层的查询仓储接口
- **PO 定义**：`persistent/persistence/{biz}/{Name}PO.java`
- **Mapper**：`persistent/mapper/` — Mybatis-Plus Mapper 接口

### interfaces 层（入站适配）
- **职责**：HTTP 请求解析、参数校验、响应封装
- **Controller**：按端分包 `manager/seller/buyer/inner`
- **RPC**：`rpc/` — Dubbo 服务实现
- **gRPC**：`grpc/` — gRPC 服务实现
- **禁止**：业务逻辑、直接调用 Repository/Infrastructure

### api 层（接口契约）
- **职责**：纯接口定义 + DTO
- **RPC 接口**：`rpc/query/`、`rpc/command/`
- **内部 Feign**：`inner/query/`、`inner/command/`
- **Protobuf**：`proto/`

### facade 层（出站适配/ACL）
- **职责**：外部服务防腐层，按外部服务分包
- **结构**：`{biz}/acl/` + `{biz}/invoker/` + `{biz}/interceptor/`

### common 层（共享）
- **职责**：枚举、常量、属性配置、设置项、工具类
- **注意**：所有模块可依赖，但避免引入业务逻辑

### assembly 层（启动）
- **职责**：`@TaoTaoBootApplication` 启动类 + 环境配置 + Docker/K8s
- **测试**：集成测试放在 `src/test/`

## 禁止违反的依赖
```java
// ❌ 禁止：Controller 直接注入 Repository
@Autowired private DictRepository dictRepository;

// ❌ 禁止：Application Service 直接调用 Mapper
@Autowired private DictMapper dictMapper;

// ❌ 禁止：Domain Service 注入 Repository
@Autowired private DictDomainRepository dictDomainRepository;

// ✅ 正确：Application Service 注入领域仓储接口
private final DictDomainRepository dictDomainRepository;

// ✅ 正确：Application Service 注入查询仓储接口
private final DictQueryRepository dictQueryRepository;
```
