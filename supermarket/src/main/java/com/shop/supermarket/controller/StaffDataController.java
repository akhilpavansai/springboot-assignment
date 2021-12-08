package com.shop.supermarket.controller;


import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.dto.ItemsDTO;
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
@RequestMapping("/staff")
public class StaffDataController {


    public static final ItemsDTO itemsDTO = new ItemsDTO();

    @Autowired
    private UsersService usersService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private ItemsConverter itemsConverter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/stockList")
    public String stockList(Model theModel)
    {
        theModel.addAttribute("allItems",itemsConverter.entityToDto(itemsService.getAllItemsList()));
        return "stock-list";
    }


    @PostMapping("/deleteItem")
    public ModelAndView deleteItem(@RequestParam int id,Principal loggedUser)
    {
        itemsService.deleteItemById(id);
        return new ModelAndView("redirect:/staff/stockList");
    }



    @GetMapping("/updateStaffPage")
    public String updatePage(Model model,Principal loggedUser)
    {
        User tempUser = new User();
        Users gettingUser = usersService.findByUsername(loggedUser.getName());
        tempUser.setPassword(gettingUser.getPassword());
        tempUser.setEmail(gettingUser.getEmail());
        tempUser.setPhoneNumber(gettingUser.getPhoneNumber());
        tempUser.setAddress(gettingUser.getAddress());
        model.addAttribute("user",tempUser);
        return "update-staff-page";
    }


    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model,Principal loggedUser)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("error",bindingResult.getFieldError("id"));
            model.addAttribute("user",user);
            return "update-staff-page";
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        usersService.updateData(loggedUser.getName(),encodedPassword,user.getEmail(),user.getPhoneNumber(),user.getAddress());
        return "redirect:/successHandler";
    }


    @GetMapping("/addItem")
    public String addNewItem(Model model)
    {
        model.addAttribute("item",itemsDTO);
        return "item-form";
    }


    @PostMapping("/saveItem")
    public String saveNewItem(@ModelAttribute("item")ItemsDTO itemsDTO)
    {
        itemsService.addNewItem(itemsConverter.dtoToEntity(itemsDTO));
        return "redirect:/successHandler";
    }

}
