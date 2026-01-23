# Context 模式 vs DTO 模式深度对比

## 一、两种模式概述

### Context 模式（富血模型）
**数据 + 业务逻辑封装在一起**

```java
public class ProductUpdateContext {
    // 数据
    private Product originalProduct;
    private Product newProduct;
    private Map<String, FieldChange> changes;

    // 业务逻辑
    public void detectChanges() { ... }
    public boolean validate() { ... }
    public String getSummary() { ... }
}
```

### DTO 模式（贫血模型）
**数据和业务逻辑分离**

```java
// DTO：只包含数据
public class ProductUpdateDTO {
    private ProductDTO originalProduct;
    private ProductDTO newProduct;
    private List<FieldChangeDTO> changes;

    // 只有 Getter/Setter
}

// Service：包含业务逻辑
public class ProductUpdateService {
    public List<FieldChangeDTO> detectChanges(ProductUpdateDTO dto) { ... }
    public boolean validate(ProductUpdateDTO dto) { ... }
}
```

---

## 二、使用方式对比

### Context 模式

```java
// 1. 创建 Context
ProductUpdateContext context = new ProductUpdateContext(...);
context.setOriginalProduct(original);
context.setNewProduct(updated);

// 2. 调用 Context 的方法（业务逻辑在 Context 内部）
context.detectChanges();  // Context 自己检测变更
boolean valid = context.validate();  // Context 自己验证
String summary = context.getSummary();  // Context 自己生成摘要

// 3. 获取结果
if (valid) {
    // 执行更新
}
```

**特点**：
- ✅ 调用简单，一行代码搞定
- ✅ 自解释性强，看 Context 就知道能做什么
- ✅ 业务逻辑封装在 Context 内部

### DTO 模式

```java
// 1. 创建 DTO
ProductUpdateDTO dto = new ProductUpdateDTO();
dto.setOriginalProduct(original);
dto.setNewProduct(updated);

// 2. 调用 Service 的方法（业务逻辑在 Service 中）
List<FieldChangeDTO> changes = service.detectChanges(dto);
dto.setChanges(changes);  // 需要手动设置

boolean valid = service.validate(dto);
dto.setValidated(valid);  // 需要手动设置

// 3. 获取结果
if (dto.getValidated()) {
    // 执行更新
}
```

**特点**：
- ❌ 调用复杂，需要多次调用 Service
- ❌ 需要手动设置结果到 DTO
- ❌ 业务逻辑分散在 Service 中

---

## 三、详细对比表

| 维度 | Context 模式（富血模型） | DTO 模式（贫血模型） | 推荐 |
|------|------------------------|-------------------|------|
| **代码组织** | ✅ 业务逻辑封装在 Context 内<br>✅ 高内聚 | ❌ 业务逻辑分散在 Service 中<br>❌ 低内聚 | **Context** |
| **易用性** | ✅ 调用简单：`context.validate()`<br>✅ 自解释性强 | ❌ 调用复杂：需要多次调用 Service<br>❌ 需要查看 Service 才知道怎么用 | **Context** |
| **可维护性** | ✅ 修改业务逻辑只需改 Context<br>✅ 业务逻辑集中 | ❌ 修改业务逻辑需要改 Service<br>❌ 业务逻辑分散 | **Context** |
| **可测试性** | ✅ 测试 Context 即可<br>✅ 单元测试简单 | ❌ 需要测试 Service + DTO<br>❌ 单元测试复杂 | **Context** |
| **序列化** | ❌ 包含业务逻辑，序列化复杂<br>❌ 不适合网络传输 | ✅ 纯数据，易于序列化<br>✅ 适合网络传输 | **DTO** |
| **学习成本** | ❌ 需要理解 Context 的设计<br>❌ 新手可能不习惯 | ✅ 简单直观，易于理解<br>✅ 符合传统习惯 | **DTO** |
| **灵活性** | ❌ 业务逻辑固定在 Context 中<br>❌ 扩展需要修改 Context | ✅ 业务逻辑可以灵活组合<br>✅ 扩展只需添加 Service 方法 | **DTO** |
| **性能** | ✅ 方法调用开销小<br>✅ 内存占用相对较小 | ❌ 多次方法调用开销大<br>❌ 需要额外的 Service 对象 | **Context** |

---

## 四、优缺点总结

### Context 模式（富血模型）

