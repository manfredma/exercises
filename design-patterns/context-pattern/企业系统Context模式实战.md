# 企业系统 Context 模式实战指南

## 一、概述

Context 模式在企业业务系统中有广泛应用，本文档总结了 12 种典型的业务场景。

---

## 二、12 种典型业务场景

### 场景对比表

| 序号 | Context 类型 | 中文名称 | 应用场景 | 常用度 |
|------|-------------|---------|---------|--------|
| 1 | RequestContext | 请求上下文 | Web请求、分布式追踪、多租户 | ⭐⭐⭐⭐⭐ |
| 2 | TransactionContext | 交易上下文 | 分布式事务、交易流程追踪 | ⭐⭐⭐⭐ |
| 3 | ApprovalContext | 审批上下文 | 多级审批、审批历史追踪 | ⭐⭐⭐⭐ |
| 4 | PriceCalculationContext | 价格计算上下文 | 电商价格、保险费率、佣金 | ⭐⭐⭐⭐ |
| 5 | ImportExportContext | 导入导出上下文 | 批量数据处理、进度追踪 | ⭐⭐⭐ |
| 6 | RuleEngineContext | 规则引擎上下文 | 业务规则执行、规则链路 | ⭐⭐⭐⭐ |
| 7 | TenantContext | 多租户上下文 | 租户隔离、租户配置 | ⭐⭐⭐⭐ |
| 8 | SecurityContext | 安全上下文 | 认证信息、权限验证 | ⭐⭐⭐⭐⭐ |
| 9 | CacheContext | 缓存上下文 | 缓存策略、缓存失效 | ⭐⭐⭐ |
| 10 | ValidationContext | 验证上下文 | 数据验证、错误收集 | ⭐⭐⭐ |
| 11 | NotificationContext | 通知上下文 | 消息推送、通知模板 | ⭐⭐⭐ |
| 12 | ReportContext | 报表上下文 | 报表生成、数据权限 | ⭐⭐⭐ |

---

## 三、详细场景分析

### 1. RequestContext（请求上下文）⭐⭐⭐⭐⭐

**最常用的 Context，几乎所有 Web 应用都需要**

#### 核心功能
- 传递用户信息（userId、username、roles）
- 传递租户信息（tenantId、tenantName）
- 分布式追踪（traceId、spanId）
- 权限验证（permissions）
- 日志记录（自动记录用户、租户）

#### 实际案例
```java
// 美团外卖
RequestContext context = RequestContext.builder()
    .userId(10001L)
    .cityId(110000)  // 北京
    .deviceType("APP")
    .build();

// 阿里云
RequestContext context = RequestContext.builder()
    .tenantId("TENANT-001")
    .accessKey("AK-XXX")
    .region("cn-beijing")
    .build();

// 企业 ERP
RequestContext context = RequestContext.builder()
    .employeeId(10001L)
    .departmentId(1001L)
    .permissions("order:create", "order:view")
    .build();
```

#### 代码示例
```java
// 1. 在 Filter 中设置
RequestContext context = RequestContext.builder()
    .requestId("REQ-001")
    .traceId("TRACE-ABC123")
    .userId(10001L)
    .tenantId("TENANT-001")
    .build();
RequestContext.set(context);

// 2. 业务代码自动获取
public class OrderService {
    public void createOrder(Order order) {
        // 自动获取当前用户
        Long userId = RequestContext.getCurrentUserId();
        String tenantId = RequestContext.getCurrentTenantId();

        // 检查权限
        if (!RequestContext.get().hasPermission("order:create")) {
            throw new PermissionDeniedException();
        }

        // 业务逻辑...
    }
}

// 3. 请求结束清理
RequestContext.clear();
```

---

### 2. TransactionContext（交易上下文）⭐⭐⭐⭐

**管理分布式事务和交易流程**

#### 核心功能
- 分布式事务管理（TCC、Saga）
- 交易流程追踪（订单、支付、发货）
- 交易日志记录
- 补偿操作管理

#### 实际案例
```java
// 支付宝支付流程
TransactionContext txContext = TransactionContext.builder()
    .transactionId("TX-20231115-001")
    .userId(10001L)
    .amount(new BigDecimal("100.00"))
    .type("PAYMENT")
    .build();

// 步骤1：扣款
txContext.addStep("DEDUCT", () -> accountService.deduct(userId, amount));

// 步骤2：记账
txContext.addStep("RECORD", () -> ledgerService.record(txId, amount));

// 步骤3：通知
txContext.addStep("NOTIFY", () -> notifyService.send(userId, "支付成功"));

// 执行事务
txContext.execute();
```

---

### 3. ApprovalContext（审批上下文）⭐⭐⭐⭐

**管理多级审批流程**

#### 核心功能
- 多级审批流程（员工 -> 主管 -> 经理 -> 总监）
- 审批历史追踪
- 审批权限验证
- 审批超时提醒

