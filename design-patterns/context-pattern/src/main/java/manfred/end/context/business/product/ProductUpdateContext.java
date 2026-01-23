package manfred.end.context.business.product;

import java.math.BigDecimal;
import java.util.*;

/**
 * 商品更新上下文
 *
 * 场景：修改商品信息（复杂结构体）
 *
 * 这是一个 OperationContext（操作上下文）+ ValidationContext（验证上下文）的组合
 *
 * 核心功能：
 * 1. 追踪修改内容（哪些字段被修改了）
 * 2. 验证修改合法性（业务规则验证）
 * 3. 记录修改历史（审计日志）
 * 4. 权限验证（谁可以修改哪些字段）
 * 5. 级联更新（修改商品影响库存、价格等）
 *
 * 实际案例：
 * - 电商系统：修改商品标题、价格、库存、图片等
 * - ERP系统：修改商品分类、供应商、成本等
 * - 库存系统：修改库存数量、预警值等
 *
 * @author manfred
 */
public class ProductUpdateContext {

    // ========== 基础信息 ==========

    /** 操作ID */
    private String operationId;

    /** 商品ID */
    private String productId;

    /** 操作人ID */
    private Long operatorId;

    /** 操作人姓名 */
    private String operatorName;

    /** 操作时间 */
    private long operationTime;

    /** 操作类型 */
    private OperationType operationType;

    // ========== 商品信息（原始 + 新值） ==========

    /** 原始商品信息 */
    private Product originalProduct;

    /** 新的商品信息 */
    private Product newProduct;

    /** 修改的字段列表 */
    private Map<String, FieldChange> changes = new LinkedHashMap<>();

    // ========== 验证信息 ==========

    /** 验证错误列表 */
    private List<ValidationError> errors = new ArrayList<>();

    /** 验证警告列表 */
    private List<ValidationWarning> warnings = new ArrayList<>();

    /** 是否通过验证 */
    private boolean validated = false;

    // ========== 权限信息 ==========

    /** 操作人权限 */
    private Set<String> permissions = new HashSet<>();

    /** 受限字段（需要特殊权限才能修改） */
    private static final Map<String, String> RESTRICTED_FIELDS = new HashMap<>();

    static {
        RESTRICTED_FIELDS.put("price", "product:price:update");
        RESTRICTED_FIELDS.put("cost", "product:cost:update");
        RESTRICTED_FIELDS.put("status", "product:status:update");
        RESTRICTED_FIELDS.put("categoryId", "product:category:update");
    }

    // ========== 审计信息 ==========

    /** 修改原因 */
    private String reason;

    /** 审批单号（如果需要审批） */
    private String approvalId;

    /** 是否需要审批 */
    private boolean needApproval = false;

    // ========== 内部类 ==========

    public enum OperationType {
        CREATE,      // 创建
        UPDATE,      // 更新
        DELETE,      // 删除
        BATCH_UPDATE // 批量更新
    }

    /**
     * 字段变更记录
     */
    public static class FieldChange {
        private String fieldName;
        private String fieldLabel;
        private Object oldValue;
        private Object newValue;
        private boolean needApproval;

        public FieldChange(String fieldName, String fieldLabel, Object oldValue, Object newValue) {
            this.fieldName = fieldName;
            this.fieldLabel = fieldLabel;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public boolean isChanged() {
            if (oldValue == null && newValue == null) return false;
            if (oldValue == null || newValue == null) return true;
            return !oldValue.equals(newValue);
        }

        @Override
        public String toString() {
            return String.format("%s: %s -> %s", fieldLabel, oldValue, newValue);
        }

        // Getters
        public String getFieldName() { return fieldName; }
        public String getFieldLabel() { return fieldLabel; }
        public Object getOldValue() { return oldValue; }
        public Object getNewValue() { return newValue; }
        public boolean isNeedApproval() { return needApproval; }
        public void setNeedApproval(boolean needApproval) { this.needApproval = needApproval; }
    }

    /**
     * 验证错误
     */
    public static class ValidationError {
        private String field;
        private String message;
        private String code;

        public ValidationError(String field, String message, String code) {
            this.field = field;
            this.message = message;
            this.code = code;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s: %s", code, field, message);
        }

        public String getField() { return field; }
        public String getMessage() { return message; }
        public String getCode() { return code; }
    }

