package com.schedule.schedule.repository;

import com.schedule.schedule.model.Building;
import com.schedule.schedule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
