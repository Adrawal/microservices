package com.microservices.productservice.service;

import com.microservices.productservice.controller.ProductController;
import com.microservices.productservice.entity.Product;
import com.microservices.productservice.mappers.ProductMapperFactory;
import com.microservices.productservice.mappers.ProductViewMapperFactory;
import com.microservices.productservice.repository.ProductRepo;
import com.microservices.productservice.views.ProductView;
import com.microservices.productservice.views.SaveProductsResponse;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;
    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Override
    public SaveProductsResponse saveProducts(List<ProductView> productViewList) throws Exception{
        LOG.info("saveProducts service execution started");
        SaveProductsResponse saveProductsResponse =new SaveProductsResponse();
        MapperFacade mapper = new ProductMapperFactory();
        List<Product> products = new ArrayList<>();
        for (ProductView productView: productViewList){
            Product product = mapper.map(productView, Product.class);
            products.add(product);
        }
        List<Product> productListResponse = productRepo.saveAll(products);
        if(productListResponse.size()>0){
            saveProductsResponse.setMessage("succesfully stored");
            saveProductsResponse.setCode(2000);
        }else {
            saveProductsResponse.setMessage("something went wrong");
            saveProductsResponse.setCode(4000);
        }
        return saveProductsResponse;
    }

    @Override
    public List<ProductView> fetchAllProducts() throws Exception {
        List<ProductView> productViewList = new ArrayList<>();

            List<Product> productList = productRepo.findAll();
            MapperFacade mapper = new ProductViewMapperFactory();

            for (Product product : productList) {
                ProductView productView = mapper.map(product, ProductView.class);
                productViewList.add(productView);
            }
        return productViewList;
    }

    @Override
    public ProductView fetchProductBasedOnID(String ID) throws Exception{
        ProductView productView = new ProductView();
                Optional<Product> response = productRepo.findById(ID);
            MapperFacade mapper = new ProductViewMapperFactory();
            if (response.isPresent()) {
                 productView = mapper.map(response.get(), ProductView.class);
            }

        return productView;
    }

    @Override
    public List<ProductView> retrieveProductsBasedOnCategory(String category) throws Exception{
        List<ProductView> productViewList = new ArrayList<>();
        List<Product> productList = productRepo.retrieveProductsBasedOnCategory(category);
        MapperFacade mapper = new ProductViewMapperFactory();

        for (Product product : productList) {
            ProductView productView = mapper.map(product, ProductView.class);
            productViewList.add(productView);
        }
        return productViewList;
    }

    @Override
    public List<ProductView> fetchProductByName(String productName) throws Exception{
        List<ProductView> productViewList = new ArrayList<>();
        List<Product> productList = productRepo.retrieveProductsBasedOnName(productName);
        MapperFacade mapper = new ProductViewMapperFactory();
        for (Product product : productList) {
            ProductView productView = mapper.map(product, ProductView.class);
            productViewList.add(productView);
        }
        return productViewList;
    }

    @Override
    public List<String> fetchProductCategories() throws Exception{
     List<String>productCategories =   productRepo.fetchProductCategories();

        return productCategories;
    }

    @Override
    public boolean deleteProductById(String ID) throws Exception {
        productRepo.deleteById(ID);
        return true;
    }

    @Override
    public ProductView updateProductDetailsById(String ID, ProductView productViewRequest) throws Exception {
        Product product = productRepo.findById(ID).orElseThrow(() -> new Exception("Employee not exist with id: " + ID));
        MapperFacade mapper = new ProductMapperFactory();
        ProductView productViewResponse = new ProductView();
        if(null!= product && null!=productViewRequest){
            productViewRequest.setProductId(ID);
            Product productData = mapper.map(productViewRequest, Product.class);
            Product saveProduct = productRepo.save(productData);
             productViewResponse = mapper.map(saveProduct, ProductView.class);
        }

        return productViewResponse;
    }

}
