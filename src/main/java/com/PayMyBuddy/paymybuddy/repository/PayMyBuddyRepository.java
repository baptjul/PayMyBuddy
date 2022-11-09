package com.PayMyBuddy.paymybuddy.repository;
import com.PayMyBuddy.paymybuddy.model.PayMyBuddy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMyBuddyRepository extends JpaRepository<PayMyBuddy, Integer> {

}