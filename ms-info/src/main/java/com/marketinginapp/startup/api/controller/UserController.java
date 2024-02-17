package com.marketinginapp.startup.api.controller;

import com.marketinginapp.startup.api.dto.request.UserRequest;
import com.marketinginapp.startup.api.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {
    @PostMapping(value = "/save")
    ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest request);

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable String id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(value = "/findByEmail/{email}")
    ResponseEntity<UserResponse> findByEmail(@PathVariable String email);

    @GetMapping(value = "/findByUsernameOrEmail/{name}/{email}")
    ResponseEntity<UserResponse> findByUsernameOrEmail(@PathVariable String name, @PathVariable String email);

    @GetMapping(value = "/findAll")
    ResponseEntity<List<UserResponse>> findAll();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PutMapping(value = "/update/{id}")
    ResponseEntity<UserResponse> update(@PathVariable String id, @Valid @RequestBody UserRequest request);
}
