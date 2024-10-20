package com.scm.repositories;

import com.scm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
//    Extra method DB related
//    Custom finder method
//    Custom Query method
}
