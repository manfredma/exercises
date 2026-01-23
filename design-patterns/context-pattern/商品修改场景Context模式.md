# 商品修改场景的 Context 模式应用

## 一、场景分析

### 问题描述
修改商品信息时，商品是一个复杂的结构体，包含：
- 基本信息（名称、编码、分类、品牌）
- 价格信息（售价、成本、市场价）
- 库存信息（库存、最小库存、最大库存）
- 规格信息（单位、重量、尺寸）
- 状态信息（上架状态、新品、热卖）
- 描述信息（描述、图片、属性）
- 供应商信息

### 核心挑战
1. **变更追踪** - 需要知道哪些字段被修改了
2. **业务规则验证** - 价格不能低于成本、库存不能为负等
3. **权限控制** - 不同字段需要不同权限
4. **审批流程** - 重要修改（如大幅降价）需要审批
5. **审计日志** - 完整记录修改历史

---

## 二、Context 类型选择

### 答案：OperationContext + ValidationContext

这是一个 **OperationContext（操作上下文）** 和 **ValidationContext（验证上下文）** 的组合。

#### 为什么不是其他 Context？

| Context 类型 | 是否适用 | 原因 |
|-------------|---------|------|
| RequestContext | ❌ | 只管理请求信息，不管理业务操作 |
| TransactionContext | ❌ | 管理分布式事务，不管理单个实体修改 |
| PriceCalculationContext | ❌ | 只管理价格计算，不管理整体修改 |
| **OperationContext** | ✅ | **管理操作过程、变更追踪、审计日志** |
| **ValidationContext** | ✅ | **管理验证规则、错误收集** |

---

## 三、核心设计

### 1. Context 结构

```java
public class ProductUpdateContext {
    // 基础信息
    private String operationId;
    private String productId;
    private Long operatorId;

    // 商品信息（原始 + 新值）
    private Product originalProduct;
    private Product newProduct;

    // 变更追踪
    private Map<String, FieldChange> changes;

    // 验证信息
    private List<ValidationError> errors;
    private List<ValidationWarning> warnings;

    // 权限信息
    private Set<String> permissions;

    // 审批信息
    private boolean needApproval;
    private String approvalId;
}
```

### 2. 核心功能

#### 功能1：自动检测变更
```java
// 自动比较新旧值，记录所有修改
context.detectChanges();

// 结果
changes = {
    "productName": "iPhone 15 Pro" -> "iPhone 15 Pro Max",
    "price": 7999 -> 6999,
    "stock": 100 -> 150
}
```

#### 功能2：业务规则验证
```java
// 验证修改
boolean valid = context.validate();

// 验证规则
- 价格不能低于成本
- 库存不能为负数
- 最小库存不能大于最大库存
- 商品名称不能为空
- 价格大幅变动需要审批（>20%）
```

#### 功能3：权限验证
```java
// 不同字段需要不同权限
RESTRICTED_FIELDS = {
    "price": "product:price:update",
    "cost": "product:cost:update",
    "status": "product:status:update"
}

// 验证权限
if (!context.hasPermission("product:price:update")) {
    addError("price", "没有权限修改售价");
}
```

#### 功能4：审批流程
```java
// 价格变动超过 20% 需要审批
if (priceChangeRate > 0.2) {
    context.setNeedApproval(true);
    String approvalId = submitApproval(context);
}
```

#### 功能5：审计日志
```java
// 完整记录修改历史
AuditLog log = {
    operationId: "OP-001",
    operatorId: 10001,
    operatorName: "张三",
    productId: "PROD-12345",
    changes: [
        "商品名称: iPhone 15 Pro -> iPhone 15 Pro Max",
        "售价: 7999 -> 6999",
        "库存: 100 -> 150"
    ],
    reason: "双11促销活动",
    timestamp: "2023-11-15 10:30:00"
}
```

---

## 四、使用示例

### 场景1：普通字段修改（成功）

```java
// 1. 创建上下文
ProductUpdateContext context = new ProductUpdateContext(
    "OP-001", "PROD-12345", 10001L, "张三");

// 2. 设置权限
context.addPermissions("product:update", "product:view");

// 3. 设置原始商品
Product original = loadProduct("PROD-12345");
context.setOriginalProduct(original);

// 4. 设置新商品（修改部分字段）
Product updated = original.clone();
updated.setProductName("iPhone 15 Pro Max");
updated.setStock(150);
updated.setIsHot(true);
context.setNewProduct(updated);

// 5. 设置修改原因
context.setReason("更新商品信息");

// 6. 执行更新
productService.updateProduct(context);
```

