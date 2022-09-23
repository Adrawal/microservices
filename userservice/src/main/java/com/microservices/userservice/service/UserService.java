package com.microservices.userservice.service;

import com.microservices.userservice.view.UserDetailView;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDetailView saveUserDetails(UserDetailView userDetails) throws Exception;
    public void fetchUserDetails() throws Exception;
    public void DeleteUser() throws Exception;
    public void saveUserAddress() throws Exception;
    public void updateUserDetail() throws Exception;
    public void deleteUserAddress() throws Exception;
    public void updateUserAddress() throws Exception;



}
