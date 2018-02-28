/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.dao.TransactionDetailDaoImpl;
import com.sales.entity.TransactionDetail;
import java.net.URL;
import java.util.ResourceBundle;
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

    private MainFormController mainController;
    @FXML
    private TextField totalField;
    @FXML
    private TextField totalQtyField;
    @FXML
    private TableView<TransactionDetail> tblTransactionDetail;
    @FXML
    private TableColumn<TransactionDetail, Integer> colItemId;
    @FXML
    private TableColumn<TransactionDetail, String> colItemName;
    @FXML
    private TableColumn<TransactionDetail, Integer> colItemSellprice;
    @FXML
    private TableColumn<TransactionDetail, Integer> colItemQuantity;
    @FXML
    private TableColumn<TransactionDetail, Integer> colTotal;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
//        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemSellprice.setCellValueFactory(new PropertyValueFactory<>(
                "sellingPrice"));
        colItemQuantity.setCellValueFactory(new PropertyValueFactory<>(
                "quantity"));
//        colTotal.setCellValueFactory((
//                TableColumn.CellDataFeatures<TransactionDetail, String> param)
//                -> new SimpleStringProperty(String.valueOf(param.getValue().
//                        getSellingPrice() * param.getValue().getQuantity())));
    }

    void setMainController(MainFormController aThis) {
        this.mainController = aThis;
        tblTransactionDetail.setItems(getTransactionDetails());
    }

}
