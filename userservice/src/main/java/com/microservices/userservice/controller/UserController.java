package com.microservices.userservice.controller;

import com.microservices.userservice.service.UserService;
import com.microservices.userservice.view.AddressView;
import com.microservices.userservice.view.UserDetailView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/saveUserDetails")
    public UserDetailView saveUserDetails(@RequestBody UserDetailView  userDetails){
        UserDetailView userDetailViewResponse = new UserDetailView();
        LOG.info("saveUserDetails:: Execution:Starts");
        if(null!=userDetails){
            try{
                userDetailViewResponse=   userService.saveUserDetails(userDetails);
            }catch(Exception e){
            LOG.error("Exception error occured ",e.getMessage());
            }
        }
    return userDetailViewResponse;
    }
    @GetMapping("/fetchUserDetail")
    public void fetchUserDetail(){

    }
    @DeleteMapping("/deleteUser")
    public void deleteUser(){

    }
    @PutMapping("/updateUser")
    public void updateUser(){

    }

    @PostMapping("/saveUserAddress")
    public void saveUserAddress(){

    }
    @GetMapping("/fetchUserAddress")
    public void fetchUserAddress(){

    }
    @DeleteMapping("/deleteAddress")
    public void deleteAddress(){

    }
    @PutMapping("/updateAddress")
    public void updateAddress(){

    }

}
