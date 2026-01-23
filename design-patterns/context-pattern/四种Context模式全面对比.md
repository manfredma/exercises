# 四种 Context 模式全面对比

## 概述

在软件开发中，"Context"（上下文）是一个常见的设计概念，但不同场景下的 Context 有着完全不同的目的和实现。本文详细对比四种常见的 Context 模式。

---

## 一、四种 Context 快速对比

| Context 类型 | JsonStreamContext | ProcessContext | ApplicationContext | ServletContext |
|-------------|-------------------|----------------|-------------------|----------------|
| **核心问题** | "我在哪里？" | "我在哪一步？" | "对象在哪里？" | "应用是什么？" |
| **主要目的** | 位置追踪 | 流程追踪 | 对象管理 | 应用管理 |
| **作用域** | 解析过程 | 流程执行 | Spring 容器 | Web 应用 |
| **生命周期** | 短（解析时） | 中（流程时） | 长（应用时） | 长（应用时） |
| **实例数量** | 多个（嵌套） | 多个（嵌套） | 1个/容器 | 1个/应用 |
| **数据共享** | 无 | 流程内共享 | 容器内共享 | 应用内共享 |
| **层级关系** | 有（父子上下文） | 有（流程嵌套） | 有（容器层级） | 无（单例） |
| **典型场景** | JSON/XML 解析 | 订单处理流程 | Spring 应用 | Web 应用 |

---

## 二、详细分析

### 1. JsonStreamContext - 位置追踪器

#### 目的
追踪 JSON/XML 解析时的当前位置

#### 核心问题
**"我在 JSON 的哪个位置？"**

#### 示例
```java
// 解析 {"user": {"orders": [1, 2, 3]}}
context.getPath();      // "/user/orders[2]"
context.getDepth();     // 3
context.inArray();      // true
context.getParent();    // orders 数组的父上下文
```

#### 主要功能
- ✅ 追踪解析位置（ROOT/ARRAY/OBJECT）
- ✅ 维护父子上下文链
- ✅ 验证结构合法性
- ✅ 提供错误定位信息

#### 使用场景
- JSON/XML 解析器
- 编译器（语法树遍历）
- 表达式求值器

#### 生命周期
**临时** - 仅在解析过程中存在

---

### 2. ProcessContext - 流程追踪器

#### 目的
追踪业务流程的执行位置和状态

#### 核心问题
**"我在流程的哪一步？"**

#### 示例
```java
// 订单处理流程
OrderProcessContext ctx = OrderProcessContext.createRoot(orderId);
OrderProcessContext validationCtx = ctx.createSequenceContext("Validation");
validationCtx.logStep("用户验证完成");

// 输出：ROOT -> OrderProcess[Validation] -> ValidateUser (elapsed: 120ms)
```

#### 主要功能
- ✅ 追踪流程执行位置
- ✅ 记录流程步骤
- ✅ 维护流程嵌套关系
- ✅ 提供流程路径和耗时

#### 使用场景
- 订单处理流程
- 审批流程
- 工作流引擎
- 复杂业务流程

#### 生命周期
**中等** - 在流程执行期间存在

---

### 3. ApplicationContext - 对象容器

#### 目的
管理应用中所有对象的创建、依赖和生命周期

#### 核心问题
**"对象在哪里？如何获取？"**

#### 示例
```java
// 获取 Spring 管理的对象
ApplicationContext ctx = SpringApplication.run(Application.class);
UserService userService = ctx.getBean(UserService.class);

// Spring 自动管理依赖注入
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;  // 自动注入
}
```

#### 主要功能
- ✅ IoC 容器 - 管理对象生命周期
- ✅ 依赖注入 - 自动装配依赖
- ✅ 配置中心 - 管理应用配置
- ✅ 事件总线 - 发布/订阅事件
- ✅ 资源加载 - 统一资源访问
- ✅ 国际化支持 - 多语言消息

#### 使用场景
- Spring 应用
- 任何需要 IoC/DI 的场景
- 企业级应用开发

#### 生命周期
**长期** - 贯穿整个应用生命周期

---

### 4. ServletContext - Web 应用上下文

#### 目的
代表整个 Web 应用，提供应用级数据共享和资源管理

#### 核心问题
**"应用是什么？如何共享数据？"**

#### 示例
```java
// 获取 ServletContext
ServletContext ctx = request.getServletContext();

// 应用级数据共享
ctx.setAttribute("visitCount", 1000);
Integer count = (Integer) ctx.getAttribute("visitCount");

// 资源访问
String path = ctx.getRealPath("/WEB-INF/config.xml");
InputStream is = ctx.getResourceAsStream("/config.properties");

// 读取配置
String dbUrl = ctx.getInitParameter("database.url");
```

#### 主要功能
- ✅ 应用级数据共享 - 所有 Servlet 共享
- ✅ 资源访问 - 访问 Web 资源
- ✅ 配置管理 - 读取 web.xml 配置
- ✅ Servlet 管理 - 动态注册 Servlet/Filter
- ✅ 日志记录 - 应用日志
- ✅ MIME 类型映射

#### 使用场景
- Web 应用
- Servlet 容器（Tomcat、Jetty）
- 需要应用级数据共享的场景

#### 生命周期
**长期** - 应用启动时创建，关闭时销毁

---

## 三、核心区别总结

### 1. 按目的分类

| 类型 | 目的 | 本质 |
|------|------|------|
| JsonStreamContext | 位置追踪 | 追踪器 |
| ProcessContext | 流程追踪 | 追踪器 |
| ApplicationContext | 对象管理 | 容器 |
| ServletContext | 应用管理 | 容器 |

### 2. 按生命周期分类

