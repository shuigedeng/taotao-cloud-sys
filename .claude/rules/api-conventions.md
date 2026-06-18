# API 设计规范

## RESTful 约定

### URL 前缀按角色分包
```
/{role}/{resource}
# role = manager | seller | buyer | inner
```

| 端 | 前缀 | Controller 包 |
|----|------|--------------|
| 管理端 | `/manager/**` | `controller/manager/` |
| 商家端 | `/seller/**` | `controller/seller/` |
| 买家端 | `/buyer/**` | `controller/buyer/` |
| 内部调用 | `/inner/**` | `controller/inner/` |

### 资源命名
- 使用名词：`/manager/dict`, `/manager/dict/item`
- 避免动词：❌ `/getDict`, ✅ `GET /manager/dict/{id}`

### HTTP 方法
| 方法 | 用途 | 示例 |
|------|------|------|
| GET | 查询 | `/manager/dict/page`, `/manager/dict/{id}` |
| POST | 创建 | `/manager/dict` |
| PUT | 全量更新 | `/manager/dict/{id}` |
| DELETE | 删除 | `/manager/dict/{id}` |

### 响应格式
```json
{
  "code": 200,
  "message": "success",
  "data": {},
  "timestamp": "2026-06-18T00:00:00Z"
}
```

### 分页响应
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [],
    "total": 100,
    "size": 20,
    "current": 1,
    "pages": 5
  }
}
```

### Controller 规范
```java
@RestController
@RequestMapping("/manager/dict")
@Tag(name = "字典管理")
public class DictManagerController extends BusinessController {
    @GetMapping("/page")
    public Result<PageResult<DictPageResult>> page(DictPageQuery query) {
        return Result.success(dictQueryService.pageQuery(query));
    }

    @PostMapping
    @Operation(summary = "创建字典")
    public Result<Void> save(@Validated @RequestBody DictSaveCommand command) {
        dictCommandService.save(command);
        return Result.success();
    }
}
```

### RPC 接口（Dubbo）
```java
// api/rpc/query/ — 接口定义
public interface DictQueryRpcService {
    DictRpcResponse findByCode(String code);
}

// interfaces/rpc/ — 实现
@DubboService
public class DictQueryRpcServiceImpl implements DictQueryRpcService {
    // ...
}
```

### gRPC 接口
- 定义在 `api/proto/`（`.proto` 文件）
- 实现 `interfaces/grpc/`

### 参数校验
```java
public class DictSaveCommand {
    @NotBlank(message = "字典名称不能为空")
    @Size(max = 100)
    private String dictName;

    @NotBlank(message = "字典编码不能为空")
    @Size(max = 100)
    private String dictCode;
}
```