#### 实际案例
```java
// OA 系统请假审批
ApprovalContext context = ApprovalContext.builder()
    .requestId("LEAVE-001")
    .applicantId(10001L)
    .type("LEAVE")
    .build();

// 添加审批节点
context.addNode("主管审批", 20001L);
context.addNode("经理审批", 30001L);
context.addNode("总监审批", 40001L);

// 提交审批
context.submit();

// 审批操作
context.approve(20001L, "同意");  // 主管审批
context.approve(30001L, "同意");  // 经理审批
context.approve(40001L, "同意");  // 总监审批

// 查看审批历史
List<ApprovalHistory> history = context.getHistory();
```

---

### 4. PriceCalculationContext（价格计算上下文）⭐⭐⭐⭐

**复杂的价格计算逻辑**

#### 核心功能
- 电商价格计算（原价、折扣、优惠券、会员价）
- 保险费率计算（基础费率、风险系数、优惠）
- 佣金计算（基础佣金、阶梯佣金、奖励）
- 积分计算（基础积分、活动加倍、会员加成）

#### 实际案例
```java
// 淘宝商品价格计算
PriceCalculationContext context = new PriceCalculationContext("CALC-001", userId, "VIP");

// 添加商品
context.addProduct("P001", "iPhone 15 Pro", new BigDecimal("7999"), 1);

// 添加优惠
context.setMemberDiscount(new BigDecimal("0.95"));  // VIP 95折
context.addFullReduction(new BigDecimal("5000"), new BigDecimal("500"));  // 满5000减500
context.addCoupon(new Coupon("COUPON-001", "新用户券", "FIXED", new BigDecimal("200")));

// 执行计算
context.calculate();

// 获取结果
BigDecimal finalPrice = context.getFinalPrice();  // 最终价格
List<CalculationStep> steps = context.getSteps();  // 计算明细
```

**计算过程示例**：
```
原价: ¥7999
- VIP 95折: ¥7999 * 0.95 = ¥7599
- 满5000减500: ¥7599 - ¥500 = ¥7099
- 新用户券: ¥7099 - ¥200 = ¥6899
实付: ¥6899
```

---

### 5. RuleEngineContext（规则引擎上下文）⭐⭐⭐⭐

**业务规则执行和管理**

#### 核心功能
- 业务规则执行（风控规则、营销规则）
- 规则链路追踪
- 规则结果缓存
- 规则优先级管理

#### 实际案例
```java
// 风控系统
RuleEngineContext context = RuleEngineContext.builder()
    .userId(10001L)
    .orderId("ORDER-001")
    .amount(new BigDecimal("10000"))
    .build();

// 添加规则
context.addRule(new BlacklistRule());      // 黑名单规则
context.addRule(new FrequencyRule());      // 频率限制规则
context.addRule(new AmountLimitRule());    // 金额限制规则
context.addRule(new DeviceRiskRule());     // 设备风险规则

// 执行规则
RuleResult result = context.execute();

if (result.isPass()) {
    // 通过风控
} else {
    // 拒绝交易
    String reason = result.getRejectReason();
}
```

---

### 6. ImportExportContext（导入导出上下文）⭐⭐⭐

**批量数据处理**

#### 核心功能
- 批量数据导入（Excel、CSV）
- 批量数据导出
- 进度追踪
- 错误收集和报告

#### 实际案例
```java
// ERP 系统批量导入商品
ImportContext context = ImportContext.builder()
    .importId("IMPORT-001")
    .userId(10001L)
    .fileName("products.xlsx")
    .totalRows(10000)
    .build();

// 开始导入
context.start();

// 处理每一行
for (int i = 0; i < totalRows; i++) {
    try {
        Product product = parseRow(row);
        productService.save(product);
        context.incrementSuccess();
    } catch (Exception e) {
        context.addError(i, e.getMessage());
    }

    // 更新进度
    context.updateProgress(i + 1);
}

// 完成导入
context.complete();

// 获取结果
ImportResult result = context.getResult();
System.out.println("成功: " + result.getSuccessCount());
System.out.println("失败: " + result.getErrorCount());
System.out.println("耗时: " + result.getElapsedTime() + "ms");
```

---

## 四、核心优势

### 1. 简化参数传递
```java
// ❌ 没有 Context - 参数传递繁琐
public void createOrder(Long userId, String tenantId, String traceId,
                       String[] permissions, Order order) {
    validatePermission(userId, permissions);
    saveOrder(userId, tenantId, order);
    logOperation(userId, tenantId, traceId, "CREATE_ORDER");
}

// ✅ 有了 Context - 简洁清晰
public void createOrder(Order order) {
    Long userId = RequestContext.getCurrentUserId();
    String tenantId = RequestContext.getCurrentTenantId();
    // 业务逻辑...
}
```

### 2. 统一数据管理
- 集中管理上下文数据
- 避免数据分散
- 易于维护和扩展

### 3. 提高可追踪性
- 完整的执行链路
- 详细的操作日志
- 便于问题排查

### 4. 支持横切关注点
- 日志记录
- 性能监控
- 审计追踪
- 权限验证

---

## 五、设计原则

### 1. 单一职责
每个 Context 只负责一类信息

