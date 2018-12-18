package com.DTP.dailyTimePlaner.repos;

import com.DTP.dailyTimePlaner.domain.RateLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateLevelRepo extends JpaRepository<RateLevel,Integer> {
    RateLevel findByName(String name);
}
