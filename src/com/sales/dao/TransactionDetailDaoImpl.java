/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.dao;

import com.sales.entity.Item;
import com.sales.entity.Transaction;
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
                        = "INSERT INTO Transaction_Detail(item_ID,transaction_ID,item_name, quantity,selling_price)"
                        + "VALUES (?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getItemId().getId());
                ps.setInt(2, object.getTransactionId());
                ps.setString(3, object.getItemName());
                ps.setInt(4, object.getQuantity());
                ps.setInt(5, object.getSellingPrice());
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
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "UPDATE transaction_detail SET item_name=? WHERE item_id=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getItemName());
                ps.setInt(2, object.getItemId().getId());
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
    public List<TransactionDetail> showAllData() {
        ObservableList<TransactionDetail> categories = FXCollections
                .observableArrayList();
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                String query
                        = "SELECT item_ID, item_name, transaction_id, quantity, selling_Price FROM Transaction_Detail JOIN item ON id=item_id";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TransactionDetail detail = new TransactionDetail();
                    Item item = new Item();
                    item.setId(rs.getInt("item_id"));
                    detail.setItemId(item);
                    detail.setItemName(rs.getString("item_name"));
                    detail.setTransactionId(rs.getInt("transaction_id"));
                    detail.setQuantity(rs.getInt("quantity"));
                    detail.setSellingPrice(rs.getInt("selling_Price"));
                    categories.add(detail);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return categories;
    }

    public List<TransactionDetail> showAllData(Transaction object) {
        ObservableList<TransactionDetail> categories = FXCollections
                .observableArrayList();
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                String query
                        = "SELECT item_ID, item_name, quantity, selling_Price FROM Transaction_Detail JOIN item ON id=item_id "
                        + "WHERE transaction_id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TransactionDetail detail = new TransactionDetail();
                    Item item = new Item();
                    item.setId(rs.getInt("item_id"));
                    detail.setItemId(item);
                    detail.setItemName(rs.getString("item_name"));
                    detail.setQuantity(rs.getInt("quantity"));
                    detail.setSellingPrice(rs.getInt("selling_Price"));
                    categories.add(detail);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return categories;
    }

    @Override
    public TransactionDetail getData(TransactionDetail id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
