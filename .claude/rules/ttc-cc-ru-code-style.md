# 代码风格规范

## 格式化规则
- 缩进：4 空格（不使用 Tab）
- 行宽：120 字符
- 大括号：K&R 风格（左括号不换行）
- 文件编码：UTF-8

## 命名约定
- **类名**：PascalCase（`DictCommandService`, `AdminDictController`）
- **方法**：小驼峰，动词开头（`queryByCode`, `saveDict`, `pageQuery`）
- **常量**：UPPER_SNAKE_CASE（`MAX_BATCH_SIZE`）
- **枚举**：PascalCase，字段 UPPER_SNAKE_CASE
- **包名**：全小写（`com.taotao.cloud.sys.domain.aggregate`）

## 导入顺序
1. Java 标准库 (`java.*`, `javax.*`)
2. 第三方库 (`org.*`, `com.*`)
3. Spring 框架 (`org.springframework.*`)
4. 项目内部包 (`com.taotao.cloud.sys.*`, `com.taotao.boot.*`)
5. 静态导入

## Lombok 使用规范
```java
@Getter          // 所有字段生成 getter
@Setter          // 需要时使用（值对象不应使用）
@ToString        // toString
@EqualsAndHashCode  // equals/hashCode
@Accessors(chain = true)  // 链式调用
@Slf4j           // 日志
@RequiredArgsConstructor  // final 字段构造器注入（推荐）
```

## MapStruct 使用规范
```java
@Mapper(componentModel = "spring", 
        nullValueIterableMappingStrategy = RETURN_DEFAULT,
        nullValueMapMappingStrategy = RETURN_DEFAULT)
public interface DictAssembler {
    DictPO toPo(DictAgg dict);
    DictAgg toDomain(DictPO po);
}
```

## Record Builder 使用规范
```java
@RecordBuilder
public record DictCreateCommand(
    @NotBlank String dictName,
    @NotBlank String dictCode,
    String remark
) {}
```

## 代码示例
```java
@Slf4j
@Service
@RequiredArgsConstructor
public class DictCommandServiceImpl implements DictCommandService {
    private final DictDomainRepository dictDomainRepository;

    @Override
    @Transactional
    public DictCreateResponse createDict(DictCreateCommand command) {
        log.info("Creating dict: {}", command.dictCode());
        DictAgg dict = DictAgg.create(command.dictName(), command.dictCode());
        dictDomainRepository.save(dict);
        return new DictCreateResponse(dict.getId());
    }
}
```
