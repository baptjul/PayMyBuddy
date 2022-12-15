package com.PayMyBuddy.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

    @GetMapping("/home")
    public String homePage() {
        return "index.html";
    }

    @GetMapping("/transfer")
    public String transferPage() {
        return "transfer.html";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile.html";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact.html";
    }
}