package com.course.repository;

import com.course.model.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageUrlRepo extends JpaRepository<ImageUrl,Integer> {
}
