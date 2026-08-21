# PROJECT KNOWLEDGE BASE

**Generated:** 2026-06-18
**Commit:** `56589ba`
**Branch:** (active branch)

## OVERVIEW

系统中心 DDD 单体服务，基于 Spring Boot 4.1.1 / JDK 25 / Gradle 9.5。严格遵循六边形架构 + 领域驱动设计。

提供系统基础能力：用户管理、角色权限（RBAC）、组织架构、字典管理、文件管理、区域管理、配置管理（OSS/短信/支付/物流/邮件等）、日志审计、国际化(i18n)、敏感词、拼音、消息中心等。

## STRUCTURE

```
.opencode/
├── commands/        # 工作流命令（9个）
├── instructions/    # 编码规范
├── skills/          # 技能脚本
├── AGENTS.md        # 代理知识库
└── opencode.json    # OpenCode 配置
```

```
taotao-cloud-sys/
├── api/               # RPC/gRPC 接口 + DTO + Protobuf 定义
├── application/       # 应用层：编排、事务、DTO转换、Handler
├── assembly/          # 启动器 + 环境配置 + Docker/K8s 部署
├── common/            # 公共工具、枚举、常量、属性配置、设置项
├── domain/            # ★ 领域层（零外部依赖）
├── facade/            # 防腐层（ACL）：对接高德/天气/日历/Connect 等外部服务
├── infrastructure/    # 持久化、MQ、事件、配置
└── interfaces/        # REST / RPC / gRPC
```

## WHERE TO LOOK

| Task | Location |
|------|----------|
| 新增业务功能 | `application/service/commad/` 定义接口 + `interfaces/controller/` |
| 修改领域模型 | `domain/aggregate/` 或 `domain/entity/` |
| 值对象 | `domain/valobj/` — 所有字段 final，无 setter |
| 领域事件 | `domain/event/` — 聚合内 registerEvent，仓储 flush |
| 领域服务 | `domain/service/` — 跨聚合业务逻辑 |
| 仓储接口(domain抽象) | `domain/repository/` |
| 仓储实现 | `infrastructure/repository/domain/` |
| 查询仓储接口 | `application/repository/` |
| 查询仓储实现 | `infrastructure/repository/aplication/` |
| 持久化PO | `infrastructure/persistent/persistence/{biz}/` |
| Mapper(Mybatis-Plus) | `infrastructure/persistent/mapper/` |
| Repository(Spring Data) | `infrastructure/persistent/repository/` |
| API 定义(rpc) | `api/rpc/` — 接口 + DTO |
| API 定义(inner) | `api/inner/` — 内部Feign接口 + DTO |
| Protobuf 定义 | `api/proto/` |
| REST Controller | `interfaces/controller/{buyer|seller|manager}/` |
| RPC 实现 | `interfaces/rpc/` |
| gRPC 实现 | `interfaces/grpc/` |
| 外部接口适配(ACL) | `facade/{biz}/acl/` |
| 外部服务调用 | `facade/{biz}/invoker/` |
| 外部服务拦截 | `facade/{biz}/interceptor/` |
| 消息监听 | `infrastructure/event/consumer/` + `infrastructure/event/subscribe/` |
| 定时任务 | `infrastructure/job/` |
| 事件发布 | `infrastructure/event/publisher/` |
| 配置 | `infrastructure/configuration/` |
| 公共枚举 | `common/enums/` |
| 公共常量 | `common/constant/` |
| 配置属性 | `common/properties/` |
| 设置项定义 | `common/info/` |
| 主启动类 | `assembly/src/main/java/com/taotao/cloud/sys/TaoTaoCloudSysApplication.java` |
| 集成测试 | `assembly/src/test/` |

## BUSINESS DOMAIN (系统中心核心业务)

| 领域 | 聚合根 | 说明 |
|------|--------|------|
| 用户管理 | `UserAgg` | 系统用户账号管理 |
| 角色权限 | `RoleAgg`, `PermissionAgg` | RBAC 角色-权限-资源管理 |
| 组织架构 | `DeptAgg`, `OrgAgg` | 部门/组织树管理 |
| 岗位管理 | `PositionAgg` | 岗位定义 |
| 字典管理 | `DictAgg` | 数据字典（含 DictItem） |
| 文件管理 | `FileAgg`, `FileLogAgg` | 文件上传/存储/日志 |
| 区域管理 | (Region) | 行政区划数据 |
| 配置管理 | (Setting) | 系统设置（OSS/短信/支付/物流/邮件/积分/秒杀等） |
| 日志审计 | (Log/OperateLog/LoginLog/SysLog) | 操作日志/登录日志/系统日志 |
| 国际化 | (I18nData) | 多语言数据管理 |
| 敏感词 | (SensitiveWord) | 敏感词过滤 |
| 拼音 | (Pinyin) | 汉字转拼音 |
| 消息通知 | (ServiceNotice) | 服务公告 |
| 应用管理 | (App) | 第三方应用接入 |
| 物流配置 | (LogisticsConfig) | 物流公司配置 |
| 支付宝配置 | (AlipayConfig) | 支付参数 |
| 邮件配置 | (EmailConfig) | 邮件服务器参数 |
| 短信管理 | (SmsTemplate/SmsSign/SmsReach) | 短信模板/签名/发送 |
| 数据版本 | (DataVersionLog) | 数据变更版本管理 |
| 访问统计 | (Visits) | 访问量统计 |
| 监控 | (Monitor) | 服务器/Redis/JVM 监控 |

