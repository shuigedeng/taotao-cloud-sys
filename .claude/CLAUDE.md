# taotao-cloud-sys — SpringBoot DDD 系统中心服务

系统中心 DDD 单体服务，提供系统基础能力：用户管理、RBAC 权限、组织架构、字典管理、文件管理、区域管理、系统配置、日志审计、国际化 (i18n)、敏感词、拼音、消息通知等。严格遵循**六边形架构 + 领域驱动设计 (DDD)**。

- **根包**: `com.taotao.cloud.sys`
- **架构**: 六边形架构（Hexagonal Architecture），严格领域隔离

---

## 技术栈

| 依赖 | 版本 |
|------|------|
| JDK | 25（预览特性 `--enable-preview`） |
| Gradle | 9.7.1 |
| Spring Boot | 4.1.1 |
| Spring Cloud | 2025.1.1 |
| Spring Cloud Alibaba | 2025.1.0.0 |
| Spring Security | 7.1.0 |
| Mybatis-Plus | 3.5.16 |
| Querydsl | 5.1.0 |
| MapStruct | 1.6.3 |
| Lombok | 1.18.46 |
| Hutool | 5.8.44 |
| Redisson | 4.3.1 |
| Knife4j | 4.5.0 |
| gRPC | 1.80.0 |
| Protobuf | 4.35.0 |
| RocketMQ | 5.2.4 |
| Kafka | 4.0.5 |

---

## 模块架构

```
taotao-cloud-sys/
├── api/                     # RPC/gRPC 接口 + DTO + Protobuf 定义（纯接口，无业务依赖）
├── application/             # 应用层：用例编排、事务、DTO 转换
│   ├── service/command/     # 命令服务（写操作，@Transactional）
│   │   └── impl/            # 命令服务实现（30+ 个）
│   ├── service/query/       # 查询服务（读操作，@Transactional(readOnly=true)）
│   ├── dto/{biz}/           # command / query / result DTO
│   ├── acl/                 # 防腐层接口定义
│   ├── repository/          # 查询仓储接口（CQRS 读模型）
│   ├── assembler/           # DTO ↔ Domain 转换
│   ├── handler/             # 业务处理器
│   ├── factory/             # 工厂类
│   └── flow/                # 流程编排
├── assembly/                # 启动器 + 环境配置 + Docker/K8s
│   └── src/test/            # 集成测试
├── common/                  # 公共模块
│   ├── enums/               # 枚举（13 个）
│   ├── constant/            # 常量
│   ├── properties/          # @ConfigurationProperties
│   ├── info/                # 业务配置项（支付/短信/OSS 等）
│   ├── utils/               # 工具类
│   └── exception/           # 异常（SysException）
├── domain/                  # ★ 领域层（零外部依赖）
│   ├── aggregate/           # 聚合根（9 个：UserAgg, RoleAgg, DeptAgg, DictAgg...）
│   ├── entity/              # 实体（DictItem）
│   ├── valobj/              # 值对象（IdsVal, IdPathVal）— 不可变 Record
│   ├── event/               # 领域事件（DeptCreateEvent, AuthChangeEvent）
│   ├── repository/          # 仓储接口（4 个）
│   ├── service/             # 领域服务接口 + 实现
│   └── assembler/           # 领域装配器
├── facade/                  # 防腐层 ACL
│   └── {biz}/               # 按外部服务分包（amap/weather/calendar/connect）
│       ├── acl/             # ACL 实现
│       ├── invoker/         # 远程调用器
│       └── interceptor/     # 拦截处理
├── infrastructure/          # 基础设施层
│   ├── persistent/          # 持久化
│   │   ├── persistence/{biz}/   # JPA PO（30+ 个）
│   │   ├── mapper/               # Mybatis-Plus Mapper（27 个）
│   │   └── repository/           # Spring Data Repository
│   ├── repository/          # 仓储实现
│   │   ├── domain/          # 领域仓储实现（4 个）
│   │   └── application/     # 查询仓储实现
│   ├── event/               # 事件发布/消费/订阅
│   ├── job/                 # 定时任务
│   ├── configuration/       # 配置（RocketMQ/Redisson/Stream）
│   └── factory/             # 工厂实现
└── interfaces/              # 接口层
    ├── controller/          # REST Controller
    │   ├── manager/         # 管理端 /manager/**
    │   ├── seller/          # 商家端 /seller/**
    │   ├── buyer/           # 买家端 /buyer/**
    │   ├── inner/           # 内部调用 /inner/**
    │   └── test/            # 测试接口
    ├── rpc/                 # Dubbo RPC 实现
    └── grpc/                # gRPC 实现
```

---

## DDD 核心原则

### 分层依赖规则

```
api  ←  interfaces  ←  application  →  facade
                          ↓
                     domain  ←  infrastructure
```

| 层 | 依赖规则 |
|----|---------|
| **domain** | 零外部框架依赖（不依赖 Spring、数据库、Web） |
| **application** | 依赖 domain，可依赖 facade 接口 |
| **infrastructure** | 依赖 domain（实现仓储），依赖 application（事件订阅） |
| **interfaces** | 依赖 application，不直接依赖 infrastructure |
| **api** | 纯接口 + DTO + Proto，不依赖任何业务模块 |