#### 优点
1. **高内聚** - 数据和行为在一起
2. **易用性强** - 调用简单，`context.validate()`
3. **易维护** - 修改业务逻辑只需改一个地方
4. **易测试** - 单元测试只需测试 Context
5. **封装性好** - 业务逻辑不外露
6. **自解释性强** - 看 Context 就知道能做什么

#### 缺点
1. **不易序列化** - 包含业务逻辑，序列化复杂
2. **不适合网络传输** - 不适合跨服务传输
3. **学习成本高** - 需要理解 Context 的设计
4. **灵活性差** - 业务逻辑固定在 Context 中

---

### DTO 模式（贫血模型）

#### 优点
1. **易序列化** - 纯数据，易于序列化
2. **适合网络传输** - 适合跨服务传输（微服务、RPC）
3. **学习成本低** - 简单直观，易于理解
4. **灵活性高** - 业务逻辑可以灵活组合

#### 缺点
1. **低内聚** - 数据和行为分离
2. **易用性差** - 调用复杂，需要多次调用 Service
3. **难维护** - 业务逻辑分散在各个 Service 中
4. **难测试** - 需要测试 Service + DTO
5. **封装性差** - 业务逻辑外露
6. **自解释性差** - 需要查看 Service 才知道怎么用

---

## 五、适用场景

### ✅ 推荐使用 Context 模式的场景

1. **单体应用或模块内部使用**
   - 不需要跨服务传输
   - 业务逻辑复杂，需要封装

2. **需要高内聚的设计**
   - 数据和行为紧密相关
   - 需要封装业务逻辑

3. **业务逻辑相对稳定**
   - 不需要频繁修改业务逻辑
   - 业务规则明确

4. **需要追踪状态和上下文信息**
   - 需要记录操作历史
   - 需要追踪变更

5. **团队倾向面向对象设计**
   - 团队理解并接受富血模型
   - 追求高质量代码

---

### ✅ 推荐使用 DTO 模式的场景

1. **跨服务传输数据**
   - 微服务架构
   - RPC 调用
   - RESTful API

2. **需要序列化和反序列化**
   - JSON 序列化
   - Protobuf 序列化
   - 消息队列传输

3. **业务逻辑需要灵活组合**
   - 业务规则经常变化
   - 需要动态组合业务逻辑

4. **团队习惯贫血模型**
   - 团队不熟悉富血模型
   - 项目已经使用贫血模型

5. **需要与第三方系统集成**
   - 第三方 API 调用
   - 数据交换

---

## 六、倾向性建议

### ⭐ 推荐方案：Context 模式（富血模型）

**理由**：

#### 1. 更符合面向对象设计原则
- **高内聚**：数据和行为在一起
- **封装性**：业务逻辑不外露
- **单一职责**：Context 负责自己的业务逻辑

#### 2. 更易于维护和扩展
- 修改业务逻辑只需改一个地方
- 新增功能只需在 Context 中添加方法
- 业务逻辑集中，易于理解

#### 3. 更易于使用
- 调用简单：`context.validate()`
- 自解释性强：看 Context 就知道能做什么
- 不需要查看 Service 代码

#### 4. 更易于测试
- 单元测试只需测试 Context
- 不需要 Mock Service
- 测试代码简单

---

### ⚠️ 例外情况：使用 DTO 模式

以下场景建议使用 DTO：

1. **跨服务传输**（微服务、RPC）
2. **需要序列化**（JSON、Protobuf）
3. **与第三方系统集成**
4. **团队强烈倾向贫血模型**

---

## 七、混合方案（最佳实践）

在实际项目中，可以结合两种方式：

### 内部使用 Context 模式
- 业务逻辑封装在 Context 中
- 高内聚、易维护

### 对外使用 DTO 模式
- API 接口使用 DTO 传输
- 易于序列化和传输

### 转换层
- DTO -> Context（接收请求时）
- Context -> DTO（返回响应时）

### 示例代码

```java
// API 层：使用 DTO
@PostMapping("/product/update")
public Result updateProduct(@RequestBody ProductUpdateDTO dto) {
    // 1. 转换：DTO -> Context
    ProductUpdateContext context = convertToContext(dto);

    // 2. 业务层：使用 Context
    context.detectChanges();
    if (!context.validate()) {
        return Result.fail(context.getErrors());
    }

    // 3. 执行更新
    productService.update(context);

    // 4. 转换：Context -> DTO
    ProductUpdateDTO result = convertToDTO(context);
    return Result.success(result);
}

// 转换方法
private ProductUpdateContext convertToContext(ProductUpdateDTO dto) {
    ProductUpdateContext context = new ProductUpdateContext(...);
    context.setOriginalProduct(dto.getOriginalProduct());
    context.setNewProduct(dto.getNewProduct());
    context.addPermissions(dto.getPermissions());
    return context;
}

private ProductUpdateDTO convertToDTO(ProductUpdateContext context) {
    ProductUpdateDTO dto = new ProductUpdateDTO();
    dto.setChanges(context.getChanges());
    dto.setErrors(context.getErrors());
    dto.setValidated(context.isValidated());
    return dto;
}
```

