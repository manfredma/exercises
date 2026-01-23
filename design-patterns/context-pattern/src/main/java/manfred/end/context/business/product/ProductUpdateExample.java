package manfred.end.context.business.product;

import manfred.end.context.business.product.ProductUpdateContext.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 商品更新示例
 *
 * @author manfred
 */
public class ProductUpdateExample {

    // ========== 模拟服务 ==========

    static class ProductService {

        /**
         * 更新商品
         */
        public boolean updateProduct(ProductUpdateContext context) {
            System.out.println("【开始更新商品】");
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println();

            // 1. 检测变更
            System.out.println("1. 检测变更...");
            context.detectChanges();
            System.out.println("   检测到 " + context.getChanges().size() + " 个字段变更");
            System.out.println();

            // 2. 验证修改
            System.out.println("2. 验证修改...");
            boolean valid = context.validate();

            if (!valid) {
                System.out.println("   ❌ 验证失败");
                context.printDetails();
                return false;
            }

            System.out.println("   ✅ 验证通过");
            System.out.println();

            // 3. 检查是否需要审批
            if (context.isNeedApproval()) {
                System.out.println("3. 提交审批...");
                String approvalId = submitApproval(context);
                System.out.println("   审批单号: " + approvalId);
                System.out.println("   等待审批通过后执行更新");
                return false;
            }

            // 4. 执行更新
            System.out.println("3. 执行更新...");
            doUpdate(context);
            System.out.println("   ✅ 更新成功");
            System.out.println();

            // 5. 记录审计日志
            System.out.println("4. 记录审计日志...");
            logAudit(context);
            System.out.println("   ✅ 日志已记录");
            System.out.println();

            // 6. 打印详情
            context.printDetails();

            return true;
        }

        private void doUpdate(ProductUpdateContext context) {
            // 模拟数据库更新
            System.out.println("   UPDATE product SET");
            for (FieldChange change : context.getChanges().values()) {
                System.out.println("     " + change.getFieldName() + " = " + change.getNewValue());
            }
            System.out.println("   WHERE product_id = " + context.getProductId());
        }

        private String submitApproval(ProductUpdateContext context) {
            // 模拟提交审批
            return "APPROVAL-" + System.currentTimeMillis();
        }

        private void logAudit(ProductUpdateContext context) {
            // 模拟记录审计日志
            System.out.println("   [AuditLog] 操作人=" + context.getOperatorName() +
                    ", 商品ID=" + context.getProductId() +
                    ", 修改=" + context.getSummary());
        }
    }

    // ========== 测试场景 ==========

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         商品更新上下文示例                                            ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        ProductService productService = new ProductService();

        // 场景1：普通字段修改（成功）
        scenario1_NormalUpdate(productService);

        System.out.println("\n\n");

        // 场景2：价格修改（需要审批）
        scenario2_PriceUpdate(productService);

        System.out.println("\n\n");

        // 场景3：违反业务规则（失败）
        scenario3_ValidationError(productService);

        System.out.println("\n\n");

        // 场景4：权限不足（失败）
        scenario4_PermissionDenied(productService);

        System.out.println("\n\n");

