package manfred.end.context.business.product;

import manfred.end.context.business.product.ProductUpdateDTO.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 使用纯数据载体（DTO）的商品更新服务
 *
 * 特点：
 * 1. DTO 只包含数据，不包含业务逻辑
 * 2. 所有业务逻辑在 Service 层
 * 3. 符合贫血模型
 *
 * @author manfred
 */
public class ProductUpdateServiceWithDTO {

    // 受限字段配置
    private static final Map<String, String> RESTRICTED_FIELDS = new HashMap<>();

    static {
        RESTRICTED_FIELDS.put("price", "product:price:update");
        RESTRICTED_FIELDS.put("cost", "product:cost:update");
        RESTRICTED_FIELDS.put("status", "product:status:update");
        RESTRICTED_FIELDS.put("categoryId", "product:category:update");
    }

    /**
     * 更新商品
     */
    public boolean updateProduct(ProductUpdateDTO dto) {
        System.out.println("【开始更新商品】（使用 DTO 版本）");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println();

        // 1. 检测变更（业务逻辑在 Service 中）
        System.out.println("1. 检测变更...");
        List<FieldChangeDTO> changes = detectChanges(dto);
        dto.setChanges(changes);
        System.out.println("   检测到 " + changes.size() + " 个字段变更");
        System.out.println();

        // 2. 验证修改（业务逻辑在 Service 中）
        System.out.println("2. 验证修改...");
        boolean valid = validate(dto);
        dto.setValidated(valid);

        if (!valid) {
            System.out.println("   ❌ 验证失败");
            printDetails(dto);
            return false;
        }

        System.out.println("   ✅ 验证通过");
        System.out.println();

        // 3. 检查是否需要审批（业务逻辑在 Service 中）
        if (dto.getNeedApproval() != null && dto.getNeedApproval()) {
            System.out.println("3. 提交审批...");
            String approvalId = submitApproval(dto);
            dto.setApprovalId(approvalId);
            System.out.println("   审批单号: " + approvalId);
            System.out.println("   等待审批通过后执行更新");
            return false;
        }

        // 4. 执行更新
        System.out.println("3. 执行更新...");
        doUpdate(dto);
        System.out.println("   ✅ 更新成功");
        System.out.println();

        // 5. 记录审计日志
        System.out.println("4. 记录审计日志...");
        logAudit(dto);
        System.out.println("   ✅ 日志已记录");
        System.out.println();

        // 6. 打印详情
        printDetails(dto);

        return true;
    }

    /**
     * 检测变更（业务逻辑）
     */
    private List<FieldChangeDTO> detectChanges(ProductUpdateDTO dto) {
        List<FieldChangeDTO> changes = new ArrayList<>();

        ProductDTO original = dto.getOriginalProduct();
        ProductDTO updated = dto.getNewProduct();

        if (original == null || updated == null) {
            return changes;
        }

        // 比较各个字段（业务逻辑分散）
        compareAndAdd(changes, "productName", "商品名称",
                original.getProductName(), updated.getProductName());
        compareAndAdd(changes, "productCode", "商品编码",
                original.getProductCode(), updated.getProductCode());
        compareAndAdd(changes, "categoryId", "分类",
                original.getCategoryName(), updated.getCategoryName());
        compareAndAdd(changes, "brandId", "品牌",
                original.getBrandName(), updated.getBrandName());
        compareAndAdd(changes, "price", "售价",
                original.getPrice(), updated.getPrice());
        compareAndAdd(changes, "cost", "成本",
                original.getCost(), updated.getCost());
        compareAndAdd(changes, "marketPrice", "市场价",
                original.getMarketPrice(), updated.getMarketPrice());
        compareAndAdd(changes, "stock", "库存",
                original.getStock(), updated.getStock());
        compareAndAdd(changes, "minStock", "最小库存",
                original.getMinStock(), updated.getMinStock());
        compareAndAdd(changes, "maxStock", "最大库存",
                original.getMaxStock(), updated.getMaxStock());
        compareAndAdd(changes, "unit", "单位",
                original.getUnit(), updated.getUnit());
        compareAndAdd(changes, "weight", "重量",
                original.getWeight(), updated.getWeight());
        compareAndAdd(changes, "size", "尺寸",
                original.getSize(), updated.getSize());
        compareAndAdd(changes, "status", "状态",
                original.getStatus(), updated.getStatus());
        compareAndAdd(changes, "isNew", "新品",
                original.getIsNew(), updated.getIsNew());
        compareAndAdd(changes, "isHot", "热卖",
                original.getIsHot(), updated.getIsHot());
        compareAndAdd(changes, "description", "描述",
                original.getDescription(), updated.getDescription());
        compareAndAdd(changes, "supplierId", "供应商",
                original.getSupplierName(), updated.getSupplierName());

        return changes;
    }

