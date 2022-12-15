package com.PayMyBuddy.paymybuddy.controller;

import com.PayMyBuddy.paymybuddy.model.Transfer;
import com.PayMyBuddy.paymybuddy.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping(value = "/transfer/{id}")
    public Transfer getTransferById(@PathVariable int id){
        return transferService.getTransferById(id);
    }

    @GetMapping(value = "/userTransfer/{id}")
    public List<Transfer> getUserTransfer(@PathVariable int id) {
        return transferService.getUserTransfer(id);
    }

    @PostMapping(value = "/addPayMyBuddy")
    public Transfer addTransfer(@RequestBody Transfer newPayMyBuddy) {
        return transferService.addTransfer(newPayMyBuddy);
    }
}
