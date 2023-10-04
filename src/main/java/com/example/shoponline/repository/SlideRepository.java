package com.example.shoponline.repository;

import com.example.shoponline.entities.Slides;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlideRepository extends JpaRepository<Slides, String> {
}
