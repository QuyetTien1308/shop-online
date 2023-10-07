package com.example.shoponline.repository;

import com.example.shoponline.entities.Slides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SlideRepository extends JpaRepository<Slides, String> {
//    List<Slides> findAllByNameOrIdContains(String title, String id);

    List<Slides> findAllByTitleOrIdContains(String title, String id);
}
