package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface OurUserRepo extends JpaRepository<Users, Integer> {
        Optional<Users> findByEmail(String email);
        Optional<Users> findByToken(String token);
        void deleteById(int userId);


    }
