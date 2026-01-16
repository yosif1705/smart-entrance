package com.smartentrance.backend.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserResponse {
    @Schema(example = "5")
    private Integer id;
    @Schema(example = "Georgi")
    private String firstName;
    @Schema(example = "Ivanov")
    private String lastName;
    @Schema(example = "georgi.ivanov@example.com")
    private String email;
}