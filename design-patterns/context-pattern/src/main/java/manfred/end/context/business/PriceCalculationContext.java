package manfred.end.context.business;

import java.math.BigDecimal;
import java.util.*;

/**
 * 场景2：价格计算上下文
 *
 * 应用场景：
 * 1. 电商价格计算 - 原价、折扣、优惠券、会员价
 * 2. 保险费率计算 - 基础费率、风险系数、优惠系数
 * 3. 佣金计算 - 基础佣金、阶梯佣金、特殊奖励
 * 4. 积分计算 - 基础积分、活动加倍、会员加成
 *
 * 实际案例：
 * - 淘宝/京东：商品价格 = 原价 - 店铺优惠 - 平台券 - 红包 + 运费
 * - 滴滴打车：费用 = 起步价 + 里程费 + 时长费 - 优惠券 + 高峰加价
 * - 美团外卖：订单金额 = 商品总价 + 配送费 - 满减 - 红包 - 会员折扣
 *
 * @author manfred
 */
public class PriceCalculationContext {

    // ========== 基础信息 ==========

    /** 计算ID */
    private String calculationId;

    /** 用户ID */
    private Long userId;

    /** 用户等级（影响折扣） */
    private String userLevel;  // VIP, SVIP, NORMAL

    /** 商品列表 */
    private List<ProductItem> products = new ArrayList<>();

    /** 原始总价 */
    private BigDecimal originalPrice = BigDecimal.ZERO;

    /** 最终价格 */
    private BigDecimal finalPrice = BigDecimal.ZERO;

    // ========== 优惠信息 ==========

    /** 优惠券列表 */
    private List<Coupon> coupons = new ArrayList<>();

    /** 满减活动 */
    private List<FullReduction> fullReductions = new ArrayList<>();

    /** 会员折扣 */
    private BigDecimal memberDiscount = BigDecimal.ONE;

    /** 限时折扣 */
    private BigDecimal flashDiscount = BigDecimal.ONE;

    // ========== 计算明细 ==========

    /** 计算步骤（用于审计和调试） */
    private List<CalculationStep> steps = new ArrayList<>();

    /** 扩展属性 */
    private Map<String, Object> attributes = new HashMap<>();

    // ========== 内部类 ==========

    public static class ProductItem {
        private String productId;
        private String productName;
        private BigDecimal price;
        private int quantity;

        public ProductItem(String productId, String productName, BigDecimal price, int quantity) {
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public BigDecimal getTotalPrice() {
            return price.multiply(BigDecimal.valueOf(quantity));
        }

        public String getProductId() { return productId; }
        public String getProductName() { return productName; }
        public BigDecimal getPrice() { return price; }
        public int getQuantity() { return quantity; }
    }

    public static class Coupon {
        private String couponId;
        private String couponName;
        private String type;  // FIXED, PERCENT
        private BigDecimal value;
        private BigDecimal minAmount;  // 最低消费金额

        public Coupon(String couponId, String couponName, String type, BigDecimal value, BigDecimal minAmount) {
            this.couponId = couponId;
            this.couponName = couponName;
            this.type = type;
            this.value = value;
            this.minAmount = minAmount;
        }

        public String getCouponId() { return couponId; }
        public String getCouponName() { return couponName; }
        public String getType() { return type; }
        public BigDecimal getValue() { return value; }
        public BigDecimal getMinAmount() { return minAmount; }
    }

    public static class FullReduction {
        private BigDecimal threshold;  // 满多少
        private BigDecimal reduction;  // 减多少

        public FullReduction(BigDecimal threshold, BigDecimal reduction) {
            this.threshold = threshold;
            this.reduction = reduction;
        }

        public BigDecimal getThreshold() { return threshold; }
        public BigDecimal getReduction() { return reduction; }
    }

    public static class CalculationStep {
        private String stepName;
        private BigDecimal beforeAmount;
        private BigDecimal afterAmount;
        private BigDecimal discountAmount;
        private String description;

        public CalculationStep(String stepName, BigDecimal beforeAmount, BigDecimal afterAmount, String description) {
            this.stepName = stepName;
            this.beforeAmount = beforeAmount;
            this.afterAmount = afterAmount;
            this.discountAmount = beforeAmount.subtract(afterAmount);
            this.description = description;
        }

        @Override
        public String toString() {
            return String.format("  [%s] %s -> %s (优惠: %s) - %s",
                    stepName, beforeAmount, afterAmount, discountAmount, description);
        }
    }

    // ========== 构造方法 ==========

