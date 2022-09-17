package com.microservices.productservice.views;

import java.util.ArrayList;
import java.util.List;

public class FetchAllProductsResponseView extends BaseResponse {
    List<ProductView> productViewList = new ArrayList<>();

    public List<ProductView> getProductViewList() {
        return productViewList;
    }

    public void setProductViewList(List<ProductView> productViewList) {
        this.productViewList = productViewList;
    }
}
