package com.isofttz.yeriko_backend.controller;

import com.isofttz.yeriko_backend.entities.ChurchTimeTableEntity;
import com.isofttz.yeriko_backend.entities.Users;
import com.isofttz.yeriko_backend.model.AuthResponseModel;
import com.isofttz.yeriko_backend.model.ResponseModel;
import com.isofttz.yeriko_backend.security.JwtTokenProvider;
import com.isofttz.yeriko_backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${app.jwt-expiration-milliseconds}")
    private Long expiration;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> login(@RequestBody Users user){
        System.out.print("-------------login start here-------------------");
        final AuthResponseModel authResponseModel;


        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUserName(),user.getPassword()
        ));

        User userDetails = (User) authentication.getPrincipal();

        Optional<Users> optionalUser = userServices.findByUserName(userDetails.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token =jwtTokenProvider.generateToken(authentication);

        System.out.print("-------------path start here-------------------");


        if (optionalUser.isPresent()) {
            Users userInfo = optionalUser.get();
            authResponseModel =new AuthResponseModel(
                    HttpStatus.OK.value(),
                    "Succesfully logged in",
                    token,
                    System.currentTimeMillis(),
                    expiration,
                    userInfo
            );
            return ResponseEntity.ok(authResponseModel);
        }else{
                // Handle the case where user is not found
                throw new RuntimeException("User not found");
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

    @GetMapping("/getAllUsers")
    public ResponseModel<List<Users>> getAllUsers(){
        final List<Users> savedUsers = userServices.getAllRegisteredUser();

        if(savedUsers.isEmpty()){
            return new ResponseModel<>(HttpStatus.NOT_FOUND.value(),"Saved Users not found",savedUsers);
        }else{
            return new ResponseModel<>(HttpStatus.OK.value(), "Saved Users successfully",savedUsers);
        }
    }


}
