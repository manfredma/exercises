package manfred.end.context.servlet;

/**
 * ServletContext 深度分析
 *
 * 这是第四种 Context 设计模式：Web 应用上下文模式
 *
 * @author manfred
 */
public class ServletContextAnalysis {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         ServletContext 深度分析                                      ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. 定义和目的
        System.out.println("【一、ServletContext 是什么？】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("ServletContext 是 Servlet 规范中的核心接口，代表整个 Web 应用。");
        System.out.println();
        System.out.println("接口定义：");
        System.out.println("  public interface ServletContext {");
        System.out.println("      // 获取应用信息");
        System.out.println("      String getContextPath();");
        System.out.println("      String getServerInfo();");
        System.out.println("      ");
        System.out.println("      // 资源访问");
        System.out.println("      String getRealPath(String path);");
        System.out.println("      InputStream getResourceAsStream(String path);");
        System.out.println("      ");
        System.out.println("      // 属性管理（应用级共享数据）");
        System.out.println("      void setAttribute(String name, Object object);");
        System.out.println("      Object getAttribute(String name);");
        System.out.println("      ");
        System.out.println("      // 初始化参数");
        System.out.println("      String getInitParameter(String name);");
        System.out.println("      ");
        System.out.println("      // Servlet 管理");
        System.out.println("      ServletRegistration.Dynamic addServlet(String name, Servlet servlet);");
        System.out.println("      ");
        System.out.println("      // 日志");
        System.out.println("      void log(String message);");
        System.out.println("  }");
        System.out.println();

        // 2. 核心目的
        System.out.println("【二、核心目的和作用】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();
        System.out.println("ServletContext 代表 \"整个 Web 应用\"，它是：");
        System.out.println();

        System.out.println("1️⃣  Web 应用的全局上下文");
        System.out.println("   • 一个 Web 应用只有一个 ServletContext 实例");
        System.out.println("   • 在应用启动时创建，应用关闭时销毁");
        System.out.println("   • 所有 Servlet 共享同一个 ServletContext");
        System.out.println();
        System.out.println("   示例：");
        System.out.println("   ServletContext ctx = request.getServletContext();");
        System.out.println("   String appName = ctx.getContextPath();  // \"/myapp\"");
        System.out.println();

        System.out.println("2️⃣  应用级数据共享容器");
        System.out.println("   • 存储应用级别的共享数据");
        System.out.println("   • 所有 Servlet、Filter、Listener 都可以访问");
        System.out.println("   • 类似于全局变量，但线程安全");
        System.out.println();
        System.out.println("   示例：");
        System.out.println("   // Servlet A 存储数据");
        System.out.println("   ctx.setAttribute(\"visitCount\", 1000);");
        System.out.println("   ");
        System.out.println("   // Servlet B 读取数据");
        System.out.println("   Integer count = (Integer) ctx.getAttribute(\"visitCount\");");
        System.out.println();

        System.out.println("3️⃣  Web 资源访问器");
        System.out.println("   • 访问 Web 应用中的资源文件");
        System.out.println("   • 获取文件的真实路径");
        System.out.println("   • 读取配置文件");
        System.out.println();
        System.out.println("   示例：");
        System.out.println("   // 获取文件真实路径");
        System.out.println("   String path = ctx.getRealPath(\"/WEB-INF/config.xml\");");
        System.out.println("   // /usr/local/tomcat/webapps/myapp/WEB-INF/config.xml");
        System.out.println("   ");
        System.out.println("   // 读取资源");
        System.out.println("   InputStream is = ctx.getResourceAsStream(\"/WEB-INF/data.txt\");");
        System.out.println();

        System.out.println("4️⃣  应用配置管理器");
        System.out.println("   • 读取 web.xml 中的初始化参数");
        System.out.println("   • 获取应用的元数据");
        System.out.println();
        System.out.println("   web.xml 配置：");
        System.out.println("   <context-param>");
        System.out.println("       <param-name>adminEmail</param-name>");
        System.out.println("       <param-value>admin@example.com</param-value>");
        System.out.println("   </context-param>");
        System.out.println("   ");
        System.out.println("   代码读取：");
        System.out.println("   String email = ctx.getInitParameter(\"adminEmail\");");
        System.out.println();

        System.out.println("5️⃣  Servlet 容器接口");
        System.out.println("   • 动态注册 Servlet、Filter、Listener");
        System.out.println("   • 获取容器信息");
        System.out.println();
        System.out.println("   示例：");
        System.out.println("   // 动态注册 Servlet");
        System.out.println("   ServletRegistration.Dynamic servlet = ");
        System.out.println("       ctx.addServlet(\"myServlet\", new MyServlet());");
        System.out.println("   servlet.addMapping(\"/api/*\");");
        System.out.println();

        System.out.println("6️⃣  日志记录器");
        System.out.println("   • 记录应用日志");
        System.out.println("   • 日志输出到容器的日志文件");
        System.out.println();
        System.out.println("   示例：");
        System.out.println("   ctx.log(\"User login: \" + username);");
        System.out.println();

        // 3. 解决的核心问题
        System.out.println("【三、解决的核心问题】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("❌ 没有 ServletContext 的问题：");
        System.out.println();
        System.out.println("1. 如何在不同 Servlet 之间共享数据？");
        System.out.println("   • 使用静态变量？（不优雅，难以管理）");
        System.out.println("   • 使用数据库？（太重，性能差）");
        System.out.println();
        System.out.println("2. 如何访问 Web 应用的资源文件？");
        System.out.println("   • 硬编码路径？（不可移植）");
        System.out.println("   • 相对路径？（不可靠）");
        System.out.println();
        System.out.println("3. 如何获取应用的配置信息？");
        System.out.println("   • 每个 Servlet 都读取配置文件？（重复、低效）");
        System.out.println();
        System.out.println("4. 如何在应用启动时初始化全局资源？");
        System.out.println("   • 数据库连接池");
        System.out.println("   • 缓存");
        System.out.println("   • 定时任务");
        System.out.println();

        System.out.println("✅ 有了 ServletContext：");
        System.out.println();
        System.out.println("1. 应用级数据共享");
        System.out.println("   ctx.setAttribute(\"dbPool\", connectionPool);");
        System.out.println("   // 所有 Servlet 都可以访问");
        System.out.println();
        System.out.println("2. 统一资源访问");
        System.out.println("   String path = ctx.getRealPath(\"/config/app.properties\");");
        System.out.println("   // 自动解析为绝对路径");
        System.out.println();
        System.out.println("3. 集中配置管理");
        System.out.println("   String dbUrl = ctx.getInitParameter(\"database.url\");");
        System.out.println("   // 从 web.xml 统一读取");
        System.out.println();
        System.out.println("4. 生命周期管理");
        System.out.println("   // 使用 ServletContextListener 在应用启动时初始化");
        System.out.println("   @WebListener");
        System.out.println("   public class AppInitListener implements ServletContextListener {");
        System.out.println("       public void contextInitialized(ServletContextEvent event) {");
        System.out.println("           ServletContext ctx = event.getServletContext();");
        System.out.println("           // 初始化数据库连接池");
        System.out.println("           ctx.setAttribute(\"dbPool\", createConnectionPool());");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println();

        // 4. 与其他 Context 的对比
        System.out.println("【四、四种 Context 模式对比】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("┌──────────────────┬─────────────────┬─────────────────┬─────────────────┬─────────────────┐");
        System.out.println("│  Context 类型    │ JsonStreamCtx   │  ProcessCtx     │ ApplicationCtx  │  ServletContext │");
        System.out.println("├──────────────────┼─────────────────┼─────────────────┼─────────────────┼─────────────────┤");
        System.out.println("│ 核心问题         │ \"我在哪里？\"   │ \"我在哪一步？\" │ \"对象在哪里？\" │ \"应用是什么？\" │");
        System.out.println("│ 主要目的         │ 位置追踪        │ 流程追踪        │ 对象管理        │ 应用管理        │");
        System.out.println("│ 作用域           │ 解析过程        │ 流程执行        │ Spring 容器     │ Web 应用        │");
        System.out.println("│ 生命周期         │ 短（解析时）    │ 中（流程时）    │ 长（应用时）    │ 长（应用时）    │");
        System.out.println("│ 实例数量         │ 多个（嵌套）    │ 多个（嵌套）    │ 1个/容器        │ 1个/应用        │");
        System.out.println("│ 数据共享         │ 无              │ 流程内共享      │ 容器内共享      │ 应用内共享      │");
        System.out.println("│ 典型场景         │ JSON 解析       │ 订单流程        │ Spring 应用     │ Web 应用        │");
        System.out.println("└──────────────────┴─────────────────┴─────────────────┴─────────────────┴─────────────────┘");
        System.out.println();

        // 5. ServletContext 的特点
        System.out.println("【五、ServletContext 的核心特点】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("1. 单例性");
        System.out.println("   • 一个 Web 应用只有一个 ServletContext");
        System.out.println("   • 所有 Servlet 共享同一个实例");
        System.out.println();

        System.out.println("2. 全局性");
        System.out.println("   • 代表整个 Web 应用");
        System.out.println("   • 存储应用级别的数据");
        System.out.println();

        System.out.println("3. 生命周期长");
        System.out.println("   • 应用启动时创建");
        System.out.println("   • 应用关闭时销毁");
        System.out.println("   • 贯穿整个应用生命周期");
        System.out.println();

        System.out.println("4. 线程安全");
        System.out.println("   • 多个线程可以同时访问");
        System.out.println("   • 需要注意并发问题");
        System.out.println("   • setAttribute/getAttribute 是线程安全的");
        System.out.println();

        // 6. 实际应用场景
        System.out.println("【六、实际应用场景】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("场景 1：应用启动时初始化全局资源");
        System.out.println("  @WebListener");
        System.out.println("  public class AppInitListener implements ServletContextListener {");
        System.out.println("      public void contextInitialized(ServletContextEvent event) {");
        System.out.println("          ServletContext ctx = event.getServletContext();");
        System.out.println("          ");
        System.out.println("          // 初始化数据库连接池");
        System.out.println("          DataSource ds = createDataSource();");
        System.out.println("          ctx.setAttribute(\"dataSource\", ds);");
        System.out.println("          ");
        System.out.println("          // 初始化缓存");
        System.out.println("          Cache cache = new CacheImpl();");
        System.out.println("          ctx.setAttribute(\"cache\", cache);");
        System.out.println("          ");
        System.out.println("          // 加载配置");
        System.out.println("          Properties config = loadConfig(ctx);");
        System.out.println("          ctx.setAttribute(\"config\", config);");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println();

        System.out.println("场景 2：统计在线用户数");
        System.out.println("  // 用户登录时");
        System.out.println("  public void userLogin(HttpServletRequest request) {");
        System.out.println("      ServletContext ctx = request.getServletContext();");
        System.out.println("      Integer count = (Integer) ctx.getAttribute(\"onlineUsers\");");
        System.out.println("      if (count == null) count = 0;");
        System.out.println("      ctx.setAttribute(\"onlineUsers\", count + 1);");
        System.out.println("  }");
        System.out.println();

        System.out.println("场景 3：访问 Web 资源");
        System.out.println("  // 读取配置文件");
        System.out.println("  ServletContext ctx = getServletContext();");
        System.out.println("  InputStream is = ctx.getResourceAsStream(\"/WEB-INF/config.xml\");");
        System.out.println("  ");
        System.out.println("  // 获取上传文件的保存路径");
        System.out.println("  String uploadPath = ctx.getRealPath(\"/uploads\");");
        System.out.println();

        System.out.println("场景 4：Spring 与 ServletContext 集成");
        System.out.println("  // Spring 启动时");
        System.out.println("  public void onStartup(ServletContext servletContext) {");
        System.out.println("      // 创建 Spring 容器");
        System.out.println("      AnnotationConfigWebApplicationContext ctx = ");
        System.out.println("          new AnnotationConfigWebApplicationContext();");
        System.out.println("      ");
        System.out.println("      // 关联 ServletContext");
        System.out.println("      ctx.setServletContext(servletContext);");
        System.out.println("      ");
        System.out.println("      // 注册 DispatcherServlet");
        System.out.println("      ServletRegistration.Dynamic servlet = ");
        System.out.println("          servletContext.addServlet(\"dispatcher\", new DispatcherServlet(ctx));");
        System.out.println("      servlet.addMapping(\"/\");");
        System.out.println("  }");
        System.out.println();

        // 7. 与 ApplicationContext 的关系
        System.out.println("【七、ServletContext vs ApplicationContext】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("两者的关系：");
        System.out.println("  ServletContext (Servlet 规范)");
        System.out.println("       ↓ 包含");
        System.out.println("  WebApplicationContext (Spring)");
        System.out.println("       ↓ 继承");
        System.out.println("  ApplicationContext (Spring)");
        System.out.println();

        System.out.println("区别：");
        System.out.println("┌─────────────────┬──────────────────────┬──────────────────────┐");
        System.out.println("│     维度        │   ServletContext     │  ApplicationContext  │");
        System.out.println("├─────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ 来源            │ Servlet 规范         │ Spring 框架          │");
        System.out.println("│ 作用域          │ Web 应用             │ Spring 容器          │");
        System.out.println("│ 管理对象        │ Servlet、Filter      │ Spring Bean          │");
        System.out.println("│ 依赖注入        │ 无                   │ 有                   │");
        System.out.println("│ AOP 支持        │ 无                   │ 有                   │");
        System.out.println("│ 生命周期管理    │ 简单                 │ 复杂（完整）         │");
        System.out.println("└─────────────────┴──────────────────────┴──────────────────────┘");
        System.out.println();

        System.out.println("协作关系：");
        System.out.println("  // Spring 可以访问 ServletContext");
        System.out.println("  @Autowired");
        System.out.println("  private ServletContext servletContext;");
        System.out.println("  ");
        System.out.println("  // ServletContext 可以存储 ApplicationContext");
        System.out.println("  servletContext.setAttribute(");
        System.out.println("      WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,");
        System.out.println("      applicationContext);");
        System.out.println();

        // 8. 总结
        System.out.println("【八、总结】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();
        System.out.println("ServletContext 是 \"Web 应用上下文\"，它：");
        System.out.println();
        System.out.println("✓ 代表整个 Web 应用");
        System.out.println("✓ 提供应用级数据共享");
        System.out.println("✓ 管理 Web 资源访问");
        System.out.println("✓ 提供应用配置");
        System.out.println("✓ 管理 Servlet 容器");
        System.out.println();
        System.out.println("核心价值：");
        System.out.println("  解决了 \"Web 应用全局信息管理\" 的问题");
        System.out.println("  提供了 \"应用级数据共享\" 的机制");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ServletContext = Web 应用上下文 + 全局数据容器 + 资源管理器       ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }
}

