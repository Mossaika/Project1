/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.entity;

import java.util.Date;

/**
 *
 * @author Developer
 */
public class Transaction {

    private int id;
    private int payment;
    private Date date;
    private User userID;

    public Transaction(int id, int payment, Date date, User userID) {
        this.id = id;
        this.payment = payment;
        this.date = date;
        this.userID = userID;
    }

    public Transaction() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

}
