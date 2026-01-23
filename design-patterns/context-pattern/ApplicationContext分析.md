# Spring ApplicationContext 深度分析

## 一、ApplicationContext 是什么？

`ApplicationContext` 是 **Spring 框架的核心接口**，代表 **IoC（控制反转）容器**。

### 接口定义

```java
public interface ApplicationContext extends
    EnvironmentCapable,           // 环境配置能力
    ListableBeanFactory,          // Bean 工厂（可列举）
    HierarchicalBeanFactory,      // 层级 Bean 工厂
    MessageSource,                // 国际化消息源
    ApplicationEventPublisher,    // 事件发布器
    ResourcePatternResolver       // 资源加载器
```

---

## 二、核心目的和作用

### 1️⃣ **IoC 容器 - 管理对象生命周期**

```java
// Spring 自动创建对象并管理依赖
ApplicationContext ctx = new AnnotationConfigApplicationContext();
UserService userService = ctx.getBean(UserService.class);
// 不需要手动 new，Spring 自动创建并注入依赖
```

**解决的问题**：
- ❌ 手动创建对象繁琐
- ❌ 依赖关系复杂
- ❌ 单例模式需要手动实现
- ✅ Spring 自动管理一切

### 2️⃣ **服务定位器 - 获取应用中的任何 Bean**

```java
// 按类型获取
UserService service = ctx.getBean(UserService.class);

// 按名称获取
UserService service = ctx.getBean("userService", UserService.class);

// 获取所有指定类型的 Bean
Map<String, UserService> services = ctx.getBeansOfType(UserService.class);
```

### 3️⃣ **配置中心 - 管理应用配置**

```java
// 读取配置
Environment env = ctx.getEnvironment();
String dbUrl = env.getProperty("spring.datasource.url");

// 多环境支持
String[] profiles = env.getActiveProfiles(); // dev, test, prod
```

### 4️⃣ **事件总线 - 发布和监听应用事件**

```java
// 发布事件
ctx.publishEvent(new OrderCreatedEvent(order));

// 监听事件
@EventListener
public void handleOrderCreated(OrderCreatedEvent event) {
    // 处理订单创建事件
}
```

### 5️⃣ **资源加载器 - 加载各种资源**

```java
// 加载单个资源
Resource resource = ctx.getResource("classpath:config.xml");

// 加载多个资源
Resource[] resources = ctx.getResources("classpath*:*.xml");
```

### 6️⃣ **国际化支持 - 多语言消息**

```java
// 获取国际化消息
String message = ctx.getMessage("welcome", null, Locale.CHINA);
```

---

## 三、解决的核心问题

### ❌ 没有 ApplicationContext 的痛点

```java
// 手动创建对象，依赖关系复杂
UserDao userDao = new UserDaoImpl();
EmailService emailService = new EmailServiceImpl();
UserService userService = new UserServiceImpl(userDao, emailService);
OrderService orderService = new OrderServiceImpl(userService);

// 问题：
// 1. 对象创建分散，难以管理
// 2. 依赖关系硬编码，难以替换
// 3. 单例模式需要手动实现
// 4. 对象生命周期难以控制
// 5. 配置分散在代码中
```

### ✅ 有了 ApplicationContext

```java
// Spring 自动管理所有对象
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailService emailService;

    // Spring 自动注入依赖
}

// 使用时直接获取
UserService userService = ctx.getBean(UserService.class);

// 优势：
// ✓ 集中管理所有对象
// ✓ 自动依赖注入
// ✓ 自动管理单例
// ✓ 统一生命周期管理
// ✓ 配置集中化
```

---

## 四、三种 Context 模式对比

| Context 类型 | JsonStreamContext | ProcessContext | ApplicationContext |
|-------------|-------------------|----------------|-------------------|
| **核心问题** | "我在哪里？" | "我在哪一步？" | "对象在哪里？" |
| **主要目的** | 位置追踪 | 流程追踪 | 对象管理 |
| **管理对象** | 解析位置 | 流程步骤 | 应用 Bean |
| **生命周期** | 短（解析期间） | 中（流程期间） | 长（应用生命周期） |
| **层级关系** | 有（父子上下文） | 有（流程嵌套） | 有（容器层级） |
| **典型场景** | JSON 解析 | 订单处理流程 | Spring 应用 |

### 详细对比

#### 1. JsonStreamContext - "我在哪里？"

```java
// 解析 {"user": {"orders": [1, 2, 3]}}
context.getPath();      // "/user/orders[2]"
context.getDepth();     // 3
context.inArray();      // true
```

**目的**：追踪 JSON 解析位置

#### 2. ProcessContext - "我在哪一步？"

```java
// 订单处理流程
OrderProcessContext ctx = OrderProcessContext.createRoot(orderId);
OrderProcessContext validationCtx = ctx.createSequenceContext("Validation");
validationCtx.logStep("用户验证完成");
// 输出：ROOT -> OrderProcess[Validation] -> ValidateUser
```

**目的**：追踪业务流程执行位置

#### 3. ApplicationContext - "对象在哪里？"

```java
// 获取应用中的对象
UserService service = ctx.getBean(UserService.class);
OrderService orderService = ctx.getBean(OrderService.class);
```

**目的**：管理应用中的所有对象

---

