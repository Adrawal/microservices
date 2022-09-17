package com.microservices.productservice.service;

import com.microservices.productservice.views.ProductView;
import com.microservices.productservice.views.SaveProductsResponse;

import java.util.List;

public interface ProductService {
    public SaveProductsResponse saveProducts(List<ProductView> productViewList) throws Exception;
    public List<ProductView> fetchAllProducts() throws Exception;
    public ProductView fetchProductBasedOnID(String ID)throws Exception;
    public List<ProductView>  retrieveProductsBasedOnCategory(String category)throws Exception;
    public List<ProductView>  fetchProductByName(String category)throws Exception;
    public List<String>  fetchProductCategories()throws Exception;
    public boolean deleteProductById(String ID)throws Exception;
    public ProductView updateProductDetailsById(String ID, ProductView productView)throws Exception;

}
