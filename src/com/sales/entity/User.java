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
public class User {

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
//    private String username;
//    private String password;
//    private Role roleID;
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
    private final StringProperty username = new SimpleStringProperty();

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String value) {
        username.set(value);
    }

    public StringProperty usernameProperty() {
        return username;
    }
    private final StringProperty password = new SimpleStringProperty();

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String value) {
        password.set(value);
    }

    public StringProperty passwordProperty() {
        return password;
    }
    private final ObjectProperty<Role> roleId = new SimpleObjectProperty<>();

    public Role getRoleId() {
        return roleId.get();
    }

    public void setRoleId(Role value) {
        roleId.set(value);
    }

    public ObjectProperty roleIdProperty() {
        return roleId;
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }

}
