package manfred.end.context.business.product;

import manfred.end.context.business.product.ProductUpdateContext.Product;
import manfred.end.context.business.product.ProductUpdateDTO.ProductDTO;

import java.math.BigDecimal;
import java.util.*;

/**
 * Context 模式 vs DTO 模式对比
 *
 * @author manfred
 */
public class ContextVsDTOComparison {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         Context 模式 vs DTO 模式对比                                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 方式1：使用 Context 模式（富血模型）
        testWithContext();

        System.out.println("\n\n");

        // 方式2：使用 DTO 模式（贫血模型）
        testWithDTO();

        System.out.println("\n\n");

        // 详细对比
        printDetailedComparison();
    }

    /**
     * 方式1：使用 Context 模式
     */
    private static void testWithContext() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("【方式1：Context 模式（富血模型）】");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();

        // 1. 创建 Context（包含业务逻辑）
        ProductUpdateContext context = new ProductUpdateContext(
                "OP-001", "PROD-12345", 10001L, "张三");

        // 2. 设置权限
        context.addPermissions("product:update", "product:view");

        // 3. 设置商品信息
        Product original = createSampleProduct();
        Product updated = createSampleProduct();
        updated.setProductName("iPhone 15 Pro Max");
        updated.setStock(150);

        context.setOriginalProduct(original);
        context.setNewProduct(updated);
        context.setReason("更新商品信息");

        // 4. 调用 Context 的方法（业务逻辑在 Context 内部）
        context.detectChanges();  // Context 自己检测变更
        boolean valid = context.validate();  // Context 自己验证

        System.out.println("✓ 检测到 " + context.getChanges().size() + " 个字段变更");
        System.out.println("✓ 验证结果: " + (valid ? "通过" : "失败"));
        System.out.println("✓ 修改摘要: " + context.getSummary());
        System.out.println();

        System.out.println("【代码特点】");
        System.out.println("• Context 包含业务逻辑");
        System.out.println("• 调用方只需调用 Context 的方法");
        System.out.println("• 业务逻辑封装在 Context 内部");
    }

    /**
     * 方式2：使用 DTO 模式
     */
    private static void testWithDTO() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("【方式2：DTO 模式（贫血模型）】");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();

        // 1. 创建 DTO（纯数据）
        ProductUpdateDTO dto = new ProductUpdateDTO();
        dto.setOperationId("OP-001");
        dto.setProductId("PROD-12345");
        dto.setOperatorId(10001L);
        dto.setOperatorName("张三");
        dto.setOperationTime(System.currentTimeMillis());

        // 2. 设置权限
        dto.setPermissions(Arrays.asList("product:update", "product:view"));

        // 3. 设置商品信息
        ProductDTO original = createSampleProductDTO();
        ProductDTO updated = createSampleProductDTO();
        updated.setProductName("iPhone 15 Pro Max");
        updated.setStock(150);

        dto.setOriginalProduct(original);
        dto.setNewProduct(updated);
        dto.setReason("更新商品信息");

        // 4. 调用 Service 的方法（业务逻辑在 Service 中）
        ProductUpdateServiceWithDTO service = new ProductUpdateServiceWithDTO();
        service.updateProduct(dto);  // Service 处理所有业务逻辑

        System.out.println("✓ 检测到 " + dto.getChanges().size() + " 个字段变更");
        System.out.println("✓ 验证结果: " + (dto.getValidated() ? "通过" : "失败"));
        System.out.println();

        System.out.println("【代码特点】");
        System.out.println("• DTO 只包含数据");
        System.out.println("• 调用方需要调用 Service 的方法");
        System.out.println("• 业务逻辑在 Service 层");
    }

    /**
     * 详细对比
     */
    private static void printDetailedComparison() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         详细对比分析                                                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // 1. 代码结构对比
        System.out.println("【1. 代码结构对比】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("Context 模式（富血模型）:");
        System.out.println("```java");
        System.out.println("// Context 包含数据 + 业务逻辑");
        System.out.println("public class ProductUpdateContext {");
        System.out.println("    private Product originalProduct;");
        System.out.println("    private Product newProduct;");
        System.out.println("    private Map<String, FieldChange> changes;");
        System.out.println("    ");
        System.out.println("    // 业务方法");
        System.out.println("    public void detectChanges() { ... }");
        System.out.println("    public boolean validate() { ... }");
        System.out.println("    public String getSummary() { ... }");
        System.out.println("}");
        System.out.println("```");
        System.out.println();

        System.out.println("DTO 模式（贫血模型）:");
        System.out.println("```java");
        System.out.println("// DTO 只包含数据");
        System.out.println("public class ProductUpdateDTO {");
        System.out.println("    private ProductDTO originalProduct;");
        System.out.println("    private ProductDTO newProduct;");
        System.out.println("    private List<FieldChangeDTO> changes;");
        System.out.println("    ");
        System.out.println("    // 只有 Getter/Setter，没有业务逻辑");
        System.out.println("}");
        System.out.println();
        System.out.println("// 业务逻辑在 Service 中");
        System.out.println("public class ProductUpdateService {");
        System.out.println("    public List<FieldChangeDTO> detectChanges(ProductUpdateDTO dto) { ... }");
        System.out.println("    public boolean validate(ProductUpdateDTO dto) { ... }");
        System.out.println("}");
        System.out.println("```");
        System.out.println();

        // 2. 使用方式对比
        System.out.println("【2. 使用方式对比】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("Context 模式:");
        System.out.println("```java");
        System.out.println("ProductUpdateContext context = new ProductUpdateContext(...);");
        System.out.println("context.setOriginalProduct(original);");
        System.out.println("context.setNewProduct(updated);");
        System.out.println();
        System.out.println("// 调用 Context 的方法");
        System.out.println("context.detectChanges();  // Context 自己处理");
        System.out.println("context.validate();       // Context 自己验证");
        System.out.println("String summary = context.getSummary();  // Context 自己生成");
        System.out.println("```");
        System.out.println();

        System.out.println("DTO 模式:");
        System.out.println("```java");
        System.out.println("ProductUpdateDTO dto = new ProductUpdateDTO();");
        System.out.println("dto.setOriginalProduct(original);");
        System.out.println("dto.setNewProduct(updated);");
        System.out.println();
        System.out.println("// 调用 Service 的方法");
        System.out.println("List<FieldChangeDTO> changes = service.detectChanges(dto);");
        System.out.println("dto.setChanges(changes);");
        System.out.println("boolean valid = service.validate(dto);");
        System.out.println("dto.setValidated(valid);");
        System.out.println("```");
        System.out.println();

        // 3. 优缺点对比
        System.out.println("【3. 优缺点对比】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        String[][] comparison = {
            {"维度", "Context 模式（富血模型）", "DTO 模式（贫血模型）"},
            {"", "", ""},
            {"代码组织", "✅ 业务逻辑封装在 Context 内", "❌ 业务逻辑分散在 Service 中"},
            {"", "✅ 高内聚", "❌ 低内聚"},
            {"", "", ""},
            {"易用性", "✅ 调用简单，context.validate()", "❌ 调用复杂，需要多次调用 Service"},
            {"", "✅ 自解释性强", "❌ 需要查看 Service 才知道怎么用"},
            {"", "", ""},
            {"可维护性", "✅ 修改业务逻辑只需改 Context", "❌ 修改业务逻辑需要改 Service"},
            {"", "✅ 业务逻辑集中", "❌ 业务逻辑分散"},
            {"", "", ""},
            {"可测试性", "✅ 测试 Context 即可", "❌ 需要测试 Service + DTO"},
            {"", "✅ 单元测试简单", "❌ 单元测试复杂"},
            {"", "", ""},
            {"序列化", "❌ 包含业务逻辑，序列化复杂", "✅ 纯数据，易于序列化"},
            {"", "❌ 不适合网络传输", "✅ 适合网络传输"},
            {"", "", ""},
            {"学习成本", "❌ 需要理解 Context 的设计", "✅ 简单直观，易于理解"},
            {"", "❌ 新手可能不习惯", "✅ 符合传统习惯"},
            {"", "", ""},
            {"灵活性", "❌ 业务逻辑固定在 Context 中", "✅ 业务逻辑可以灵活组合"},
            {"", "❌ 扩展需要修改 Context", "✅ 扩展只需添加 Service 方法"},
            {"", "", ""},
            {"性能", "✅ 方法调用开销小", "❌ 多次方法调用开销大"},
            {"", "✅ 内存占用相对较小", "❌ 需要额外的 Service 对象"},
        };

        for (String[] row : comparison) {
            if (row[0].isEmpty()) {
                System.out.println();
            } else {
                System.out.printf("%-12s %-35s %-35s%n", row[0], row[1], row[2]);
            }
        }
        System.out.println();

        // 4. 适用场景
        System.out.println("【4. 适用场景】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("✅ 推荐使用 Context 模式的场景:");
        System.out.println("  • 业务逻辑复杂，需要封装");
        System.out.println("  • 需要追踪状态和上下文信息");
        System.out.println("  • 单体应用或模块内部使用");
        System.out.println("  • 需要高内聚的设计");
        System.out.println("  • 业务逻辑相对稳定");
        System.out.println();

        System.out.println("✅ 推荐使用 DTO 模式的场景:");
        System.out.println("  • 跨服务传输数据（微服务、RPC）");
        System.out.println("  • 需要序列化和反序列化");
        System.out.println("  • 业务逻辑需要灵活组合");
        System.out.println("  • 团队习惯贫血模型");
        System.out.println("  • 需要与第三方系统集成");
        System.out.println();

        // 5. 倾向性建议
        System.out.println("【5. 倾向性建议】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("⭐ 推荐方案：Context 模式（富血模型）");
        System.out.println();
        System.out.println("理由：");
        System.out.println("1. 更符合面向对象设计原则");
        System.out.println("   • 高内聚：数据和行为在一起");
        System.out.println("   • 封装性：业务逻辑不外露");
        System.out.println();
        System.out.println("2. 更易于维护和扩展");
        System.out.println("   • 修改业务逻辑只需改一个地方");
        System.out.println("   • 新增功能只需在 Context 中添加方法");
        System.out.println();
        System.out.println("3. 更易于使用");
        System.out.println("   • 调用简单：context.validate()");
        System.out.println("   • 自解释性强：看 Context 就知道能做什么");
        System.out.println();
        System.out.println("4. 更易于测试");
        System.out.println("   • 单元测试只需测试 Context");
        System.out.println("   • 不需要 Mock Service");
        System.out.println();

        System.out.println("⚠️  例外情况：使用 DTO 模式");
        System.out.println();
        System.out.println("以下场景建议使用 DTO:");
        System.out.println("1. 跨服务传输（微服务、RPC）");
        System.out.println("2. 需要序列化（JSON、Protobuf）");
        System.out.println("3. 与第三方系统集成");
        System.out.println("4. 团队强烈倾向贫血模型");
        System.out.println();

        // 6. 混合方案
        System.out.println("【6. 混合方案（最佳实践）】");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        System.out.println("在实际项目中，可以结合两种方式：");
        System.out.println();
        System.out.println("• 内部使用 Context 模式");
        System.out.println("  - 业务逻辑封装在 Context 中");
        System.out.println("  - 高内聚、易维护");
        System.out.println();
        System.out.println("• 对外使用 DTO 模式");
        System.out.println("  - API 接口使用 DTO 传输");
        System.out.println("  - 易于序列化和传输");
        System.out.println();
        System.out.println("• 转换层");
        System.out.println("  - DTO -> Context（接收请求时）");
        System.out.println("  - Context -> DTO（返回响应时）");
        System.out.println();

        System.out.println("示例：");
        System.out.println("```java");
        System.out.println("// API 层：使用 DTO");
        System.out.println("@PostMapping(\"/product/update\")");
        System.out.println("public Result updateProduct(@RequestBody ProductUpdateDTO dto) {");
        System.out.println("    // 转换：DTO -> Context");
        System.out.println("    ProductUpdateContext context = convertToContext(dto);");
        System.out.println("    ");
        System.out.println("    // 业务层：使用 Context");
        System.out.println("    context.detectChanges();");
        System.out.println("    if (!context.validate()) {");
        System.out.println("        return Result.fail(context.getErrors());");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    // 转换：Context -> DTO");
        System.out.println("    ProductUpdateDTO result = convertToDTO(context);");
        System.out.println("    return Result.success(result);");
        System.out.println("}");
        System.out.println("```");
        System.out.println();

        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  推荐：内部用 Context（富血），对外用 DTO（贫血）                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }

    // ========== 辅助方法 ==========

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
        product.setDescription("全新 iPhone 15 Pro");
        product.setImages(Arrays.asList("img1.jpg", "img2.jpg"));
        product.setSupplierId("SUP-001");
        product.setSupplierName("Apple 官方");
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        return product;
    }

    private static ProductDTO createSampleProductDTO() {
        ProductDTO product = new ProductDTO();
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
        product.setDescription("全新 iPhone 15 Pro");
        product.setImages(Arrays.asList("img1.jpg", "img2.jpg"));
        product.setSupplierId("SUP-001");
        product.setSupplierName("Apple 官方");
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        return product;
    }
}

