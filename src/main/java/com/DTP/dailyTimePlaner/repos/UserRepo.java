package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.XML.org.itroi.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserType,String> {
    @Override
    Optional<UserType> findById(String id);

    List<UserType> findByEmailIn(List<String> emails);

    UserType findByEmail(String email);

    List<UserType> findByEmailNotIn(List<String> emails);
}