**输出**：
```
1. 检测变更...
   检测到 3 个字段变更

2. 验证修改...
   ✅ 验证通过

3. 执行更新...
   UPDATE product SET
     productName = iPhone 15 Pro Max
     stock = 150
     isHot = true
   WHERE product_id = PROD-12345
   ✅ 更新成功

4. 记录审计日志...
   [AuditLog] 操作人=张三, 商品ID=PROD-12345,
   修改=修改了 3 个字段: 商品名称、库存、热卖
   ✅ 日志已记录
```

---

### 场景2：价格修改（需要审批）

```java
// 价格从 ¥7999 降到 ¥5999，降幅超过 20%
Product updated = original.clone();
updated.setPrice(new BigDecimal("5999"));
context.setNewProduct(updated);
context.setReason("双11促销活动");

productService.updateProduct(context);
```

**输出**：
```
1. 检测变更...
   检测到 1 个字段变更

2. 验证修改...
   ⚠️  [WARNING] price: 价格变动超过20%，需要审批
   ✅ 验证通过

3. 提交审批...
   审批单号: APPROVAL-1699999999999
   等待审批通过后执行更新
```

---

### 场景3：违反业务规则（失败）

```java
// 尝试将售价设置为低于成本价
Product updated = original.clone();
updated.setPrice(new BigDecimal("4000"));  // 售价 4000
updated.setCost(new BigDecimal("5000"));   // 成本 5000
updated.setStock(-10);  // 库存为负数
context.setNewProduct(updated);

productService.updateProduct(context);
```

**输出**：
```
1. 检测变更...
   检测到 3 个字段变更

2. 验证修改...
   ❌ 验证失败

【验证错误】(2 个)
  ❌ [PRICE_BELOW_COST] price: 售价不能低于成本价
  ❌ [INVALID_STOCK] stock: 库存不能为负数
```

---

### 场景4：权限不足（失败）

```java
// 普通用户尝试修改价格（需要特殊权限）
context.addPermissions("product:update", "product:view");
// 注意：没有 "product:price:update" 权限

Product updated = original.clone();
updated.setPrice(new BigDecimal("6999"));
context.setNewProduct(updated);

productService.updateProduct(context);
```

**输出**：
```
1. 检测变更...
   检测到 1 个字段变更

2. 验证修改...
   ❌ 验证失败

【验证错误】(1 个)
  ❌ [PERMISSION_DENIED] price: 没有权限修改售价
```

---

## 五、核心优势

### 1. 统一验证逻辑
```java
// ❌ 没有 Context - 验证代码分散
public void updateProduct(Product product) {
    if (product.getPrice().compareTo(product.getCost()) < 0) {
        throw new Exception("价格不能低于成本");
    }
    if (product.getStock() < 0) {
        throw new Exception("库存不能为负数");
    }
    // 验证代码分散在各处...
}

// ✅ 有了 Context - 验证逻辑集中
public void updateProduct(ProductUpdateContext context) {
    if (!context.validate()) {
        // 所有验证错误都在 context 中
        return context.getErrors();
    }
}
```

### 2. 完整审计追踪
```java
// 自动记录所有修改
context.getChanges() = {
    "商品名称": "iPhone 15 Pro" -> "iPhone 15 Pro Max",
    "售价": 7999 -> 6999,
    "库存": 100 -> 150
}

// 审计日志
{
    "who": "张三",
    "when": "2023-11-15 10:30:00",
    "what": "修改了 3 个字段: 商品名称、售价、库存",
    "why": "双11促销活动"
}
```

### 3. 灵活权限控制
```java
// 字段级权限
RESTRICTED_FIELDS = {
    "price": "product:price:update",      // 价格需要特殊权限
    "cost": "product:cost:update",        // 成本需要特殊权限
    "status": "product:status:update"     // 状态需要特殊权限
}

// 自动验证权限
if (!hasPermission(requiredPermission)) {
    addError("没有权限修改该字段");
}
```

