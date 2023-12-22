package com.example.user.service.services;

import com.example.user.service.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

   List<User> getAllUser();

   User getUser(String userId);

}
