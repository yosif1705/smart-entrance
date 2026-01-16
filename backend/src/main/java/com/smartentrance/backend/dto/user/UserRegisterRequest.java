package com.smartentrance.backend.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @Schema(example = "Georgi")
    @NotBlank
    private String firstName;

    @Schema(example = "Ivanov")
    private String lastName;

    @Schema(example = "georgi.ivanov@example.com")
    @NotBlank
    @Email
    private String email;

    @Schema(example = "SuperSecretPass123")
    @NotBlank
    private String password;

    @Schema(example = "true")
    private boolean rememberMe;
}