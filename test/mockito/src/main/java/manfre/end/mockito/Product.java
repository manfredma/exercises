package manfre.end.mockito;


import java.util.List;

public class Product {
    private Integer productId;
    private Boolean isDel;
    private List<ProductInfo> productInfoList;
    private ProductTitle productTitle;
    private String opReason;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public ProductTitle getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(ProductTitle productTitle) {
        this.productTitle = productTitle;
    }

    public String getOpReason() {
        return opReason;
    }

    public void setOpReason(String opReason) {
        this.opReason = opReason;
    }
}

