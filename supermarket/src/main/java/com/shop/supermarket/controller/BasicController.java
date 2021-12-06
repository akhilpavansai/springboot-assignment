package com.shop.supermarket.controller;

import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.User;
import com.shop.supermarket.entity.Users;
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
public class BasicController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loginPage")
    public String login()
    {
        return "login-page";
    }

    @GetMapping("/successHandler")
    public String successHandler(Principal loggedUser, Model theModel)
    {
        theModel.addAttribute("loggedUser",loggedUser);
        return "home";
    }

    @GetMapping("/access-denied")
    public String failHandler(Principal loggedUser, Model theModel)
    {
        return "access-denied";
    }

    @GetMapping("/registerPage")
    public String registerPage(Principal loggedUser, Model theModel)
    {
        theModel.addAttribute("user",new Users());
        return "register";
    }

    @RequestMapping(path = "/saveNewUser",method = RequestMethod.POST)
    public String registerPage(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult, Model model, Principal loggedUser)
    {
        if (usersService.getUser(user.getUsername())!=null)
        {
            return "duplicate-user";
        }
        if(bindingResult.hasErrors())
        {
            model.addAttribute("user",user);
            return "redirect:/registerPage";
        }
        user.setEnabled((short) 1);
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usersService.registerUser(user);
        model.addAttribute("finalUser",user);
        return "redirect:/loginPage?user=True";
    }

    @GetMapping("/role")
    public String role()
    {
        return "role";
    }
    @RequestMapping(path = "/saveRole",method = RequestMethod.POST)
    public String saveRole( @RequestParam("authority") Roles role,@RequestParam("username") String username, Model model, Principal loggedUser)
    {
        if(usersService.getUser(username).getRoles().size()>=1)
        {
            return "prompt-page";
        }
        usersService.saveRole(username,role);
        return "redirect:/loginPage";
    }

}
