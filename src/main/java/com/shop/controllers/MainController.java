package com.shop.controllers;

import com.shop.entities.User;
import com.shop.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    MainService service;

    @GetMapping("/")
    public String getHome(){
        System.out.println("Controllers");
        return "home";
    }

    @GetMapping("/userInfo/{username}")
    public String showUserInfo(@PathVariable("username") String username, Model model){
        model.addAttribute("user",service.getUser(username));
        return "userInfo";
    }

    @GetMapping("/commodities")
    public String showCommodities(Model model){
        model.addAttribute("commodities",service.getCommodities());
        return "commodities";
    }

    @GetMapping("/userOrders")
    public String showUserOrders(Model model){
        UserDetails principal= (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orders",service.getUserOrders(principal.getUsername()));
        return "userOrders";
    }

}
