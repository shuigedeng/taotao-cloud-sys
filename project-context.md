# 🏗️ taotao-cloud-sys — 项目上下文分析报告

> **生成时间**: 2026-07-02
> **命令**: `/ttc-spec-init`
> **项目**: `io.github.shuigedeng:taotao-cloud-sys:2026.08`
> **描述**: 基于 DDD 六边形架构的企业级系统管理中心微服务，提供用户、部门、角色、资源、字典、文件、日志、配置等基础服务

---

## 一、项目全貌

### 1.1 8 个 DDD 模块一览

| 模块 | 目录 | 职责 |
|------|------|------|
| **api** | `taotao-cloud-sys-api` | 接口定义层：对外 RPC/HTTP 接口契约（含 protobuf）、DTO、Feign/HttpExchange 接口 |
| **application** | `taotao-cloud-sys-application` | 应用层：业务编排、用例服务（Command/Query）、DTO、Assembler、ACL、事件发布 |
| **domain** | `taotao-cloud-sys-domain` | 领域层：聚合根、实体、值对象、仓储接口、领域服务、领域事件 |
| **infrastructure** | `taotao-cloud-sys-infrastructure` | 基础设施层：PO 持久化对象、Mapper、Repository 实现、配置、事件订阅/发布、MQ、Job |
| **interfaces** | `taotao-cloud-sys-interfaces` | 用户界面层：Controller（buyer/seller/manager 三端）、gRPC 实现、RPC 实现 |
| **facade** | `taotao-cloud-sys-facade` | 防腐层（ACL）：外部服务适配（高德地图、天气、第三方登录等） |
| **common** | `taotao-cloud-sys-common` | 公共层：常量、枚举、异常、配置属性、工具类 |
| **assembly** | `taotao-cloud-sys-assembly` | 装配层：Spring Boot 启动入口、Docker/K8s 部署、多环境打包 |

### 1.2 模块依赖方向

```
assembly → facade, interfaces, infrastructure
  facade → application
  interfaces → application
  infrastructure → application, domain
  application → api, domain
  domain → common
  api → (无内部依赖，仅依赖第三方 starter)
  common → (无内部依赖)
```

**符合 DDD 六边形架构约束** ✅：
- interfaces → application → domain ← infrastructure
- domain 层无框架注解侵入（使用 `taotao-boot-starter-ddd` 抽象基类）

---

## 二、技术栈清单

### 2.1 基础环境

| 技术 | 版本 |
|------|------|
| JDK | **25**（启用 `--enable-preview` 预览特性） |
| Gradle | **9.6.0** |
| Spring Boot | **4.1.0** |
| Spring Cloud | **2025.1.1** |
| Spring Cloud Alibaba | **2025.1.0.0-preview** |
| Spring | **7.0.8** |
| Spring Security | **7.1.0** |

### 2.2 核心框架

| 技术 | 版本 | 用途 |
|------|------|------|
| MyBatis-Plus | 3.5.16 | ORM 持久化 |
| QueryDSL | 5.1.0 | 类型安全查询 |
| MapStruct | 1.6.3 | DTO/PO/VO 转换 |
| Lombok | 1.18.46 | 代码简化 |
| Record-Builder | 53 | Java Record 构建器 |
| Hutool | 5.8.44 | 工具库 |

### 2.3 中间件 & 基础设施

| 技术 | 版本 | 用途 |
|------|------|------|
| Nacos | ~3.x | 注册中心 / 配置中心 |
| Sentinel | 1.8.9 | 流量控制 / 熔断降级 |
| Seata | 2.6.0 | 分布式事务 |
| RocketMQ | 5.2.4 | 消息队列 |
| Kafka | ~4.0.5 | 消息队列 / 事件流 |
| XXL-Job | 3.4.0 | 分布式定时任务 |
| Redis (Redisson) | 4.3.1 | 分布式缓存 / 延迟队列 |
| Elasticsearch | 9.2.2 | 全文检索 |
| MySQL | 9.6.0 | 关系数据库 |
| protobuf / gRPC | 4.35.0 / 1.82.0 | RPC 通信 |
| Knife4j / Swagger | 4.5.0 / 3.0.0 | API 文档 |
| Prometheus / ELK / Skywalking | - | 监控 / 日志 / 链路追踪 |
| GraalVM Native | 1.1.3 | 原生编译支持 |

### 2.4 代码质量门禁

