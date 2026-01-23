package manfred.end.context.comparison;

/**
 * 状态模式示例 - 用于对比
 *
 * 状态模式的特点：
 * 1. 对象的行为随状态改变而改变
 * 2. 状态之间可以相互转换
 * 3. 每个状态封装了特定的行为
 *
 * @author manfred
 */
public class StatePatternExample {

    // ========== 状态接口 ==========
    interface OrderState {
        void pay(Order order);
        void ship(Order order);
        void complete(Order order);
        String getStateName();
    }

    // ========== 具体状态类 ==========

    // 待支付状态
    static class PendingPaymentState implements OrderState {
        @Override
        public void pay(Order order) {
            System.out.println("支付成功，订单进入待发货状态");
            order.setState(new PendingShipmentState());
        }

        @Override
        public void ship(Order order) {
            System.out.println("错误：订单未支付，不能发货");
        }

        @Override
        public void complete(Order order) {
            System.out.println("错误：订单未支付，不能完成");
        }

        @Override
        public String getStateName() {
            return "待支付";
        }
    }

    // 待发货状态
    static class PendingShipmentState implements OrderState {
        @Override
        public void pay(Order order) {
            System.out.println("错误：订单已支付");
        }

        @Override
        public void ship(Order order) {
            System.out.println("发货成功，订单进入待收货状态");
            order.setState(new ShippedState());
        }

        @Override
        public void complete(Order order) {
            System.out.println("错误：订单未发货，不能完成");
        }

        @Override
        public String getStateName() {
            return "待发货";
        }
    }

    // 已发货状态
    static class ShippedState implements OrderState {
        @Override
        public void pay(Order order) {
            System.out.println("错误：订单已支付");
        }

        @Override
        public void ship(Order order) {
            System.out.println("错误：订单已发货");
        }

        @Override
        public void complete(Order order) {
            System.out.println("确认收货，订单完成");
            order.setState(new CompletedState());
        }

        @Override
        public String getStateName() {
            return "已发货";
        }
    }

    // 已完成状态
    static class CompletedState implements OrderState {
        @Override
        public void pay(Order order) {
            System.out.println("错误：订单已完成");
        }

        @Override
        public void ship(Order order) {
            System.out.println("错误：订单已完成");
        }

        @Override
        public void complete(Order order) {
            System.out.println("订单已经完成");
        }

        @Override
        public String getStateName() {
            return "已完成";
        }
    }

    // ========== 订单类（Context） ==========
    static class Order {
        private String orderId;
        private OrderState state;

        public Order(String orderId) {
            this.orderId = orderId;
            this.state = new PendingPaymentState();
        }

        public void setState(OrderState state) {
            this.state = state;
        }

        // 委托给状态对象处理
        public void pay() {
            System.out.println("\n[订单 " + orderId + "] 当前状态: " + state.getStateName());
            state.pay(this);
        }

        public void ship() {
            System.out.println("\n[订单 " + orderId + "] 当前状态: " + state.getStateName());
            state.ship(this);
        }

        public void complete() {
            System.out.println("\n[订单 " + orderId + "] 当前状态: " + state.getStateName());
            state.complete(this);
        }
    }

    // ========== 测试 ==========
    public static void main(String[] args) {
        System.out.println("========== 状态模式示例 ==========");
        System.out.println("特点：对象行为随状态改变，状态封装了行为逻辑\n");

        Order order = new Order("ORDER-001");

        // 正常流程
        order.pay();      // 待支付 -> 待发货
        order.ship();     // 待发货 -> 已发货
        order.complete(); // 已发货 -> 已完成

        // 错误操作
        order.pay();      // 已完成状态不能支付
        order.ship();     // 已完成状态不能发货
    }
}

