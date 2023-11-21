package com.main.backend.features.token.api;

import com.main.backend.features.user.dto.ChangePasswordDTO;
import com.main.backend.features.user.dto.ForgetPasswordDTO;
import com.main.backend.features.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
public class TokenController {
    private final TokenService service;

    @Autowired
    public TokenController(TokenService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        log.info("[{}] User is trying to log into page", userDTO.getEmail());

        try {
            final String token = service.login(userDTO.getEmail(), userDTO.getPassword());
            log.info("[{}] Session token created successfully", token);
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, "sessionToken=" + token)
                    .body(token);
        } catch (Exception ex) {
            final String response = ex.getMessage();
            log.error("[{}] " + response, userDTO.getEmail(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<String> logout(@PathVariable String token) {
        log.info("[{}] User with token is trying to log out", token);
        String response;

        try {
            response = service.logout(token);
            log.info("[{}] Session logout successfully", token);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response = ex.getMessage();
            log.error("[{}] " + response, token, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/change-password/{token}")
    public ResponseEntity<String> changePassword(@PathVariable String token, @RequestBody ChangePasswordDTO changePasswordDTO) {
        HttpStatus status;
        String response;

        try {
            status = HttpStatus.OK;
            response = service.changePassword(token, changePasswordDTO.getCurrentPassword(), changePasswordDTO.getNewPassword());
            log.info("[{}] Password changed successfully", token);
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = ex.getMessage();
            log.error("[{}] " + response, token, ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserDTO userDTO) {
        HttpStatus status;
        String response;

        try {
            status = HttpStatus.OK;
            response = service.registration(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
            log.info("[{}] Registration token created successfully", userDTO.getEmail());
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = ex.getMessage();
            log.error("[{}] " + response, userDTO.getEmail(), ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/registration/{token}")
    public ResponseEntity<String> confirmRegistration(@PathVariable String token) {
        HttpStatus status;
        String response;

        try {
            status = HttpStatus.OK;
            response = service.confirmRegistration(token);
            log.info("[{}] Account confirmed successfully", token);
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = ex.getMessage();
            log.error("[{}] " + response, token, ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {
        HttpStatus status;
        String response;

        try {
            status = HttpStatus.OK;
            response = service.forgetPassword(forgetPasswordDTO.getEmail());
            log.info("[{}] Forget-password token created successfully", forgetPasswordDTO.getEmail());
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = ex.getMessage();
            log.error("[{}] " + response, forgetPasswordDTO.getEmail(), ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/forget-password/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token) {
        HttpStatus status;
        String response;

        try {
            status = HttpStatus.OK;
            response = service.resetPassword(token);
            log.info("[{}] Password reset successfully", token);
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = ex.getMessage();
            log.error("[{}] " + response, token, ex);
        }

        return ResponseEntity.status(status).body(response);
    }
}