## 五、ApplicationContext 使用的设计模式

### 1. **工厂模式 (Factory Pattern)**
- `BeanFactory` 接口
- 负责创建和管理 Bean

### 2. **单例模式 (Singleton Pattern)**
- 默认所有 Bean 都是单例
- 容器级别的单例管理

### 3. **代理模式 (Proxy Pattern)**
- AOP 功能实现
- 事务管理

### 4. **观察者模式 (Observer Pattern)**
- `ApplicationEvent` 事件机制
- `@EventListener` 监听器

### 5. **策略模式 (Strategy Pattern)**
- 不同的 ApplicationContext 实现
  - `AnnotationConfigApplicationContext`
  - `ClassPathXmlApplicationContext`
  - `WebApplicationContext`

### 6. **模板方法模式 (Template Method Pattern)**
- `AbstractApplicationContext`
- `refresh()` 方法定义容器启动流程

### 7. **适配器模式 (Adapter Pattern)**
- 各种 Aware 接口
  - `ApplicationContextAware`
  - `BeanFactoryAware`

---

## 六、实际应用场景

### 场景 1：Web 应用启动

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class);
        // 容器管理所有 Controller、Service、Repository
    }
}
```

### 场景 2：动态获取 Bean

```java
@RestController
public class UserController {
    @Autowired
    private ApplicationContext ctx;

    public void dynamicGetBean(String serviceName) {
        // 根据名称动态获取 Bean
        Object service = ctx.getBean(serviceName);
    }
}
```

### 场景 3：发布业务事件

```java
@Service
public class OrderService {
    @Autowired
    private ApplicationContext ctx;

    public void createOrder(Order order) {
        // 保存订单
        orderRepository.save(order);

        // 发布事件（解耦）
        ctx.publishEvent(new OrderCreatedEvent(order));
    }
}

// 其他模块监听事件
@Component
public class EmailNotificationListener {
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        // 发送邮件通知
        emailService.sendOrderConfirmation(event.getOrder());
    }
}
```

### 场景 4：多环境配置

```java
Environment env = ctx.getEnvironment();
String profile = env.getActiveProfiles()[0];  // dev/test/prod

if ("prod".equals(profile)) {
    // 生产环境逻辑
} else {
    // 开发环境逻辑
}

String dbUrl = env.getProperty("spring.datasource.url");
```

### 场景 5：父子容器（Spring MVC）

```java
// Spring MVC 中的典型场景
// 父容器：管理 Service、Repository
ApplicationContext parentContext = ...;

// 子容器：管理 Controller
WebApplicationContext childContext = ...;
childContext.setParent(parentContext);

// 子容器可以访问父容器的 Bean
// 父容器不能访问子容器的 Bean
```

---

## 七、ApplicationContext 的核心价值

### 解决的根本问题

**对象创建和管理的复杂性**

### 实现的核心理念

1. **控制反转（IoC）**
   - 对象的创建权交给容器
   - 不再是 `new` 对象，而是从容器获取

2. **依赖注入（DI）**
   - 容器自动注入依赖
   - 对象之间解耦

3. **面向接口编程**
   - 依赖抽象而非具体实现
   - 易于替换和测试

### 带来的好处

✅ **集中管理** - 所有对象在容器中统一管理
✅ **自动装配** - 自动解决依赖关系
✅ **生命周期** - 统一管理对象生命周期
✅ **配置集中** - 配置与代码分离
✅ **易于测试** - 可以轻松替换依赖进行测试
✅ **AOP 支持** - 方便实现横切关注点

---

## 八、总结

### ApplicationContext 的本质

```
ApplicationContext = IoC 容器 + 服务定位器 + 配置中心 + 事件总线 + 资源管理器
```

### 三种 Context 的区别

| 特性 | JsonStreamContext | ProcessContext | ApplicationContext |
|------|-------------------|----------------|-------------------|
| **本质** | 位置追踪器 | 流程追踪器 | 对象容器 |
| **核心问题** | 我在哪里？ | 我在哪一步？ | 对象在哪里？ |
| **生命周期** | 临时（解析时） | 中等（流程时） | 长期（应用时） |
| **主要功能** | 追踪、验证 | 追踪、记录 | 创建、管理、查找 |

### 关键要点

1. **ApplicationContext 不是位置追踪**，而是对象容器
2. **ApplicationContext 不是状态管理**，而是依赖管理
3. **ApplicationContext 的核心**是 IoC 和 DI
4. **ApplicationContext 解决的是**对象创建和管理的复杂性

---

## 九、代码示例

完整示例代码位于：
- `/Users/maxingfang/IdeaProjects/exercises/design-patterns/context-pattern/`

运行示例：
```bash
cd design-patterns/context-pattern

# 运行 ApplicationContext 分析
javac -d target/classes src/main/java/manfred/end/context/spring/SpringApplicationContextAnalysis.java
java -cp target/classes manfred.end.context.spring.SpringApplicationContextAnalysis

# 运行 ApplicationContext 示例
javac -d target/classes src/main/java/manfred/end/context/spring/ApplicationContextExample.java
java -cp target/classes manfred.end.context.spring.ApplicationContextExample
```

---

## 十、参考资料

- Spring Framework 官方文档
- 《Spring 实战》
- 《Spring 源码深度解析》

