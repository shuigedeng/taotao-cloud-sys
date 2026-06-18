# 项目编码规范 — taotao-cloud-sys

> 系统中心 DDD 单体服务编码规范，补充 `.claude/rules/` 中未覆盖的实现细节

---

## 1. 模块依赖规则

```
api  ←  interfaces  ←  application  →  facade
                          ↓
                     domain  ←  infrastructure
```

- `api`：纯 DTO + 接口定义 + Protobuf，不依赖任何业务模块
- `domain`：零外部依赖，不依赖 Spring、不依赖数据库
- `application`：依赖 `domain`，可依赖 `facade` 接口，不依赖 `infrastructure`
- `infrastructure`：依赖 `domain` 实现仓储，依赖 `application` 实现事件订阅
- `interfaces`：依赖 `application`，不直接依赖 `infrastructure`
- `facade`：防腐层，依赖外部 SDK，对上层暴露适配接口
- `common`：被所有模块依赖，仅放枚举/常量/工具/属性配置
- `assembly`：启动模块，依赖所有模块（启动类 + 配置 + 测试）

### 禁止违反的依赖
```java
// ❌ 禁止：Controller 直接调用 Repository
@Autowired private DictRepository dictRepository;

// ❌ 禁止：Application Service 直接调用 Mapper
@Autowired private DictMapper dictMapper;

// ❌ 禁止：Domain Service 注入 Repository
@Autowired private DictDomainRepository dictRepository;

// ✅ 正确：Application Service 通过领域仓储接口操作持久化
private final DictDomainRepository dictDomainRepository;

// ✅ 正确：Application Service 通过查询仓储读取数据
private final DictQueryRepository dictQueryRepository;
```

## 2. 包结构规范

### domain 层（零外部依赖）
```
com.taotao.cloud.sys.domain/
├── aggregate/          # 聚合根（如 DictAgg, UserAgg, DeptAgg, RoleAgg 等）
├── entity/             # 实体（如 DictItem）
├── valobj/             # 值对象（如 IdsVal, IdPathVal）
├── event/              # 领域事件（如 DeptCreateEvent, AuthChangeEvent）
├── repository/         # 仓储接口（如 DictDomainRepository）
├── service/            # 领域服务（如 UserDomainService, DeptDomainService）
│   └── impl/           # 领域服务实现
└── assembler/          # 领域 Assembler（如 DeptAssembler）
```

### application 层（用例编排）
```
com.taotao.cloud.sys.application/
├── service/
│   ├── commad/                 # 命令服务接口
│   │   ├── DictCommandService.java
│   │   └── impl/
│   │       └── DictCommandServiceImpl.java
│   └── query/                  # 查询服务接口
│       ├── DictQueryService.java
│       └── impl/
│           └── DictQueryServiceImpl.java
├── dto/
│   ├── {biz}/command/          # 命令 DTO
│   ├── {biz}/query/            # 查询 DTO
│   └── {biz}/result/           # 结果 DTO
├── repository/                 # 查询仓储接口
│   ├── DictQueryRepository.java
│   └── UserQueryRepository.java
├── handler/                    # 事件处理器
│   ├── dict/DictHandler.java
│   └── dept/DeptHandler.java
├── factory/                    # 应用层工厂
│   └── UserFactory.java
├── flow/                       # 工作流编排
│   └── SysFlowService.java
├── support/                    # 支撑服务
│   └── BizFn.java
├── shared/                     # 共享模型
│   ├── server/                 # 服务器信息
│   ├── monitor/                # 监控信息
│   └── generator/              # 代码生成
└── utils/                      # 应用层工具
```

### infrastructure 层（技术实现）
```
com.taotao.cloud.sys.infrastructure/
├── persistent/
│   ├── persistence/{biz}/      # JPA 实体 PO
│   │   ├── dict/DictPO.java
│   │   ├── system/UserPO.java
│   │   └── ...
│   ├── mapper/                  # Mybatis-Plus Mapper
│   │   ├── DictMapper.java
│   │   └── ...
│   └── repository/             # Spring Data Repository
│       ├── DictRepository.java
│       └── ...
├── repository/
│   ├── domain/                 # 领域仓储实现
│   │   ├── DictDomainRepositoryImpl.java
│   │   └── ...
│   └── aplication/             # 查询仓储实现
│       ├── DictQueryRepositoryImpl.java
│       └── ...
├── event/
│   ├── publisher/              # 事件发布器
│   ├── consumer/               # 消息消费者
│   │   ├── kafka/SysKafkaConsumer.java
│   │   └── roketmq/SysRocketmqConsumer.java
│   ├── subscribe/              # 事件订阅
│   │   ├── redis/RedisEventSubscriber.java
│   │   ├── guava/GuavaEventSubscriber.java
│   │   └── greenrobot/GreenrobotEventSubscriber.java
│   └── listener/               # Spring 事件监听
├── job/                        # 定时任务
│   └── SysJob.java
├── configuration/              # 配置
│   ├── roketmq/
│   ├── redisson/
│   └── stream/
└── factory/                    # 基础设施工厂
    └── DictFactory.java
```