| 工具 | 版本 | 配置位置 |
|------|------|----------|
| **Checkstyle** | 13.5.0 | `code-analysis/checkstyle/google_checks.xml` |
| **SpotBugs** | 6.5.8 | `code-analysis/spotbugs/`（含 findsecbugs 插件） |
| **PMD** | 7.25.0 | `code-analysis/pmd/pmd.xml` |
| **Spotless** | 8.7.0 | google-java-format 1.35.0 (AOSP style) |
| **JaCoCo** | 0.8.15 | 方法行覆盖率 ≥ 90%，分支覆盖率 ≥ 75% |
| **OWASP** | 12.2.2 | 依赖安全扫描 |
| **SonarQube** | 7.3.1 | 静态代码分析 |

---

## 三、DDD 各层深度分析

### 3.1 `domain` 层 — 核心业务逻辑

**包结构**：

```
domain/
  ├── aggregate/    — 聚合根
  │   ├── UserAgg.java          — 用户聚合（充血模型：create/enable/freeze/assignRoles）
  │   ├── DeptAgg.java          — 部门聚合
  │   ├── RoleAgg.java          — 角色聚合
  │   ├── DictAgg.java          — 字典聚合
  │   ├── PermissionAgg.java    — 权限聚合
  │   ├── FileAgg.java          — 文件聚合
  │   ├── FileLogAgg.java       — 文件日志聚合
  │   ├── OrgAgg.java           — 组织聚合
  │   └── PositionAgg.java      — 岗位聚合
  ├── entity/       — 实体
  │   └── DictItem.java
  ├── valobj/       — 值对象
  │   ├── IdPathVal.java
  │   └── IdsVal.java
  ├── event/        — 领域事件
  │   ├── AuthChangeEvent.java    — 权限变更事件
  │   └── DeptCreateEvent.java    — 部门创建事件
  ├── repository/   — 仓储接口（端口定义）
  │   ├── UserDomainRepository.java
  │   ├── DeptDomainRepository.java
  │   ├── DictDomainRepository.java
  │   └── RoleDomainRepository.java
  ├── service/      — 领域服务
  │   ├── UserDomainService.java
  │   ├── DeptDomainService.java
  │   └── RoleDomainService.java
  └── assembler/    — 组装器
      └── DeptAssembler.java
```

**关键观察**：
- 聚合根继承 `AggregateRoot<BizId>`，采用**充血模型**，包含业务行为（如 `UserAgg.enable()`、`UserAgg.assignRoles()`）
- 聚合根使用 `registerEvent()` 注册领域事件，事件驱动解耦
- 仓储接口继承 `DomainRepository<BizId, UserAgg>` 泛型接口
- **零 Spring 注解** ✅，纯 POJO，符合 DDD 规范

### 3.2 `application` 层 — 用例编排

**包结构**：

```
application/
  ├── service/
  │   ├── command/    — 命令服务（写操作）
  │   │   ├── UserCommandService.java      → UserCommandServiceImpl.java
  │   │   ├── DeptCommandService.java      → DeptCommandServiceImpl.java
  │   │   ├── RoleCommandService.java      → RoleCommandServiceImpl.java
  │   │   ├── DictCommandService.java      → DictCommandServiceImpl.java
  │   │   ├── ResourceCommandService.java  → ResourceCommandServiceImpl.java
  │   │   └── RegionCommandService.java    → RegionCommandServiceImpl.java
  │   └── query/      — 查询服务（读操作）
  │       ├── UserQueryService.java        → UserQueryServiceImpl.java
  │       ├── DictQueryService.java        → DictQueryServiceImpl.java
  │       └── AppQueryService.java         → AppQueryServiceImpl.java
  ├── dto/
  │   ├── user/command/     — 用户命令 DTO（CreateUserCommand / UpdateUserCommand / AssignRolesCommand）
  │   ├── user/query/       — 用户查询 DTO（UserQuery）
  │   ├── user/result/      — 用户结果 DTO（UserQueryResult）
  │   └── dept/, dict/, role/, ... 按业务分包
  ├── acl/           — 防腐层接口（调用外部服务）
  │   └── service/   — AmapAclService, CalendarAclService, ConnectAclService, WeatherAclService
  ├── assembler/     — 应用层组装器
  ├── repository/    — 应用层查询仓储
  ├── factory/       — 工厂（UserFactory）
  ├── handler/       — 业务处理器（DeptHandler, DictHandler）
  ├── context/       — 用户上下文
  └── shared/        — 共享模型（monitor/server 监控相关）
```

