package com.htpteam.vkserver.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String Main(Model model)
    {
        model.addAttribute("title","Главная страница");
        return "main";
    }

}
