/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Developer
 */
public class Item {

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
//    private String name;
//    private int price;
//    private int stock;
    private final StringProperty name = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    private final IntegerProperty price = new SimpleIntegerProperty();

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int value) {
        price.set(value);
    }

    public IntegerProperty priceProperty() {
        return price;
    }
    private final IntegerProperty stock = new SimpleIntegerProperty();

    public int getStock() {
        return stock.get();
    }

    public void setStock(int value) {
        stock.set(value);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    @Override
    public String toString() {
        return String.valueOf(getId()) + " - " + getName();
    }

}
