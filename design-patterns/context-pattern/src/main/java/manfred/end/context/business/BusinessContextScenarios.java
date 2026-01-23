package manfred.end.context.business;

import java.math.BigDecimal;

/**
 * 企业业务系统中的 Context 模式典型场景汇总
 *
 * @author manfred
 */
public class BusinessContextScenarios {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         企业业务系统中的 Context 模式典型场景                        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 场景1：请求上下文
        scenario1_RequestContext();

        // 场景2：价格计算上下文
        scenario2_PriceCalculation();

        // 场景汇总
        printAllScenarios();
    }

    /**
     * 场景1：请求上下文（最常见）
     */
    private static void scenario1_RequestContext() {
        System.out.println("【场景1：RequestContext - 请求上下文】");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();

        // 模拟 Web 请求
        RequestContext context = RequestContext.builder()
                .requestId("REQ-20231115-001")
                .traceId("TRACE-ABC123")
                .userId(10001L)
                .username("张三")
                .tenantId("TENANT-001")
                .tenantName("阿里巴巴")
                .departmentId(1001L)
                .roles("USER", "MANAGER")
                .permissions("order:create", "order:view")
                .clientIp("192.168.1.100")
                .deviceType("WEB")
                .build();

        RequestContext.set(context);

        try {
            System.out.println("✓ 请求上下文已设置");
            System.out.println("  " + context);
            System.out.println();

            // 业务代码自动获取上下文
            System.out.println("业务代码执行:");
            System.out.println("  当前用户ID: " + RequestContext.getCurrentUserId());
            System.out.println("  当前租户ID: " + RequestContext.getCurrentTenantId());
            System.out.println("  当前TraceId: " + RequestContext.getCurrentTraceId());
            System.out.println("  是否有权限: " + context.hasPermission("order:create"));

        } finally {
            RequestContext.clear();
        }

        System.out.println();
        System.out.println("应用场景:");
        System.out.println("  • Web 请求处理");
        System.out.println("  • 分布式追踪");
        System.out.println("  • 多租户系统");
        System.out.println("  • 权限验证");
        System.out.println("  • 日志记录");
        System.out.println();
        System.out.println();
    }

    /**
     * 场景2：价格计算上下文
     */
    private static void scenario2_PriceCalculation() {
        System.out.println("【场景2：PriceCalculationContext - 价格计算上下文】");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();

        // 创建计算上下文
        PriceCalculationContext context = new PriceCalculationContext(
                "CALC-001", 10001L, "VIP");

        // 添加商品
        context.addProduct("P001", "iPhone 15 Pro", new BigDecimal("7999"), 1);
        context.addProduct("P002", "AirPods Pro", new BigDecimal("1999"), 1);

        // 添加优惠
        context.setMemberDiscount(new BigDecimal("0.95"));  // VIP 95折
        context.addFullReduction(new BigDecimal("5000"), new BigDecimal("500"));  // 满5000减500
        context.addCoupon(new PriceCalculationContext.Coupon(
                "COUPON-001", "新用户券", "FIXED", new BigDecimal("200"), new BigDecimal("1000")));

        // 执行计算
        context.calculate();

        // 打印明细
        context.printDetails();

        System.out.println();
        System.out.println("应用场景:");
        System.out.println("  • 电商价格计算");
        System.out.println("  • 保险费率计算");
        System.out.println("  • 佣金计算");
        System.out.println("  • 积分计算");
        System.out.println();
        System.out.println();
    }

    /**
     * 打印所有典型场景
     */
    private static void printAllScenarios() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         企业系统中 Context 模式的典型场景汇总                        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        String[][] scenarios = {
            {"1", "RequestContext", "请求上下文", "Web请求、分布式追踪、多租户", "⭐⭐⭐⭐⭐"},
            {"2", "TransactionContext", "交易上下文", "分布式事务、交易流程追踪", "⭐⭐⭐⭐"},
            {"3", "ApprovalContext", "审批上下文", "多级审批、审批历史追踪", "⭐⭐⭐⭐"},
            {"4", "PriceCalculationContext", "价格计算上下文", "电商价格、保险费率、佣金", "⭐⭐⭐⭐"},
            {"5", "ImportExportContext", "导入导出上下文", "批量数据处理、进度追踪", "⭐⭐⭐"},
            {"6", "RuleEngineContext", "规则引擎上下文", "业务规则执行、规则链路", "⭐⭐⭐⭐"},
            {"7", "TenantContext", "多租户上下文", "租户隔离、租户配置", "⭐⭐⭐⭐"},
            {"8", "SecurityContext", "安全上下文", "认证信息、权限验证", "⭐⭐⭐⭐⭐"},
            {"9", "CacheContext", "缓存上下文", "缓存策略、缓存失效", "⭐⭐⭐"},
            {"10", "ValidationContext", "验证上下文", "数据验证、错误收集", "⭐⭐⭐"},
            {"11", "NotificationContext", "通知上下文", "消息推送、通知模板", "⭐⭐⭐"},
            {"12", "ReportContext", "报表上下文", "报表生成、数据权限", "⭐⭐⭐"},
        };

        System.out.println("┌────┬──────────────────────┬────────────────┬──────────────────────────┬────────┐");
        System.out.println("│ 序号│ Context 类型         │ 中文名称       │ 应用场景                 │ 常用度 │");
        System.out.println("├────┼──────────────────────┼────────────────┼──────────────────────────┼────────┤");

        for (String[] scenario : scenarios) {
            System.out.printf("│ %-2s │ %-20s │ %-14s │ %-24s │ %-6s │%n",
                    scenario[0], scenario[1], scenario[2], scenario[3], scenario[4]);
        }

        System.out.println("└────┴──────────────────────┴────────────────┴──────────────────────────┴────────┘");
        System.out.println();

        // 详细说明
        System.out.println("【详细说明】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        printScenarioDetail("1. RequestContext（请求上下文）",
                "最常用的 Context，几乎所有 Web 应用都需要",
                new String[]{
                    "• 传递用户信息（userId、username、roles）",
                    "• 传递租户信息（tenantId、tenantName）",
                    "• 分布式追踪（traceId、spanId）",
                    "• 权限验证（permissions）",
                    "• 日志记录（自动记录用户、租户）"
                },
                new String[]{
                    "美团外卖：用户下单时携带用户ID、城市ID、设备信息",
                    "阿里云：API调用时携带租户ID、AccessKey、Region",
                    "企业ERP：操作时携带员工ID、部门ID、权限列表"
                });

        printScenarioDetail("2. TransactionContext（交易上下文）",
                "管理分布式事务和交易流程",
                new String[]{
                    "• 分布式事务管理（TCC、Saga）",
                    "• 交易流程追踪（订单、支付、发货）",
                    "• 交易日志记录",
                    "• 补偿操作管理"
                },
                new String[]{
                    "支付宝：支付流程中的多个服务调用",
                    "京东：订单创建 -> 扣库存 -> 扣款 -> 发货",
                    "银行转账：扣款 -> 加款 -> 记账"
                });

        printScenarioDetail("3. ApprovalContext（审批上下文）",
                "管理多级审批流程",
                new String[]{
                    "• 多级审批流程（员工 -> 主管 -> 经理 -> 总监）",
                    "• 审批历史追踪",
                    "• 审批权限验证",
                    "• 审批超时提醒"
                },
                new String[]{
                    "OA系统：请假审批、报销审批",
                    "采购系统：采购申请审批",
                    "合同系统：合同审批流程"
                });

        printScenarioDetail("4. PriceCalculationContext（价格计算上下文）",
                "复杂的价格计算逻辑",
                new String[]{
                    "• 电商价格计算（原价、折扣、优惠券、会员价）",
                    "• 保险费率计算（基础费率、风险系数、优惠）",
                    "• 佣金计算（基础佣金、阶梯佣金、奖励）",
                    "• 积分计算（基础积分、活动加倍、会员加成）"
                },
                new String[]{
                    "淘宝：商品价格 = 原价 - 店铺优惠 - 平台券 - 红包 + 运费",
                    "滴滴：费用 = 起步价 + 里程费 + 时长费 - 优惠券 + 高峰加价",
                    "美团外卖：订单 = 商品价 + 配送费 - 满减 - 红包 - 会员折扣"
                });

        printScenarioDetail("5. RuleEngineContext（规则引擎上下文）",
                "业务规则执行和管理",
                new String[]{
                    "• 业务规则执行（风控规则、营销规则）",
                    "• 规则链路追踪",
                    "• 规则结果缓存",
                    "• 规则优先级管理"
                },
                new String[]{
                    "风控系统：反欺诈规则、信用评分规则",
                    "营销系统：优惠券发放规则、活动参与规则",
                    "推荐系统：商品推荐规则、内容推荐规则"
                });

        printScenarioDetail("6. ImportExportContext（导入导出上下文）",
                "批量数据处理",
                new String[]{
                    "• 批量数据导入（Excel、CSV）",
                    "• 批量数据导出",
                    "• 进度追踪",
                    "• 错误收集和报告"
                },
                new String[]{
                    "ERP系统：批量导入商品、客户、订单",
                    "财务系统：批量导出账单、报表",
                    "HR系统：批量导入员工信息"
                });

        System.out.println();
        System.out.println("【核心优势】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("✓ 简化参数传递 - 避免方法签名膨胀");
        System.out.println("✓ 统一数据管理 - 集中管理上下文数据");
        System.out.println("✓ 提高可维护性 - 易于扩展和修改");
        System.out.println("✓ 增强可追踪性 - 完整的执行链路");
        System.out.println("✓ 支持横切关注点 - 日志、监控、审计");
        System.out.println();

        System.out.println("【设计原则】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("1. 单一职责 - 每个 Context 只负责一类信息");
        System.out.println("2. 不可变性 - 尽量设计为不可变对象");
        System.out.println("3. 线程安全 - 使用 ThreadLocal 或不可变对象");
        System.out.println("4. 生命周期管理 - 明确创建和销毁时机");
        System.out.println("5. 可序列化 - 支持分布式传递");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  Context 模式是企业系统中最重要的设计模式之一                       ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }

    private static void printScenarioDetail(String title, String description, String[] features, String[] examples) {
        System.out.println(title);
        System.out.println("  " + description);
        System.out.println();
        System.out.println("  核心功能:");
        for (String feature : features) {
            System.out.println("  " + feature);
        }
        System.out.println();
        System.out.println("  实际案例:");
        for (String example : examples) {
            System.out.println("  " + example);
        }
        System.out.println();
    }
}

