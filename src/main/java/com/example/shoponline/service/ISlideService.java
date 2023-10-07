package com.example.shoponline.service;

import com.example.shoponline.dto.SlideDTO;
import com.example.shoponline.entities.Slides;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISlideService {
    List<Slides> loadAll();



//    boolean create(Slides slides);

    Page<Slides> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    Slides create(Slides slides, MultipartFile files);

    boolean uploadListImage(String slideId, MultipartFile[] files);
}
