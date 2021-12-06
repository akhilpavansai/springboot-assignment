package com.shop.supermarket.controller;


import com.shop.supermarket.entity.User;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.service.ItemsService;
import com.shop.supermarket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersDataController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/ordersList")
    public String ordersList(Principal loggedUser,Model theModel)
    {
        theModel.addAttribute("loggedUser",loggedUser.getName());
        theModel.addAttribute("orderedItems",usersService.getItemsList(loggedUser.getName()));
        return "orders-list";
    }

    @GetMapping("/order")
    public String order(Model theModel)
    {
        theModel.addAttribute("items",itemsService.getAllItemsList());
        return "order-item";
    }

    @PostMapping("/orderItem")
    public ModelAndView orderItem(@RequestParam String itemId, Principal loggedUser, Model theModel)
    {
        usersService.addItem(loggedUser.getName(),Integer.parseInt(itemId));
        return new ModelAndView("redirect:/successHandler");
    }
    @PostMapping("/deleteOrder")
    public ModelAndView deleteItem(@RequestParam int id,Principal loggedUser)
    {
        usersService.deleteItem(loggedUser.getName(),id);
        return new ModelAndView("redirect:/user/ordersList");
    }



    @GetMapping("/updatePage")
    public String updatePage(Model model,Principal loggedUser)
    {
        User tempUser = new User();
        Users gettingUser = usersService.getUser(loggedUser.getName());
        tempUser.setEmail(gettingUser.getEmail());
        tempUser.setPhoneNumber(gettingUser.getPhoneNumber());
        tempUser.setAddress(gettingUser.getAddress());
        model.addAttribute("loggedUser",loggedUser.getName());
        model.addAttribute("user",tempUser);
        return "update-page";
    }


    @RequestMapping(path = "/saveUser",method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model,Principal loggedUser)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("loggedUser",loggedUser.getName());
            model.addAttribute("user",user);
            return "update-page";
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        usersService.updateData(loggedUser.getName(),encodedPassword,user.getEmail(),user.getPhoneNumber(),user.getAddress());
        return "redirect:/successHandler";
    }

}
