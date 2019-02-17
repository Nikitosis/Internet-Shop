package com.shop.controllers;

import com.shop.entities.User;
import com.shop.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    MainService service;

    @GetMapping("/")
    public String getHome(){
        System.out.println("Controllers");
        return "home";
    }

    @GetMapping("/userInfo/{id}")
    public String showUserInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("user",service.getUser(id));

        return "ShowUserInfo";
    }

    @GetMapping("/commodities")
    public String showCommodities(Model model){
        model.addAttribute("commodities",service.getCommodities());
        return "showCommodities";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user){
        service.loginUser(user);
        return "home";
    }
}
