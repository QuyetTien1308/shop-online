package com.example.shoponline.service;

import com.example.shoponline.dto.SlideDTO;
import com.example.shoponline.entities.Slides;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISlideService {
    List<Slides> loadAll();



    boolean create(Slides slides);

    boolean uploadListImage(String slideId, MultipartFile[] files);
}
