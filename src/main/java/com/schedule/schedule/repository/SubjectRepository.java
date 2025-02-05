package com.schedule.schedule.repository;

import com.schedule.schedule.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Page<Subject> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Subject> findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(String name, String description, Pageable pageable);

}


