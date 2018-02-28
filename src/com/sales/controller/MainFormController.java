/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.MainApp;
import com.sales.dao.ItemDaoImpl;
import com.sales.dao.TransactionDaoImpl;
import com.sales.dao.UserDaoImpl;
import com.sales.entity.Item;
import com.sales.entity.Transaction;
import com.sales.entity.TransactionDetail;
import com.sales.entity.User;
import com.sales.utility.DBUtil;
import com.sales.utility.TextUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Button btnUpdateItem;
    @FXML
    private Button btnDeleteItem;
    @FXML
    private Button btnUpdateUser;
    @FXML
    private Button btnDeleteUser;
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
    @FXML
    private AnchorPane mainForm;

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

    public Item selectedItem;
    public int selectedItemId;
    public User selectedUser;
    public int selectedUserId;
    public Transaction selectedTransaction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnUpdateItem.setDisable(true);
        btnDeleteItem.setDisable(true);
        btnUpdateUser.setDisable(true);
        btnDeleteUser.setDisable(true);
        highestSelling.setDisable(true);
        cashIn.setDisable(true);
        cashIn.setText(String.valueOf(hitungKas()));
        highestSelling.setText(String.valueOf(selectMaxCountItem()));

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
        if (!TextUtil.isEmptyField(txtItemName, txtItemPrice, txtItemStock)) {
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
        if (!TextUtil.isEmptyField(txtItemName, txtItemPrice, txtItemStock)) {
            Item d = new Item();
            d.setId(selectedItemId);
            d.setName(txtItemName.getText().trim());
            d.setPrice(Integer.valueOf(txtItemPrice.getText().trim()));
            d.setStock(Integer.valueOf(txtItemStock.getText().trim()));
            if (getItemDao().updateData(d) == 1) {
                getItems().clear();
                getItems().addAll(getItemDao().showAllData());
            }
        }
    }

    @FXML
    private void btnDeleteItemAction(ActionEvent event) {
        Item d = new Item();
        d.setId(selectedItemId);
        if (getItemDao().deleteData(d) == 1) {
            btnUpdateItem.setDisable(true);
            btnDeleteItem.setDisable(true);
            getItems().clear();
            getItems().addAll(getItemDao().showAllData());
        }
    }

    @FXML
    private void btnCancelItemAction(ActionEvent event) {
        btnUpdateItem.setDisable(true);
        btnDeleteItem.setDisable(true);
        txtItemName.clear();
        txtItemPrice.clear();
        txtItemStock.clear();
    }

    @FXML
    private void btnAddUserAction(ActionEvent event) {
        if (!TextUtil.
                isEmptyField(txtUserUsername, txtUserPassword, txtUserName)) {
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
        if (!TextUtil.
                isEmptyField(txtUserUsername, txtUserPassword, txtUserName)) {
            User d = new User();
            d.setId(selectedUserId);
            d.setUsername(txtUserUsername.getText().trim());
            d.setPassword(txtUserPassword.getText().trim());
            d.setName(txtUserName.getText().trim());
            if (getUserDao().updateData(d) == 1) {
                getUsers().clear();
                getUsers().addAll(getUserDao().showAllData());
            }
        }
    }

    @FXML
    private void btnDeleteUserAction(ActionEvent event) {
        User d = new User();
        d.setId(selectedUserId);
        if (getUserDao().deleteData(d) == 1) {
            btnUpdateUser.setDisable(true);
            btnDeleteUser.setDisable(true);
            getUsers().clear();
            getUsers().addAll(getUserDao().showAllData());
        }
    }

    @FXML
    private void btnCancelUserAction(ActionEvent event) {
        btnUpdateUser.setDisable(true);
        btnDeleteUser.setDisable(true);
        txtUserUsername.clear();
        txtUserPassword.clear();
        txtUserName.clear();
    }

    @FXML
    private void tblTransactionClickedAction(MouseEvent event) {
        selectedTransaction = tableTransaction.getSelectionModel().
                getSelectedItem();
        if (selectedTransaction != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(
                        "view/TransactionDetail.fxml"));
                AnchorPane pane = loader.load();
                TransactionDetailController transactionDetailController
                        = loader.
                                getController();
                transactionDetailController.setMainController(this);
                Scene scene = new Scene(pane);
                Stage secondStage = new Stage();
                secondStage.setScene(scene);
                secondStage.setTitle("Transaction Detail Form");
                secondStage.initOwner(mainForm.getScene().getWindow());
                secondStage.initModality(Modality.NONE);
                //                    anchorPane.getScene().getWindow().hide();
                secondStage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainFormController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void tblItemClickedAction(MouseEvent event) {
        selectedItem = tableItem.getSelectionModel().getSelectedItem();
        btnUpdateItem.setDisable(false);
        btnDeleteItem.setDisable(false);
        if (selectedItem != null) {
            txtItemName.setText(selectedItem.getName());
            txtItemPrice.setText(String.valueOf(selectedItem.getPrice()));
            txtItemStock.setText(String.valueOf(selectedItem.getStock()));
            selectedItemId = selectedItem.getId();
        }
    }

    @FXML
    private void tblUserClickedAction(MouseEvent event) {
        selectedUser = tableUser.getSelectionModel().getSelectedItem();
        btnUpdateUser.setDisable(false);
        btnDeleteUser.setDisable(true);
        if (selectedUser != null) {
            txtUserUsername.setText(selectedUser.getUsername());
            txtUserName.setText(String.valueOf(selectedUser.getName()));
            selectedUserId = selectedUser.getId();
            if (selectedUser.getRoleId().getId() != 1) {
                btnDeleteUser.setDisable(false);
            }
        }
    }

    private int hitungKas() {
        int kas = 0;
        for (Transaction transaction : getTransactions()) {
            kas += transaction.getPayment();
        }
        return kas;
    }

    public String selectMaxCountItem() {
        String max = null;
        String query = "SELECT item_name, SUM(quantity) AS Count "
                + "FROM transaction_detail " + "GROUP BY item_id "
                + "ORDER BY Count DESC " + "LIMIT 1";
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {;
                PreparedStatement ps = connection.prepareStatement(query);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    TransactionDetail detail = new TransactionDetail();
                    detail.setItemName(rs.getString("item_name"));
                    max = detail.getItemName();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return max;
    }
}
