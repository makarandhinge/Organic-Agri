package com.scm.service.Impl;

import com.scm.entity.User;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger logger  = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        //user passwordEncoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setEnabled(user.getEnabled());
        user2.setEmailVerified(user.getEmailVerified());
        user2.setPhoneVerified(user.getPhoneVerified());
        user2.setProviderId(user.getProviderId());
        user2.setProvider(user.getProvider());

        User save = userRepo.save(user2);
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        userRepo.delete(user2);
    }

    @Override
    public Boolean isUserExist(String userid) {
        User user2 = userRepo.findById(userid).orElse(null);
        return user2 != null ? true : false;

    }

    @Override
    public Boolean isUserExistEmail(String email) {
      User user =   userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
