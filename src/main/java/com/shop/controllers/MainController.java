package com.shop.controllers;

import com.shop.entities.Commodity;
import com.shop.entities.User;
import com.shop.entities.UserRole;
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

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

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

    @GetMapping("/commodities/addCommodity")
    public String showAddCommodity(){
        return "addCommodity";
    }
    @PostMapping("/commodities/addCommodity")
    public String addCommodity(@ModelAttribute("commodity") Commodity commodity){
        service.addCommodityToDb(commodity);
        return "redirect:/commodities";
    }

    @GetMapping("/commodities/modifyCommodity/{id}")
    public String showModifyCommodity(@PathVariable("id") int commodity_id, Model model){
        Commodity commodity=service.getCommodityById(commodity_id);
        model.addAttribute("commodity",commodity);
        return "modifyCommodity";
    }

    @PostMapping("/commodities/modifyCommodity")
    public String modifyCommodity(@ModelAttribute("commodity") Commodity commodity){
        service.modifyCommodity(commodity);
        return "redirect:/commodities";
    }

    @PostMapping("/commodities/deleteCommodity")
    public String deleteCommodity(@ModelAttribute("id") int id){
        service.deleteCommodityById(id);
        return "redirect:/commodities";
    }

    @GetMapping("/userOrders")
    public String showUserOrders(Model model){
        UserDetails principal= (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orders",service.getUserOrders(principal.getUsername()));
        return "userOrders";
    }

    @PostMapping("/addToBasket")
    public String addCommodityToBasket(@ModelAttribute("commodity_id") int id, HttpSession session){
        service.addCommodityToBasket(id,session);
        return "redirect:/commodities";
    }

    @GetMapping("/basket")
    public String showUserBasket(HttpSession session,Model model){
        model.addAttribute("commoditiesInBasket",service.getCommoditiesFromBasket(session));
        return "basket";
    }

    @PostMapping("/basket/confirmBuy")
    public String confirmBasketBuy(HttpSession session){
        service.confirmBasketBuy(session);
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user){
        System.out.println("registr");

        user.addUserRole(new UserRole("ROLE_USER"));
        service.registerUser(user);
        return "redirect:/login";
    }

}
