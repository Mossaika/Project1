/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Developer
 */
public class ItemFormController implements Initializable {

    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemPriceField;
    @FXML
    private TextField itemStockField;
    @FXML
    private TableView<?> tableItem;
    @FXML
    private TableColumn<?, ?> colItemId;
    @FXML
    private TableColumn<?, ?> colItemName;
    @FXML
    private TableColumn<?, ?> colItemPrice;
    @FXML
    private TableColumn<?, ?> colItemStock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
