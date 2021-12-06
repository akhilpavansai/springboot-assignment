package com.shop.supermarket.controller;



import com.shop.supermarket.entity.TempData;
import com.shop.supermarket.entity.User;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.service.ItemsService;
import com.shop.supermarket.service.StaffService;
import com.shop.supermarket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/staff")
public class StaffDataController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/stockList")
    public String ordersList(Principal loggedUser,Model theModel)
    {
        theModel.addAttribute("loggedUser",loggedUser.getName());
        theModel.addAttribute("allItems",itemsService.getAllItemsList());
        return "stock-list";
    }


    @PostMapping("/deleteItem")
    public ModelAndView deleteItem(@RequestParam int id,Principal loggedUser)
    {
        staffService.deleteItem(id);
        return new ModelAndView("redirect:/staff/stockList");
    }



    @GetMapping("/updateStaffPage")
    public String updatePage(Model model,Principal loggedUser)
    {
        User tempUser = new User();
        Users gettingUser = usersService.getUser(loggedUser.getName());
        tempUser.setPassword(gettingUser.getPassword());
        System.out.println("password before populating : "+tempUser.getPassword());
        tempUser.setEmail(gettingUser.getEmail());
        tempUser.setPhoneNumber(gettingUser.getPhoneNumber());
        tempUser.setAddress(gettingUser.getAddress());
        model.addAttribute("user",tempUser);
        return "updatestaff-page";
    }


    @RequestMapping(path = "/saveUser",method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model,Principal loggedUser)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("error",bindingResult.getFieldError("id"));
            model.addAttribute("user",user);
            return "updatestaff-page";
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        usersService.updateData(loggedUser.getName(),encodedPassword,user.getEmail(),user.getPhoneNumber(),user.getAddress());
        return "redirect:/successHandler";
    }


    @GetMapping("/addItem")
    public String addNewItem()
    {
        return "item-form";
    }

    @PostMapping("/saveItem")
    public String saveNewItem(@RequestParam("itemName") String itemName,@RequestParam("cost") String cost,@RequestParam("company") String company)
    {
        staffService.addNewItem(itemName,Integer.parseInt(cost),company);
        return "redirect:/successHandler";
    }

}
