/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Developer
 */
public class MainFormController implements Initializable {

    @FXML
    private TextField itemIDField;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemPriceField;
    @FXML
    private TextField itemStockField;
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
    }

    @FXML
    private void btnCancelAction(ActionEvent event) {
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {
    }

}
