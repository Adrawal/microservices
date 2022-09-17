package com.microservices.productservice.repository;

import com.microservices.productservice.entity.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {

    @Query("{ProductCategory : ?0}")
    List<Product> retrieveProductsBasedOnCategory(String ProductCategory);

    @Query("{productName : ?0}")
    List<Product> retrieveProductsBasedOnName(String productName);

    @Aggregation(pipeline = { "{ '$group': { '_id' : '$ProductCategory' } }" })
    List<String> fetchProductCategories();

}