    private void compareAndAdd(List<FieldChangeDTO> changes, String fieldName, String fieldLabel,
                               Object oldValue, Object newValue) {
        if (!Objects.equals(oldValue, newValue)) {
            FieldChangeDTO change = new FieldChangeDTO();
            change.setFieldName(fieldName);
            change.setFieldLabel(fieldLabel);
            change.setOldValue(oldValue);
            change.setNewValue(newValue);

            // 检查是否需要特殊权限
            if (RESTRICTED_FIELDS.containsKey(fieldName)) {
                change.setNeedApproval(true);
            }

            changes.add(change);
        }
    }

    /**
     * 验证修改（业务逻辑）
     */
    private boolean validate(ProductUpdateDTO dto) {
        List<ValidationErrorDTO> errors = new ArrayList<>();
        List<ValidationWarningDTO> warnings = new ArrayList<>();

        // 1. 验证权限（业务逻辑）
        validatePermissions(dto, errors);

        // 2. 验证业务规则（业务逻辑）
        validateBusinessRules(dto, errors, warnings);

        // 3. 验证数据完整性（业务逻辑）
        validateDataIntegrity(dto, errors);

        dto.setErrors(errors);
        dto.setWarnings(warnings);

        return errors.isEmpty();
    }

    /**
     * 验证权限（业务逻辑）
     */
    private void validatePermissions(ProductUpdateDTO dto, List<ValidationErrorDTO> errors) {
        List<String> permissions = dto.getPermissions();
        if (permissions == null) {
            permissions = new ArrayList<>();
        }

        for (FieldChangeDTO change : dto.getChanges()) {
            String requiredPermission = RESTRICTED_FIELDS.get(change.getFieldName());
            if (requiredPermission != null && !permissions.contains(requiredPermission)) {
                ValidationErrorDTO error = new ValidationErrorDTO();
                error.setField(change.getFieldName());
                error.setMessage("没有权限修改" + change.getFieldLabel());
                error.setCode("PERMISSION_DENIED");
                errors.add(error);
            }
        }
    }

    /**
     * 验证业务规则（业务逻辑）
     */
    private void validateBusinessRules(ProductUpdateDTO dto,
                                      List<ValidationErrorDTO> errors,
                                      List<ValidationWarningDTO> warnings) {
        ProductDTO newProduct = dto.getNewProduct();
        ProductDTO originalProduct = dto.getOriginalProduct();

        // 规则1：价格不能低于成本
        if (hasChange(dto, "price") || hasChange(dto, "cost")) {
            BigDecimal price = newProduct.getPrice();
            BigDecimal cost = newProduct.getCost();
            if (price != null && cost != null && price.compareTo(cost) < 0) {
                ValidationErrorDTO error = new ValidationErrorDTO();
                error.setField("price");
                error.setMessage("售价不能低于成本价");
                error.setCode("PRICE_BELOW_COST");
                errors.add(error);
            }
        }

        // 规则2：库存不能为负数
        if (hasChange(dto, "stock")) {
            Integer stock = newProduct.getStock();
            if (stock != null && stock < 0) {
                ValidationErrorDTO error = new ValidationErrorDTO();
                error.setField("stock");
                error.setMessage("库存不能为负数");
                error.setCode("INVALID_STOCK");
                errors.add(error);
            }
        }

        // 规则3：最小库存不能大于最大库存
        if (hasChange(dto, "minStock") || hasChange(dto, "maxStock")) {
            Integer minStock = newProduct.getMinStock();
            Integer maxStock = newProduct.getMaxStock();
            if (minStock != null && maxStock != null && minStock > maxStock) {
                ValidationErrorDTO error = new ValidationErrorDTO();
                error.setField("minStock");
                error.setMessage("最小库存不能大于最大库存");
                error.setCode("INVALID_STOCK_RANGE");
                errors.add(error);
            }
        }

        // 规则4：商品名称不能为空
        if (hasChange(dto, "productName")) {
            String name = newProduct.getProductName();
            if (name == null || name.trim().isEmpty()) {
                ValidationErrorDTO error = new ValidationErrorDTO();
                error.setField("productName");
                error.setMessage("商品名称不能为空");
                error.setCode("EMPTY_NAME");
                errors.add(error);
            }
        }

        // 规则5：价格大幅变动需要审批
        if (hasChange(dto, "price")) {
            BigDecimal oldPrice = originalProduct.getPrice();
            BigDecimal newPrice = newProduct.getPrice();
            if (oldPrice != null && newPrice != null) {
                BigDecimal changeRate = newPrice.subtract(oldPrice)
                        .divide(oldPrice, 4, BigDecimal.ROUND_HALF_UP)
                        .abs();
                if (changeRate.compareTo(new BigDecimal("0.2")) > 0) {
                    dto.setNeedApproval(true);

                    ValidationWarningDTO warning = new ValidationWarningDTO();
                    warning.setField("price");
                    warning.setMessage("价格变动超过20%，需要审批");
                    warnings.add(warning);
                }
            }
        }
    }

