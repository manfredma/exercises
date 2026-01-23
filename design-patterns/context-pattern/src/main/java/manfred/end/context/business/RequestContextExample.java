package manfred.end.context.business;

/**
 * RequestContext 使用示例
 *
 * @author manfred
 */
public class RequestContextExample {

    // ========== 模拟业务服务 ==========

    static class UserService {
        public void updateProfile(String name, String email) {
            // 自动获取当前用户ID
            Long userId = RequestContext.getCurrentUserId();
            String traceId = RequestContext.getCurrentTraceId();

            System.out.println(String.format("  [UserService] 更新用户资料 - userId=%d, traceId=%s", userId, traceId));
            System.out.println(String.format("    更新内容: name=%s, email=%s", name, email));

            // 记录操作日志
            logOperation("UPDATE_PROFILE");
        }

        private void logOperation(String operation) {
            RequestContext ctx = RequestContext.get();
            System.out.println(String.format("  [AuditLog] 操作=%s, 用户=%s, 租户=%s, IP=%s",
                    operation, ctx.getUsername(), ctx.getTenantName(), ctx.getClientIp()));
        }
    }

    static class OrderService {
        public void createOrder(String productId, int quantity) {
            // 检查权限
            RequestContext ctx = RequestContext.get();
            if (!ctx.hasPermission("order:create")) {
                System.out.println("  [OrderService] ❌ 权限不足，无法创建订单");
                return;
            }

            // 获取租户信息（多租户隔离）
            String tenantId = RequestContext.getCurrentTenantId();
            Long userId = RequestContext.getCurrentUserId();

            System.out.println(String.format("  [OrderService] ✅ 创建订单 - 租户=%s, 用户=%d", tenantId, userId));
            System.out.println(String.format("    商品ID=%s, 数量=%d", productId, quantity));

            // 调用其他服务（自动传递上下文）
            InventoryService inventoryService = new InventoryService();
            inventoryService.deductStock(productId, quantity);
        }
    }

    static class InventoryService {
        public void deductStock(String productId, int quantity) {
            // 自动获取租户ID（多租户数据隔离）
            String tenantId = RequestContext.getCurrentTenantId();
            String traceId = RequestContext.getCurrentTraceId();

            System.out.println(String.format("  [InventoryService] 扣减库存 - 租户=%s, traceId=%s", tenantId, traceId));
            System.out.println(String.format("    商品ID=%s, 数量=%d", productId, quantity));
        }
    }

    static class ReportService {
        public void generateReport(String reportType) {
            RequestContext ctx = RequestContext.get();

            // 数据权限过滤（只能查看自己部门的数据）
            Long departmentId = ctx.getDepartmentId();

            System.out.println(String.format("  [ReportService] 生成报表 - 类型=%s", reportType));
            System.out.println(String.format("    数据范围: 部门ID=%d", departmentId));

            // 检查角色
            if (ctx.hasRole("ADMIN")) {
                System.out.println("    权限: 管理员，可查看所有数据");
            } else {
                System.out.println("    权限: 普通用户，仅查看本部门数据");
            }
        }
    }

    // ========== 模拟 Web 过滤器 ==========

    static class RequestContextFilter {
        public void doFilter(String requestId, String userId, String tenantId) {
            try {
                // 1. 从请求中提取信息，构建 RequestContext
                RequestContext context = RequestContext.builder()
                        .requestId(requestId)
                        .traceId("trace-" + requestId)
                        .userId(Long.parseLong(userId))
                        .username("user_" + userId)
                        .tenantId(tenantId)
                        .tenantName("租户_" + tenantId)
                        .departmentId(1001L)
                        .roles("USER", "MANAGER")
                        .permissions("order:create", "order:view", "user:update")
                        .clientIp("192.168.1.100")
                        .deviceType("WEB")
                        .build();

                // 2. 设置到 ThreadLocal
                RequestContext.set(context);

                System.out.println("✓ RequestContext 已设置: " + context);
                System.out.println();

                // 3. 执行业务逻辑（业务代码自动获取上下文）
                processRequest();

            } finally {
                // 4. 请求结束，清理 ThreadLocal（防止内存泄漏）
                RequestContext ctx = RequestContext.get();
                System.out.println();
                System.out.println("✓ 请求处理完成，耗时: " + ctx.getElapsedTime() + "ms");
                RequestContext.clear();
            }
        }

        private void processRequest() {
            // 业务代码不需要显式传递用户信息
            // 直接从 RequestContext 获取
        }
    }

    // ========== 测试 ==========

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         业务场景1：RequestContext（请求上下文）                      ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("【应用场景】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("1. Web 请求处理 - 传递用户信息、租户信息");
        System.out.println("2. 分布式追踪 - TraceId、SpanId");
        System.out.println("3. 日志记录 - 自动记录用户、租户、请求ID");
        System.out.println("4. 权限验证 - 传递用户权限信息");
        System.out.println("5. 多租户系统 - 租户隔离");
        System.out.println();

        System.out.println("【实际案例】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("• 美团外卖：用户下单时，RequestContext 携带用户ID、城市ID、设备信息");
        System.out.println("• 阿里云：API 调用时，RequestContext 携带租户ID、AccessKey、Region");
        System.out.println("• 企业 ERP：操作时，RequestContext 携带员工ID、部门ID、权限列表");
        System.out.println();

        // 模拟请求1
        System.out.println("【模拟请求1：用户更新资料】");
        System.out.println("─────────────────────────────────────────────────────────");
        RequestContextFilter filter = new RequestContextFilter();
        filter.doFilter("REQ-001", "10001", "TENANT-A");

        UserService userService = new UserService();
        userService.updateProfile("张三", "zhangsan@example.com");

        RequestContext.clear();
        System.out.println();
        System.out.println();

        // 模拟请求2
        System.out.println("【模拟请求2：创建订单（多租户 + 权限验证）】");
        System.out.println("─────────────────────────────────────────────────────────");
        filter.doFilter("REQ-002", "10002", "TENANT-B");

        OrderService orderService = new OrderService();
        orderService.createOrder("PROD-12345", 2);

        RequestContext.clear();
        System.out.println();
        System.out.println();

        // 模拟请求3
        System.out.println("【模拟请求3：生成报表（数据权限）】");
        System.out.println("─────────────────────────────────────────────────────────");
        filter.doFilter("REQ-003", "10003", "TENANT-A");

        ReportService reportService = new ReportService();
        reportService.generateReport("销售报表");

        RequestContext.clear();
        System.out.println();
        System.out.println();

        // 总结
        System.out.println("【核心优势】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("✓ 无需显式传递用户信息 - 自动从 ThreadLocal 获取");
        System.out.println("✓ 多租户数据隔离 - 自动过滤租户数据");
        System.out.println("✓ 分布式追踪 - TraceId 自动传递");
        System.out.println("✓ 权限验证简化 - 统一的权限检查");
        System.out.println("✓ 日志记录增强 - 自动记录用户、租户信息");
        System.out.println();

        System.out.println("【注意事项】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("⚠️  必须在请求结束时调用 clear() - 防止内存泄漏");
        System.out.println("⚠️  异步线程需要手动传递 - ThreadLocal 不会自动传递");
        System.out.println("⚠️  线程池场景需要特殊处理 - 使用 TransmittableThreadLocal");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  RequestContext 是企业系统中最常用的 Context 模式                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }
}