    /**
     * 验证警告
     */
    public static class ValidationWarning {
        private String field;
        private String message;

        public ValidationWarning(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @Override
        public String toString() {
            return String.format("[WARNING] %s: %s", field, message);
        }
    }

    /**
     * 商品信息（复杂结构体）
     */
    public static class Product {
        // 基本信息
        private String productId;
        private String productName;
        private String productCode;
        private String categoryId;
        private String categoryName;
        private String brandId;
        private String brandName;

        // 价格信息
        private BigDecimal price;
        private BigDecimal cost;
        private BigDecimal marketPrice;

        // 库存信息
        private Integer stock;
        private Integer minStock;
        private Integer maxStock;

        // 规格信息
        private String unit;
        private BigDecimal weight;
        private String size;

        // 状态信息
        private String status;  // ON_SALE, OFF_SALE, DELETED
        private Boolean isNew;
        private Boolean isHot;

        // 描述信息
        private String description;
        private List<String> images;
        private Map<String, String> attributes;

        // 供应商信息
        private String supplierId;
        private String supplierName;

        // 时间信息
        private Date createTime;
        private Date updateTime;

        // Getters and Setters
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }

        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }

        public String getProductCode() { return productCode; }
        public void setProductCode(String productCode) { this.productCode = productCode; }

        public String getCategoryId() { return categoryId; }
        public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

        public String getCategoryName() { return categoryName; }
        public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

        public String getBrandId() { return brandId; }
        public void setBrandId(String brandId) { this.brandId = brandId; }

        public String getBrandName() { return brandName; }
        public void setBrandName(String brandName) { this.brandName = brandName; }

        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }

        public BigDecimal getCost() { return cost; }
        public void setCost(BigDecimal cost) { this.cost = cost; }

        public BigDecimal getMarketPrice() { return marketPrice; }
        public void setMarketPrice(BigDecimal marketPrice) { this.marketPrice = marketPrice; }

        public Integer getStock() { return stock; }
        public void setStock(Integer stock) { this.stock = stock; }

        public Integer getMinStock() { return minStock; }
        public void setMinStock(Integer minStock) { this.minStock = minStock; }

