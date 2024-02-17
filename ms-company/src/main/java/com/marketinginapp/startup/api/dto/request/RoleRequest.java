package com.marketinginapp.startup.api.dto.request;

import com.marketinginapp.startup.utils.Constant;
import com.marketinginapp.startup.utils.validator.TrimString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequest(
        @TrimString
        @NotBlank(message = Constant.MUST_NOT_BE_NULL_OR_EMPTY)
        @Size(min = 3, max = 15,  message = Constant.MUST_BE_BETWEEN_3_AND_15_CHARACTERS)
        String name
) {}
