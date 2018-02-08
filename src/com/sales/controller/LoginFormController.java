/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Developer
 */
public class LoginFormController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnLoginAction(ActionEvent event) {
//        System.out.println(txtUsername.getText());
//        System.out.println(txtPassword.getText());
        if (txtUsername.getText().trim().isEmpty() || txtPassword.getText().
                trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all field");
            alert.showAndWait();

        } else if (txtUsername.getText().equals("1672023") && txtPassword.
                getText().equals("Joses")) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(
                        "view/MainForm.fxml"));
                AnchorPane pane = loader.load();
                Scene scene = new Scene(pane);
                Stage secondStage = new Stage();
                secondStage.setScene(scene);
                secondStage.setTitle("Main Form");
//                secondStage.initOwner(anchorPane.getScene().getWindow());
//                secondStage.initModality(Modality.NONE);
                anchorPane.getScene().getWindow().hide();
                secondStage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }
}
