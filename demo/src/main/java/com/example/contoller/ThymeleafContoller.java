package com.example.contoller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafContoller {

    @GetMapping("/index")
    public String   index(Model model){

        model.addAttribute("key", "123456");
        return "index";
    }
}
