package com.schedule.schedule.repository;

import com.schedule.schedule.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
