package com.PayMyBuddy.paymybuddy.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transfer")
    private int id_transfer;

    @Column(name="amount")
    private double amount;

    @Column(name="type")
    private String type;

    @Column(name="transfer_date")
    private Date transfer_date;

    @Column(name="iban")
    private String iban;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payMyBuddyAccount", referencedColumnName="id_user", nullable = false)
    private User payMyBuddyAccount;

    public int getId_transfer() {
        return id_transfer;
    }

    public void setId_transfer(int id_transfer) {
        this.id_transfer = id_transfer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTransfer_date() {
        return transfer_date;
    }

    public void setTransfer_date(Date transfer_date) {
        this.transfer_date = transfer_date;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public User getPayMyBuddyAccount() {
        return payMyBuddyAccount;
    }

    public void setPayMyBuddyAccount(User payMyBuddyAccount) {
        this.payMyBuddyAccount = payMyBuddyAccount;
    }
}
