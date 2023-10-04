package com.example.shoponline.service.impl;

import com.example.shoponline.entities.Slides;
import com.example.shoponline.entities.enums.SlideStatus;
import com.example.shoponline.repository.SlideRepository;
import com.example.shoponline.service.ISlideService;
import com.example.shoponline.uploadIMG.FileUploadUtil;
import com.example.shoponline.uploadIMG.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public boolean create(Slides slides){
        try {
            slides.setTitle(slides.getTitle());
            slides.setImage(slides.getImage());
            if("ACTIVE".equals(slides.getStatus())){
                slides.setStatus(SlideStatus.ACTIVE);
            }else if("INACTIVE".equals(slides.getStatus())){
                slides.setStatus(SlideStatus.INACTIVE);
            }

            slideRepository.save(slides);
        }catch (Exception e){
            return false;
        }
        return true;
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
