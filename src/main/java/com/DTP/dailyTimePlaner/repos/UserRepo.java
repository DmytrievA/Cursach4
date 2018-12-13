package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserType,Integer> {
    @Override
    Optional<UserType> findById(Integer id);

    UserType findByEmail(String email);
}
