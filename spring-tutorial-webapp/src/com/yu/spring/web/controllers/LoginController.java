package com.yu.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yu.spring.web.dao.User;
import com.yu.spring.web.service.UsersService;

@Controller
public class LoginController {

    private UsersService usersService;
    
    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }
    
    @RequestMapping("/loggedout")
    public String showLoggedOut() {
        return "loggedout";
    }
    
    @RequestMapping("/admin")
    public String showAdmin(Model model) {
        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users); 
        return "admin";
    }
    
    @RequestMapping("/denied")
    public String showDenied() {
        return "denied";
    }
    
    @RequestMapping("/newaccount")
    public String showNewAccount(Model model) {
        model.addAttribute("user", new User());
        return "newaccount";
    }
    
    @RequestMapping(value="/createaccount", method=RequestMethod.POST)
    public String createAccount(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "newaccount";
        }
        
        user.setAuthority("user");
        user.setEnabled(true);
        
        if (usersService.exists(user.getUsername())) { // Better to use this fasion to check duplicates instead of try catch block.
            result.rejectValue("username", "DuplicateKey.user.username", "This username already exists");
            return "newaccount";
        }
        
        usersService.create(user);
        
        return "accountcreated";
    }

}
