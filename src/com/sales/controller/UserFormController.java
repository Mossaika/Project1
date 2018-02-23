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
public class UserFormController implements Initializable {

    @FXML
    private TableView<?> tableUser;
    @FXML
    private TableColumn<?, ?> colUserId;
    @FXML
    private TableColumn<?, ?> colUsername;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtUserLoginId;
    @FXML
    private TextField txtUserPassword;
    @FXML
    private TextField txtUserName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
