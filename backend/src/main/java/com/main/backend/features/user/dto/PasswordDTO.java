package com.main.backend.features.user.dto;

import lombok.Getter;

@Getter
public class PasswordDTO {
    private String currentPassword;
    private String newPassword;
}