## CONVENTIONS

- 分层依赖方向：`interfaces → application → domain ← infrastructure`
- 跨聚合通过 ID 引用，非对象引用
- 事务边界仅开在 `application/` 层
- Controller 按角色 buyer / seller / manager 分包
- 命令/查询命名：`{动词}{名词}{Command|Query}Service`
  - 命令接口：`application/service/commad/{Biz}CommandService`
  - 查询接口：`application/service/query/{Biz}QueryService`
  - 实现：`application/service/commad/impl/{Biz}CommandServiceImpl`
- 领域模型与持久化模型分离（domain entity ≠ PO）
- Application 层通过 `repository/` 定义查询仓储接口，infrastructure 层实现
- Domain 层通过 `repository/` 定义领域仓储接口，infrastructure 层实现
- 防腐层独立模块：`facade/` 作为独立 gradle module，按外部服务分包（amap/weather/calendar/connect）
- 事件驱动：`infrastructure/event/publisher/` 发布事件 → `infrastructure/event/subscribe/` 或 `infrastructure/event/consumer/` 订阅
- 基础设施支持多种事件总线：Spring Event / Kafka / RocketMQ / Redis / Guava / GreenRobot
- 代码映射三件套：MapStruct + Record Builder + Lombok

## ANTI-PATTERNS (THIS PROJECT)

- Controller 中写业务逻辑判断
- 聚合根中注入 Repository 或 Domain Service
- 值对象中包含业务行为以外的逻辑
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态
- Application Service 直接调用 Mapper 或 Repository（应通过领域仓储接口）

## UNIQUE STYLES

- **API/Interfaces 分离**：`api/` 模块只放接口定义和 DTO/protobuf，`interfaces/` 模块放实现
- **Controller 按端分层**：manager / seller / buyer / inner / test 五个子包
- **双仓储体系**：`domain/repository/`（领域仓储接口）+ `application/repository/`（查询仓储接口），infrastructure 分别实现
- **防腐层独立为模块**：`facade/` 作为独立 gradle module，非 application 的子包
- **事件总线多样化**：支持 Spring Event + Kafka + RocketMQ + Redis + Guava + GreenRobot
- **Protobuf + gRPC**：`api/proto/` 定义 proto 文件，自动生成 gRPC 服务端/客户端
- **MapStruct + Record Builder + Lombok**：减少样板代码的同时保持不可变性
- **持久化路径约定**：`infrastructure/persistent/persistence/{biz}/{Name}PO.java`

## CLIENTS & ENDPOINTS

| 类型 | Controller 分包 | URL 前缀 |
|------|----------------|----------|
| 管理端 | `controller/manager/` | `/manager/**` |
| 商家端 | `controller/seller/` | `/seller/**` |
| 买家端 | `controller/buyer/` | `/buyer/**` |
| 内部调用 | `controller/inner/` | `/inner/**` |
| RPC | `rpc/` | Dubbo 服务 |
| gRPC | `grpc/` | gRPC 服务 |

## COMMANDS

```bash
./gradlew build                              # 编译
./gradlew :taotao-cloud-sys-assembly:bootRun --args='--spring.profiles.active=dev'  # 启动dev
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain          # 质量检查
./gradlew test                               # 测试
./gradlew publishToMavenLocal                # 发布到本地
```

## NOTES

- JDK 25 预览特性，`--enable-preview` + 大量 `--add-opens` / `--add-exports`
- `taotao-cloud-dependencies:2026.09` BOM 通过 gradle `platform()` 引入
- 四个环境配置：dev / test / pre / pro
- 代码质量门禁：Checkstyle + SpotBugs + PMD + Spotless + OWASP + JaCoCo
- 采用 `@TaoTaoBootApplication`（自定义组合注解）替代 `@SpringBootApplication`
- 自定义启动器：`StartupSpringApplication` 包装启动逻辑
