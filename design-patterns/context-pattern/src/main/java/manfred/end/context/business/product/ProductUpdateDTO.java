package manfred.end.context.business.product;

import java.math.BigDecimal;
import java.util.*;

/**
 * 商品更新数据传输对象（纯数据载体版本）
 *
 * 设计理念：
 * 1. 只包含数据，不包含业务逻辑
 * 2. 作为数据传输的容器
 * 3. 业务逻辑由外部服务处理
 *
 * 优点：
 * - 简单清晰，易于理解
 * - 职责单一，只负责数据传输
 * - 易于序列化和传输
 * - 符合贫血模型
 *
 * 缺点：
 * - 业务逻辑分散在各个服务中
 * - 缺少封装，容易被误用
 * - 难以保证数据一致性
 * - 缺少上下文信息
 *
 * @author manfred
 */
public class ProductUpdateDTO {

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
    private Long operationTime;

    // ========== 商品信息 ==========

    /** 原始商品信息 */
    private ProductDTO originalProduct;

    /** 新的商品信息 */
    private ProductDTO newProduct;

    // ========== 变更信息 ==========

    /** 修改的字段列表 */
    private List<FieldChangeDTO> changes;

    // ========== 验证信息 ==========

    /** 验证错误列表 */
    private List<ValidationErrorDTO> errors;

    /** 验证警告列表 */
    private List<ValidationWarningDTO> warnings;

    /** 是否通过验证 */
    private Boolean validated;

    // ========== 权限信息 ==========

    /** 操作人权限 */
    private List<String> permissions;

    // ========== 审批信息 ==========

    /** 修改原因 */
    private String reason;

    /** 审批单号 */
    private String approvalId;

    /** 是否需要审批 */
    private Boolean needApproval;

    // ========== 内部类（纯数据） ==========

    /**
     * 商品信息 DTO
     */
    public static class ProductDTO {
        private String productId;
        private String productName;
        private String productCode;
        private String categoryId;
        private String categoryName;
        private String brandId;
        private String brandName;
        private BigDecimal price;
        private BigDecimal cost;
        private BigDecimal marketPrice;
        private Integer stock;
        private Integer minStock;
        private Integer maxStock;
        private String unit;
        private BigDecimal weight;
        private String size;
        private String status;
        private Boolean isNew;
        private Boolean isHot;
        private String description;
        private List<String> images;
        private Map<String, String> attributes;
        private String supplierId;
        private String supplierName;
        private Date createTime;
        private Date updateTime;

        // 只有 Getter 和 Setter，没有业务逻辑

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

    /**
     * 字段变更 DTO
     */
    public static class FieldChangeDTO {
        private String fieldName;
        private String fieldLabel;
        private Object oldValue;
        private Object newValue;
        private Boolean needApproval;

        // 只有 Getter 和 Setter

        public String getFieldName() { return fieldName; }
        public void setFieldName(String fieldName) { this.fieldName = fieldName; }

        public String getFieldLabel() { return fieldLabel; }
        public void setFieldLabel(String fieldLabel) { this.fieldLabel = fieldLabel; }

        public Object getOldValue() { return oldValue; }
        public void setOldValue(Object oldValue) { this.oldValue = oldValue; }

        public Object getNewValue() { return newValue; }
        public void setNewValue(Object newValue) { this.newValue = newValue; }

        public Boolean getNeedApproval() { return needApproval; }
        public void setNeedApproval(Boolean needApproval) { this.needApproval = needApproval; }
    }

    /**
     * 验证错误 DTO
     */
    public static class ValidationErrorDTO {
        private String field;
        private String message;
        private String code;

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }

    /**
     * 验证警告 DTO
     */
    public static class ValidationWarningDTO {
        private String field;
        private String message;

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    // ========== Getter 和 Setter（无业务逻辑） ==========

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public ProductDTO getOriginalProduct() {
        return originalProduct;
    }

    public void setOriginalProduct(ProductDTO originalProduct) {
        this.originalProduct = originalProduct;
    }

    public ProductDTO getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(ProductDTO newProduct) {
        this.newProduct = newProduct;
    }

    public List<FieldChangeDTO> getChanges() {
        return changes;
    }

    public void setChanges(List<FieldChangeDTO> changes) {
        this.changes = changes;
    }

    public List<ValidationErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationErrorDTO> errors) {
        this.errors = errors;
    }

    public List<ValidationWarningDTO> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<ValidationWarningDTO> warnings) {
        this.warnings = warnings;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public Boolean getNeedApproval() {
        return needApproval;
    }

    public void setNeedApproval(Boolean needApproval) {
        this.needApproval = needApproval;
    }
}

