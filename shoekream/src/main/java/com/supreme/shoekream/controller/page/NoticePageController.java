package com.supreme.shoekream.controller.page;

import com.supreme.shoekream.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("") // http://localhost:8889/
@RequiredArgsConstructor
public class NoticePageController {
    private final NoticeService noticeService;
    @GetMapping(path="faq")   //http://localhost:8889/faq
    public ModelAndView faq(){
        return new ModelAndView("/notice/faq");
    }

    @GetMapping(path="auth_policy")   //http://localhost:8889/auth_policy
    public ModelAndView auth_policy(){
        return new ModelAndView("/notice/auth_policy");
    }

    @GetMapping(path="notice")   //http://localhost:8889/notice
    public String notice(ModelMap map,@PageableDefault(size = 20, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable){
        map.addAttribute("notices",noticeService.list(pageable));
        return"/notice/notice";
    }

//    @GetMapping(path="notice/{Idx}")
//    public String noticeDetail(@PathVariable Long idx, ModelMap map){
//
//        return("/notice/notice_view");
//    }


    @GetMapping(path="notice/{idx}")
    public String noticeDetail(ModelMap map,@PathVariable Long idx){
        map.addAttribute("notice",noticeService.read(idx));
        return "/notice/notice_view";
    }
}
