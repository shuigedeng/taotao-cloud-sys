# taotao-cloud-sys — SpringBoot DDD 系统中心服务

## 项目架构概述

系统中心 DDD 单体服务，提供系统基础能力：用户管理、RBAC 权限、组织架构、字典管理、文件管理、区域管理、系统配置、日志审计、国际化(i18n)、敏感词、拼音、消息通知等。

严格遵循 **六边形架构 + 领域驱动设计（DDD）**。

## 技术栈

| 依赖 | 版本 |
|------|------|
| JDK | 25（预览特性 `--enable-preview`） |
| Gradle | 9.5 |
| Spring Boot | 4.1.0 |
| Spring Cloud | 2025.1.1 |
| Spring Cloud Alibaba | 2025.1.0.0 |
| Spring Security | 7.1.0 |
| Mybatis-Plus | 3.5.16 |
| Querydsl | 5.1.0 |
| MapStruct | 1.6.3 |
| Lombok | 1.18.46 |
| Record Builder | 52 |
| Hutool | 5.8.44 |
| Redisson | 4.3.1 |
| Knife4j | 4.5.0 |
| gRPC | 1.80.0 |
| Protobuf | 4.35.0 |

## 模块结构

```
taotao-cloud-sys/
├── api/                  # RPC/gRPC 接口 + DTO + Protobuf 定义（纯接口，无业务依赖）
├── application/          # 应用层：用例编排、事务、DTO转换、Handler
│   ├── service/commad/   # 命令服务接口+实现
│   ├── service/query/    # 查询服务接口+实现
│   ├── dto/{biz}/        # Command / Query / Result DTO
│   ├── repository/       # 查询仓储接口（读操作）
│   ├── handler/          # 事件处理器
│   ├── factory/          # 工厂
│   ├── flow/             # 工作流编排
│   └── shared/           # 共享模型（monitor/server/generator）
├── assembly/             # 启动器 + 环境配置 + Docker/K8s
│   └── src/test/         # 集成测试
├── common/               # 公共模块（枚举、常量、工具、属性配置）
│   ├── enums/            # 枚举（UserStatusEnum, FileTypeEnum, EnabledEnum...）
│   ├── constant/         # 常量（SysConstants, PinyinConstants...）
│   ├── properties/       # @ConfigurationProperties（SysProperties, GenProperties...）
│   ├── info/             # 设置项定义（BaseSetting, SmsSetting, OssSetting...）
│   ├── utils/            # 工具（DateUtils, BizParamUtils...）
│   └── execption/        # 异常（SysException）
├── domain/               # ★ 领域层（零外部依赖）
│   ├── aggregate/        # 聚合根（DictAgg, UserAgg, DeptAgg, RoleAgg, FileAgg...）
│   ├── entity/           # 实体（DictItem）
│   ├── valobj/           # 值对象（IdsVal, IdPathVal）
│   ├── event/            # 领域事件（DeptCreateEvent, AuthChangeEvent）
│   ├── repository/       # 仓储接口
│   ├── service/          # 领域服务（UserDomainService, DeptDomainService...）
│   └── assembler/        # 领域 Assembler
├── facade/               # 防腐层 ACL
│   └── {biz}/            # 按外部服务分包（amap/weather/calendar/connect）
│       ├── acl/          # 防腐适配实现
│       ├── invoker/      # 远程调用
│       └── interceptor/  # 拦截处理
├── infrastructure/       # 基础设施层
│   ├── persistent/       # 持久化
│   │   ├── persistence/{biz}/  # JPA PO（DictPO, UserPO, FilePO...）
│   │   ├── mapper/       # Mybatis-Plus Mapper
│   │   └── repository/   # Spring Data Repository
│   ├── repository/       # 仓储实现
│   │   ├── domain/       # 领域仓储实现
│   │   └── aplication/   # 查询仓储实现
│   ├── event/            # 事件
│   │   ├── publisher/    # 事件发布（Spring/Kafka/RocketMQ）
│   │   ├── consumer/     # 消息消费（Kafka/RocketMQ）
│   │   ├── subscribe/    # 事件订阅（Redis/Guava/GreenRobot）
│   │   └── listener/     # Spring 事件监听
│   ├── job/              # 定时任务
│   ├── configuration/    # 配置（RocketMQ/Redisson/Stream）
│   └── factory/          # 工厂
└── interfaces/           # 接口层
    ├── controller/       # REST Controller（按端分包）
    │   ├── manager/      # 管理端 /manager/**
    │   ├── seller/       # 商家端 /seller/**
    │   ├── buyer/        # 买家端 /buyer/**
    │   ├── inner/        # 内部调用 /inner/**
    │   └── test/         # 测试接口
    ├── rpc/              # Dubbo RPC 实现
    └── grpc/             # gRPC 实现
```

## DDD 核心原则

### 分层依赖规则
```
api  ←  interfaces  ←  application  →  facade
                          ↓
                     domain  ←  infrastructure
```

