package com.main.backend.features.token.dto;

import lombok.Getter;

@Getter
public class ChangePasswordDTO {
    private String currentPassword;
    private String newPassword;
}
