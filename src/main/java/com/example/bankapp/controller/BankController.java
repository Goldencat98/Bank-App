package com.example.bankapp.controller;


import com.example.bankapp.model.Account;
import com.example.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUsername(username);
        model.addAttribute("account", account);
        return "dashboard" ;
    }

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "register" ;
    }

    @PostMapping("/register")
    public String registerAccount(@RequestParam String username, @RequestParam String password, Model model){
        try {
            accountService.registerAccount(username, password);
            return "redirect:/login";
        } catch (RuntimeException e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String


















}