| 类型 | 生命周期 | 创建时机 | 销毁时机 |
|------|---------|---------|---------|
| JsonStreamContext | 临时 | 开始解析 | 解析完成 |
| ProcessContext | 中等 | 流程开始 | 流程结束 |
| ApplicationContext | 长期 | 应用启动 | 应用关闭 |
| ServletContext | 长期 | 应用启动 | 应用关闭 |

### 3. 按作用域分类

| 类型 | 作用域 | 可见范围 |
|------|--------|---------|
| JsonStreamContext | 解析过程 | 当前解析线程 |
| ProcessContext | 流程执行 | 当前流程 |
| ApplicationContext | Spring 容器 | 所有 Spring Bean |
| ServletContext | Web 应用 | 所有 Servlet/Filter/Listener |

---

## 四、实际应用对比

### 场景 1：数据共享

```java
// JsonStreamContext - 不支持数据共享
// 只追踪位置，不存储业务数据

// ProcessContext - 流程内共享
ctx.setCurrentValue(orderData);  // 流程步骤间共享

// ApplicationContext - 容器内共享
ctx.getBean("userService");  // 所有 Bean 共享

// ServletContext - 应用内共享
ctx.setAttribute("dbPool", pool);  // 所有 Servlet 共享
```

### 场景 2：配置管理

```java
// JsonStreamContext - 无配置管理

// ProcessContext - 无配置管理

// ApplicationContext - 强大的配置管理
env.getProperty("spring.datasource.url");
@Value("${server.port}")

// ServletContext - 简单的配置管理
ctx.getInitParameter("database.url");
```

### 场景 3：生命周期管理

```java
// JsonStreamContext - 无生命周期管理
// 解析完就销毁

// ProcessContext - 简单的生命周期
// 流程开始创建，结束销毁

// ApplicationContext - 完整的生命周期
@PostConstruct  // 初始化
@PreDestroy     // 销毁

// ServletContext - 简单的生命周期
@WebListener
public class AppListener implements ServletContextListener {
    void contextInitialized(ServletContextEvent event) {}
    void contextDestroyed(ServletContextEvent event) {}
}
```

---

## 五、设计模式对比

### JsonStreamContext
- 抽象基类模式
- 责任链模式（父子上下文）
- 对象池模式（SmileWriteContext）

### ProcessContext
- 抽象基类模式
- 责任链模式（流程嵌套）
- 模板方法模式

### ApplicationContext
- 工厂模式（BeanFactory）
- 单例模式
- 代理模式（AOP）
- 观察者模式（事件机制）
- 策略模式（不同实现）
- 模板方法模式（refresh）

### ServletContext
- 单例模式（一个应用一个实例）
- 观察者模式（Listener）
- 工厂模式（动态注册 Servlet）

---

## 六、选择指南

### 何时使用 JsonStreamContext？
✅ 需要追踪解析位置
✅ 需要验证结构合法性
✅ 需要提供错误定位
❌ 不需要数据共享
❌ 不需要配置管理

### 何时使用 ProcessContext？
✅ 复杂的业务流程
✅ 需要追踪流程执行
✅ 需要记录流程路径
✅ 需要流程内数据共享
❌ 简单的单步操作

### 何时使用 ApplicationContext？
✅ 需要 IoC/DI
✅ 需要管理对象生命周期
✅ 需要 AOP
✅ 需要事件机制
✅ Spring 应用
❌ 简单的工具类

### 何时使用 ServletContext？
✅ Web 应用
✅ 需要应用级数据共享
✅ 需要访问 Web 资源
✅ 需要动态注册 Servlet
❌ 非 Web 应用

---

## 七、关系图

```
┌─────────────────────────────────────────────────────────┐
│                    Context 家族                         │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  追踪型 Context                                         │
│  ├─ JsonStreamContext  (位置追踪)                      │
│  └─ ProcessContext     (流程追踪)                      │
│                                                         │
│  容器型 Context                                         │
│  ├─ ApplicationContext (对象容器)                      │
│  └─ ServletContext     (应用容器)                      │
│                                                         │
└─────────────────────────────────────────────────────────┘

协作关系：
ServletContext
    ↓ 包含
WebApplicationContext (Spring)
    ↓ 继承
ApplicationContext (Spring)
```

---

## 八、总结

### 核心要点

1. **Context 不是一种模式，而是一类模式**
   - 不同场景下的 Context 有不同的目的和实现

2. **按目的分类**
   - **追踪型**：JsonStreamContext、ProcessContext
   - **容器型**：ApplicationContext、ServletContext

3. **按生命周期分类**
   - **临时**：JsonStreamContext
   - **中等**：ProcessContext
   - **长期**：ApplicationContext、ServletContext

4. **关键区别**
   - JsonStreamContext：追踪位置
   - ProcessContext：追踪流程
   - ApplicationContext：管理对象
   - ServletContext：管理应用

### 记忆口诀

```
JsonStream 追位置，
Process 追流程，
Application 管对象，
Servlet 管应用。

追踪型短暂存在，
容器型长期运行。
```

---

## 九、代码示例

完整示例代码位于：
```
/Users/maxingfang/IdeaProjects/exercises/design-patterns/context-pattern/
```

运行示例：
```bash
cd design-patterns/context-pattern

# JsonStreamContext vs 状态模式
java -cp target/classes manfred.end.context.comparison.ContextPatternExample

# ApplicationContext 分析
java -cp target/classes manfred.end.context.spring.ApplicationContextExample

# ServletContext 分析
java -cp target/classes manfred.end.context.servlet.ServletContextExample

# 可视化对比
java -cp target/classes manfred.end.context.comparison.VisualComparison
```

---

## 十、参考资料

- Jackson 源码
- Spring Framework 官方文档
- Servlet 规范
- 《设计模式》
- 《Spring 实战》

