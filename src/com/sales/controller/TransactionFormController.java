/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.dao.ItemDaoImpl;
import com.sales.dao.TransactionDaoImpl;
import com.sales.entity.Transaction;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class TransactionFormController implements Initializable {

    @FXML
    private TableView<Transaction> tableTransaction;
    @FXML
    private TableColumn<Transaction, Date> colDate;
    @FXML
    private TableColumn<Transaction, Integer> colTransactionId;
    @FXML
    private TableColumn<Transaction, String> colUsername;
    @FXML
    private TableColumn<Transaction, Integer> colPayment;
    @FXML
    private TextField highestSelling;
    @FXML
    private TextField cashIn;

    private ObservableList<Transaction> transactions;
    private TransactionDaoImpl transactionDao;

    public TransactionDaoImpl getTransactionDao() {
        if (transactionDao == null) {
            transactionDao = new TransactionDaoImpl();
        }
        return transactionDao;
    }

    private ItemDaoImpl itemDao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        highestSelling.setDisable(true);
        cashIn.setDisable(true);

        tableTransaction.setItems(getTransactions());
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTransactionId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(p -> p.getValue().getUserId().
                nameProperty());
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
    }

    public ObservableList<Transaction> getTransactions() {
        if (transactions == null) {
            transactions = FXCollections.observableArrayList();
            transactions.addAll(getTransactionDao().showAllData());
        }
        return transactions;
    }

    @FXML
    private void tableClickedAction(MouseEvent event) {
    }

}
