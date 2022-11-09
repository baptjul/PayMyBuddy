package com.PayMyBuddy.paymybuddy.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transaction")
    private int idUser;

    @Column(name="transaction ")
    private int transaction;

    @Column(name="transaction_date ")
    private Date transaction_date;

    @Column(name="user_target")
    private int user_target;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PayMyBuddy payMyBuddy;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public int getUser_target() {
        return user_target;
    }

    public void setUser_target(int user_target) {
        this.user_target = user_target;
    }

    public PayMyBuddy getPayMyBuddy() {
        return payMyBuddy;
    }

    public void setPayMyBuddy(PayMyBuddy payMyBuddy) {
        this.payMyBuddy = payMyBuddy;
    }
}
