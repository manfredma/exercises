package manfred.end.context.spring;

/**
 * Spring ApplicationContext 深度分析
 *
 * 这是第三种 Context 设计模式：容器上下文模式
 *
 * @author manfred
 */
public class SpringApplicationContextAnalysis {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         Spring ApplicationContext 深度分析                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. 定义和目的
        System.out.println("【一、ApplicationContext 是什么？】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("ApplicationContext 是 Spring 的核心接口，代表 Spring IoC 容器。");
        System.out.println();
        System.out.println("接口定义：");
        System.out.println("  public interface ApplicationContext extends");
        System.out.println("      EnvironmentCapable,           // 环境配置");
        System.out.println("      ListableBeanFactory,          // Bean 工厂");
        System.out.println("      HierarchicalBeanFactory,      // 层级 Bean 工厂");
        System.out.println("      MessageSource,                // 国际化消息");
        System.out.println("      ApplicationEventPublisher,    // 事件发布");
        System.out.println("      ResourcePatternResolver       // 资源加载");
        System.out.println();

        // 2. 核心目的
        System.out.println("【二、核心目的和作用】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();
        System.out.println("1️⃣  IoC 容器 - 管理对象生命周期");
        System.out.println("   • 创建对象（Bean）");
        System.out.println("   • 管理依赖注入");
        System.out.println("   • 控制对象生命周期");
        System.out.println();
        System.out.println("   示例：");
        System.out.println("   ApplicationContext ctx = new AnnotationConfigApplicationContext();");
        System.out.println("   UserService userService = ctx.getBean(UserService.class);");
        System.out.println("   // Spring 自动创建对象并注入依赖");
        System.out.println();

        System.out.println("2️⃣  服务定位器 - 获取应用中的任何 Bean");
        System.out.println("   • 按类型获取：ctx.getBean(UserService.class)");
        System.out.println("   • 按名称获取：ctx.getBean(\"userService\")");
        System.out.println("   • 获取所有：ctx.getBeansOfType(Service.class)");
        System.out.println();

        System.out.println("3️⃣  配置中心 - 管理应用配置");
        System.out.println("   • 读取配置文件：application.properties");
        System.out.println("   • 环境管理：dev、test、prod");
        System.out.println("   • 属性注入：@Value(\"${server.port}\")");
        System.out.println();

        System.out.println("4️⃣  事件总线 - 发布和监听应用事件");
        System.out.println("   • 发布事件：ctx.publishEvent(new OrderCreatedEvent())");
        System.out.println("   • 监听事件：@EventListener");
        System.out.println();

        System.out.println("5️⃣  资源加载器 - 加载各种资源");
        System.out.println("   • 加载文件：ctx.getResource(\"classpath:config.xml\")");
        System.out.println("   • 加载多个：ctx.getResources(\"classpath*:*.xml\")");
        System.out.println();

        System.out.println("6️⃣  国际化支持 - 多语言消息");
        System.out.println("   • 获取消息：ctx.getMessage(\"welcome\", null, Locale.CHINA)");
        System.out.println();

        // 3. 解决的核心问题
        System.out.println("【三、解决的核心问题】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("❌ 没有 ApplicationContext 的问题：");
        System.out.println();
        System.out.println("// 手动创建对象，依赖关系复杂");
        System.out.println("UserDao userDao = new UserDaoImpl();");
        System.out.println("EmailService emailService = new EmailServiceImpl();");
        System.out.println("UserService userService = new UserServiceImpl(userDao, emailService);");
        System.out.println("OrderService orderService = new OrderServiceImpl(userService);");
        System.out.println();
        System.out.println("问题：");
        System.out.println("  1. 对象创建分散，难以管理");
        System.out.println("  2. 依赖关系硬编码，难以替换");
        System.out.println("  3. 单例模式需要手动实现");
        System.out.println("  4. 对象生命周期难以控制");
        System.out.println("  5. 配置分散在代码中");
        System.out.println();

        System.out.println("✅ 有了 ApplicationContext：");
        System.out.println();
        System.out.println("// Spring 自动管理所有对象");
        System.out.println("@Service");
        System.out.println("public class UserServiceImpl implements UserService {");
        System.out.println("    @Autowired");
        System.out.println("    private UserDao userDao;");
        System.out.println("    ");
        System.out.println("    @Autowired");
        System.out.println("    private EmailService emailService;");
        System.out.println("}");
        System.out.println();
        System.out.println("// 使用时直接获取");
        System.out.println("UserService userService = ctx.getBean(UserService.class);");
        System.out.println();
        System.out.println("优势：");
        System.out.println("  ✓ 集中管理所有对象");
        System.out.println("  ✓ 自动依赖注入");
        System.out.println("  ✓ 自动管理单例");
        System.out.println("  ✓ 统一生命周期管理");
        System.out.println("  ✓ 配置集中化");
        System.out.println();

        // 4. 与其他 Context 的对比
        System.out.println("【四、三种 Context 模式对比】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("┌─────────────────┬──────────────────┬──────────────────┬──────────────────┐");
        System.out.println("│   Context 类型  │  JsonStreamCtx   │   ProcessCtx     │  ApplicationCtx  │");
        System.out.println("├─────────────────┼──────────────────┼──────────────────┼──────────────────┤");
        System.out.println("│ 核心问题        │ \"我在哪里？\"    │ \"我在哪一步？\"  │ \"对象在哪里？\"  │");
        System.out.println("│ 主要目的        │ 位置追踪         │ 流程追踪         │ 对象管理         │");
        System.out.println("│ 管理对象        │ 解析位置         │ 流程步骤         │ 应用 Bean        │");
        System.out.println("│ 生命周期        │ 短（解析期间）   │ 中（流程期间）   │ 长（应用生命周期）│");
        System.out.println("│ 层级关系        │ 有（父子上下文） │ 有（流程嵌套）   │ 有（容器层级）   │");
        System.out.println("│ 典型场景        │ JSON 解析        │ 订单处理流程     │ Spring 应用      │");
        System.out.println("└─────────────────┴──────────────────┴──────────────────┴──────────────────┘");
        System.out.println();

        // 5. ApplicationContext 的设计模式
        System.out.println("【五、ApplicationContext 使用的设计模式】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("1. 工厂模式 (Factory Pattern)");
        System.out.println("   • BeanFactory 接口");
        System.out.println("   • 负责创建和管理 Bean");
        System.out.println();

        System.out.println("2. 单例模式 (Singleton Pattern)");
        System.out.println("   • 默认所有 Bean 都是单例");
        System.out.println("   • 容器级别的单例管理");
        System.out.println();

        System.out.println("3. 代理模式 (Proxy Pattern)");
        System.out.println("   • AOP 功能实现");
        System.out.println("   • 事务管理");
        System.out.println();

        System.out.println("4. 观察者模式 (Observer Pattern)");
        System.out.println("   • ApplicationEvent 事件机制");
        System.out.println("   • @EventListener 监听器");
        System.out.println();

        System.out.println("5. 策略模式 (Strategy Pattern)");
        System.out.println("   • 不同的 ApplicationContext 实现");
        System.out.println("   • AnnotationConfigApplicationContext");
        System.out.println("   • ClassPathXmlApplicationContext");
        System.out.println();

        System.out.println("6. 模板方法模式 (Template Method Pattern)");
        System.out.println("   • AbstractApplicationContext");
        System.out.println("   • refresh() 方法定义容器启动流程");
        System.out.println();

        System.out.println("7. 适配器模式 (Adapter Pattern)");
        System.out.println("   • 各种 Aware 接口");
        System.out.println("   • ApplicationContextAware");
        System.out.println();

        // 6. 实际应用场景
        System.out.println("【六、实际应用场景】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("场景 1：Web 应用");
        System.out.println("  @SpringBootApplication");
        System.out.println("  public class Application {");
        System.out.println("      public static void main(String[] args) {");
        System.out.println("          ApplicationContext ctx = SpringApplication.run(Application.class);");
        System.out.println("          // 容器管理所有 Controller、Service、Repository");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println();

        System.out.println("场景 2：获取 Bean");
        System.out.println("  @RestController");
        System.out.println("  public class UserController {");
        System.out.println("      @Autowired");
        System.out.println("      private ApplicationContext ctx;");
        System.out.println("      ");
        System.out.println("      public void dynamicGetBean() {");
        System.out.println("          // 动态获取 Bean");
        System.out.println("          UserService service = ctx.getBean(UserService.class);");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println();

        System.out.println("场景 3：发布事件");
        System.out.println("  @Service");
        System.out.println("  public class OrderService {");
        System.out.println("      @Autowired");
        System.out.println("      private ApplicationContext ctx;");
        System.out.println("      ");
        System.out.println("      public void createOrder(Order order) {");
        System.out.println("          // 保存订单");
        System.out.println("          orderRepository.save(order);");
        System.out.println("          // 发布事件");
        System.out.println("          ctx.publishEvent(new OrderCreatedEvent(order));");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println();

        System.out.println("场景 4：多环境配置");
        System.out.println("  Environment env = ctx.getEnvironment();");
        System.out.println("  String profile = env.getActiveProfiles()[0];  // dev/test/prod");
        System.out.println("  String dbUrl = env.getProperty(\"spring.datasource.url\");");
        System.out.println();

        // 7. 总结
        System.out.println("【七、总结】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();
        System.out.println("ApplicationContext 是一个 \"容器上下文\"，它：");
        System.out.println();
        System.out.println("✓ 不是位置追踪（JsonStreamContext）");
        System.out.println("✓ 不是流程追踪（ProcessContext）");
        System.out.println("✓ 不是状态管理（State Pattern）");
        System.out.println();
        System.out.println("而是：");
        System.out.println("✓ 对象容器 - 管理应用中所有对象");
        System.out.println("✓ 服务定位器 - 提供对象查找服务");
        System.out.println("✓ 配置中心 - 管理应用配置");
        System.out.println("✓ 事件总线 - 实现组件间解耦通信");
        System.out.println("✓ 资源管理器 - 统一资源访问");
        System.out.println();
        System.out.println("核心价值：");
        System.out.println("  解决了 \"对象创建和管理\" 的复杂性问题");
        System.out.println("  实现了 \"控制反转（IoC）\" 和 \"依赖注入（DI）\"");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ApplicationContext = IoC 容器 + 服务定位器 + 配置中心 + 事件总线   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }
}