### 聚合设计
- 聚合根命名以 `Agg` 后缀（`DictAgg`, `UserAgg`）
- 跨聚合通过 **ID 引用**（非对象引用）
- 聚合内保证**事务一致性**，聚合间使用事件驱动**最终一致性**
- 使用 `registerEvent()` 注册领域事件，仓储 `save()` 时自动 flush

### 值对象
- **不可变**：`final` 字段，无 setter 方法
- 构造时自验证
- 覆写 `equals()` / `hashCode()`（基于所有属性）
- 位于 `domain/valobj/` 包，使用 Record 风格

### 应用服务
- **命令服务**（`service/command/`）：写操作，标注 `@Transactional`
- **查询服务**（`service/query/`）：读操作，标注 `@Transactional(readOnly = true)`
- 禁止在 Application Service 中包含**业务规则判断**
- 事务边界仅开在 application 层

### Controller
- 按角色分包：`manager` / `seller` / `buyer` / `inner`
- 只做 HTTP 解析 + 参数校验 + Result 封装
- **禁止业务逻辑**
- Controller 继承 `BusinessController`，使用 `@TaoTaoBootApplication`

---

## 命名约定

| 元素 | 格式 | 示例 |
|------|------|------|
| 聚合根 | `{Biz}Agg` | `DictAgg`, `UserAgg` |
| 实体 | `{Name}` | `DictItem` |
| 值对象 | `{Desc}Val` | `IdsVal`, `IdPathVal` |
| 领域事件 | `{Biz}{Action}Event` | `DeptCreateEvent`, `AuthChangeEvent` |
| 领域服务 | `{Biz}DomainService` | `UserDomainService` |
| 仓储接口 (domain) | `{Biz}DomainRepository` | `DictDomainRepository` |
| 命令服务 | `{Biz}CommandService` | `DictCommandService` |
| 查询服务 | `{Biz}QueryService` | `DictQueryService` |
| 查询仓储 | `{Biz}QueryRepository` | `DictQueryRepository` |
| PO | `{Biz}PO` | `DictPO`, `UserPO` |
| Controller | `{Biz}{Role}Controller` | `AdminDictController` |

---

## 业务领域

| 业务 | 聚合根 | Mapper | 命令服务 | Controller |
|------|--------|--------|---------|------------|
| 用户 | `UserAgg` | `UserMapper` | `UserCommandService` | `AdminUserController` |
| 角色 | `RoleAgg` | `RoleMapper` | `RoleCommandService` | `AdminRoleController` |
| 权限 | `PermissionAgg` | `ResourceMapper` | `ResourceCommandService` | `AdminResourceController` |
| 部门 | `DeptAgg` | `DeptMapper` | `DeptCommandService` | `AdminDeptController` |
| 组织 | `OrgAgg` | — | — | `AdminOrgController` |
| 岗位 | `PositionAgg` | `PositionMapper` | `PositionCommandService` | `AdminPositionController` |
| 字典 | `DictAgg` + `DictItem` | `DictMapper` | `DictCommandService` | `AdminDictController` |
| 文件 | `FileAgg` + `FileLogAgg` | `FileMapper` | `FileCommandService` | `AdminFileController` |
| 区域 | — | `RegionMapper` | `RegionCommandService` | `AdminRegionController` |
| 设置 | — | `SettingMapper` | `SettingCommandService` | `AdminSettingController` |
| 日志 | — | `LogMapper` | `LogCommandService` | `AdminLogController` |
| 国际化 | — | `I18nDataMapper` | `I18nDataCommandService` | `AdminI18nDataController` |
| 敏感词 | — | `SensitiveWordMapper` | `SensitiveWordCommandService` | `AdminSensitiveWordsController` |

---

## 常用命令

```bash
# 编译（跳过测试）
./gradlew build -x test

# 本地启动 (dev 环境)
./gradlew :taotao-cloud-sys-assembly:bootRun --args='--spring.profiles.active=dev'

# 运行全部测试
./gradlew test

# 运行指定模块测试
./gradlew :taotao-cloud-sys-domain:test

# 代码质量检查
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain

# 代码格式化
./gradlew spotlessApply

# JaCoCo 覆盖率报告
./gradlew jacocoTestReport

# 发布到本地仓库
./gradlew publishToMavenLocal

# 依赖漏洞检查
./gradlew dependencyCheckAnalyze
```

---

## 反模式（严禁）

- Controller 中写业务逻辑
- 聚合根中注入 Repository 或 Domain Service
- 值对象中包含业务行为以外的逻辑
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态
- Application Service 直接调用 Mapper/Repository
- 领域模型与持久化模型混用（domain entity ≠ PO）
- `as any`、`@ts-ignore`、`catch(e){}` 等类型安全违规（Java 中对应原始类型 RawType、捕获 Exception 却不处理）

---

## 项目特有约定

- **`@TaoTaoBootApplication`**：自定义组合注解，替代 `@SpringBootApplication`
- **`StartupSpringApplication`**：自定义启动器包装类
- **双仓储体系**：`domain/repository/`（领域仓储接口）+ `application/repository/`（查询仓储接口）
- **MapStruct + Record Builder + Lombok**：三件套减少样板代码
- **多事件总线**：Spring Event / Kafka / RocketMQ / Redis / Guava / GreenRobot
- **代码质量门禁**：Checkstyle + SpotBugs + PMD + Spotless + OWASP dependency-check + JaCoCo
- **JDK 25 预览特性**：`--enable-preview` + 大量 `--add-opens` / `--add-exports` JVM 参数
