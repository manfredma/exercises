package manfred.end.context.spring;

import java.util.*;

/**
 * 模拟 ApplicationContext 的核心功能
 * 展示容器上下文的设计思想
 *
 * @author manfred
 */
public class ApplicationContextExample {

    // ========== 简化版的 ApplicationContext ==========
    static class SimpleApplicationContext {
        // Bean 容器（单例池）
        private Map<String, Object> singletonBeans = new HashMap<>();

        // Bean 定义
        private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();

        // 配置属性
        private Properties properties = new Properties();

        // 事件监听器
        private List<ApplicationListener> listeners = new ArrayList<>();

        // 父容器
        private SimpleApplicationContext parent;

        /**
         * 注册 Bean 定义
         */
        public void registerBean(String name, Class<?> clazz) {
            beanDefinitions.put(name, new BeanDefinition(name, clazz));
        }

        /**
         * 获取 Bean（核心功能）
         */
        @SuppressWarnings("unchecked")
        public <T> T getBean(String name, Class<T> requiredType) {
            // 1. 先从单例池获取
            Object bean = singletonBeans.get(name);
            if (bean != null) {
                return (T) bean;
            }

            // 2. 从父容器获取
            if (parent != null) {
                return parent.getBean(name, requiredType);
            }

            // 3. 创建新的 Bean
            BeanDefinition bd = beanDefinitions.get(name);
            if (bd != null) {
                try {
                    bean = bd.getBeanClass().getDeclaredConstructor().newInstance();
                    singletonBeans.put(name, bean);
                    System.out.println("  [容器] 创建 Bean: " + name + " -> " + bean.getClass().getSimpleName());
                    return (T) bean;
                } catch (Exception e) {
                    throw new RuntimeException("创建 Bean 失败: " + name, e);
                }
            }

            throw new RuntimeException("找不到 Bean: " + name);
        }

        /**
         * 按类型获取 Bean
         */
        @SuppressWarnings("unchecked")
        public <T> T getBean(Class<T> requiredType) {
            for (Map.Entry<String, Object> entry : singletonBeans.entrySet()) {
                if (requiredType.isInstance(entry.getValue())) {
                    return (T) entry.getValue();
                }
            }

            // 尝试创建
            for (Map.Entry<String, BeanDefinition> entry : beanDefinitions.entrySet()) {
                if (requiredType.isAssignableFrom(entry.getValue().getBeanClass())) {
                    return getBean(entry.getKey(), requiredType);
                }
            }

            throw new RuntimeException("找不到类型为 " + requiredType + " 的 Bean");
        }

        /**
         * 获取所有指定类型的 Bean
         */
        @SuppressWarnings("unchecked")
        public <T> Map<String, T> getBeansOfType(Class<T> type) {
            Map<String, T> result = new HashMap<>();
            for (Map.Entry<String, Object> entry : singletonBeans.entrySet()) {
                if (type.isInstance(entry.getValue())) {
                    result.put(entry.getKey(), (T) entry.getValue());
                }
            }
            return result;
        }

        /**
         * 设置配置属性
         */
        public void setProperty(String key, String value) {
            properties.setProperty(key, value);
        }

        /**
         * 获取配置属性
         */
        public String getProperty(String key) {
            String value = properties.getProperty(key);
            if (value == null && parent != null) {
                return parent.getProperty(key);
            }
            return value;
        }

        /**
         * 发布事件
         */
        public void publishEvent(ApplicationEvent event) {
            System.out.println("  [容器] 发布事件: " + event.getClass().getSimpleName());
            for (ApplicationListener listener : listeners) {
                listener.onApplicationEvent(event);
            }
            // 传播到父容器
            if (parent != null) {
                parent.publishEvent(event);
            }
        }

        /**
         * 添加事件监听器
         */
        public void addApplicationListener(ApplicationListener listener) {
            listeners.add(listener);
        }

        /**
         * 设置父容器
         */
        public void setParent(SimpleApplicationContext parent) {
            this.parent = parent;
        }

        /**
         * 获取所有 Bean 名称
         */
        public String[] getBeanDefinitionNames() {
            return beanDefinitions.keySet().toArray(new String[0]);
        }
    }

    // ========== Bean 定义 ==========
    static class BeanDefinition {
        private String name;
        private Class<?> beanClass;

        public BeanDefinition(String name, Class<?> beanClass) {
            this.name = name;
            this.beanClass = beanClass;
        }

        public String getName() {
            return name;
        }

        public Class<?> getBeanClass() {
            return beanClass;
        }
    }

    // ========== 事件相关 ==========
    static class ApplicationEvent {
        private Object source;

        public ApplicationEvent(Object source) {
            this.source = source;
        }

        public Object getSource() {
            return source;
        }
    }

