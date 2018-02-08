/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.entity;

/**
 *
 * @author Developer
 */
public class TransactionDetail {

    private int itemID;
    private int transactionID;
    private int quantity;
    private int sellingPrice;

    public TransactionDetail(int itemID, int transactionID, int quantity,
            int sellingPrice) {
        this.itemID = itemID;
        this.transactionID = transactionID;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}