**关键观察**：
- 命令服务和查询服务**职责分离**（CQRS 模式雏形）
- DTO 按**业务领域分包**：`user/command/`, `user/query/`, `user/result/`
- **事务边界**在 application 层控制
- 本层仅依赖 `api` 和 `domain` 模块

### 3.3 `infrastructure` 层 — 技术实现

**包结构**：

```
infrastructure/
  ├── persistent/       — 持久化层
  │   ├── mapper/       — MyBatis-Plus Mapper（27 个）
  │   │   ├── UserMapper.java, DeptMapper.java, RoleMapper.java, ...
  │   │   └── DictMapper.java, DictItemMapper.java, FileMapper.java, ...
  │   ├── persistence/  — PO 持久化对象（按业务分包）
  │   │   ├── system/   — UserPO, DeptPO, RolePO, ResourcePO, ...
  │   │   ├── dict/     — DictPO, DictItemPO
  │   │   ├── file/     — FilePO, FileLogPO
  │   │   └── log/, region/, setting/, sms/, config/, ...
  │   └── repository/   — Repository 实现 (MyBatis-Plus)
  │       ├── UserRepository.java, DeptRepository.java, ...
  │       └── FileRepository.java, LogRepository.java, ...
  ├── repository/       — 仓储实现适配层
  │   ├── domain/       — 领域仓储实现
  │   │   ├── UserDomainRepositoryImpl.java
  │   │   ├── DeptDomainRepositoryImpl.java
  │   │   └── DictDomainRepositoryImpl.java
  │   └── application/  — 应用查询仓储实现
  │       ├── UserQueryRepositoryImpl.java
  │       └── DictQueryRepositoryImpl.java
  ├── assembler/        — 基础设施层组装器（PO ↔ Domain）
  ├── event/            — 事件基础设施
  │   ├── publisher/    — KafkaEventPublisher, RocketMqEventPublisher, SpringEventPublisher
  │   ├── consumer/     — SysKafkaConsumer, SysRocketmqConsumer
  │   ├── listener/     — AuthChangeSpringEventListener
  │   └── subscribe/    — Guava/Greenrobot/Redis 事件订阅
  ├── configuration/    — 配置
  │   ├── mq/           — RocketMQ/Kafka 配置
  │   ├── cache/        — Redis/Cache 配置
  │   ├── grpc/         — gRPC 配置
  │   ├── redisson/     — Redisson 延迟队列
  │   └── stream/       — Spring Cloud Stream
  ├── job/              — XXL-Job 任务
  │   └── SysJob.java
  ├── data/             — 数据对象（DO/Params）
  └── factory/          — 基础设施工厂
```

**关键观察**：
- **PO 与 Domain 分离**：PO 位于 `persistence/persistence/`，Domain 在 `domain/aggregate/`
- 使用 **MyBatis-Plus** 作为 ORM，同时配置了 **jOOQ** 代码生成（注释状态）
- 支持**多事件通道**：Kafka、RocketMQ、Spring Event、Guava EventBus、Redis pub/sub

### 3.4 `interfaces` 层 — 用户界面 / 适配器

**包结构**：

```
interfaces/
  ├── controller/
  │   ├── manager/  — 管理端 API（28 个 Controller）
  │   │   ├── UserManagerController.java
  │   │   ├── DeptManagerController.java
  │   │   ├── RoleManagerController.java
  │   │   ├── DictManagerController.java
  │   │   ├── ResourceManagerController.java
  │   │   ├── RegionManagerController.java
  │   │   ├── FileManagerController.java
  │   │   ├── MonitorManagerController.java
  │   │   └── ...（日志/配置/系统/数据版本/...）
  │   ├── buyer/    — 买家端
  │   │   └── DictBuyerController.java
  │   └── seller/   — 卖家端
  │       └── DictSellerController.java
  ├── rpc/          — RPC 实现
  │   └── DictQueryRpcServiceImpl.java
  └── grpc/         — gRPC 实现
      └── DictGrpcServiceImpl.java
```

**关键观察**：
- 三端分层：**manager**（管理后台）、**buyer**（买家端）、**seller**（卖家端）
- Controller **不含业务逻辑** ✅，仅做参数校验 + 调用 Application Service
- RPC 接口使用 **Spring `@HttpExchange`**（声明式 HTTP 客户端）或 gRPC

### 3.5 `api` 层 — 接口契约

