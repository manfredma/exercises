package manfred.end.context.servlet;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟 ServletContext 的核心功能
 * 展示 Web 应用上下文的设计思想
 *
 * @author manfred
 */
public class ServletContextExample {

    // ========== 简化版的 ServletContext ==========
    static class SimpleServletContext {
        // 应用路径
        private String contextPath;

        // 应用级属性（线程安全）
        private Map<String, Object> attributes = new ConcurrentHashMap<>();

        // 初始化参数（来自 web.xml）
        private Map<String, String> initParameters = new HashMap<>();

        // 注册的 Servlet
        private Map<String, Object> servlets = new HashMap<>();

        // MIME 类型映射
        private Map<String, String> mimeTypes = new HashMap<>();

        public SimpleServletContext(String contextPath) {
            this.contextPath = contextPath;
            initDefaultMimeTypes();
        }

        private void initDefaultMimeTypes() {
            mimeTypes.put("html", "text/html");
            mimeTypes.put("css", "text/css");
            mimeTypes.put("js", "application/javascript");
            mimeTypes.put("json", "application/json");
            mimeTypes.put("xml", "application/xml");
            mimeTypes.put("jpg", "image/jpeg");
            mimeTypes.put("png", "image/png");
        }

        // ========== 应用信息 ==========

        public String getContextPath() {
            return contextPath;
        }

        public String getServerInfo() {
            return "SimpleServletContainer/1.0";
        }

        public int getMajorVersion() {
            return 3;
        }

        public int getMinorVersion() {
            return 1;
        }

        // ========== 属性管理（应用级数据共享） ==========

        public void setAttribute(String name, Object object) {
            if (object == null) {
                attributes.remove(name);
            } else {
                attributes.put(name, object);
            }
            log("设置属性: " + name + " = " + object);
        }

        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        public void removeAttribute(String name) {
            attributes.remove(name);
            log("移除属性: " + name);
        }

        public Enumeration<String> getAttributeNames() {
            return Collections.enumeration(attributes.keySet());
        }

        // ========== 初始化参数 ==========

        public void setInitParameter(String name, String value) {
            initParameters.put(name, value);
        }

        public String getInitParameter(String name) {
            return initParameters.get(name);
        }

        public Enumeration<String> getInitParameterNames() {
            return Collections.enumeration(initParameters.keySet());
        }

        // ========== 资源访问 ==========

        public String getRealPath(String path) {
            // 模拟获取真实路径
            return "/usr/local/tomcat/webapps" + contextPath + path;
        }

        public InputStream getResourceAsStream(String path) {
            // 模拟读取资源
            log("读取资源: " + path);
            return null; // 简化实现
        }

        public String getMimeType(String file) {
            int dotIndex = file.lastIndexOf('.');
            if (dotIndex > 0) {
                String extension = file.substring(dotIndex + 1);
                return mimeTypes.get(extension);
            }
            return null;
        }

        // ========== Servlet 管理 ==========

        public void addServlet(String name, Object servlet) {
            servlets.put(name, servlet);
            log("注册 Servlet: " + name);
        }

        public Object getServlet(String name) {
            return servlets.get(name);
        }

        // ========== 日志 ==========

        public void log(String message) {
            System.out.println("  [ServletContext] " + message);
        }

        public void log(String message, Throwable throwable) {
            System.out.println("  [ServletContext] " + message);
            throwable.printStackTrace();
        }
    }

    // ========== 模拟 Servlet ==========
    static class UserServlet {
        private SimpleServletContext servletContext;

        public UserServlet(SimpleServletContext servletContext) {
            this.servletContext = servletContext;
        }

        public void service() {
            System.out.println("    [UserServlet] 处理请求");

            // 访问应用级数据
            Integer visitCount = (Integer) servletContext.getAttribute("visitCount");
            if (visitCount == null) {
                visitCount = 0;
            }
            visitCount++;
            servletContext.setAttribute("visitCount", visitCount);

            System.out.println("    [UserServlet] 访问次数: " + visitCount);
        }
    }

    static class OrderServlet {
        private SimpleServletContext servletContext;

        public OrderServlet(SimpleServletContext servletContext) {
            this.servletContext = servletContext;
        }

        public void service() {
            System.out.println("    [OrderServlet] 处理请求");

            // 读取配置
            String dbUrl = servletContext.getInitParameter("database.url");
            System.out.println("    [OrderServlet] 数据库URL: " + dbUrl);

            // 访问共享数据
            Integer visitCount = (Integer) servletContext.getAttribute("visitCount");
            System.out.println("    [OrderServlet] 当前访问次数: " + visitCount);
        }
    }

    // ========== 模拟 ServletContextListener ==========
    static class AppInitListener {
        public void contextInitialized(SimpleServletContext context) {
            System.out.println("  [Listener] 应用启动，初始化全局资源");

            // 初始化数据库连接池（模拟）
            Object dbPool = new Object() {
                @Override
                public String toString() {
                    return "DatabaseConnectionPool[size=10]";
                }
            };
            context.setAttribute("dbPool", dbPool);

            // 初始化缓存（模拟）
            Map<String, Object> cache = new ConcurrentHashMap<>();
            context.setAttribute("cache", cache);

            // 初始化访问计数器
            context.setAttribute("visitCount", 0);

            System.out.println("  [Listener] 全局资源初始化完成");
        }

