package com.schedule.schedule.service;

import com.schedule.schedule.model.ClassTime;
import com.schedule.schedule.repository.ClassTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTimeService {

    @Autowired
    private ClassTimeRepository classTimeRepository;

    public List<ClassTime> getAllClassTimes() {
        return classTimeRepository.findAll();
    }
}
