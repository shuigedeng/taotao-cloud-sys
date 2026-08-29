# taotao-cloud-sys interfaces 层 Controller 接口设计文档

> 本文档由源码自动解析生成，覆盖 `taotao-cloud-sys-interfaces` 模块 `interfaces.controller` 包下全部 Controller。

## 统计

- Controller 总数：32
- 已激活接口总数：14
- 含有效接口的 Controller：7

## 目录

| 模块 | Controller | 接口数 | 文档 |
| --- | --- | --- | --- |
| buyer | `DictBuyerController` | **2** | [DictBuyerController.md](buyer/DictBuyerController.md) |
| inner | `InnerDictController` | **4** | [DictInnerApiController.md](inner/DictInnerApiController.md) |
| manager | `AdminAliPayController` | 0 | [AliPayManagerController.md](manager/AliPayManagerController.md) |
| manager | `AdminAppController` | 0 | [AppManagerController.md](manager/AppManagerController.md) |
| manager | `AdminDataVersionLogController` | 0 | [DataVersionLogManagerController.md](manager/DataVersionLogManagerController.md) |
| manager | `AdminDeptController` | **1** | [DeptManagerController.md](manager/DeptManagerController.md) |
| manager | `AdminDictItemController` | 0 | [DictItemManagerController.md](manager/DictItemManagerController.md) |
| manager | `AdminDictController` | **1** | [DictManagerController.md](manager/DictManagerController.md) |
| manager | `AdminEmailController` | 0 | [EmailManagerController.md](manager/EmailManagerController.md) |
| manager | `AdminFileController` | 0 | [FileManagerController.md](manager/FileManagerController.md) |
| manager | `AdminI18nDataController` | 0 | [I18nDataManagerController.md](manager/I18nDataManagerController.md) |
| manager | `AdminLogController` | 0 | [LogManagerController.md](manager/LogManagerController.md) |
| manager | `AdminLoginLogController` | 0 | [LoginLogManagerController.md](manager/LoginLogManagerController.md) |
| manager | `AdminLogisticsController` | 0 | [LogisticsManagerController.md](manager/LogisticsManagerController.md) |
| manager | `AdminMonitorController` | 0 | [MonitorManagerController.md](manager/MonitorManagerController.md) |
| manager | `AdminOperateLogController` | 0 | [OperateLogManagerController.md](manager/OperateLogManagerController.md) |
| manager | `AdminOrgController` | 0 | [OrgManagerController.md](manager/OrgManagerController.md) |
| manager | `AdminPinYinController` | 0 | [PinYinManagerController.md](manager/PinYinManagerController.md) |
| manager | `AdminPositionController` | 0 | [PositionManagerController.md](manager/PositionManagerController.md) |
| manager | `AdminRegionController` | 0 | [RegionManagerController.md](manager/RegionManagerController.md) |
| manager | `AdminResourceController` | 0 | [ResourceManagerController.md](manager/ResourceManagerController.md) |
| manager | `AdminRoleController` | 0 | [RoleManagerController.md](manager/RoleManagerController.md) |
| manager | `AdminSensitiveWordsController` | 0 | [SensitiveWordsManagerController.md](manager/SensitiveWordsManagerController.md) |
| manager | `AdminSettingController` | 0 | [SettingManagerController.md](manager/SettingManagerController.md) |
| manager | `AdminSysLogLoginController` | 0 | [SysLogLoginManagerController.md](manager/SysLogLoginManagerController.md) |
| manager | `AdminSysLogController` | 0 | [SysLogManagerController.md](manager/SysLogManagerController.md) |
| manager | `AdminSystemController` | 0 | [SystemManagerController.md](manager/SystemManagerController.md) |
| manager | `AdminTestRedisController` | **3** | [TestRedisManagerController.md](manager/TestRedisManagerController.md) |
| manager | `AdminUserController` | **1** | [UserManagerController.md](manager/UserManagerController.md) |
| manager | `AdminVisitsController` | 0 | [VisitsManagerController.md](manager/VisitsManagerController.md) |
| seller | `DictSellerController` | 0 | [DictSellerController.md](seller/DictSellerController.md) |
| test | `DictMallController` | **2** | [DictMallController.md](test/DictMallController.md) |

## 通用约定

### 统一返回格式 Result<T>（Web 接口）

```json
{
  "status": "SUCCESS",
  "code": "0000000",
  "message": "请求成功",
  "timestamp": 1772632838459,
  "version": "2026.10",
  "requestId": "1772632838459",
  "data": {}
}
```

### 内部接口请求/响应包装（Request<T> / Response<T>）

- 内部远程调用接口（`*Inner*ApiController`）使用 `Request<T>` / `Response<T>` 包装，非 Web 的 `Result<T>`。

### 认证

- `@NotAuth`：免认证访问；
- 无 `@NotAuth`：需登录（由统一安全框架拦截）；
- `@PreAuthorize`：需指定权限。
