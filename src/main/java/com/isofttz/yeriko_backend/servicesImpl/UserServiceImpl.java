package com.isofttz.yeriko_backend.servicesImpl;

import com.isofttz.yeriko_backend.entities.Users;
import com.isofttz.yeriko_backend.repository.AppUserRepository;
import com.isofttz.yeriko_backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public Users registerUser(Users users) {

        final Users userData;

        final boolean doesUserExist = appUserRepository.existsByPhone(users.getPhone());

        if(doesUserExist){
            userData = appUserRepository.findByPhone(users.getPhone()).orElseThrow();
        }else{

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encryptedPassword = passwordEncoder.encode(users.getPassword());
            users.setPassword(encryptedPassword);

            userData = appUserRepository.save(users);
        }

        return userData;
    }



    @Override
    public List<Users> getAllRegisteredUser() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<Users> findByPhone(String phoneNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Users> findByUserName(String phoneNumber) {
        return appUserRepository.findByUserName(phoneNumber);
    }


}
