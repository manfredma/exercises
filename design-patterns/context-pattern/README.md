# JsonStreamContext 设计模式分析

## 核心问题：Context 主要是为了实现状态模式吗？

**答案：不是！** Context 模式和状态模式是两种不同的设计模式，虽然都涉及"状态"，但目的完全不同。

---

## 一、两种模式的本质区别

### 1. **Context 模式（位置追踪模式）**

**核心目的**：追踪当前处理位置和层级关系

```
目的：我在哪里？
- 当前在 JSON 的哪个位置？
- 父级是什么？
- 嵌套深度是多少？
- 当前路径是什么？
```

**典型应用**：
- JSON/XML 解析器（追踪解析位置）
- 编译器（追踪语法树位置）
- 业务流程引擎（追踪流程执行位置）

**示例输出**：
```
✅ 写入字段名: "user" at //user
✅ 写入值: Alice at //user/name
✅ 写入值: 1 at //user/orders[0]
❌ 错误：不能在 ARRAY 中写入字段名
```

### 2. **状态模式（行为切换模式）**

**核心目的**：对象行为随状态改变而改变

```
目的：我能做什么？
- 当前状态下允许哪些操作？
- 操作后会转换到什么状态？
- 不同状态下行为如何不同？
```

**典型应用**：
- 订单状态机（待支付 -> 待发货 -> 已发货）
- TCP 连接状态（CLOSED -> LISTEN -> ESTABLISHED）
- 游戏角色状态（正常 -> 眩晕 -> 死亡）

**示例输出**：
```
[订单 ORDER-001] 当前状态: 待支付
支付成功，订单进入待发货状态

[订单 ORDER-001] 当前状态: 待发货
发货成功，订单进入待收货状态
```

---

## 二、详细对比表

| 维度 | Context 模式 | 状态模式 |
|------|-------------|---------|
| **主要目的** | 追踪位置和层级关系 | 改变对象行为 |
| **核心问题** | "我在哪里？" | "我能做什么？" |
| **状态含义** | 位置类型（ROOT/ARRAY/OBJECT） | 业务状态（待支付/待发货） |
| **状态转换** | 通过进入/退出上下文 | 通过业务操作触发 |
| **父子关系** | 有（维护上下文链） | 无 |
| **路径追踪** | 有（可构建完整路径） | 无 |
| **行为变化** | 行为不变，只验证合法性 | 行为随状态改变 |
| **典型场景** | 解析器、编译器、流程引擎 | 订单、连接、游戏状态 |

---

## 三、JsonStreamContext 的真正设计模式

`JsonStreamContext` 实际上是以下几种模式的组合：

### 1. **抽象基类模式 (Abstract Base Class)**
```java
public abstract class JsonStreamContext {
    // 定义通用状态
    protected int _type;
    protected int _index;

    // 抽象方法
    public abstract JsonStreamContext getParent();
    public abstract String getCurrentName();

    // 通用实现
    public final boolean inArray() { return _type == TYPE_ARRAY; }
}
```

### 2. **责任链模式 (Chain of Responsibility)**
```java
// 通过 getParent() 形成上下文链
Root Context
    └── Object Context
            ├── Array Context
            └── Object Context
```

### 3. **模板方法模式 (Template Method)**
```java
// 提供构建路径的模板方法
public String getFullPath() {
    StringBuilder sb = new StringBuilder();
    _appendPath(sb);  // 递归构建
    return sb.toString();
}
```

### 4. **对象池模式 (Object Pool)** - 在 SmileWriteContext 中
```java
// 复用子上下文对象，减少 GC 压力
protected SmileWriteContext _childToRecycle;

public SmileWriteContext createChildArrayContext() {
    if (_childToRecycle == null) {
        _childToRecycle = new SmileWriteContext(...);
    }
    return _childToRecycle.reset(...);
}
```

---

## 四、Context 模式的核心价值

