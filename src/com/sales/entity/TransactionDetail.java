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
    private final ObjectProperty<Item> itemName = new SimpleObjectProperty<>();

    public Item getItemName() {
        return itemName.get();
    }

    public void setItemName(Item value) {
        itemName.set(value);
    }

    public ObjectProperty itemNameProperty() {
        return itemName;
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