```java
// ✅ 好的设计
RequestContext  - 只管理请求信息
TransactionContext - 只管理交易信息

// ❌ 不好的设计
GlobalContext - 什么都管，职责不清
```

### 2. 不可变性
尽量设计为不可变对象

```java
// ✅ 不可变设计
public class RequestContext {
    private final String requestId;
    private final Long userId;

    // 只提供 getter，不提供 setter
}
```

### 3. 线程安全
使用 ThreadLocal 或不可变对象

```java
// ✅ 线程安全
private static final ThreadLocal<RequestContext> CONTEXT_HOLDER = new ThreadLocal<>();
```

### 4. 生命周期管理
明确创建和销毁时机

```java
try {
    // 创建
    RequestContext.set(context);

    // 使用
    processRequest();

} finally {
    // 销毁（防止内存泄漏）
    RequestContext.clear();
}
```

### 5. 可序列化
支持分布式传递

```java
public class RequestContext implements Serializable {
    private static final long serialVersionUID = 1L;
    // ...
}
```

---

## 六、注意事项

### 1. 内存泄漏
⚠️ **必须在请求结束时调用 clear()**

```java
// ❌ 错误 - 忘记清理
RequestContext.set(context);
processRequest();
// 忘记调用 clear()，导致内存泄漏

// ✅ 正确 - 使用 try-finally
try {
    RequestContext.set(context);
    processRequest();
} finally {
    RequestContext.clear();  // 必须清理
}
```

### 2. 异步线程
⚠️ **ThreadLocal 不会自动传递到异步线程**

```java
// ❌ 错误 - 异步线程获取不到
RequestContext.set(context);
CompletableFuture.runAsync(() -> {
    Long userId = RequestContext.getCurrentUserId();  // null!
});

// ✅ 正确 - 手动传递
RequestContext context = RequestContext.get();
CompletableFuture.runAsync(() -> {
    RequestContext.set(context);
    try {
        Long userId = RequestContext.getCurrentUserId();
        // 业务逻辑...
    } finally {
        RequestContext.clear();
    }
});
```

### 3. 线程池场景
⚠️ **使用 TransmittableThreadLocal**

```java
// 使用阿里的 TransmittableThreadLocal
private static final TransmittableThreadLocal<RequestContext> CONTEXT_HOLDER
    = new TransmittableThreadLocal<>();
```

---

## 七、实际案例

### 案例1：美团外卖下单流程

```java
// 1. 用户下单
RequestContext context = RequestContext.builder()
    .userId(10001L)
    .cityId(110000)  // 北京
    .latitude(39.9042)
    .longitude(116.4074)
    .deviceType("APP")
    .build();
RequestContext.set(context);

// 2. 创建订单
OrderService.createOrder(order);
    -> 自动获取 userId、cityId
    -> 检查用户权限
    -> 记录操作日志

// 3. 扣减库存
InventoryService.deduct(productId, quantity);
    -> 自动获取 cityId（城市库存隔离）
    -> 自动获取 traceId（分布式追踪）

// 4. 创建配送单
DeliveryService.createDelivery(orderId);
    -> 自动获取位置信息
    -> 自动获取 traceId

// 5. 发送通知
NotificationService.send("订单创建成功");
    -> 自动获取 userId
    -> 自动获取设备类型
```

### 案例2：阿里云 API 调用

```java
// 1. API 请求
RequestContext context = RequestContext.builder()
    .tenantId("TENANT-001")
    .accessKey("AK-XXX")
    .secretKey("SK-XXX")
    .region("cn-beijing")
    .build();
RequestContext.set(context);

// 2. 创建 ECS 实例
EcsService.createInstance(instanceConfig);
    -> 自动获取租户ID（多租户隔离）
    -> 自动获取 region
    -> 自动验证 AccessKey

// 3. 记录账单
BillingService.record(instanceId, price);
    -> 自动获取租户ID
    -> 自动记录操作日志

// 4. 发送通知
NotificationService.send("实例创建成功");
    -> 自动获取租户联系方式
```

---

## 八、总结

### Context 模式的价值

1. **简化开发** - 避免参数传递地狱
2. **提高质量** - 统一的数据管理
3. **增强可维护性** - 易于扩展和修改
4. **支持横切关注点** - 日志、监控、审计
5. **提升用户体验** - 更快的响应速度

### 最佳实践

✅ 使用 ThreadLocal 确保线程安全
✅ 使用 Builder 模式构建 Context
✅ 使用 try-finally 确保清理
✅ 设计为不可变对象
✅ 明确生命周期管理

### 适用场景

- ✅ Web 应用
- ✅ 微服务架构
- ✅ 多租户系统
- ✅ 分布式系统
- ✅ 企业级应用

---

## 九、代码示例

完整示例代码位于：
```
/Users/maxingfang/IdeaProjects/exercises/design-patterns/context-pattern/src/main/java/manfred/end/context/business/
```

运行示例：
```bash
cd design-patterns/context-pattern

# 请求上下文示例
java -cp target/classes manfred.end.context.business.RequestContextExample

# 业务场景汇总
java -cp target/classes manfred.end.context.business.BusinessContextScenarios

