package com.smartentrance.backend.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LoginRequest {
    @Schema(description = "User's registered email", example = "admin@smartentrance.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "User's password", example = "SecurePass123!")
    @NotBlank
    private String password;

    @Schema(description = "Keep the session active for longer", example = "true")
    private boolean rememberMe;
}