```
api/
  ├── inner/               — 内部 HTTP 接口（Feign/HttpExchange）
  │   ├── query/           — 查询接口（11 个）
  │   │   ├── UserQueryApi.java, DictQueryApi.java, RoleQueryApi.java ...
  │   ├── command/         — 命令接口（2 个）
  │   │   ├── DeptCommandApi.java, DictCommandApi.java
  │   └── dto/             — DTO 定义
  │       ├── command/     — SysLogApiCommand, SysLogLoginApiCommand
  │       ├── query/       — DictApiQuery, CompanyApiQuery, FileApiQuery
  │       └── response/    — UserQueryApiResponse, DictQueryApiResponse, ...
  ├── rpc/                 — RPC 接口
  │   ├── query/           — 11 个 RPC 查询服务接口
  │   ├── command/         — DeptCommandRpcService, DictCommandRpcService
  │   └── dto/             — RPC DTO
  └── proto/               — protobuf 定义（6 个 .proto 文件）
      ├── Dict.proto, User.proto, File.proto
      ├── Menu.proto, SysLogs.proto
      └── EnumPostfixOverride.proto
```

### 3.6 `facade` 层 — 防腐层

```
facade/
  ├── amap/      — 高德地图适配（ACL + Interceptor + Invoker）
  ├── calendar/  — 日历适配
  ├── connect/   — 第三方登录适配（微信/QQ）
  └── weather/   — 天气服务适配
```

### 3.7 `common` 层 — 公共

```
common/
  ├── constant/   — SysConstants, PinyinConstants
  ├── enums/      — UserStatusEnum, ResourceTypeEnum, FileTypeEnum, ...（13 个）
  ├── execption/  — SysException
  ├── properties/ — SysProperties, GenProperties, LogStoreTypeProperties
  ├── info/       — 配置信息（支付/短信/OSS/积分等设置）
  ├── group/      — 分组校验
  └── utils/      — BizParamUtils, DateUtils
```

### 3.8 `assembly` 层 — 启动装配

```
assembly/
  ├── TaoTaoCloudSysApplication.java   — Spring Boot 启动类（@TaoTaoBootApplication）
  ├── resources/                        — 配置文件（application.yml, bootstrap.yml）
  ├── Dockerfile                        — Docker 构建
  ├── taotao-cloud-sys-k8s.yml          — K8s 部署
  └── smart-doc.json                    — API 文档配置
```

---

## 四、架构合规性检查

| 检查项 | 状态 | 说明 |
|--------|------|------|
| domain 层零框架注解 | ✅ | 纯 POJO，仅使用 `taotao-boot-starter-ddd` 抽象 |
| 依赖方向正确 | ✅ | interfaces→application→domain←infrastructure |
| 事务边界在 application 层 | ✅ | Service 层 `@Transactional` |
| Controller 不含业务逻辑 | ✅ | 仅做参数校验 + 调用 Service |
| 模块间无循环依赖 | ✅ | 单向依赖链 |
| PO 与 Domain 分离 | ✅ | infrastructure/persistence 与 domain/aggregate |
| CQRS 读写分离 | ✅ | command/query 分包 |
| 事件驱动架构 | ✅ | 领域事件 + 多通道事件发布 |

---

## 五、架构图示

