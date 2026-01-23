package manfred.end.context.example;

import manfred.end.context.impl.OrderProcessContext;

/**
 * 订单处理流程示例
 * 演示如何使用 Context 模式管理复杂业务流程
 *
 * @author manfred
 */
public class OrderProcessExample {

    public static void main(String[] args) {
        // 模拟订单处理流程
        processOrder("ORDER-12345", "USER-001");
    }

    /**
     * 订单处理主流程
     */
    public static void processOrder(String orderId, String userId) {
        // 1. 创建根上下文
        OrderProcessContext rootCtx = OrderProcessContext.createRootContext(orderId, userId);
        rootCtx.logStep("开始处理订单");

        // 2. 订单验证流程（顺序执行）
        OrderProcessContext validationCtx = rootCtx.createSequenceContext("OrderValidation");
        validationCtx.logStep("开始订单验证");

        // 2.1 验证用户信息
        validationCtx.nextStep("ValidateUser");
        validationCtx.setCurrentValue("用户验证通过");
        validationCtx.logStep("用户信息验证完成");

        // 2.2 验证商品库存
        validationCtx.nextStep("ValidateInventory");
        validationCtx.setCurrentValue("库存充足");
        validationCtx.logStep("库存验证完成");

        // 2.3 验证价格
        validationCtx.nextStep("ValidatePrice");
        validationCtx.setCurrentValue("价格正确");
        validationCtx.logStep("价格验证完成");

        // 完成验证，返回父上下文
        rootCtx = validationCtx.completeAndGetParent();
        rootCtx.logStep("订单验证完成");

        // 3. 并行处理流程
        OrderProcessContext parallelCtx = rootCtx.createParallelContext("ParallelProcessing");
        parallelCtx.logStep("开始并行处理");

        // 3.1 库存扣减（子流程1）
        OrderProcessContext inventoryCtx = parallelCtx.createSequenceContext("InventoryDeduction");
        inventoryCtx.logStep("开始扣减库存");
        inventoryCtx.nextStep("LockInventory");
        inventoryCtx.logStep("锁定库存");
        inventoryCtx.nextStep("DeductInventory");
        inventoryCtx.logStep("扣减库存完成");
        parallelCtx = inventoryCtx.completeAndGetParent();

        // 3.2 优惠券处理（子流程2）
        OrderProcessContext couponCtx = parallelCtx.createSequenceContext("CouponProcessing");
        couponCtx.logStep("开始处理优惠券");
        couponCtx.nextStep("ValidateCoupon");
        couponCtx.logStep("验证优惠券");
        couponCtx.nextStep("UseCoupon");
        couponCtx.logStep("使用优惠券完成");
        parallelCtx = couponCtx.completeAndGetParent();

        // 3.3 积分处理（子流程3）
        OrderProcessContext pointsCtx = parallelCtx.createSequenceContext("PointsProcessing");
        pointsCtx.logStep("开始处理积分");
        pointsCtx.nextStep("CalculatePoints");
        pointsCtx.logStep("计算积分");
        pointsCtx.nextStep("AddPoints");
        pointsCtx.logStep("增加积分完成");
        parallelCtx = pointsCtx.completeAndGetParent();

        // 完成并行处理
        rootCtx = parallelCtx.completeAndGetParent();
        rootCtx.logStep("并行处理完成");

        // 4. 支付流程（条件分支）
        OrderProcessContext paymentCtx = rootCtx.createConditionalContext("PaymentProcessing");
        paymentCtx.logStep("开始支付处理");

        // 模拟支付方式选择
        String paymentMethod = "ALIPAY"; // 可以是 ALIPAY, WECHAT, BANK_CARD

        if ("ALIPAY".equals(paymentMethod)) {
            OrderProcessContext alipayCtx = paymentCtx.createSequenceContext("AlipayPayment");
            alipayCtx.logStep("使用支付宝支付");
            alipayCtx.nextStep("CreateAlipayOrder");
            alipayCtx.logStep("创建支付宝订单");
            alipayCtx.nextStep("WaitPaymentCallback");
            alipayCtx.logStep("等待支付回调");
            alipayCtx.nextStep("ConfirmPayment");
            alipayCtx.logStep("确认支付成功");
            paymentCtx = alipayCtx.completeAndGetParent();
        } else if ("WECHAT".equals(paymentMethod)) {
            OrderProcessContext wechatCtx = paymentCtx.createSequenceContext("WechatPayment");
            wechatCtx.logStep("使用微信支付");
            // ... 微信支付流程
            paymentCtx = wechatCtx.completeAndGetParent();
        }

        rootCtx = paymentCtx.completeAndGetParent();
        rootCtx.logStep("支付处理完成");

        // 5. 订单完成
        rootCtx.nextStep("OrderCompleted");
        rootCtx.logStep("订单处理完成");

        // 6. 打印完整路径和统计信息
        System.out.println("\n========== 流程统计 ==========");
        System.out.println("订单ID: " + rootCtx.getOrderId());
        System.out.println("用户ID: " + rootCtx.getUserId());
        System.out.println("总耗时: " + rootCtx.getElapsedTime() + "ms");
        System.out.println("最大嵌套深度: " + getMaxDepth(rootCtx));
    }

    /**
     * 获取最大嵌套深度（示例方法）
     */
    private static int getMaxDepth(OrderProcessContext ctx) {
        // 在实际应用中，可以在处理过程中记录最大深度
        return 4; // 示例值
    }
}

