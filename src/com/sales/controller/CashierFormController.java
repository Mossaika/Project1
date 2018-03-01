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
import com.sales.entity.TransactionDetail;
import com.sales.utility.DBUtil;
import com.sales.utility.TextUtil;
import com.sales.utility.ViewUtil;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
    private TableColumn<Item, String> colItemPrice;
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
    public Cart selectedCart;

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
            items.addAll(getItemDao().showAllData2());
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
        colItemPrice.setCellValueFactory((
                TableColumn.CellDataFeatures<Item, String> param)
                -> new SimpleStringProperty(String.valueOf(TextUtil.ThisIsMoney(
                        param.getValue().
                                getPrice()))));
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
                -> new SimpleStringProperty(String.valueOf(TextUtil.ThisIsMoney(
                        param.getValue().
                                getPrice()))));
        colCartItemQty.setCellValueFactory((
                TableColumn.CellDataFeatures<Cart, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getQty())));
        colCartTotal.setCellValueFactory((
                TableColumn.CellDataFeatures<Cart, String> param)
                -> new SimpleStringProperty(String.
                        valueOf(TextUtil.ThisIsMoney(param.getValue().
                                getQty() * param.getValue().getPrice()))));
    }

    @FXML
    private void btnAddCartAction(ActionEvent event) {
        if (TextUtil.isNumber(txtQty.getText())) {
            int qty = Integer.valueOf(txtQty.getText());
            if (qty < 1) {
                TextUtil.alerting(Alert.AlertType.ERROR, "input kurang dari 1",
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
            Item d = new Item();
            Transaction transaction = new Transaction();
            transaction.setId(getTransactionDao().showAllData().size() + 1);
            transaction.setPayment(0);
            for (Cart cart : carts) {
                transaction.setPayment(transaction.getPayment() + (cart.getQty()
                        * cart.getPrice()));
            }
            transaction.setUserId(mainController.getSelectedUser());
            getTransactionDao().addData(transaction);
            for (Cart cart : carts) {
                TransactionDetail transactionDetail = new TransactionDetail();
                transactionDetail.setItemId(new Item(cart.getId()));
                transactionDetail.setItemName(cart.getName());
                transactionDetail.setQuantity(cart.getQty());
                transactionDetail.setSellingPrice(cart.getPrice());
                transactionDetail.setTransactionId(transaction);
                getTransactionDetailDao().addData(transactionDetail);

            }
            for (Item item : getItems()) {
                d.setId(item.getId());
                d.setName(item.getName());
                d.setPrice(item.getPrice());
                d.setStock(item.getStock());
                getItemDao().updateData(d);
            }
//            System.out.println(mainController.getSelectedUser().getId());
            carts.clear();

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        HashMap parameters = new HashMap();
                        JasperPrint jasperPrint = JasperFillManager.fillReport(
                                "report/report_sales.jasper",
                                parameters, DBUtil.createMySQLConnection());
                        JasperViewer jasperViewer
                                = new JasperViewer(jasperPrint,
                                        false);
                        jasperViewer.setVisible(true);
                    } catch (ClassNotFoundException | SQLException | JRException ex) {
                        Logger.getLogger(MainFormController.class.getName()).
                                log(
                                        Level.SEVERE, null, ex);
                        ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.
                                getMessage());
                    }
                    return null;
                }
            };
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(task);
            service.shutdown();
        }
    }

    @FXML
    private void btnRemoveAction(ActionEvent event) {
        if (selectedCart != null) {
            Item d = new Item();
            for (Item item : getItems()) {
                if (item.getId() == selectedCart.getId()) {
                    item.setStock(item.getStock() + selectedCart.getQty());
                }
            }
            tableItem.refresh();
            carts.remove(selectedCart);
            btnRemove.setDisable(true);
        }
    }

    @FXML
    private void tblItemClickedAction(MouseEvent event) {
        btnRemove.setDisable(true);
        selectedItem = tableItem.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            btnAddCart.setDisable(false);
            txtQty.setDisable(false);
        }
    }

    @FXML
    private void tblCartClickedAction(MouseEvent event
    ) {
        btnAddCart.setDisable(true);
        selectedCart = tableCart.getSelectionModel().getSelectedItem();
        btnRemove.setDisable(false);
    }

    void setMainController(LoginFormController mainController
    ) {
        this.mainController = mainController;

    }

}