    /**
     * 验证数据完整性（业务逻辑）
     */
    private void validateDataIntegrity(ProductUpdateDTO dto, List<ValidationErrorDTO> errors) {
        ProductDTO newProduct = dto.getNewProduct();

        if (newProduct.getProductName() == null || newProduct.getProductName().trim().isEmpty()) {
            ValidationErrorDTO error = new ValidationErrorDTO();
            error.setField("productName");
            error.setMessage("商品名称不能为空");
            error.setCode("REQUIRED_FIELD");
            errors.add(error);
        }

        if (newProduct.getPrice() == null) {
            ValidationErrorDTO error = new ValidationErrorDTO();
            error.setField("price");
            error.setMessage("售价不能为空");
            error.setCode("REQUIRED_FIELD");
            errors.add(error);
        }

        if (newProduct.getCategoryId() == null) {
            ValidationErrorDTO error = new ValidationErrorDTO();
            error.setField("categoryId");
            error.setMessage("分类不能为空");
            error.setCode("REQUIRED_FIELD");
            errors.add(error);
        }
    }

    private boolean hasChange(ProductUpdateDTO dto, String fieldName) {
        for (FieldChangeDTO change : dto.getChanges()) {
            if (change.getFieldName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }

    private void doUpdate(ProductUpdateDTO dto) {
        System.out.println("   UPDATE product SET");
        for (FieldChangeDTO change : dto.getChanges()) {
            System.out.println("     " + change.getFieldName() + " = " + change.getNewValue());
        }
        System.out.println("   WHERE product_id = " + dto.getProductId());
    }

    private String submitApproval(ProductUpdateDTO dto) {
        return "APPROVAL-" + System.currentTimeMillis();
    }

    private void logAudit(ProductUpdateDTO dto) {
        StringBuilder summary = new StringBuilder();
        summary.append("修改了 ").append(dto.getChanges().size()).append(" 个字段: ");

        List<String> fieldLabels = new ArrayList<>();
        for (FieldChangeDTO change : dto.getChanges()) {
            fieldLabels.add(change.getFieldLabel());
        }
        summary.append(String.join("、", fieldLabels));

        System.out.println("   [AuditLog] 操作人=" + dto.getOperatorName() +
                ", 商品ID=" + dto.getProductId() +
                ", 修改=" + summary);
    }

    private void printDetails(ProductUpdateDTO dto) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         商品更新详情（DTO 版本）                                     ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("【基本信息】");
        System.out.println("  操作ID: " + dto.getOperationId());
        System.out.println("  商品ID: " + dto.getProductId());
        System.out.println("  操作人: " + dto.getOperatorName() + " (ID: " + dto.getOperatorId() + ")");
        if (dto.getReason() != null) {
            System.out.println("  修改原因: " + dto.getReason());
        }
        System.out.println();

        System.out.println("【修改内容】(" + dto.getChanges().size() + " 个字段)");
        if (dto.getChanges().isEmpty()) {
            System.out.println("  无修改");
        } else {
            for (FieldChangeDTO change : dto.getChanges()) {
                String approval = (change.getNeedApproval() != null && change.getNeedApproval())
                        ? " [需审批]" : "";
                System.out.println(String.format("  • %s: %s -> %s%s",
                        change.getFieldLabel(), change.getOldValue(), change.getNewValue(), approval));
            }
        }
        System.out.println();

        if (dto.getErrors() != null && !dto.getErrors().isEmpty()) {
            System.out.println("【验证错误】(" + dto.getErrors().size() + " 个)");
            for (ValidationErrorDTO error : dto.getErrors()) {
                System.out.println(String.format("  ❌ [%s] %s: %s",
                        error.getCode(), error.getField(), error.getMessage()));
            }
            System.out.println();
        }

        if (dto.getWarnings() != null && !dto.getWarnings().isEmpty()) {
            System.out.println("【验证警告】(" + dto.getWarnings().size() + " 个)");
            for (ValidationWarningDTO warning : dto.getWarnings()) {
                System.out.println(String.format("  ⚠️  [WARNING] %s: %s",
                        warning.getField(), warning.getMessage()));
            }
            System.out.println();
        }

        if (dto.getNeedApproval() != null && dto.getNeedApproval()) {
            System.out.println("【审批要求】");
            System.out.println("  ⚠️  此次修改需要审批");
            if (dto.getApprovalId() != null) {
                System.out.println("  审批单号: " + dto.getApprovalId());
            }
            System.out.println();
        }

        System.out.println("【验证结果】");
        if (dto.getValidated() != null && dto.getValidated()) {
            if (dto.getErrors() == null || dto.getErrors().isEmpty()) {
                System.out.println("  ✅ 验证通过");
            } else {
                System.out.println("  ❌ 验证失败");
            }
        } else {
            System.out.println("  ⏳ 未验证");
        }
    }
}

