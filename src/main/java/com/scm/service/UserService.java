package com.scm.service;

import com.scm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    Boolean isUserExist(String userid);
    Boolean isUserExistEmail(String email);
    List<User> getAllUsers();
}
