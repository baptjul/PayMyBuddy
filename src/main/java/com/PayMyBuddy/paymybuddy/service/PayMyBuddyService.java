package com.PayMyBuddy.paymybuddy.service;
import com.PayMyBuddy.paymybuddy.model.PayMyBuddy;
import com.PayMyBuddy.paymybuddy.repository.PayMyBuddyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayMyBuddyService {

    @Autowired
    private PayMyBuddyRepository payMyBuddyRepository;

    public List<PayMyBuddy> getPayMyBuddy() {
        return payMyBuddyRepository.findAll();
    }

    public PayMyBuddy addPayMyBuddy(PayMyBuddy payMyBuddy) {
        return payMyBuddyRepository.save(payMyBuddy);
    }
}