### 1. **位置追踪**
```java
// 解析 {"user": {"orders": [1, 2, 3]}}
// 当解析到 3 时，Context 告诉你：
context.getPath();  // "/user/orders[2]"
context.getDepth(); // 3
context.inArray();  // true
```

### 2. **结构验证**
```java
// 确保 JSON 结构正确
if (!context.inObject()) {
    throw new Exception("不能在数组中写字段名");
}

if (context.inObject() && !hasFieldName) {
    throw new Exception("对象中必须先写字段名");
}
```

### 3. **错误定位**
```java
// 解析错误时提供精确位置
throw new JsonParseException(
    "Unexpected token at " + context.getPath()
);
// 输出：Unexpected token at /user/orders[2]
```

### 4. **调试支持**
```java
// 打印当前处理位置
System.out.println("Processing: " + context.toString());
// 输出：OBJECT["name"] at depth 2
```

---

## 五、业务代码中如何应用

### 场景 1：复杂业务流程追踪

```java
// 订单处理流程
OrderProcessContext rootCtx = OrderProcessContext.createRoot(orderId);
OrderProcessContext validationCtx = rootCtx.createSequenceContext("Validation");
validationCtx.nextStep("ValidateUser");
validationCtx.logStep("用户验证完成");

// 输出：ROOT -> OrderProcess[Validation] -> ValidateUser (elapsed: 120ms)
```

### 场景 2：SQL 查询构建

```java
// 构建复杂 SQL
SqlContext root = SqlContext.createRoot();
SqlContext selectCtx = root.createSelectContext();
SqlContext whereCtx = selectCtx.createWhereContext();
SqlContext andCtx = whereCtx.createAndContext();

// 验证：不能在 SELECT 子句中使用 WHERE 条件
```

### 场景 3：表达式求值

```java
// 求值 (1 + 2) * (3 + 4)
ExprContext root = ExprContext.createRoot();
ExprContext mulCtx = root.createBinaryOpContext("*");
ExprContext addCtx1 = mulCtx.createBinaryOpContext("+");
// 追踪当前求值位置，便于调试
```

### 场景 4：审批流程

```java
// 多级审批流程
ApprovalContext root = ApprovalContext.createRoot(requestId);
ApprovalContext deptCtx = root.createApprovalLevel("部门审批");
ApprovalContext managerCtx = deptCtx.createApprovalLevel("经理审批");
ApprovalContext directorCtx = managerCtx.createApprovalLevel("总监审批");

// 追踪：当前在第几级审批，完整审批路径是什么
```

---

## 六、总结

### Context 模式 ≠ 状态模式

| 模式 | 关注点 | 典型问题 |
|------|--------|---------|
| **Context 模式** | 位置和层级 | "我在哪里？路径是什么？" |
| **状态模式** | 行为和转换 | "我能做什么？下一步是什么？" |

### JsonStreamContext 的设计精髓

1. ✅ **位置追踪**：知道当前在 JSON 的哪个位置
2. ✅ **结构验证**：确保 JSON 结构合法
3. ✅ **错误定位**：提供精确的错误位置信息
4. ✅ **性能优化**：通过对象复用减少 GC
5. ✅ **可扩展性**：抽象类设计支持不同实现

### 业务代码应用建议

**适合使用 Context 模式的场景**：
- ✅ 需要追踪处理位置的场景
- ✅ 有嵌套层级关系的场景
- ✅ 需要验证操作合法性的场景
- ✅ 需要构建完整路径的场景

**不适合使用 Context 模式的场景**：
- ❌ 简单的状态转换（用状态模式）
- ❌ 无层级关系的场景
- ❌ 不需要追踪位置的场景

---

## 运行示例

```bash
# 编译并运行状态模式示例
cd design-patterns/context-pattern
javac -d target/classes src/main/java/manfred/end/context/comparison/StatePatternExample.java
java -cp target/classes manfred.end.context.comparison.StatePatternExample

# 编译并运行 Context 模式示例
javac -d target/classes src/main/java/manfred/end/context/comparison/ContextPatternExample.java
java -cp target/classes manfred.end.context.comparison.ContextPatternExample

