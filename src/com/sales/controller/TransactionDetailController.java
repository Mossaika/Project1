/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.dao.ItemDaoImpl;
import com.sales.dao.TransactionDetailDaoImpl;
import com.sales.entity.Item;
import com.sales.entity.TransactionDetail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Developer
 */
public class TransactionDetailController implements Initializable {

    @FXML
    private MainFormController mainController;
    @FXML
    private TextField totalQty;
    @FXML
    private TextField subtotal;
    @FXML
    private TableView<TransactionDetail> tblTransactionDetail;
    @FXML
    private TableColumn<TransactionDetail, Item> colItemId;
    @FXML
    private TableColumn<TransactionDetail, Item> colItemName;
    @FXML
    private TableColumn<TransactionDetail, Integer> colItemSellprice;
    @FXML
    private TableColumn<TransactionDetail, Integer> colItemQuantity;
    @FXML
    private TableColumn<TransactionDetail, String> colTotal;
    @FXML
    private TableView<Item> tblItem;

    // transaction detail stuff
    private ObservableList<TransactionDetail> transactionDetails;
    private TransactionDetailDaoImpl transactionDetailDao;

    public TransactionDetailDaoImpl getTransactionDetailDao() {
        if (transactionDetailDao == null) {
            transactionDetailDao = new TransactionDetailDaoImpl();
        }
        return transactionDetailDao;
    }

    public ObservableList<TransactionDetail> getTransactionDetails() {
        if (transactionDetails == null) {
            transactionDetails = FXCollections.observableArrayList();
            transactionDetails.addAll(getTransactionDetailDao().showAllData(
                    mainController.selectedTransaction));
        }
        return transactionDetails;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        totalQty.setDisable(true);
        subtotal.setDisable(true);

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
//        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemSellprice.setCellValueFactory(new PropertyValueFactory<>(
                "sellingPrice"));
        colItemQuantity.setCellValueFactory(new PropertyValueFactory<>(
                "quantity"));
        colTotal.setCellValueFactory((
                TableColumn.CellDataFeatures<TransactionDetail, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getSellingPrice() * param.getValue().getQuantity())));
    }

    void setMainController(MainFormController aThis) {
        this.mainController = aThis;
        tblTransactionDetail.setItems(getTransactionDetails());
        totalQty.setText(String.valueOf(countQty()));
        subtotal.setText(String.valueOf(countSubtotal()));
    }

    private int countQty() {
        int qty = 0;
        for (TransactionDetail x : tblTransactionDetail.getItems()) {
            qty += x.getQuantity();
        }
        return qty;
    }

    private int countSubtotal() {
        int sub = 0;
        for (TransactionDetail x : tblTransactionDetail.getItems()) {
            sub += x.getQuantity() * x.getSellingPrice();
        }
        return sub;
    }
}
