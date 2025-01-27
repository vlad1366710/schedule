package com.schedule.schedule.repository;

import com.schedule.schedule.model.ClassTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassTimeRepository extends JpaRepository<ClassTime, Long> {
}
