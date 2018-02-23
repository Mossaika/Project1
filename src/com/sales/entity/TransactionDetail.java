/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Developer
 */
public class TransactionDetail {

    private final IntegerProperty itemId = new SimpleIntegerProperty();

    public int getItemId() {
        return itemId.get();
    }

    public void setItemId(int value) {
        itemId.set(value);
    }

    public IntegerProperty itemIdProperty() {
        return itemId;
    }

//    private int itemID;
//    private int transactionID;
//    private int quantity;
//    private int sellingPrice;
    private final IntegerProperty transactionId = new SimpleIntegerProperty();

    public int getTransactionId() {
        return transactionId.get();
    }

    public void setTransactionId(int value) {
        transactionId.set(value);
    }

    public IntegerProperty transactionIdProperty() {
        return transactionId;
    }
    private final IntegerProperty quantity = new SimpleIntegerProperty();

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int value) {
        quantity.set(value);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }
    private final IntegerProperty sellingPrice = new SimpleIntegerProperty();

    public int getSellingPrice() {
        return sellingPrice.get();
    }

    public void setSellingPrice(int value) {
        sellingPrice.set(value);
    }

    public IntegerProperty sellingPriceProperty() {
        return sellingPrice;
    }

}
