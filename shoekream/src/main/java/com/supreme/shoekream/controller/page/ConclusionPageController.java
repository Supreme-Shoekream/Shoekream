package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.model.entity.Conclusion;
import com.supreme.shoekream.repository.ConclusionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
//@RequestMapping("admin/conclusion")
public class ConclusionPageController {

//    private final ConclusionRepository conclusionRepository;
//    @GetMapping("")
//    public String conclusionList(ModelMap map) {
//        List<Conclusion> list = conclusionRepository.findAll();
//        map.addAttribute("list", list);
//        System.out.println(list);
//        return "adminpage/conclusion";
//    }



}