    static class OrderCreatedEvent extends ApplicationEvent {
        public OrderCreatedEvent(Object source) {
            super(source);
        }
    }

    interface ApplicationListener {
        void onApplicationEvent(ApplicationEvent event);
    }

    // ========== 业务类 ==========
    static class UserService {
        public void createUser(String name) {
            System.out.println("    [UserService] 创建用户: " + name);
        }
    }

    static class OrderService {
        public void createOrder(String orderId) {
            System.out.println("    [OrderService] 创建订单: " + orderId);
        }
    }

    static class EmailService {
        public void sendEmail(String to, String content) {
            System.out.println("    [EmailService] 发送邮件到: " + to);
        }
    }

    // ========== 测试 ==========
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         ApplicationContext 功能演示                                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. 创建容器
        System.out.println("【1. 创建 ApplicationContext 容器】");
        System.out.println("─────────────────────────────────────────────────────────");
        SimpleApplicationContext context = new SimpleApplicationContext();
        System.out.println("✓ 容器创建完成");
        System.out.println();

        // 2. 注册 Bean
        System.out.println("【2. 注册 Bean 定义】");
        System.out.println("─────────────────────────────────────────────────────────");
        context.registerBean("userService", UserService.class);
        context.registerBean("orderService", OrderService.class);
        context.registerBean("emailService", EmailService.class);
        System.out.println("✓ 注册了 3 个 Bean 定义");
        System.out.println();

        // 3. 获取 Bean（懒加载）
        System.out.println("【3. 获取 Bean（首次获取会创建）】");
        System.out.println("─────────────────────────────────────────────────────────");
        UserService userService1 = context.getBean("userService", UserService.class);
        UserService userService2 = context.getBean("userService", UserService.class);
        System.out.println("✓ 两次获取的是同一个实例: " + (userService1 == userService2));
        System.out.println();

        // 4. 按类型获取
        System.out.println("【4. 按类型获取 Bean】");
        System.out.println("─────────────────────────────────────────────────────────");
        OrderService orderService = context.getBean(OrderService.class);
        orderService.createOrder("ORDER-001");
        System.out.println();

        // 5. 配置管理
        System.out.println("【5. 配置管理】");
        System.out.println("─────────────────────────────────────────────────────────");
        context.setProperty("server.port", "8080");
        context.setProperty("app.name", "MyApp");
        System.out.println("  配置: server.port = " + context.getProperty("server.port"));
        System.out.println("  配置: app.name = " + context.getProperty("app.name"));
        System.out.println();

        // 6. 事件发布
        System.out.println("【6. 事件发布和监听】");
        System.out.println("─────────────────────────────────────────────────────────");

        // 添加监听器
        context.addApplicationListener(event -> {
            if (event instanceof OrderCreatedEvent) {
                System.out.println("    [监听器1] 收到订单创建事件，发送通知邮件");
            }
        });

        context.addApplicationListener(event -> {
            if (event instanceof OrderCreatedEvent) {
                System.out.println("    [监听器2] 收到订单创建事件，更新库存");
            }
        });

        // 发布事件
        context.publishEvent(new OrderCreatedEvent("ORDER-002"));
        System.out.println();

        // 7. 层级容器
        System.out.println("【7. 层级容器（父子容器）】");
        System.out.println("─────────────────────────────────────────────────────────");

        // 创建父容器
        SimpleApplicationContext parentContext = new SimpleApplicationContext();
        parentContext.registerBean("commonService", UserService.class);
        parentContext.setProperty("common.config", "parent-value");

        // 创建子容器
        SimpleApplicationContext childContext = new SimpleApplicationContext();
        childContext.setParent(parentContext);
        childContext.registerBean("childService", OrderService.class);

        // 子容器可以访问父容器的 Bean
        System.out.println("  子容器获取父容器的 Bean:");
        UserService commonService = childContext.getBean("commonService", UserService.class);
        commonService.createUser("Alice");

        // 子容器可以访问父容器的配置
        System.out.println("  子容器获取父容器的配置: " +
                childContext.getProperty("common.config"));
        System.out.println();

        // 8. 获取所有 Bean
        System.out.println("【8. 获取所有 Bean 名称】");
        System.out.println("─────────────────────────────────────────────────────────");
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("  容器中的所有 Bean:");
        for (String name : beanNames) {
            System.out.println("    • " + name);
        }
        System.out.println();

        // 9. 对比总结
        System.out.println("【9. ApplicationContext vs 其他 Context】");
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
        System.out.println("  目的: 管理应用中的所有对象");
        System.out.println("  问题: \"对象在哪里？如何获取？\"");
        System.out.println("  示例: ctx.getBean(UserService.class)");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ApplicationContext 是 IoC 容器，负责对象的创建、管理和查找         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }
}

