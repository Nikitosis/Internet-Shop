package com.shop.controllers;

import com.shop.dao.CommodityPaginator;
import com.shop.entities.*;
import com.shop.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    @GetMapping("/commodities/search")
//    public String showCommoditiesFilter(@RequestParam("startPrice") Double startPrice,
//                                        @RequestParam("endPrice") Double endPrice,
//                                        @RequestParam("searchName") String searchName,
//                                        Model model){
//        System.out.println(startPrice+" "+endPrice+" "+searchName);
//        model.addAttribute("commodities",service.getCommoditiesWithFilter(startPrice,endPrice,searchName));
//        return "commodities";
//    }

//    @GetMapping("/commodities")
//    public String showCommodities(Model model){
//        model.addAttribute("commodities",service.getCommodities());
//        return "commodities";
//    }

    @GetMapping("/commodities")
    public String showCommodities(@RequestParam(value="tags",required = false) List<String> tags,
                                  @RequestParam(value="minPrice",required = false) Integer minPrice,
                                  @RequestParam(value="maxPrice",required = false) Integer maxPrice,
                                  @RequestParam(value="namePattern",required = false) String namePattern,
                                  @RequestParam(value = "curPage",required = false,defaultValue = "1") Integer curPage,
                                  Model model) {
        CommodityPaginator paginator;
        if(tags!=null)
            paginator=new CommodityPaginator(service.getCommoditiesByTagsNames(tags));
        else
            paginator=new CommodityPaginator(service.getCommodities());


        if(minPrice!=null){
            paginator.filterMinPrice(minPrice);
        }
        if(maxPrice!=null){
            paginator.filterMaxPrice(maxPrice);
        }
        if(namePattern!=null){
            paginator.filterNamePattern(namePattern);
        }

        model.addAttribute("curPage",curPage);
        model.addAttribute("paginator",paginator);

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
    public String deleteCommodity(@RequestParam("id") int id){
        service.deleteCommodityById(id);
        return "redirect:/commodities";
    }

    @GetMapping("commodities/{id}")
    public String showCommodityInfo(@PathVariable("id") int id,Model model){
        /*Commodity commodity=service.getCommodityById(id);
        User user=service.getUser("user");
        Comment comment=new Comment("sadsa",new Date(1000),user,commodity);
        service.addComment(comment);
        commodity.addComment(comment);
        user.addComment(comment);*/
        model.addAttribute("commodity",service.getCommodityById(id));
        return "commodity_page";
    }

    @PostMapping("commodities/addComment")
    public String addComment(@RequestParam("content") String content,
                             @RequestParam("commodity_id") int commodity_id){

        service.addComment(content,service.getCommodityById(commodity_id),(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "redirect:/commodities/"+commodity_id;
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
    public String registerUser(@ModelAttribute("user") User user){
        System.out.println("registr");

        user.addUserRole(new UserRole("ROLE_USER"));
        service.registerUser(user);
        return "redirect:/login";
    }

}
