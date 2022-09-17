package com.microservices.productservice.controller;


import com.microservices.productservice.service.ProductService;
import com.microservices.productservice.service.ProductServiceImpl;
import com.microservices.productservice.views.BaseResponse;
import com.microservices.productservice.views.FetchAllProductsResponseView;
import com.microservices.productservice.views.ProductView;
import com.microservices.productservice.views.SaveProductsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/saveProducts")
    public SaveProductsResponse saveProducts(@RequestBody List<ProductView> productViewList){
        LOG.info("inside saveProducts call");
        SaveProductsResponse saveProductsResponse = new SaveProductsResponse();
        if(null!= productViewList && productViewList.size()>0){
            try {
                 saveProductsResponse = productService.saveProducts(productViewList);
            }catch(Exception e){
                LOG.error("some error occured while saving the products");
                LOG.error(e.getMessage());
                saveProductsResponse.setCode(400);
                saveProductsResponse.setMessage("error occured please try again after some time");
            }
        }else{
            saveProductsResponse.setCode(1000);
            saveProductsResponse.setMessage("No request found");
        }

        return saveProductsResponse;
    }
 //optional
    //@pathvariable
    @GetMapping("/fetchProducts")
    public FetchAllProductsResponseView fetchProducts(){
     LOG.info("fetch all the products");
        FetchAllProductsResponseView fetchAllProductsResponseView = new FetchAllProductsResponseView();
     try {
         List<ProductView>  response=  productService.fetchAllProducts();
         if(null!=response && response.size()>0) {
             fetchAllProductsResponseView.setProductViewList(response);
             fetchAllProductsResponseView.setCode(300);
             fetchAllProductsResponseView.setMessage("All records have been succesfully fetched");
         }else{
             fetchAllProductsResponseView.setCode(301);
             fetchAllProductsResponseView.setMessage("No records found.");

         }
     }catch(Exception e){
         LOG.error("some error occured while fetching the products");
         LOG.error(e.getMessage());
         fetchAllProductsResponseView.setCode(400);
         fetchAllProductsResponseView.setMessage("error occured please try again after some time");

     }
     return fetchAllProductsResponseView;
    }


    @GetMapping("/fetchProduct/{id}")
    public ProductView fetchProductBasedOnId(@PathVariable String id){
        LOG.info("fetch single product based on id");
        ProductView  response= new ProductView();
        try {
              response= productService.fetchProductBasedOnID(id);

        }catch(Exception e){
            LOG.error("some error occured while fetching the products");
            LOG.error(e.getMessage());

        }
        return response;
    }

    @GetMapping("/fetchProductByCategory/{category}")
    public List<ProductView>  fetchProductByCategory(@PathVariable String category){
        List<ProductView>   response= new ArrayList<>();
        try {
            response= productService.retrieveProductsBasedOnCategory(category);

        }catch(Exception e){
            LOG.error("some error occured while fetching the products");
            LOG.error(e.getMessage());

        }
        return response;
    }

    @GetMapping("/fetchProductByName")
    public List<ProductView>  fetchProductByName(@RequestParam  String productName){
        List<ProductView>   response= new ArrayList<>();
        try {
            response= productService.fetchProductByName(productName);

        }catch(Exception e){
            LOG.error("some error occured while fetching the products");
            LOG.error(e.getMessage());

        }
        return response;
    }

    @GetMapping("/fetchProductCategories")
    public List<String> fetchProductCategories(){
        LOG.info("fetch single product based on id");
        List<String> productCategories = new ArrayList<>();
        try{
             productCategories = productService.fetchProductCategories();
        }catch(Exception e){
            LOG.error("some error occured while fetching the products");
            LOG.error(e.getMessage());
        }
        return productCategories;
    }

    @DeleteMapping("/deleteProduct")
    public BaseResponse deleteProductById(@RequestParam List<String> id){
        BaseResponse baseResponse = new BaseResponse();
        try{
            for (String productID:id){
                productService.deleteProductById(productID);
                baseResponse.setCode(700);
                baseResponse.setMessage("successfully deleted");
            }

        }catch(Exception e){
            LOG.error("some error occured while fetching the products");
            LOG.error(e.getMessage());
            baseResponse.setCode(400);
            baseResponse.setMessage("not able to deleted");
        }
        return baseResponse;
    }


    @PutMapping("/updateProductDetails")
    public ProductView updateProductDetailsById(@RequestParam String id, @RequestBody ProductView productView){
        LOG.info("fetch single product based on id");
        ProductView productViewResponse = new ProductView();
    try{
    productViewResponse = productService.updateProductDetailsById(id, productView);
    }catch (Exception e){
    LOG.error("some error occured while fetching the products");
    LOG.error(e.getMessage());
    }
        return productViewResponse;
    }

//TODO :: product can be uploaded through csv/excel


}
