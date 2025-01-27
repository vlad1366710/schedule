package com.schedule.schedule.repository;

import com.schedule.schedule.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}
