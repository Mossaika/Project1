/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.utility;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author Developer
 */
public class TextUtil {

    public static boolean isEmptyField(TextInputControl... textFields) {
        for (TextInputControl tic : textFields) {
            if (tic.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            alerting(Alert.AlertType.ERROR, "Not a number",
                    "Your input is not a number");
            return false;
        }
        return true;
    }

    public static void alerting(Alert.AlertType alertType, String header,
            String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static String ThisIsMoney(int num) {
        Locale indo = new Locale("id", "ID");
        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(indo);

        return rupiahFormat.format(num);
    }
}
