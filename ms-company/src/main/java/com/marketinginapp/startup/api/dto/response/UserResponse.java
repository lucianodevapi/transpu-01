package com.marketinginapp.startup.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        String id,
        @JsonProperty(value = "name")
        String username,
        String email,
        String profile,
        String status
) {}