### 4. 支持审批流程
```java
// 重要修改需要审批
if (priceChangeRate > 0.2) {
    context.setNeedApproval(true);
    submitApproval(context);
    return "等待审批";
}
```

### 5. 易于扩展
```java
// 新增字段验证
private void validateBusinessRules() {
    // 原有规则...

    // 新增规则：商品重量不能超过 100kg
    if (newProduct.getWeight().compareTo(new BigDecimal("100")) > 0) {
        addError("weight", "商品重量不能超过100kg", "INVALID_WEIGHT");
    }
}
```

---

## 六、适用场景

### 1. 电商系统
- 商品信息修改
- SKU 信息修改
- 店铺信息修改

### 2. ERP 系统
- 物料信息修改
- 供应商信息修改
- 客户信息修改

### 3. CRM 系统
- 客户资料修改
- 联系人信息修改
- 商机信息修改

### 4. 库存系统
- 库存信息修改
- 仓库信息修改
- 货位信息修改

### 5. 配置系统
- 系统配置修改
- 参数配置修改
- 规则配置修改

---

## 七、设计要点

### 1. 变更追踪
```java
// 自动比较新旧值
private void compareField(String fieldName, String fieldLabel,
                         Object oldValue, Object newValue) {
    FieldChange change = new FieldChange(fieldName, fieldLabel, oldValue, newValue);
    if (change.isChanged()) {
        changes.put(fieldName, change);
    }
}
```

### 2. 验证规则
```java
// 业务规则验证
private void validateBusinessRules() {
    // 规则1：价格不能低于成本
    if (price.compareTo(cost) < 0) {
        addError("price", "售价不能低于成本价", "PRICE_BELOW_COST");
    }

    // 规则2：库存不能为负数
    if (stock < 0) {
        addError("stock", "库存不能为负数", "INVALID_STOCK");
    }

    // 规则3：价格大幅变动需要审批
    if (priceChangeRate > 0.2) {
        needApproval = true;
        addWarning("price", "价格变动超过20%，需要审批");
    }
}
```

### 3. 权限控制
```java
// 字段级权限验证
private void validatePermissions() {
    for (FieldChange change : changes.values()) {
        String requiredPermission = RESTRICTED_FIELDS.get(change.getFieldName());
        if (requiredPermission != null && !permissions.contains(requiredPermission)) {
            addError(change.getFieldName(),
                    "没有权限修改" + change.getFieldLabel(),
                    "PERMISSION_DENIED");
        }
    }
}
```

### 4. 审计日志
```java
// 记录完整的修改历史
private void logAudit(ProductUpdateContext context) {
    AuditLog log = new AuditLog();
    log.setOperationId(context.getOperationId());
    log.setOperatorId(context.getOperatorId());
    log.setOperatorName(context.getOperatorName());
    log.setProductId(context.getProductId());
    log.setChanges(context.getChanges());
    log.setReason(context.getReason());
    log.setTimestamp(new Date());

    auditLogRepository.save(log);
}
```

---

## 八、总结

### Context 类型
**OperationContext（操作上下文）+ ValidationContext（验证上下文）**

### 核心价值
1. ✅ **自动检测变更** - 比较新旧值，记录所有修改
2. ✅ **业务规则验证** - 价格、库存、状态等规则校验
3. ✅ **权限验证** - 不同字段需要不同权限
4. ✅ **审批流程** - 重要修改需要审批
5. ✅ **审计日志** - 完整记录修改历史

### 适用场景
- ✅ 复杂结构体修改
- ✅ 需要变更追踪
- ✅ 需要业务规则验证
- ✅ 需要权限控制
- ✅ 需要审计日志

### 最佳实践
1. 使用 Builder 模式构建 Context
2. 使用 Map 存储变更记录
3. 使用 List 收集验证错误
4. 使用 Set 管理权限
5. 提供详细的错误信息

---

## 九、代码示例

完整示例代码位于：
```
/Users/maxingfang/IdeaProjects/exercises/design-patterns/context-pattern/
src/main/java/manfred/end/context/business/product/
```

运行示例：
```bash
cd design-patterns/context-pattern
javac -d target/classes src/main/java/manfred/end/context/business/product/*.java
java -cp target/classes manfred.end.context.business.product.ProductUpdateExample
```

---

**ProductUpdateContext 是处理复杂结构体修改的最佳实践！**

