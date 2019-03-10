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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/commodities/search")
    public String showCommoditiesFilter(@RequestParam("startPrice") Double startPrice,
                                        @RequestParam("endPrice") Double endPrice,
                                        @RequestParam("searchName") String searchName,
                                        Model model){
        System.out.println(startPrice+" "+endPrice+" "+searchName);
        model.addAttribute("commodities",service.getCommoditiesWithFilter(startPrice,endPrice,searchName));
        return "commodities";
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
    public String addCommodity(@RequestParam("commodity") Commodity commodity){
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
    public String modifyCommodity(@RequestParam("commodity") Commodity commodity){
        service.modifyCommodity(commodity);
        return "redirect:/commodities";
    }

    @PostMapping("/commodities/deleteCommodity")
    public String deleteCommodity(@RequestParam("id") int id){
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
    public String addCommodityToBasket(@RequestParam("commodity_id") int id, HttpSession session){
        service.addCommodityToBasket(id,session);
        return "redirect:/commodities";
    }

    @GetMapping("/basket")
    public String showUserBasket(HttpSession session,Model model){
        model.addAttribute("commoditiesInBasket",service.getCommoditiesFromBasket(session));
        return "basket";
    }

    @PostMapping("/basket/removeCommodity")
    public String removeCommodityFromBasket(@RequestParam("id") int commodityId,HttpSession session){
        service.removeCommodityFromBasket(commodityId,session);
        return "redirect:/basket";
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
    public String registerUser(@RequestParam("user") User user){
        System.out.println("registr");

        user.addUserRole(new UserRole("ROLE_USER"));
        service.registerUser(user);
        return "redirect:/login";
    }

}
