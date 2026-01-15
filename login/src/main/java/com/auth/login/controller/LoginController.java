package com.auth.login.controller;

import com.auth.login.requestDTO.LoginRequest;
import com.auth.login.service.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginServiceImpl loginService;

    public LoginController(LoginServiceImpl loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginRequest loginRequest) {
        try{
            String code = loginService.getJWTPayload(loginRequest);
            return ResponseEntity.ok(code);
        } catch (ServiceException se) {
            log.error(se.getMessage());
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(se.getMessage());

        }
    }
}
