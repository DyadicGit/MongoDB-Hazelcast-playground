package com.dyadic.playground.mongocrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getInventory(Model model) {

        model.addAttribute("msg", "Home page");
        return "index";
    }


}
