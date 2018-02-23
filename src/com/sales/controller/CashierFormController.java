/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.dao.ItemDaoImpl;
import com.sales.dao.TransactionDaoImpl;
import com.sales.dao.TransactionDetailDaoImpl;
import com.sales.entity.Cart;
import com.sales.entity.Item;
import com.sales.entity.Transaction;
import com.sales.utility.TextUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Developer
 */
public class CashierFormController implements Initializable {

    private LoginFormController mainController;

    @FXML
    private TextField txtQty;
    @FXML
    private Button btnAddCart;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnChkout;
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
    private TableView<Cart> tableCart;
    @FXML
    private TableColumn<Cart, String> colCartItemId;
    @FXML
    private TableColumn<Cart, String> colCartItemName;
    @FXML
    private TableColumn<Cart, String> colCartItemPrice;
    @FXML
    private TableColumn<Cart, String> colCartItemQty;
    @FXML
    private TableColumn<Cart, String> colCartTotal;

    public Item selectedItem;

    // item stuff goes here
    public ObservableList<Item> items;
    public ObservableList<Cart> carts;
    @FXML
    private AnchorPane anchorPane;

    private TransactionDetailDaoImpl transactionDetailDao;

    public TransactionDetailDaoImpl getTransactionDetailDao() {
        if (transactionDetailDao == null) {
            transactionDetailDao = new TransactionDetailDaoImpl();
        }
        return transactionDetailDao;
    }

    private TransactionDaoImpl transactionDao;

    public TransactionDaoImpl getTransactionDao() {
        if (transactionDao == null) {
            transactionDao = new TransactionDaoImpl();
        }
        return transactionDao;
    }

    public ObservableList<Cart> getCarts() {
        if (carts == null) {
            carts = FXCollections.observableArrayList();
        }
        return carts;
    }
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
        btnAddCart.setDisable(true);
        btnRemove.setDisable(true);

        tableItem.setItems(getItems());
        tableCart.setItems(getCarts());
        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colCartItemId.setCellValueFactory((
                TableColumn.CellDataFeatures<Cart, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getId())));
        colCartItemName.setCellValueFactory((
                TableColumn.CellDataFeatures<Cart, String> param)
                -> new SimpleStringProperty(param.getValue().getName()));
        colCartItemPrice.setCellValueFactory((
                TableColumn.CellDataFeatures<Cart, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getPrice())));
        colCartItemQty.setCellValueFactory((
                TableColumn.CellDataFeatures<Cart, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getQty())));
        colCartTotal.setCellValueFactory((
                TableColumn.CellDataFeatures<Cart, String> param)
                -> new SimpleStringProperty(String.
                        valueOf(param.getValue().
                                getQty() * param.getValue().getPrice())));
    }

    @FXML
    private void btnAddCartAction(ActionEvent event) {
        if (TextUtil.isNumber(txtQty.getText())) {
            int qty = Integer.valueOf(txtQty.getText());
            if (qty < 1) {
                TextUtil.alerting(Alert.AlertType.ERROR, "input kurang dari 0",
                        "silahkan input ulang");
            } else if (qty > selectedItem.getStock()) {
                TextUtil.alerting(Alert.AlertType.ERROR, "Habis",
                        "Barang yang dibeli melebihi stock");
            } else {
                Cart cart = new Cart();
                cart.setId(selectedItem.getId());
                cart.setName(selectedItem.getName());
                cart.setPrice(selectedItem.getPrice());
                cart.setQty(qty);
                cart = itemCart(cart);
                if (itemCartBoolean(cart)) {
                    cart.setQty(cart.getQty() + qty);
                    tableCart.refresh();
                } else {
                    carts.add(cart);
                }
                selectedItem.setStock(selectedItem.getStock() - qty);
                tableItem.refresh();
                selectedItem = null;
                txtQty.setText("");
                btnAddCart.setDisable(true);
                txtQty.setDisable(true);
            }
        }
    }

    private Cart itemCart(Cart nowCart) {
        for (Cart cart : carts) {
            if (cart.getId() == nowCart.getId()) {
                return cart;
            }
        }
        return nowCart;
    }

    private boolean itemCartBoolean(Cart nowCart) {
        for (Cart cart : carts) {
            if (cart.getId() == nowCart.getId()) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void btnChkoutAction(ActionEvent event) {
        if (!carts.isEmpty()) {
            Transaction transaction = new Transaction();
            transaction.setId(getTransactionDao().showAllData().size() + 1);
            transaction.setPayment(0);
            for (Cart cart : carts) {
                transaction.setPayment(transaction.getPayment() + (cart.getQty()
                        * cart.getPrice()));
            }
            transaction.setUserId(mainController.getSelectedUser());
            getTransactionDao().addData(transaction);
            System.out.println(mainController.getSelectedUser().getId());
            carts.clear();
        }
    }

    @FXML
    private void btnRemoveAction(ActionEvent event) {
    }

    @FXML
    private void tblItemClickedAction(MouseEvent event) {
        selectedItem = tableItem.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            btnAddCart.setDisable(false);
            txtQty.setDisable(false);
        }
    }

    @FXML
    private void tblCartClickedAction(MouseEvent event) {
    }

    void setMainController(LoginFormController mainController) {
        this.mainController = mainController;

    }

}
