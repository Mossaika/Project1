/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.entity.Item;
import com.sales.entity.Transaction;
import com.sales.entity.User;
import com.sales.utility.TextUtil;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Developer
 */
public class MainFormController implements Initializable {

    private TextField itemIDField;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemPriceField;
    @FXML
    private TextField itemStockField;
    @FXML
    private TableView<Transaction> tableTransaction;
    @FXML
    private TableColumn<Transaction, Date> colDate;
    @FXML
    private TableColumn<Transaction, Integer> colTransactionId;
    @FXML
    private TableColumn<Transaction, Integer> colUserId;
    @FXML
    private TableColumn<Transaction, Integer> colPayment;
    @FXML
    private ComboBox<User> comboUser;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtUserLoginId;
    @FXML
    private TextField txtUserPassword;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField highestSelling;
    @FXML
    private TextField cashIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        highestSelling.setDisable(true);
        cashIn.setDisable(true);
    }

    @FXML
    private void btnCancelAction(ActionEvent event) {
        itemIDField.clear();
        itemNameField.clear();
        itemPriceField.clear();
        itemStockField.clear();
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {

    }

    @FXML
    private void btnAddAction(ActionEvent event) {
        if (!TextUtil.isEmptyField(itemNameField)) {
            Item d = new Item();
            d.setId(Integer.valueOf(itemIDField.getText().trim()));
            d.setName(itemNameField.getText().trim());
            d.setPrice(Integer.valueOf(itemPriceField.getText().trim()));
            d.setStock(Integer.valueOf(itemStockField.getText().trim()));
        }
    }

    @FXML
    private void btnSaveUserAction(ActionEvent event) {
    }

    @FXML
    private void btnDeleteUserAction(ActionEvent event) {
    }

}
