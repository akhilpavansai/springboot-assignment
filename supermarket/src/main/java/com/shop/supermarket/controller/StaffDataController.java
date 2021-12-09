package com.shop.supermarket.controller;


import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.dto.ItemsDTO;
import com.shop.supermarket.dto.UsersDTO;
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

    @Autowired
    private UsersService usersServiceObject;

    @Autowired
    private ItemsService itemsServiceObject;

    @Autowired
    private ItemsConverter itemsConverterObject;

    @Autowired
    private UsersConverter usersConverterObject;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoderObject;

    static final String SUCCESS_HANDLER = "redirect:/successHandler";

    @GetMapping("/stockList")
    public String stockList(Model theModel)
    {
        theModel.addAttribute("allItems", itemsConverterObject.entityToDto(itemsServiceObject.getAllItemsList()));
        return "stock-list";
    }


    @PostMapping("/deleteItem")
    public ModelAndView deleteItem(@RequestParam int id)
    {
        itemsServiceObject.deleteItemById(id);
        return new ModelAndView("redirect:/staff/stockList");
    }



    @GetMapping("/updateStaffPage")
    public String updatePage(Model model,Principal loggedUser)
    {
        UsersDTO tempUser = usersConverterObject.entityToDto(usersServiceObject.findByUsername(loggedUser.getName()));
        model.addAttribute("user",tempUser);
        return "update-staff-page";
    }


    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") UsersDTO user, BindingResult bindingResult, Model model,Principal loggedUser)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("error",bindingResult.getFieldError("id"));
            model.addAttribute("user",user);
            return "update-staff-page";
        }
        Users tempUser = usersConverterObject.dtoToEntity(user);
        String encodedPassword = bCryptPasswordEncoderObject.encode(tempUser.getPassword());
        usersServiceObject.updateUser(loggedUser.getName(),encodedPassword,tempUser.getEmail(),tempUser.getPhoneNumber(),tempUser.getAddress());
        return SUCCESS_HANDLER;
    }


    @GetMapping("/addItem")
    public String addNewItem()
    {
        return "item-form";
    }


    @PostMapping("/saveItem")
    public String saveNewItem(@ModelAttribute("item")ItemsDTO itemsDTO)
    {
        itemsServiceObject.addNewItem(itemsConverterObject.dtoToEntity(itemsDTO));
        return SUCCESS_HANDLER;
    }

    @PostMapping("/updateItem")
    public String updateItem(@RequestParam("itemId") int itemId, Model model)
    {
        model.addAttribute("item",itemsConverterObject.entityToDto(itemsServiceObject.getItemById(itemId)));
        return "item-update";
    }

    @PostMapping("/updateItemsData")
    public String updateItemsData(@ModelAttribute("item")ItemsDTO itemsDTO)
    {
        itemsServiceObject.updateItem(itemsConverterObject.dtoToEntity(itemsDTO));
        return SUCCESS_HANDLER;
    }
}
