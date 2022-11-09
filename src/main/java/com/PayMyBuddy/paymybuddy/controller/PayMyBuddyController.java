package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.PayMyBuddy;
import com.PayMyBuddy.paymybuddy.service.PayMyBuddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PayMyBuddyController {

    @Autowired
    private PayMyBuddyService payMyBuddyService;

    @GetMapping(value = "/paymybuddy")
    public List<PayMyBuddy> getPayMyBuddy(){
        return payMyBuddyService.getPayMyBuddy();
    }

    @PostMapping(value = "/addPayMyBuddy")
    public PayMyBuddy addPayMyBuddy(@RequestBody PayMyBuddy newPayMyBuddy){
        return payMyBuddyService.addPayMyBuddy(newPayMyBuddy);
    }
}