```
┌─────────────────────────────────────────────────────────────────────┐
│                     assembly（启动装配层）                            │
│  TaoTaoCloudSysApplication + Docker/K8s + 多环境配置                │
├─────────────────────────────────────────────────────────────────────┤
│  interfaces（用户界面层/适配器层）                                    │
│  ┌──────────────┬────────────────┬──────────────┐                 │
│  │ manager/     │ buyer/         │ seller/      │                 │
│  │ Controller   │ Controller     │ Controller   │                 │
│  ├──────────────┴────────────────┴──────────────┤                 │
│  │ rpc/          │ grpc/                         │                 │
│  └───────────────────────────────────────────────┘                 │
├─────────────────────────────────────────────────────────────────────┤
│  application（应用层 — 用例编排）                                     │
│  ┌─────────────────────────┬────────────────────────────────────┐  │
│  │ command/（写服务）       │ query/（读服务）                    │  │
│  │ UserCommandService      │ UserQueryService                   │  │
│  │ DeptCommandService      │ DictQueryService                   │  │
│  ├─────────────────────────┴────────────────────────────────────┤  │
│  │ acl/（防腐层接口）│ assembler/ │ dto/ │ factory/ │ handler/  │  │
│  └───────────────────────────────────────────────────────────────┘  │
├───────────┬─────────────────────────────────────┬───────────────────┤
│           │            domain（领域层）            │                  │
│           │  ┌──────────────────────────────┐    │                  │
│           │  │ aggregate/（聚合根 - 充血模型）│    │                  │
│           │  │ entity/ · valobj/            │    │                  │
│           │  │ repository/（仓储端口）       │    │                  │
│           │  │ service/（领域服务）           │    │                  │
│           │  │ event/（领域事件）             │    │                  │
│           │  └──────────────────────────────┘    │                  │
│           └──────────┬────────────────────────────┘                  │
│                      │ 依赖反转                                    │
│                      ▼                                              │
│  infrastructure（基础设施层）                                        │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │ persistent/ — Mapper + PO + Repository 实现                  │  │
│  │ repository/ — 领域仓储/应用仓储实现（适配 domain 端口）        │  │
│  │ event/      — Kafka/RocketMQ/Spring Event 发布订阅           │  │
│  │ job/        — XXL-Job 定时任务                                │  │
│  │ configuration/ — Cache/MQ/gRPC/Redis 配置                    │  │
│  │ assembler/  — PO ↔ Domain 转换                                │  │
│  └──────────────────────────────────────────────────────────────┘  │
├─────────────────────────────────────────────────────────────────────┤
│  facade（防腐层 — 外部服务适配）                                      │
│  高德地图 / 天气 / 第三方登录 / 日历                                │
├─────────────────────────────────────────────────────────────────────┤
│  api（接口层）— 对外暴露的接口契约                                   │
│  inner/（HttpExchange）· rpc/（RPC接口）· proto/（gRPC）            │
├─────────────────────────────────────────────────────────────────────┤
│  common（公共层）— 常量/枚举/异常/工具                               │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 六、常见操作指引

### 6.1 新增一个业务模块（如 `xxx`）

1. **domain** — 创建聚合根 `XxxAgg.java`、仓储接口 `XxxDomainRepository.java`、值对象
2. **api** — 定义 `XxxQueryApi.java` / `XxxCommandApi.java` 及对应 DTO
3. **application** — 创建 `XxxCommandService.java` / `XxxQueryService.java` + DTO
4. **infrastructure** — 创建 `XxxPO.java`、`XxxMapper.java`、`XxxRepositoryImpl.java`
5. **interfaces** — 创建 `XxxManagerController.java` 及 buyer/seller 端
6. **assembly** — 无需改动（自动装配）

### 6.2 常用命令

```bash
# 完整构建（跳过测试）
gradle clean build -x test

# 代码检查
gradle check               # 执行所有代码质量检查
gradle spotbugsMain        # SpotBugs 静态分析
gradle checkstyleMain      # Checkstyle 检查
gradle pmdMain             # PMD 检查
gradle spotlessApply       # 自动格式化代码

# 测试与覆盖率
gradle test                # 运行测试
gradle jacocoTestReport    # 生成 JaCoCo 覆盖率报告

# 构建并发布
gradle bootJar             # 打包可执行 jar
gradle publishJar          # 构建 + 发布到 Maven 仓库

# 多环境打包
gradle bootJar -Pprofile=dev    # 开发环境
gradle bootJar -Pprofile=test   # 测试环境
gradle bootJar -Pprofile=prod   # 生产环境

# Docker
gradle bootBuildImage      # 构建 OCI 镜像
```

### 6.3 JDK 25 预览特性

项目全局启用 `--enable-preview`，可以使用：
- **Record 模式**（JEP 440）
- **Switch 模式匹配**（JEP 441）
- **String Templates**（JEP 459）
- **Stream Gatherers**（JEP 473）
- **结构化并发**（JEP 462）
- **作用域值**（Scoped Values, JEP 446）
- **虚拟线程**（Virtual Threads, JEP 476）

---

## 七、推荐后续步骤

| 命令 | 用途 |
|------|------|
| `/ttc-propose` | 创建 DDD 变更提案，生成渐进式 Spec |
| `/ttc-review` | 执行 DDD 代码审查 — 检查领域模型、架构合规、代码质量 |
| `/ttc-apply` | 按确认后的 Spec 执行 DDD 编码 |
| `/ttc-test` | 运行测试并生成 JaCoCo 覆盖率报告 |
| `/ttc-archive` | 归档已完成变更并更新项目上下文 |