### interfaces 层（API 实现）
```
com.taotao.cloud.sys.interfaces/
├── controller/
│   ├── manager/        # 管理端 REST API
│   ├── seller/         # 商家端 REST API
│   ├── buyer/          # 买家端 REST API
│   ├── inner/          # 内部 Feign 回调
│   └── test/           # 测试接口
├── rpc/                # Dubbo RPC 实现
├── grpc/               # gRPC 实现
```

### 聚合根的写法
```java
// 聚合根统一在 domain/aggregate/ 包下，以 Agg 后缀命名
public class DictAgg {
    // 聚合内实体用对象引用（非 ID）
    private List<DictItem> items;

    // 跨聚合用 ID 引用
    private Long parentId;

    // 业务行为方法（不是 setter）
    public void addItem(DictItem item) {
        // 校验业务规则
        // 修改内部状态
        // 注册领域事件
        registerEvent(new DictItemAddedEvent(this.id, item.getId()));
    }

    // 无参构造（JPA 要求），protected
    protected DictAgg() {}

    // 静态工厂方法
    public static DictAgg create(...) { ... }
}
```

### 值对象的写法
```java
// 值对象统一在 domain/valobj/ 包下
public class IdsVal {
    private final List<Long> ids;

    public IdsVal(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new DomainException("ID列表不能为空");
        }
        this.ids = Collections.unmodifiableList(ids);
    }

    // 只有 getter，无 setter（不可变）
    // 覆写 equals/hashCode（基于所有属性）
}
```

## 3. Application Service 规范

### 命令服务（写操作）— service/commad/
```java
@ApplicationService
@Service
@Transactional
public class DictCommandServiceImpl implements DictCommandService {
    private final DictDomainRepository dictDomainRepository;
    private final DictDomainService dictDomainService;

    @Override
    public DictCreateResponse createDict(DictCreateCommand command) {
        // 1. 构建领域对象（调用工厂或静态工厂方法）
        // 2. 调用领域服务（如果需要跨聚合逻辑）
        // 3. 保存聚合（仓储 save）
        // 4. 返回 DTO
        return DictCreateResponse.fromDomain(dict);
    }
}
```

### 查询服务（读操作）— service/query/
```java
@ApplicationService
@Service
@Transactional(readOnly = true)
public class DictQueryServiceImpl implements DictQueryService {
    private final DictQueryRepository dictQueryRepository;

    @Override
    public DictPageResult queryPage(DictPageQuery query) {
        // 直接返回 DTO/Result，不经过领域模型
        return dictQueryRepository.page(query);
    }
}
```

## 4. Controller 规范

```java
@RestController
@RequestMapping("/{role}/dict")
// role = manager | seller | buyer
public class DictManagerController extends BusinessController {
    // HTTP 解析 + 参数校验 + Result 封装
    // 禁止业务逻辑

    @GetMapping("/page")
    public Result<PageResult<DictPageResult>> page(DictPageQuery query) {
        return Result.success(dictQueryService.pageQuery(query));
    }

    @PostMapping
    public Result<Void> save(@Validated @RequestBody DictSaveCommand command) {
        dictCommandService.save(command);
        return Result.success();
    }
}
```

## 5. 枚举规范

```java
// 枚举在 common/enums/ 模块定义
public enum EnabledEnum {
    ENABLED(true, "启用"),
    DISABLED(false, "禁用");

    private final Boolean value;
    private final String description;
    // ...
}
```

## 6. 领域事件规范

```java
// 事件定义在 domain/event/
public class DeptCreateEvent extends DomainEvent {
    private final Long deptId;
    // 不可变，构造时赋值
}

// 事件在聚合根内 registerEvent()
// 仓储 save() 时自动 flush 发布
// 订阅在 infrastructure/event/listener/ 或 infrastructure/event/subscribe/
```

## 7. 持久化规范

```java
// PO 统一在 infrastructure/persistent/persistence/{biz}/{Name}PO.java
// 使用 JPA @Entity 注解

@Entity
@Table(name = "tt_sys_dict")
public class DictPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dict_name")
    private String dictName;

    @Column(name = "dict_code")
    private String dictCode;

    // 基础审计字段
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private Boolean isDeleted;
    private Long tenantId;
    private Integer version;
}

// Mapper 在 infrastructure/persistent/mapper/
// 使用 Mybatis-Plus 方式（interface + XML）
@Mapper
public interface DictMapper extends BaseMapper<DictPO> {
}
```

## 8. 构建与测试

```bash
# 全量构建
./gradlew build

# 运行所有测试
./gradlew test

# 运行指定模块测试
./gradlew :taotao-cloud-sys-domain:test
./gradlew :taotao-cloud-sys-infrastructure:test

# 代码质量
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain

# 本地启动
./gradlew :taotao-cloud-sys-assembly:bootRun --args='--spring.profiles.active=dev'
```

## 9. 数据库规范

### 表必备字段
```sql
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
`create_by` bigint DEFAULT NULL COMMENT '创建人ID',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_by` bigint DEFAULT NULL COMMENT '更新人ID',
`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_deleted` tinyint(1) DEFAULT 0 COMMENT '删除标记',
`tenant_id` bigint DEFAULT 0 COMMENT '租户ID',
`version` int DEFAULT 0 COMMENT '乐观锁'
```

### 禁止
- 循环中查询数据库（N+1 问题）
- `SELECT *`
- 在 Java 代码中拼接 SQL
- 跨聚合直接操作其他聚合的数据表
- Application Service 中直接调用 Mapper
