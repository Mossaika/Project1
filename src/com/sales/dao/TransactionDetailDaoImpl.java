/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.dao;

import com.sales.entity.TransactionDetail;
import com.sales.utility.DBUtil;
import com.sales.utility.DaoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Joses
 */
public class TransactionDetailDaoImpl implements DaoService<TransactionDetail> {

    @Override
    public int addData(TransactionDetail object) {
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO TransactionDetail(itemID,transactionID,quantity,sellingprice)"
                        + "VALUES (?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getItemID());
                ps.setInt(2, object.getTransactionID());
                ps.setInt(3, object.getQuantity());
                ps.setInt(4, object.getSellingPrice());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int deleteData(TransactionDetail object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(TransactionDetail object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TransactionDetail> showAllData() {
        ObservableList<TransactionDetail> categories = FXCollections
                .observableArrayList();
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                String query
                        = "SELECT itemID, transactionID, quantity, sellingPrice FROM TransactionDetail";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TransactionDetail detail = new TransactionDetail();
                    detail.setItemID(rs.getInt("itemID"));
                    detail.setTransactionID(rs.getInt("transactionID"));
                    detail.setQuantity(rs.getInt("quantity"));
                    detail.setSellingPrice(rs.getInt("sellingPrice"));
                    categories.add(detail);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return categories;
    }

}
