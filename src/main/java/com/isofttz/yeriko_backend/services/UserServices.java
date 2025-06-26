package com.isofttz.yeriko_backend.services;


import com.isofttz.yeriko_backend.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    Users registerUser(Users users);

    List<Users> getAllRegisteredUser();

    Optional<Users> findByPhone(String phoneNumber);

    Optional<Users>  findByUserName(String phoneNumber);

}
