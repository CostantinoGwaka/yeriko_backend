package com.isofttz.yeriko_backend.controller;

import com.isofttz.yeriko_backend.entities.Users;
import com.isofttz.yeriko_backend.model.AuthResponseModel;
import com.isofttz.yeriko_backend.model.ResponseModel;
import com.isofttz.yeriko_backend.resources.graphql.dto.LoginDTOInput;
import com.isofttz.yeriko_backend.security.JwtTokenProvider;
import com.isofttz.yeriko_backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
//@RequestMapping("/api/auth")
public class AuthController {

    @Value("${app.jwt-expiration-milliseconds}")
    private Long expiration;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserServices userServices;

    @MutationMapping
    public AuthResponseModel login(@Argument LoginDTOInput input){
        System.out.print("-------------GraphQL login start here-------------------");

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getUserName(), input.getPassword())
            );

            User userDetails = (User) authentication.getPrincipal();
            Optional<Users> optionalUser = userServices.findByUserName(userDetails.getUsername());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);

            System.out.print("-------------GraphQL path start here-------------------");

            if (optionalUser.isPresent()) {
                Users userInfo = optionalUser.get();
                return new AuthResponseModel(
                        200,
                        "Successfully logged in",
                        token,
                        System.currentTimeMillis(),
                        expiration,
                        userInfo
                );
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponseModel> logout() {
        // Option 1: If you want just simple logout (client deletes token)
        SecurityContextHolder.clearContext();

        AuthResponseModel response = new AuthResponseModel(
                HttpStatus.OK.value(),
                "Successfully logged out",
                null, // No token
                System.currentTimeMillis(),
                0L,    // No expiration
                null  // No user info
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseModel<Users> registerUser(@RequestBody Users users){
        final Users savedUser = userServices.registerUser(users);
        return new ResponseModel<>(HttpStatus.OK.value(), "user registered successfully",savedUser);
    }


}
