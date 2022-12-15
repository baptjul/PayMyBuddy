package com.PayMyBuddy.paymybuddy.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transaction")
    private int idTransaction;

    @Column(name="transaction ", precision = 5, scale = 4)
    private double transaction;

    @Column(name="transaction_date ")
    private Date transaction_date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userTransmitter", referencedColumnName="id_user", nullable = false)
    private User userTransmitter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userReceiver", referencedColumnName="id_user", nullable = false)
    private User userReceiver;

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public double getTransaction() {
        return transaction;
    }

    public void setTransaction(double transaction) {
        this.transaction = transaction;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }

    public User getUserTransmitter() {
        return userTransmitter;
    }

    public void setUserTransmitter(User userTransmitter) {
        this.userTransmitter = userTransmitter;
    }


}
