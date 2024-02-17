package com.marketinginapp.startup.service;

import com.marketinginapp.startup.api.dto.request.RoleRequest;
import com.marketinginapp.startup.api.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse save(RoleRequest request);
    RoleResponse findById(String id);
    List<RoleResponse> findAll();
    RoleResponse update(String id, RoleRequest request);
}
