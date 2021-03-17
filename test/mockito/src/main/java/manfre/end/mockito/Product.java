package manfre.end.mockito;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Integer productId;
    private Boolean isDel;
    private List<ProductInfo> productInfoList;
    private ProductTitle productTitle;
    private String opReason;
}

