package com.main.backend.features.user.dto;

import lombok.Getter;

@Getter
public class ChangePasswordDTO {
    private String currentPassword;
    private String newPassword;
}
