package com.microservices.userservice.service;

import com.microservices.userservice.entity.Users;
import com.microservices.userservice.mapper.UserMapperFactory;
import com.microservices.userservice.mapper.UserViewMapperFactory;
import com.microservices.userservice.repository.UserRepo;
import com.microservices.userservice.view.UserDetailView;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl  implements UserService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetailView saveUserDetails(UserDetailView userDetails) throws Exception {
        UserDetailView userDetailViewResponse = new UserDetailView();
        MapperFacade mapper = new UserMapperFactory();
        Users userObject = mapper.map(userDetails, Users.class);
        Users saveResponse = userRepo.save(userObject);
        if(null!= saveResponse){
            MapperFacade UserViewMapper = new UserViewMapperFactory();
            userDetailViewResponse = mapper.map(UserViewMapper, UserDetailView.class);
        }
        return userDetailViewResponse;
    }

    @Override
    public void fetchUserDetails() throws Exception {

    }

    @Override
    public void DeleteUser() throws Exception {

    }

    @Override
    public void saveUserAddress() throws Exception {

    }

    @Override
    public void updateUserDetail() throws Exception {

    }

    @Override
    public void deleteUserAddress() throws Exception {

    }

    @Override
    public void updateUserAddress() throws Exception {

    }
}