        // 总结
        printSummary();
    }

    /**
     * 场景1：普通字段修改（成功）
     */
    private static void scenario1_NormalUpdate(ProductService productService) {
        System.out.println("【场景1：普通字段修改】");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("修改商品名称、描述、库存等普通字段");
        System.out.println();

        // 1. 创建上下文
        ProductUpdateContext context = new ProductUpdateContext(
                "OP-001", "PROD-12345", 10001L, "张三");

        // 2. 设置权限
        context.addPermissions("product:update", "product:view");

        // 3. 设置原始商品
        Product original = createSampleProduct();
        context.setOriginalProduct(original);

        // 4. 设置新商品（修改部分字段）
        Product updated = createSampleProduct();
        updated.setProductName("iPhone 15 Pro Max");  // 修改名称
        updated.setDescription("最新款 iPhone，性能更强大");  // 修改描述
        updated.setStock(150);  // 修改库存
        updated.setIsHot(true);  // 设为热卖
        context.setNewProduct(updated);

        // 5. 设置修改原因
        context.setReason("更新商品信息");

        // 6. 执行更新
        productService.updateProduct(context);
    }

    /**
     * 场景2：价格修改（需要审批）
     */
    private static void scenario2_PriceUpdate(ProductService productService) {
        System.out.println("【场景2：价格大幅修改（需要审批）】");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("价格从 ¥7999 降到 ¥5999，降幅超过 20%");
        System.out.println();

        // 1. 创建上下文
        ProductUpdateContext context = new ProductUpdateContext(
                "OP-002", "PROD-12345", 10002L, "李四");

        // 2. 设置权限（包括价格修改权限）
        context.addPermissions("product:update", "product:price:update");

        // 3. 设置原始商品
        Product original = createSampleProduct();
        context.setOriginalProduct(original);

        // 4. 设置新商品（大幅降价）
        Product updated = createSampleProduct();
        updated.setPrice(new BigDecimal("5999"));  // 从 7999 降到 5999
        context.setNewProduct(updated);

        // 5. 设置修改原因
        context.setReason("双11促销活动");

        // 6. 执行更新
        productService.updateProduct(context);
    }

    /**
     * 场景3：违反业务规则（失败）
     */
    private static void scenario3_ValidationError(ProductService productService) {
        System.out.println("【场景3：违反业务规则（验证失败）】");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("尝试将售价设置为低于成本价");
        System.out.println();

        // 1. 创建上下文
        ProductUpdateContext context = new ProductUpdateContext(
                "OP-003", "PROD-12345", 10003L, "王五");

        // 2. 设置权限
        context.addPermissions("product:update", "product:price:update");

        // 3. 设置原始商品
        Product original = createSampleProduct();
        context.setOriginalProduct(original);

        // 4. 设置新商品（售价低于成本）
        Product updated = createSampleProduct();
        updated.setPrice(new BigDecimal("4000"));  // 售价 4000
        updated.setCost(new BigDecimal("5000"));   // 成本 5000（售价 < 成本）
        updated.setStock(-10);  // 库存为负数（错误）
        context.setNewProduct(updated);

        // 5. 设置修改原因
        context.setReason("测试错误场景");

        // 6. 执行更新
        productService.updateProduct(context);
    }

    /**
     * 场景4：权限不足（失败）
     */
    private static void scenario4_PermissionDenied(ProductService productService) {
        System.out.println("【场景4：权限不足（验证失败）】");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("普通用户尝试修改价格（需要特殊权限）");
        System.out.println();

        // 1. 创建上下文
        ProductUpdateContext context = new ProductUpdateContext(
                "OP-004", "PROD-12345", 10004L, "赵六");

        // 2. 设置权限（只有基本权限，没有价格修改权限）
        context.addPermissions("product:update", "product:view");
        // 注意：没有 "product:price:update" 权限

        // 3. 设置原始商品
        Product original = createSampleProduct();
        context.setOriginalProduct(original);

        // 4. 设置新商品（尝试修改价格）
        Product updated = createSampleProduct();
        updated.setPrice(new BigDecimal("6999"));  // 尝试修改价格
        context.setNewProduct(updated);

        // 5. 设置修改原因
        context.setReason("调整价格");

        // 6. 执行更新
        productService.updateProduct(context);
    }

    /**
     * 创建示例商品
     */
    private static Product createSampleProduct() {
        Product product = new Product();
        product.setProductId("PROD-12345");
        product.setProductName("iPhone 15 Pro");
        product.setProductCode("IP15PRO");
        product.setCategoryId("CAT-001");
        product.setCategoryName("手机");
        product.setBrandId("BRAND-001");
        product.setBrandName("Apple");

        product.setPrice(new BigDecimal("7999"));
        product.setCost(new BigDecimal("5000"));
        product.setMarketPrice(new BigDecimal("8999"));

        product.setStock(100);
        product.setMinStock(10);
        product.setMaxStock(1000);

        product.setUnit("台");
        product.setWeight(new BigDecimal("0.2"));
        product.setSize("6.1英寸");

        product.setStatus("ON_SALE");
        product.setIsNew(true);
        product.setIsHot(false);

        product.setDescription("全新 iPhone 15 Pro，A17 Pro 芯片");
        product.setImages(Arrays.asList("img1.jpg", "img2.jpg"));

        Map<String, String> attributes = new HashMap<>();
        attributes.put("颜色", "深空黑");
        attributes.put("存储", "256GB");
        product.setAttributes(attributes);

        product.setSupplierId("SUP-001");
        product.setSupplierName("Apple 官方");

        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());

        return product;
    }

    /**
     * 打印总结
     */
    private static void printSummary() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         商品更新上下文 - 核心价值                                    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("【核心功能】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("✓ 自动检测变更 - 比较新旧值，记录所有修改");
        System.out.println("✓ 业务规则验证 - 价格、库存、状态等规则校验");
        System.out.println("✓ 权限验证 - 不同字段需要不同权限");
        System.out.println("✓ 审批流程 - 重要修改需要审批");
        System.out.println("✓ 审计日志 - 完整记录修改历史");
        System.out.println();

        System.out.println("【适用场景】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("• 电商系统 - 商品信息修改");
        System.out.println("• ERP系统 - 物料信息修改");
        System.out.println("• CRM系统 - 客户信息修改");
        System.out.println("• 库存系统 - 库存信息修改");
        System.out.println("• 配置系统 - 系统配置修改");
        System.out.println();

        System.out.println("【核心优势】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("✓ 统一验证逻辑 - 避免验证代码分散");
        System.out.println("✓ 完整审计追踪 - 知道谁改了什么");
        System.out.println("✓ 灵活权限控制 - 字段级权限");
        System.out.println("✓ 支持审批流程 - 重要修改需审批");
        System.out.println("✓ 易于扩展 - 新增字段或规则很容易");
        System.out.println();

        System.out.println("【Context 类型】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("这是一个 OperationContext（操作上下文）+ ValidationContext（验证上下文）");
        System.out.println("的组合，专门用于处理复杂结构体的修改场景。");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ProductUpdateContext 是处理复杂结构体修改的最佳实践                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }
}