        public void contextDestroyed(SimpleServletContext context) {
            System.out.println("  [Listener] 应用关闭，清理资源");

            // 关闭数据库连接池
            Object dbPool = context.getAttribute("dbPool");
            System.out.println("  [Listener] 关闭: " + dbPool);

            // 清理缓存
            context.removeAttribute("cache");
        }
    }

    // ========== 测试 ==========
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         ServletContext 功能演示                                      ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. 创建 ServletContext（应用启动）
        System.out.println("【1. 创建 ServletContext（应用启动）】");
        System.out.println("─────────────────────────────────────────────────────────");
        SimpleServletContext context = new SimpleServletContext("/myapp");
        System.out.println("✓ ServletContext 创建完成");
        System.out.println("  应用路径: " + context.getContextPath());
        System.out.println("  服务器信息: " + context.getServerInfo());
        System.out.println("  Servlet 版本: " + context.getMajorVersion() + "." + context.getMinorVersion());
        System.out.println();

        // 2. 设置初始化参数（来自 web.xml）
        System.out.println("【2. 设置初始化参数（模拟 web.xml）】");
        System.out.println("─────────────────────────────────────────────────────────");
        context.setInitParameter("database.url", "jdbc:mysql://localhost:3306/mydb");
        context.setInitParameter("database.username", "root");
        context.setInitParameter("admin.email", "admin@example.com");
        System.out.println("✓ 初始化参数设置完成");
        System.out.println();

        // 3. 应用启动监听器
        System.out.println("【3. 应用启动监听器（初始化全局资源）】");
        System.out.println("─────────────────────────────────────────────────────────");
        AppInitListener listener = new AppInitListener();
        listener.contextInitialized(context);
        System.out.println();

        // 4. 注册 Servlet
        System.out.println("【4. 注册 Servlet】");
        System.out.println("─────────────────────────────────────────────────────────");
        UserServlet userServlet = new UserServlet(context);
        OrderServlet orderServlet = new OrderServlet(context);
        context.addServlet("userServlet", userServlet);
        context.addServlet("orderServlet", orderServlet);
        System.out.println();

        // 5. 模拟请求处理（多个 Servlet 共享数据）
        System.out.println("【5. 模拟请求处理（Servlet 共享数据）】");
        System.out.println("─────────────────────────────────────────────────────────");

        System.out.println("  请求 1: UserServlet");
        userServlet.service();
        System.out.println();

        System.out.println("  请求 2: OrderServlet");
        orderServlet.service();
        System.out.println();

        System.out.println("  请求 3: UserServlet");
        userServlet.service();
        System.out.println();

        // 6. 资源访问
        System.out.println("【6. 资源访问】");
        System.out.println("─────────────────────────────────────────────────────────");
        String configPath = context.getRealPath("/WEB-INF/config.xml");
        System.out.println("  配置文件路径: " + configPath);

        String uploadPath = context.getRealPath("/uploads");
        System.out.println("  上传目录路径: " + uploadPath);

        String mimeType = context.getMimeType("index.html");
        System.out.println("  MIME 类型: index.html -> " + mimeType);
        System.out.println();

        // 7. 查看所有属性
        System.out.println("【7. 查看所有应用级属性】");
        System.out.println("─────────────────────────────────────────────────────────");
        Enumeration<String> attrNames = context.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String name = attrNames.nextElement();
            Object value = context.getAttribute(name);
            System.out.println("  " + name + " = " + value);
        }
        System.out.println();

        // 8. 应用关闭
        System.out.println("【8. 应用关闭（清理资源）】");
        System.out.println("─────────────────────────────────────────────────────────");
        listener.contextDestroyed(context);
        System.out.println();

        // 9. 对比总结
        System.out.println("【9. 四种 Context 对比】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();
        System.out.println("JsonStreamContext:");
        System.out.println("  目的: 追踪 JSON 解析位置");
        System.out.println("  问题: \"我在 JSON 的哪个位置？\"");
        System.out.println("  示例: /user/orders[2]");
        System.out.println();
        System.out.println("ProcessContext:");
        System.out.println("  目的: 追踪业务流程执行位置");
        System.out.println("  问题: \"我在流程的哪一步？\"");
        System.out.println("  示例: ROOT -> Validation -> PaymentCheck");
        System.out.println();
        System.out.println("ApplicationContext:");
        System.out.println("  目的: 管理 Spring 容器中的对象");
        System.out.println("  问题: \"对象在哪里？如何获取？\"");
        System.out.println("  示例: ctx.getBean(UserService.class)");
        System.out.println();
        System.out.println("ServletContext:");
        System.out.println("  目的: 管理 Web 应用的全局信息");
        System.out.println("  问题: \"应用是什么？如何共享数据？\"");
        System.out.println("  示例: ctx.getAttribute(\"dbPool\")");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ServletContext 是 Web 应用上下文，负责应用级数据共享和资源管理    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }
}

