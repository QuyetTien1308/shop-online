package com.example.shoponline.controller.AdminController;

import com.example.shoponline.common.Constants;
import com.example.shoponline.dto.SlideDTO;
import com.example.shoponline.entities.Slides;
import com.example.shoponline.service.impl.SlideService;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SlideController {
    @Autowired
    SlideService slideService;
    @RequestMapping(value = Constants.ADMIN_SLIDEMNG_PATH, method = RequestMethod.POST)
    public String CreateSlide( Slides slides, Model model){
        boolean flag = slideService.create(slides);
        if(flag) {
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

//    public String UpdateSlider(Slides slides, Model)
}