- **domain**：零外部依赖（不依赖 Spring、框架、数据库）
- **application**：依赖 domain，可依赖 facade 接口
- **infrastructure**：依赖 domain（实现仓储），依赖 application（事件订阅）
- **interfaces**：依赖 application，不直接依赖 infrastructure
- **api**：纯接口 + DTO + Proto，不依赖任何业务模块

### 聚合设计
- 聚合根命名以 `Agg` 后缀（`DictAgg`, `UserAgg`, `DeptAgg`）
- 跨聚合通过 ID 引用（非对象引用）
- 聚合内保证事务一致性，聚合间使用事件驱动最终一致性
- 聚合内 `registerEvent()` 注册事件，仓储 `save()` 时自动 flush

### 值对象
- 不可变：`final` 字段，无 setter 方法
- 构造时自验证
- 覆写 `equals()` / `hashCode()`（基于所有属性）
- 位于 `domain/valobj/` 包

### 应用服务
- **命令服务**（`service/commad/`）：写操作，`@Transactional`
- **查询服务**（`service/query/`）：读操作，`@Transactional(readOnly = true)`
- 禁止在 Application Service 中包含业务规则判断
- 事务边界仅开在 application 层

### Controller
- 按角色分包：`manager` / `seller` / `buyer` / `inner`
- 只做 HTTP 解析 + 参数校验 + Result 封装
- 禁止业务逻辑
- Controller 继承 `BusinessController`

## 命名约定

| 元素 | 格式 | 示例 |
|------|------|------|
| 聚合根 | `{Biz}Agg` | `DictAgg`, `UserAgg` |
| 实体 | `{Name}` | `DictItem` |
| 值对象 | `{Desc}Val` | `IdsVal`, `IdPathVal` |
| 领域事件 | `{Biz}{Action}Event` | `DeptCreateEvent`, `AuthChangeEvent` |
| 领域服务 | `{Biz}DomainService` | `UserDomainService` |
| 仓储接口(domain) | `{Biz}DomainRepository` | `DictDomainRepository` |
| 命令服务 | `{Biz}CommandService` | `DictCommandService` |
| 查询服务 | `{Biz}QueryService` | `DictQueryService` |
| 查询仓储 | `{Biz}QueryRepository` | `DictQueryRepository` |
| PO | `{Biz}PO` | `DictPO`, `UserPO` |
| Controller | `{Biz}{Role}Controller` | `DictManagerController` |

## 常用命令

```bash
# 编译
./gradlew build -x test

# 本地启动 (dev 环境)
./gradlew :taotao-cloud-sys-assembly:bootRun --args='--spring.profiles.active=dev'

# 运行测试
./gradlew test

# 运行指定模块测试
./gradlew :taotao-cloud-sys-domain:test

# 代码质量检查
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain

# 代码格式化
./gradlew spotlessApply

# 发布到本地仓库
./gradlew publishToMavenLocal

# JaCoCo 覆盖率报告
./gradlew jacocoTestReport
```

## 业务领域

| 业务 | 聚合根 | 说明 |
|------|--------|------|
| 用户 | `UserAgg` | 系统用户账号 |
| 角色 | `RoleAgg` | RBAC 角色定义 |
| 权限 | `PermissionAgg` | 权限资源管理 |
| 部门 | `DeptAgg` | 组织架构树 |
| 组织 | `OrgAgg` | 机构管理 |
| 岗位 | `PositionAgg` | 岗位定义 |
| 字典 | `DictAgg` + `DictItem` | 数据字典 |
| 文件 | `FileAgg` + `FileLogAgg` | 文件存储 |
| 区域 | Region | 行政区划 |
| 配置 | Setting | 系统设置（OSS/短信/支付等） |
| 日志 | Log/OperateLog/LoginLog/SysLog | 审计日志 |
| 国际化 | I18nData | 多语言 |
| 敏感词 | SensitiveWord | 过滤 |
| 拼音 | Pinyin | 汉字转拼音 |

## 反模式（严禁）

- Controller 中写业务逻辑
- 聚合根中注入 Repository 或 Domain Service
- 值对象中包含业务行为以外的逻辑
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态
- Application Service 直接调用 Mapper/Repository
- 领域模型与持久化模型混用（domain entity ≠ PO）

## 项目特有约定

- **`@TaoTaoBootApplication`**：自定义组合注解，替代 `@SpringBootApplication`
- **`StartupSpringApplication`**：自定义启动器包装类
- **双仓储体系**：`domain/repository/`（领域仓储接口）+ `application/repository/`（查询仓储接口）
- **MapStruct + Record Builder + Lombok**：三件套减少样板代码
- **多事件总线**：Spring Event / Kafka / RocketMQ / Redis / Guava / GreenRobot
- **代码质量门禁**：Checkstyle + SpotBugs + PMD + Spotless + OWASP + JaCoCo
- **JDK 25 预览特性**：`--enable-preview` + 大量 `--add-opens` / `--add-exports`
