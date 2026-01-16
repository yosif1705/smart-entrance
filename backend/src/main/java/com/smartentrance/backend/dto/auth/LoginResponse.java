package com.smartentrance.backend.dto.auth;

import com.smartentrance.backend.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    @Schema(description = "JWT Token used for authorization (if not using cookies)", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Details of the authenticated user")
    private UserResponse user;
}