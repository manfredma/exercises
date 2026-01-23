package manfred.end.context.comparison;

/**
 * 可视化对比：Context 模式 vs 状态模式
 *
 * @author manfred
 */
public class VisualComparison {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║           Context 模式 vs 状态模式 - 核心区别对比                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Context 模式示例
        System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 【Context 模式】- 位置追踪                                          │");
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        System.out.println("│                                                                     │");
        System.out.println("│  解析 JSON: {\"user\": {\"orders\": [1, 2, 3]}}                       │");
        System.out.println("│                                                                     │");
        System.out.println("│  Root Context                                                       │");
        System.out.println("│    │                                                                │");
        System.out.println("│    ├─→ Object Context (user)                                       │");
        System.out.println("│    │     │                                                          │");
        System.out.println("│    │     ├─→ Object Context (orders)                               │");
        System.out.println("│    │     │     │                                                    │");
        System.out.println("│    │     │     └─→ Array Context                                   │");
        System.out.println("│    │     │           │                                              │");
        System.out.println("│    │     │           ├─→ [0] = 1  ← 当前位置                       │");
        System.out.println("│    │     │           ├─→ [1] = 2                                   │");
        System.out.println("│    │     │           └─→ [2] = 3                                   │");
        System.out.println("│                                                                     │");
        System.out.println("│  当前路径: /user/orders[0]                                          │");
        System.out.println("│  嵌套深度: 3                                                        │");
        System.out.println("│  上下文类型: ARRAY                                                  │");
        System.out.println("│                                                                     │");
        System.out.println("│  核心问题: \"我在哪里？\"                                            │");
        System.out.println("│  主要作用: 追踪位置、验证结构、错误定位                             │");
        System.out.println("│                                                                     │");
        System.out.println("└─────────────────────────────────────────────────────────────────────┘");
        System.out.println();

        // 状态模式示例
        System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 【状态模式】- 行为切换                                              │");
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        System.out.println("│                                                                     │");
        System.out.println("│  订单状态流转:                                                      │");
        System.out.println("│                                                                     │");
        System.out.println("│  ┌──────────┐   pay()    ┌──────────┐   ship()   ┌──────────┐    │");
        System.out.println("│  │  待支付  │ ────────→  │  待发货  │ ────────→  │  已发货  │    │");
        System.out.println("│  └──────────┘            └──────────┘            └──────────┘    │");
        System.out.println("│       │                       │                       │           │");
        System.out.println("│       │ ship() ✗             │ pay() ✗               │           │");
        System.out.println("│       │ complete() ✗         │ complete() ✗          │           │");
        System.out.println("│       │                       │                       │           │");
        System.out.println("│       └───────────────────────┴───────────────────────┘           │");
        System.out.println("│                               │                                   │");
        System.out.println("│                          complete()                               │");
        System.out.println("│                               ↓                                   │");
        System.out.println("│                         ┌──────────┐                              │");
        System.out.println("│                         │  已完成  │                              │");
        System.out.println("│                         └──────────┘                              │");
        System.out.println("│                                                                     │");
        System.out.println("│  当前状态: 待发货                                                   │");
        System.out.println("│  允许操作: ship() ✓, pay() ✗, complete() ✗                        │");
        System.out.println("│                                                                     │");
        System.out.println("│  核心问题: \"我能做什么？\"                                          │");
        System.out.println("│  主要作用: 改变行为、状态转换、操作控制                             │");
        System.out.println("│                                                                     │");
        System.out.println("└─────────────────────────────────────────────────────────────────────┘");
        System.out.println();

        // 对比表格
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          核心区别对比表                               ║");
        System.out.println("╠═══════════════╦═══════════════════════╦═══════════════════════════════╣");
        System.out.println("║   对比维度    ║    Context 模式       ║       状态模式                ║");
        System.out.println("╠═══════════════╬═══════════════════════╬═══════════════════════════════╣");
        System.out.println("║ 主要目的      ║ 追踪位置和层级关系    ║ 改变对象行为                  ║");
        System.out.println("║ 核心问题      ║ \"我在哪里？\"         ║ \"我能做什么？\"              ║");
        System.out.println("║ 状态含义      ║ 位置类型              ║ 业务状态                      ║");
        System.out.println("║               ║ (ROOT/ARRAY/OBJECT)   ║ (待支付/待发货/已发货)        ║");
        System.out.println("║ 状态转换      ║ 进入/退出上下文       ║ 业务操作触发                  ║");
        System.out.println("║ 父子关系      ║ 有（上下文链）        ║ 无                            ║");
        System.out.println("║ 路径追踪      ║ 有（完整路径）        ║ 无                            ║");
        System.out.println("║ 行为变化      ║ 行为不变，只验证      ║ 行为随状态改变                ║");
        System.out.println("║ 典型场景      ║ 解析器、编译器        ║ 订单、连接、游戏              ║");
        System.out.println("╚═══════════════╩═══════════════════════╩═══════════════════════════════╝");
        System.out.println();

        // 代码对比
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          代码特征对比                                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("【Context 模式】典型代码:");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("JsonContext ctx = root.createObjectContext();");
        System.out.println("ctx.writeFieldName(\"user\");  // 记录位置");
        System.out.println("System.out.println(ctx.getPath());  // 输出: /user");
        System.out.println("System.out.println(ctx.inObject());  // 输出: true");
        System.out.println();
        System.out.println("特点: 关注位置、路径、层级");
        System.out.println();

        System.out.println("【状态模式】典型代码:");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("Order order = new Order(\"ORDER-001\");");
        System.out.println("order.pay();    // 状态: 待支付 -> 待发货");
        System.out.println("order.ship();   // 状态: 待发货 -> 已发货");
        System.out.println("order.pay();    // 错误: 已发货状态不能支付");
        System.out.println();
        System.out.println("特点: 关注行为、转换、操作");
        System.out.println();

        // 应用建议
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          应用场景建议                                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("✅ 使用 Context 模式的场景:");
        System.out.println("   • JSON/XML 解析器 - 追踪解析位置");
        System.out.println("   • 编译器 - 追踪语法树位置");
        System.out.println("   • 业务流程引擎 - 追踪流程执行位置");
        System.out.println("   • SQL 查询构建器 - 追踪查询结构");
        System.out.println("   • 表达式求值器 - 追踪求值位置");
        System.out.println();

        System.out.println("✅ 使用状态模式的场景:");
        System.out.println("   • 订单系统 - 订单状态流转");
        System.out.println("   • TCP 连接 - 连接状态管理");
        System.out.println("   • 游戏角色 - 角色状态切换");
        System.out.println("   • 工作流引擎 - 审批状态流转");
        System.out.println("   • 文档编辑器 - 编辑模式切换");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  结论: Context 模式 ≠ 状态模式                                        ║");
        System.out.println("║  它们解决不同的问题，应根据实际需求选择合适的模式                     ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }
}