---

## 八、实际案例对比

### 案例：商品价格修改

#### Context 模式

```java
// 1. 创建 Context
ProductUpdateContext context = new ProductUpdateContext(...);
context.setOriginalProduct(original);
context.setNewProduct(updated);
context.addPermissions("product:price:update");

// 2. 一行代码完成所有验证
if (!context.validate()) {
    return context.getErrors();  // 所有错误都在这里
}

// 3. 检查是否需要审批
if (context.isNeedApproval()) {
    submitApproval(context);
}

// 4. 执行更新
productRepository.update(context.getNewProduct());

// 5. 记录审计日志
auditLogService.log(context);  // Context 包含所有信息
```

**代码行数**：~10 行
**复杂度**：低
**可读性**：高

---

#### DTO 模式

```java
// 1. 创建 DTO
ProductUpdateDTO dto = new ProductUpdateDTO();
dto.setOriginalProduct(original);
dto.setNewProduct(updated);
dto.setPermissions(Arrays.asList("product:price:update"));

// 2. 调用 Service 检测变更
List<FieldChangeDTO> changes = service.detectChanges(dto);
dto.setChanges(changes);  // 需要手动设置

// 3. 调用 Service 验证
List<ValidationErrorDTO> errors = service.validatePermissions(dto);
errors.addAll(service.validateBusinessRules(dto));
errors.addAll(service.validateDataIntegrity(dto));
dto.setErrors(errors);  // 需要手动设置

if (!errors.isEmpty()) {
    return errors;
}

// 4. 检查是否需要审批
if (service.needApproval(dto)) {
    dto.setNeedApproval(true);
    String approvalId = service.submitApproval(dto);
    dto.setApprovalId(approvalId);
}

// 5. 执行更新
productRepository.update(dto.getNewProduct());

// 6. 记录审计日志
auditLogService.log(dto);
```

**代码行数**：~20 行
**复杂度**：高
**可读性**：中

---

## 九、总结

### 核心观点

1. **Context 模式（富血模型）更优**
   - 符合面向对象设计原则
   - 高内聚、易维护、易测试
   - 推荐在单体应用或模块内部使用

2. **DTO 模式（贫血模型）有其适用场景**
   - 适合跨服务传输
   - 适合需要序列化的场景
   - 适合与第三方系统集成

3. **混合方案是最佳实践**
   - 内部用 Context（富血）
   - 对外用 DTO（贫血）
   - 通过转换层连接两者

### 推荐方案

```
┌─────────────────────────────────────────┐
│         推荐方案                        │
├─────────────────────────────────────────┤
│                                         │
│  内部：Context 模式（富血模型）         │
│  ├─ 业务逻辑封装                        │
│  ├─ 高内聚、易维护                      │
│  └─ 易于测试                            │
│                                         │
│  对外：DTO 模式（贫血模型）             │
│  ├─ API 接口                            │
│  ├─ 易于序列化                          │
│  └─ 跨服务传输                          │
│                                         │
│  转换层：DTO <-> Context                │
│  ├─ 接收请求：DTO -> Context            │
│  └─ 返回响应：Context -> DTO            │
│                                         │
└─────────────────────────────────────────┘
```

---

## 十、代码示例

完整示例代码位于：
```
/Users/maxingfang/IdeaProjects/exercises/design-patterns/context-pattern/
src/main/java/manfred/end/context/business/product/
```

文件列表：
- `ProductUpdateContext.java` - Context 模式实现
- `ProductUpdateDTO.java` - DTO 模式实现
- `ProductUpdateServiceWithDTO.java` - DTO 模式的 Service
- `ContextVsDTOComparison.java` - 对比示例

运行示例：
```bash
cd design-patterns/context-pattern
java -cp target/classes manfred.end.context.business.product.ContextVsDTOComparison
```

---

**结论：推荐使用 Context 模式（富血模型），在需要跨服务传输时使用 DTO 模式（贫血模型）。**

