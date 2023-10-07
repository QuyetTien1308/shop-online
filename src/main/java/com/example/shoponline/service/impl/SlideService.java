package com.example.shoponline.service.impl;

import com.example.shoponline.entities.Slides;
import com.example.shoponline.entities.enums.SlideStatus;
import com.example.shoponline.repository.SlideRepository;
import com.example.shoponline.service.ISlideService;
import com.example.shoponline.uploadIMG.FileUploadUtil;
import com.example.shoponline.uploadIMG.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SlideService implements ISlideService {
    @Autowired
    SlideRepository slideRepository;
    @Override
    public List<Slides> loadAll(){
        return slideRepository.findAll();
    }
    @Override
    public Page<Slides> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.slideRepository.findAll(pageable);
    }
    //Lọc theo slide
    public Page<Slides> FilterAndPagenated(String search, int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        PageRequest pageRequest = PageRequest.of(pageNo -1, pageSize, sort);
        List<Slides> slides= new ArrayList<>();
        if (search == ""){
            slides = slideRepository.findAll();
        }else {
            slides = slideRepository.findAllByTitleOrIdContains(search, search);
        }
        Page<Slides> result = (Page<Slides>) Utils.toPage(slides, pageRequest);
        return result;
    }

    @Override
    public Slides create(Slides slides, MultipartFile files){
        slides.setTitle(slides.getTitle());
//            slides.setImage(slides.getImage());
        if(files != null){
            String fileType = Utils.getFileType(files.getContentType());
            if(!"".equals(fileType)){
                try {
                    String fileName = "slide-" + new Date().getTime()+ fileType;
                    FileUploadUtil.saveFile("", fileName,files);
                    slides.setImage(fileName);
                    return slideRepository.save(slides);
                }catch (Exception e){

                }
            }
        }
        if("ACTIVE".equals(slides.getStatus())){
            slides.setStatus(SlideStatus.ACTIVE);
        }else if("INACTIVE".equals(slides.getStatus())){
            slides.setStatus(SlideStatus.INACTIVE);
        }

        return slideRepository.save(slides);
    }

    public boolean updateSlide(Slides slides){
        try {
            Slides slide = slideRepository.getById(slides.getId());
            if(slide != null){
                slide.setTitle(slides.getTitle());
                if("ACTIVE".equals(slides.getStatus())){
                    slides.setStatus(SlideStatus.ACTIVE);
                }else if("INACTIVE".equals(slides.getStatus())){
                    slides.setStatus(SlideStatus.INACTIVE);
                }
                slide.setImage(slides.getImage());
                slideRepository.save(slide);
                return true;

            }
        }catch (Exception e){

        }
        return false;
    }
    @Override
    public boolean uploadListImage(String slideId, MultipartFile[] files){
        try{
            Slides slides = slideRepository.getById(slideId);
            if(slides == null){
                throw new Exception();
            }
            for (MultipartFile file : files){
                if(file == null){
                    continue;
                }
                String fileType = Utils.getFileType(file.getContentType());
                if("".equals(fileType)){
                    throw new Exception();
                }
                String fileName = slideId + "-" + new Date().getTime() + fileType;
                FileUploadUtil.saveFile("", fileName,file);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
