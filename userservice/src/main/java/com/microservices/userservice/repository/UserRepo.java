package com.microservices.userservice.repository;

import com.microservices.userservice.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<Users, Integer> {


}
