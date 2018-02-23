/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.entity;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Developer
 */
public class Transaction {

    private final IntegerProperty id = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

//    private int id;
//    private int payment;
//    private Date date;
//    private User userID;
//
    private final IntegerProperty payment = new SimpleIntegerProperty();

    public int getPayment() {
        return payment.get();
    }

    public void setPayment(int value) {
        payment.set(value);
    }

    public IntegerProperty paymentProperty() {
        return payment;
    }
    private final ObjectProperty<Date> date = new SimpleObjectProperty<>();

    public Date getDate() {
        return date.get();
    }

    public void setDate(Date value) {
        date.set(value);
    }

    public ObjectProperty dateProperty() {
        return date;
    }

    private final ObjectProperty<User> userId = new SimpleObjectProperty<>();

    public User getUserId() {
        return userId.get();
    }

    public void setUserId(User value) {
        userId.set(value);
    }

    public ObjectProperty userIdProperty() {
        return userId;
    }

}
