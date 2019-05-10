package com.course.repository;

import com.course.model.loginCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface loginCaseRepo extends JpaRepository<loginCase,Integer> {
}
