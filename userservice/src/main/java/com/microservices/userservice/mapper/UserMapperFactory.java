package com.microservices.userservice.mapper;

import com.microservices.userservice.entity.Users;
import com.microservices.userservice.view.UserDetailView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMapperFactory extends ConfigurableMapper {
    private static final Logger LOG = LoggerFactory.getLogger(UserMapperFactory.class);
    @Override
    protected void configure(MapperFactory factory) {
        LOG.info("mapper execution started");
        factory.classMap(UserDetailView.class, Users.class).byDefault().register();
    }
}
