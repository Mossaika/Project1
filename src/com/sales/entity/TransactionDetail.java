/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Developer
 */
public class TransactionDetail {

    private final ObjectProperty<Item> itemId = new SimpleObjectProperty<>();

    public Item getItemId() {
        return itemId.get();
    }

    public void setItemId(Item value) {
        itemId.set(value);
    }

    public ObjectProperty itemIdProperty() {
        return itemId;
    }

    private final StringProperty itemName = new SimpleStringProperty();

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String value) {
        itemName.set(value);
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

//    private int itemID;
//    private int transactionID;
//    private int quantity;
//    private int sellingPrice;
    
    private final ObjectProperty<Transaction> transactionId = new SimpleObjectProperty<>();

    public Transaction getTransactionId() {
        return transactionId.get();
    }

    public void setTransactionId(Transaction value) {
        transactionId.set(value);
    }

    public ObjectProperty transactionIdProperty() {
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
