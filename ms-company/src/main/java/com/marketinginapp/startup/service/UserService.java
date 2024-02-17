package com.marketinginapp.startup.service;

import com.marketinginapp.startup.api.dto.request.UserRequest;
import com.marketinginapp.startup.api.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest request);
    UserResponse findById(String id);
    UserResponse findByEmail(String email);
    UserResponse findByUsernameOrEmail(String username, String email);
    List<UserResponse> findAll();
    UserResponse update(String id, UserRequest request);
}
