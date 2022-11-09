package com.PayMyBuddy.paymybuddy.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paymybuddy")
public class PayMyBuddy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paymybuddy")
    private int id_notification;

    @Column(name="balance ")
    private int balance;

    @Column(name="modification ")
    private Date modification;

//    @Column(name="transaction_id_transaction ")
//    private int transaction_id_transaction;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_transaction")
    private Transaction transaction;

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getModification() {
        return modification;
    }

    public void setModification(Date modification) {
        this.modification = modification;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