        public Integer getMaxStock() { return maxStock; }
        public void setMaxStock(Integer maxStock) { this.maxStock = maxStock; }

        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }

        public BigDecimal getWeight() { return weight; }
        public void setWeight(BigDecimal weight) { this.weight = weight; }

        public String getSize() { return size; }
        public void setSize(String size) { this.size = size; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public Boolean getIsNew() { return isNew; }
        public void setIsNew(Boolean isNew) { this.isNew = isNew; }

        public Boolean getIsHot() { return isHot; }
        public void setIsHot(Boolean isHot) { this.isHot = isHot; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public List<String> getImages() { return images; }
        public void setImages(List<String> images) { this.images = images; }

        public Map<String, String> getAttributes() { return attributes; }
        public void setAttributes(Map<String, String> attributes) { this.attributes = attributes; }

        public String getSupplierId() { return supplierId; }
        public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

        public String getSupplierName() { return supplierName; }
        public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

        public Date getCreateTime() { return createTime; }
        public void setCreateTime(Date createTime) { this.createTime = createTime; }

        public Date getUpdateTime() { return updateTime; }
        public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    }

    // ========== 构造方法 ==========

    public ProductUpdateContext(String operationId, String productId, Long operatorId, String operatorName) {
        this.operationId = operationId;
        this.productId = productId;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.operationTime = System.currentTimeMillis();
        this.operationType = OperationType.UPDATE;
    }

    // ========== 核心方法 ==========

    /**
     * 设置原始商品信息
     */
    public void setOriginalProduct(Product product) {
        this.originalProduct = product;
    }

    /**
     * 设置新的商品信息
     */
    public void setNewProduct(Product product) {
        this.newProduct = product;
    }

    /**
     * 比较并记录变更
     */
    public void detectChanges() {
        if (originalProduct == null || newProduct == null) {
            return;
        }

        // 比较各个字段
        compareField("productName", "商品名称", originalProduct.getProductName(), newProduct.getProductName());
        compareField("productCode", "商品编码", originalProduct.getProductCode(), newProduct.getProductCode());
        compareField("categoryId", "分类", originalProduct.getCategoryName(), newProduct.getCategoryName());
        compareField("brandId", "品牌", originalProduct.getBrandName(), newProduct.getBrandName());
        compareField("price", "售价", originalProduct.getPrice(), newProduct.getPrice());
        compareField("cost", "成本", originalProduct.getCost(), newProduct.getCost());
        compareField("marketPrice", "市场价", originalProduct.getMarketPrice(), newProduct.getMarketPrice());
        compareField("stock", "库存", originalProduct.getStock(), newProduct.getStock());
        compareField("minStock", "最小库存", originalProduct.getMinStock(), newProduct.getMinStock());
        compareField("maxStock", "最大库存", originalProduct.getMaxStock(), newProduct.getMaxStock());
        compareField("unit", "单位", originalProduct.getUnit(), newProduct.getUnit());
        compareField("weight", "重量", originalProduct.getWeight(), newProduct.getWeight());
        compareField("size", "尺寸", originalProduct.getSize(), newProduct.getSize());
        compareField("status", "状态", originalProduct.getStatus(), newProduct.getStatus());
        compareField("isNew", "新品", originalProduct.getIsNew(), newProduct.getIsNew());
        compareField("isHot", "热卖", originalProduct.getIsHot(), newProduct.getIsHot());
        compareField("description", "描述", originalProduct.getDescription(), newProduct.getDescription());
        compareField("supplierId", "供应商", originalProduct.getSupplierName(), newProduct.getSupplierName());
    }

    private void compareField(String fieldName, String fieldLabel, Object oldValue, Object newValue) {
        FieldChange change = new FieldChange(fieldName, fieldLabel, oldValue, newValue);
        if (change.isChanged()) {
            changes.put(fieldName, change);

            // 检查是否需要特殊权限
            if (RESTRICTED_FIELDS.containsKey(fieldName)) {
                change.setNeedApproval(true);
            }
        }
    }

    /**
     * 验证修改
     */
    public boolean validate() {
        errors.clear();
        warnings.clear();

        // 1. 验证权限
        validatePermissions();

        // 2. 验证业务规则
        validateBusinessRules();

        // 3. 验证数据合法性
        validateDataIntegrity();

        validated = true;
        return errors.isEmpty();
    }

    /**
     * 验证权限
     */
    private void validatePermissions() {
        for (FieldChange change : changes.values()) {
            String requiredPermission = RESTRICTED_FIELDS.get(change.getFieldName());
            if (requiredPermission != null && !permissions.contains(requiredPermission)) {
                addError(change.getFieldName(),
                        String.format("没有权限修改%s", change.getFieldLabel()),
                        "PERMISSION_DENIED");
            }
        }
    }

    /**
     * 验证业务规则
     */
    private void validateBusinessRules() {
        // 规则1：价格不能低于成本
        if (changes.containsKey("price") || changes.containsKey("cost")) {
            BigDecimal price = newProduct.getPrice();
            BigDecimal cost = newProduct.getCost();
            if (price != null && cost != null && price.compareTo(cost) < 0) {
                addError("price", "售价不能低于成本价", "PRICE_BELOW_COST");
            }
        }

        // 规则2：库存不能为负数
        if (changes.containsKey("stock")) {
            Integer stock = newProduct.getStock();
            if (stock != null && stock < 0) {
                addError("stock", "库存不能为负数", "INVALID_STOCK");
            }
        }

        // 规则3：最小库存不能大于最大库存
        if (changes.containsKey("minStock") || changes.containsKey("maxStock")) {
            Integer minStock = newProduct.getMinStock();
            Integer maxStock = newProduct.getMaxStock();
            if (minStock != null && maxStock != null && minStock > maxStock) {
                addError("minStock", "最小库存不能大于最大库存", "INVALID_STOCK_RANGE");
            }
        }

        // 规则4：商品名称不能为空
        if (changes.containsKey("productName")) {
            String name = newProduct.getProductName();
            if (name == null || name.trim().isEmpty()) {
                addError("productName", "商品名称不能为空", "EMPTY_NAME");
            }
        }

        // 规则5：价格大幅变动需要审批
        if (changes.containsKey("price")) {
            BigDecimal oldPrice = originalProduct.getPrice();
            BigDecimal newPrice = newProduct.getPrice();
            if (oldPrice != null && newPrice != null) {
                BigDecimal changeRate = newPrice.subtract(oldPrice)
                        .divide(oldPrice, 4, BigDecimal.ROUND_HALF_UP)
                        .abs();
                if (changeRate.compareTo(new BigDecimal("0.2")) > 0) {
                    needApproval = true;
                    addWarning("price", "价格变动超过20%，需要审批");
                }
            }
        }
    }

    /**
     * 验证数据完整性
     */
    private void validateDataIntegrity() {
        // 验证必填字段
        if (newProduct.getProductName() == null || newProduct.getProductName().trim().isEmpty()) {
            addError("productName", "商品名称不能为空", "REQUIRED_FIELD");
        }

        if (newProduct.getPrice() == null) {
            addError("price", "售价不能为空", "REQUIRED_FIELD");
        }

        if (newProduct.getCategoryId() == null) {
            addError("categoryId", "分类不能为空", "REQUIRED_FIELD");
        }
    }

    /**
     * 添加验证错误
     */
    public void addError(String field, String message, String code) {
        errors.add(new ValidationError(field, message, code));
    }

    /**
     * 添加验证警告
     */
    public void addWarning(String field, String message) {
        warnings.add(new ValidationWarning(field, message));
    }

    /**
     * 添加权限
     */
    public void addPermission(String permission) {
        permissions.add(permission);
    }

    /**
     * 添加多个权限
     */
    public void addPermissions(String... permissions) {
        this.permissions.addAll(Arrays.asList(permissions));
    }

    /**
     * 设置修改原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取修改摘要
     */
    public String getSummary() {
        if (changes.isEmpty()) {
            return "无修改";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("修改了 ").append(changes.size()).append(" 个字段: ");

        List<String> fieldLabels = new ArrayList<>();
        for (FieldChange change : changes.values()) {
            fieldLabels.add(change.getFieldLabel());
        }
        sb.append(String.join("、", fieldLabels));

        return sb.toString();
    }

    /**
     * 打印详细信息
     */
    public void printDetails() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         商品更新上下文详情                                            ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("【基本信息】");
        System.out.println("  操作ID: " + operationId);
        System.out.println("  商品ID: " + productId);
        System.out.println("  操作人: " + operatorName + " (ID: " + operatorId + ")");
        System.out.println("  操作类型: " + operationType);
        if (reason != null) {
            System.out.println("  修改原因: " + reason);
        }
        System.out.println();

        System.out.println("【修改内容】(" + changes.size() + " 个字段)");
        if (changes.isEmpty()) {
            System.out.println("  无修改");
        } else {
            for (FieldChange change : changes.values()) {
                String approval = change.isNeedApproval() ? " [需审批]" : "";
                System.out.println("  • " + change + approval);
            }
        }
        System.out.println();

        if (!errors.isEmpty()) {
            System.out.println("【验证错误】(" + errors.size() + " 个)");
            for (ValidationError error : errors) {
                System.out.println("  ❌ " + error);
            }
            System.out.println();
        }

        if (!warnings.isEmpty()) {
            System.out.println("【验证警告】(" + warnings.size() + " 个)");
            for (ValidationWarning warning : warnings) {
                System.out.println("  ⚠️  " + warning);
            }
            System.out.println();
        }

        if (needApproval) {
            System.out.println("【审批要求】");
            System.out.println("  ⚠️  此次修改需要审批");
            if (approvalId != null) {
                System.out.println("  审批单号: " + approvalId);
            }
            System.out.println();
        }

        System.out.println("【验证结果】");
        if (validated) {
            if (errors.isEmpty()) {
                System.out.println("  ✅ 验证通过");
            } else {
                System.out.println("  ❌ 验证失败");
            }
        } else {
            System.out.println("  ⏳ 未验证");
        }
    }

    // ========== Getters ==========

    public String getOperationId() {
        return operationId;
    }

    public String getProductId() {
        return productId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public Map<String, FieldChange> getChanges() {
        return changes;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public List<ValidationWarning> getWarnings() {
        return warnings;
    }

    public boolean isValidated() {
        return validated;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean hasChanges() {
        return !changes.isEmpty();
    }

    public boolean isNeedApproval() {
        return needApproval;
    }

    public String getReason() {
        return reason;
    }

    public Product getOriginalProduct() {
        return originalProduct;
    }

    public Product getNewProduct() {
        return newProduct;
    }
}

