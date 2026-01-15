package com.auth.login.service;

import com.auth.login.entity.AccountStatus;
import com.auth.login.entity.User;
import com.auth.login.repository.UserRepository;
import com.auth.login.requestDTO.LoginRequest;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {

    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getJWTPayload(LoginRequest request) {
        String username = request.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ServiceException("user does not exist"));
        if(AccountStatus.BLOCKED.equals(user.getAccountStatus())) {
            throw new ServiceException("not authorized 403");
        }
        if (!request.getPassword().equals(
                user.getPassowrd())) {
            throw new ServiceException("incorrect credentials 401");
        }
        //this we can generate later
//        String token = jwtService.generate(user);

        return "test token";

    }
}
