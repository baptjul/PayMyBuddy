package com.PayMyBuddy.paymybuddy.service;
import com.PayMyBuddy.paymybuddy.model.Transfer;
import com.PayMyBuddy.paymybuddy.model.User;
import com.PayMyBuddy.paymybuddy.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    UserService userService;
    @Autowired
    TransferRepository transferRepository;

    public Transfer getTransferById(int transferId) {
        return transferRepository.findById(transferId).orElse(null);
    }

    public List<Transfer> getUserTransfer(int userId) {
        User userTarget = userService.getUserById(userId);
        return  userTarget.getTransfer();
    }

    public Transfer addTransfer(Transfer newTransfer) {
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        userService.updateBalanceUser(newTransfer.getPayMyBuddyAccount().getIdUser(), newTransfer.getAmount(), newTransfer.getType());

        newTransfer.setTransfer_date(date);
        return transferRepository.save(newTransfer);
    }
}