    public PriceCalculationContext(String calculationId, Long userId, String userLevel) {
        this.calculationId = calculationId;
        this.userId = userId;
        this.userLevel = userLevel;
    }

    // ========== 业务方法 ==========

    /**
     * 添加商品
     */
    public void addProduct(String productId, String productName, BigDecimal price, int quantity) {
        products.add(new ProductItem(productId, productName, price, quantity));
        originalPrice = originalPrice.add(price.multiply(BigDecimal.valueOf(quantity)));
    }

    /**
     * 添加优惠券
     */
    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    /**
     * 添加满减活动
     */
    public void addFullReduction(BigDecimal threshold, BigDecimal reduction) {
        fullReductions.add(new FullReduction(threshold, reduction));
    }

    /**
     * 设置会员折扣
     */
    public void setMemberDiscount(BigDecimal discount) {
        this.memberDiscount = discount;
    }

    /**
     * 设置限时折扣
     */
    public void setFlashDiscount(BigDecimal discount) {
        this.flashDiscount = discount;
    }

    /**
     * 执行价格计算
     */
    public void calculate() {
        finalPrice = originalPrice;

        // 步骤1：原始价格
        addStep("原始价格", originalPrice, originalPrice, "商品总价");

        // 步骤2：会员折扣
        if (memberDiscount.compareTo(BigDecimal.ONE) < 0) {
            BigDecimal before = finalPrice;
            finalPrice = finalPrice.multiply(memberDiscount);
            addStep("会员折扣", before, finalPrice,
                    String.format("%s会员享受%.1f折", userLevel, memberDiscount.multiply(BigDecimal.TEN)));
        }

        // 步骤3：限时折扣
        if (flashDiscount.compareTo(BigDecimal.ONE) < 0) {
            BigDecimal before = finalPrice;
            finalPrice = finalPrice.multiply(flashDiscount);
            addStep("限时折扣", before, finalPrice,
                    String.format("限时%.1f折", flashDiscount.multiply(BigDecimal.TEN)));
        }

        // 步骤4：满减活动
        for (FullReduction fr : fullReductions) {
            if (finalPrice.compareTo(fr.getThreshold()) >= 0) {
                BigDecimal before = finalPrice;
                finalPrice = finalPrice.subtract(fr.getReduction());
                addStep("满减活动", before, finalPrice,
                        String.format("满%s减%s", fr.getThreshold(), fr.getReduction()));
            }
        }

        // 步骤5：优惠券
        for (Coupon coupon : coupons) {
            if (finalPrice.compareTo(coupon.getMinAmount()) >= 0) {
                BigDecimal before = finalPrice;
                if ("FIXED".equals(coupon.getType())) {
                    finalPrice = finalPrice.subtract(coupon.getValue());
                } else if ("PERCENT".equals(coupon.getType())) {
                    finalPrice = finalPrice.multiply(BigDecimal.ONE.subtract(coupon.getValue()));
                }
                addStep("优惠券", before, finalPrice, coupon.getCouponName());
            }
        }

        // 确保价格不为负
        if (finalPrice.compareTo(BigDecimal.ZERO) < 0) {
            finalPrice = BigDecimal.ZERO;
        }
    }

    private void addStep(String stepName, BigDecimal before, BigDecimal after, String description) {
        steps.add(new CalculationStep(stepName, before, after, description));
    }

    /**
     * 获取计算明细
     */
    public List<CalculationStep> getSteps() {
        return steps;
    }

    /**
     * 获取总优惠金额
     */
    public BigDecimal getTotalDiscount() {
        return originalPrice.subtract(finalPrice);
    }

    /**
     * 打印计算明细
     */
    public void printDetails() {
        System.out.println("  计算ID: " + calculationId);
        System.out.println("  用户ID: " + userId + " (" + userLevel + ")");
        System.out.println();
        System.out.println("  商品清单:");
        for (ProductItem item : products) {
            System.out.println(String.format("    • %s x%d = ¥%s",
                    item.getProductName(), item.getQuantity(), item.getTotalPrice()));
        }
        System.out.println();
        System.out.println("  计算过程:");
        for (CalculationStep step : steps) {
            System.out.println(step);
        }
        System.out.println();
        System.out.println(String.format("  原价: ¥%s", originalPrice));
        System.out.println(String.format("  优惠: ¥%s", getTotalDiscount()));
        System.out.println(String.format("  实付: ¥%s", finalPrice));
    }

    // ========== Getters ==========

    public String getCalculationId() {
        return calculationId;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }
}

