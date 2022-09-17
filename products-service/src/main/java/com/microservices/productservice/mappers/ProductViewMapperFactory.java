package com.microservices.productservice.mappers;

import com.microservices.productservice.entity.Product;
import com.microservices.productservice.views.ProductView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductViewMapperFactory extends ConfigurableMapper {
    private static final Logger LOG = LoggerFactory.getLogger(ProductViewMapperFactory.class);
    @Override
    protected void configure(MapperFactory factory) {
        LOG.info("mapper execution started");
        factory.classMap( Product.class, ProductView.class).byDefault().register();

        /**
         * factory.classMap(ProductView.class, Product.class).field("id", "avc").byDefault().register();
         */
    }
}
