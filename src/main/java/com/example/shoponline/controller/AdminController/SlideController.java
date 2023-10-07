package com.example.shoponline.controller.AdminController;

import com.example.shoponline.common.Constants;
import com.example.shoponline.common.Validate;
import com.example.shoponline.dto.SlideDTO;
import com.example.shoponline.entities.Slides;
import com.example.shoponline.service.impl.SlideService;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class SlideController {
    @Autowired
    SlideService slideService;

    public String CreateSlide( Slides slides, Model model,
                               @RequestParam(name = "files", required = true) MultipartFile files){
        Slides flag = slideService.create(slides, files);
        if(flag != null) {
            model.addAttribute("flag", flag);
            model.addAttribute("msg", "Add Success");
        }else {
            model.addAttribute("flag", flag);
            model.addAttribute("msg", "Add Not Success");
        }
        model.addAttribute("slides", slideService.loadAll());
        return getSlidePage(model);
    }
    @RequestMapping(Constants.ADMIN_SLIDEMNG_PATH)
    public String getSlidePage(Model model) {
        if(model.getAttribute("slide")==null) {
            model.addAttribute("slide",slideService.loadAll());
        }
        //anchor model in create new brand
        model.addAttribute("newslide",new Slides());
        return Constants.ADMIN_SLIDE_VIEW;
    }
    public String getFormSlide(Model model){
        return Constants.ADMIN_SLIDE_VIEW;
    }
    @RequestMapping(Constants.ADMIN_SLIDEMNG_PATH + "/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("softField") String sortField,
                                @RequestParam("sortDir") String sortDir, Model model){
        int pageSize = 4;
        Page<Slides> page;
        boolean flag = model.getAttribute("archo") == null;
        if(flag){
            page = slideService.findPaginated(pageNo, pageSize, sortField, sortDir);
        }else {
            String search = model.getAttribute("search").toString();
            page = slideService.FilterAndPagenated(search, pageNo, pageSize, sortField, sortDir);

            if(page.getContent().size() == 0){
                model.addAttribute("flag", false);
                model.addAttribute("msg", "Find not" + search);
            }else {
                model.addAttribute("flag", true);
                model.addAttribute("msg", "Find" + page.getTotalElements() + "result by" + search);
            }
        }
        List<Slides> slidesList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("slides", slidesList);
        return Constants.ADMIN_SLIDE_VIEW;
    }

    @RequestMapping(value = Constants.ADMIN_SLIDEMNG_PATH, method = RequestMethod.POST)
    public String UpdateSlider(Slides slides, Model model,
                               @RequestParam(name = "files", required = true) MultipartFile[] files){
        if(Validate.checkStringNotEmptyOrNull(slides.getId())){
            if (files != null){
                slideService.uploadListImage(slides.getId(), files);
            }
            boolean flag = slideService.updateSlide(slides);
            if(flag) {
                model.addAttribute("flag", true);
                model.addAttribute("msg", "Update Success");
                return findPaginated(1, "name", "asc", model);

            }else {
                model.addAttribute("flag", false);
                model.addAttribute("msg", "Not Update Success");
                return getFormSlide(model);
            }

        }else {
            if (files != null && files.length>0){
                slides = slideService.create(slides, files[0]);
                files[0]= null;
            }else {
                slides = slideService.create(slides,null);
            }
            if(slides != null){
                if(files != null && files.length > 1){
                    slideService.uploadListImage(slides.getId(), files);
                }
                model.addAttribute("flag", true);
                model.addAttribute("msg", "Add Success");
                return findPaginated(1, "name", "asc", model);

            }else {
                model.addAttribute("flag", false);
                model.addAttribute("msg", "Add Success");
                return getFormSlide(model);
            }

        }

    }
}
