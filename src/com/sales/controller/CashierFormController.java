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
public class CashierFormController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField subtotalField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnAddCartAction(ActionEvent event) {
    }

    @FXML
    private void btnCheckoutAction(ActionEvent event) {
    }

}
