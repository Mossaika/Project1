/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.dao.ItemDaoImpl;
import com.sales.dao.TransactionDaoImpl;
import com.sales.dao.UserDaoImpl;
import com.sales.entity.Item;
import com.sales.entity.Transaction;
import com.sales.entity.User;
import com.sales.utility.TextUtil;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Developer
 */
public class MainFormController implements Initializable {

    @FXML
    private TextField highestSelling;
    @FXML
    private TextField cashIn;
    @FXML
    private TextField txtItemName;
    @FXML
    private TextField txtItemPrice;
    @FXML
    private TextField txtItemStock;
    @FXML
    private TextField txtUserUsername;
    @FXML
    private PasswordField txtUserPassword;
    @FXML
    private TextField txtUserName;
    @FXML
    private TableView<Transaction> tableTransaction;
    @FXML
    private TableColumn<Transaction, Date> colTransactionDate;
    @FXML
    private TableColumn<Transaction, Integer> colTransactionId;
    @FXML
    private TableColumn<Transaction, String> colTransactionUser;
    @FXML
    private TableColumn<Transaction, Integer> colTransactionPayment;
    @FXML
    private TableView<Item> tableItem;
    @FXML
    private TableColumn<Item, Integer> colItemId;
    @FXML
    private TableColumn<Item, String> colItemName;
    @FXML
    private TableColumn<Item, Integer> colItemPrice;
    @FXML
    private TableColumn<Item, Integer> colItemStock;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, Integer> colUserId;
    @FXML
    private TableColumn<User, String> colUserUsername;
    @FXML
    private TableColumn<User, String> colUserName;

    // transaction stuff goes here
    private ObservableList<Transaction> transactions;
    private TransactionDaoImpl transactionDao;

    public TransactionDaoImpl getTransactionDao() {
        if (transactionDao == null) {
            transactionDao = new TransactionDaoImpl();
        }
        return transactionDao;
    }

    public ObservableList<Transaction> getTransactions() {
        if (transactions == null) {
            transactions = FXCollections.observableArrayList();
            transactions.addAll(getTransactionDao().showAllData());
        }
        return transactions;
    }

    // item stuff goes here
    public ObservableList<Item> items;
    private ItemDaoImpl itemDao;

    public ItemDaoImpl getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDaoImpl();
        }
        return itemDao;
    }

    public ObservableList<Item> getItems() {
        if (items == null) {
            items = FXCollections.observableArrayList();
            items.addAll(getItemDao().showAllData());
        }
        return items;
    }

    // user stuff goes here
    public ObservableList<User> users;
    private UserDaoImpl userDao;

    public UserDaoImpl getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public ObservableList<User> getUsers() {
        if (users == null) {
            users = FXCollections.observableArrayList();
            users.addAll(getUserDao().showAllData());
        }
        return users;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        highestSelling.setDisable(true);
        cashIn.setDisable(true);

        tableTransaction.setItems(getTransactions());
        colTransactionDate.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        colTransactionId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTransactionUser.setCellValueFactory(p -> p.getValue().getUserId().
                nameProperty());
        colTransactionPayment.setCellValueFactory(new PropertyValueFactory<>(
                "payment"));

        tableItem.setItems(getItems());
        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableUser.setItems(getUsers());
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserUsername.setCellValueFactory(new PropertyValueFactory<>(
                "username"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    private void btnAddItemAction(ActionEvent event) {
        if (!TextUtil.isEmptyField(txtItemName)) {
            Item d = new Item();
            d.setName(txtItemName.getText().trim());
            d.setPrice(Integer.valueOf(txtItemPrice.getText().trim()));
            d.setStock(Integer.valueOf(txtItemStock.getText().trim()));
            if (getItemDao().addData(d) == 1) {
                getItems().clear();
                getItems().addAll(getItemDao().showAllData());
            }
        }
    }

    @FXML
    private void btnUpdateItemAction(ActionEvent event) {
    }

    @FXML
    private void btnDeleteItemAction(ActionEvent event) {
    }

    @FXML
    private void btnCancelItemAction(ActionEvent event) {
        txtItemName.clear();
        txtItemPrice.clear();
        txtItemStock.clear();
    }

    @FXML
    private void btnAddUserAction(ActionEvent event) {
        if (!TextUtil.isEmptyField(txtUserName)) {
            User d = new User();
            d.setUsername(txtUserUsername.getText().trim());
            d.setPassword(txtUserPassword.getText().trim());
            d.setName(txtUserName.getText().trim());
            if (getUserDao().addData(d) == 1) {
                getUsers().clear();
                getUsers().addAll(getUserDao().showAllData());
            }
        }
    }

    @FXML
    private void btnUpdateUserAction(ActionEvent event) {
    }

    @FXML
    private void btnDeleteUserAction(ActionEvent event) {
    }

    @FXML
    private void btnCancelUserAction(ActionEvent event) {
        txtUserUsername.clear();
        txtUserPassword.clear();
        txtUserName.clear();
    }

    @FXML
    private void tblTransactionClickedAction(MouseEvent event) {
    }

    @FXML
    private void tblItemClickedAction(MouseEvent event) {
    }

    @FXML
    private void tblUserClickedAction(MouseEvent event) {
    }

}
