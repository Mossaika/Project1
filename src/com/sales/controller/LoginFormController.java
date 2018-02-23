/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.controller;

import com.sales.MainApp;
import com.sales.dao.UserDaoImpl;
import com.sales.entity.User;
import com.sales.utility.TextUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
    private UserDaoImpl userDao;

    public UserDaoImpl getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
    private ObservableList<User> users;

    public ObservableList<User> getUser() {
        if (users == null) {
            users = FXCollections.observableArrayList();
            users.addAll(getUserDao().showAllData());
        }
        return users;
    }
    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnLoginAction(ActionEvent event) {
        User user = new User();
        user.setUsername(txtUsername.getText());
        user.setPassword(txtPassword.getText());
//        System.out.println(txtUsername.getText());
//        System.out.println(txtPassword.getText());
        if (txtUsername.getText().trim().isEmpty() || txtPassword.getText().
                trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all field");
            alert.showAndWait();

        } else if (getUserDao().getData(user) != null) {
            selectedUser = getUserDao().getData(user);
            if (selectedUser.getRoleId().getName().equalsIgnoreCase("owner")) {
                try {
                    Stage secondStage = new Stage();
                    secondStage.setTitle("Main Form");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource(
                            "view/MainForm.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    secondStage.setScene(scene);
                    secondStage.initOwner(anchorPane.getScene().getWindow());
                    secondStage.initModality(Modality.WINDOW_MODAL);
//                    anchorPane.getScene().getWindow().hide();
                    secondStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginFormController.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else if (selectedUser.getRoleId().getName().equalsIgnoreCase(
                    "cashier")) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource(
                            "view/CashierForm.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    Stage secondStage = new Stage();
                    secondStage.setScene(scene);
                    secondStage.setTitle("Cashier Form");
//                secondStage.initOwner(anchorPane.getScene().getWindow());
//                secondStage.initModality(Modality.NONE);
                    anchorPane.getScene().getWindow().hide();
                    secondStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginFormController.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        } else {
            TextUtil.alerting(Alert.AlertType.ERROR, "Invalid Login",
                    "Username/password yang anda masukkan salah!");
        }
    }
}
