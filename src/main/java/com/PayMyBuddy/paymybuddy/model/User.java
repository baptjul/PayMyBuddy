package com.PayMyBuddy.paymybuddy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private int id_user;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="balance")
    private double balance;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="user_name")
    private String user_name;

    @Column(name="creation_date")
    private Date creation_date;

    @OneToMany(orphanRemoval = true, mappedBy="userTransmitter")
    List<Transaction> transmittedTransactions = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy="userReceiver")
    List<Transaction> receivedTransactions = new ArrayList<>();

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "first_userid", referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "second_userid", referencedColumnName = "id_user"))
    List<User> friendlist = new ArrayList<>();

    @OneToMany(orphanRemoval = true, mappedBy = "payMyBuddyAccount")
    List<Transfer> transfer = new ArrayList<>();

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public List<Transaction> getTransmittedTransactions() {
        return transmittedTransactions;
    }

    public void setTransmittedTransactions(List<Transaction> transmittedTransactions) {
        this.transmittedTransactions = transmittedTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public List<User> getFriendlist() {
        return friendlist;
    }

    public void setFriendlist(List<User> friendlist) {
        this.friendlist = friendlist;
    }

    public List<Transfer> getTransfer() {
        return transfer;
    }

    public void setTransfer(List<Transfer> transfer) {
        this.transfer = transfer;
    }
}
