package com.course.repository;

import com.course.model.BadImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadImageUrlRepo extends JpaRepository<BadImageUrl,Integer> {
}
