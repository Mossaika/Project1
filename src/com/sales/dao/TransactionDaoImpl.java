/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.dao;

import com.sales.entity.Transaction;
import com.sales.entity.User;
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
public class TransactionDaoImpl implements DaoService<Transaction> {

    @Override
    public int addData(Transaction object) {
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query = "INSERT INTO Transaction(payment,userID)"
                        + "VALUES (?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getPayment());
                ps.setInt(2, object.getUserId().getId());
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
    public int deleteData(Transaction object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Transaction object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> showAllData() {
        ObservableList<Transaction> transactions = FXCollections
                .observableArrayList();
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                String query
                        = "SELECT t.id, t.payment, t.trans_date, u.username FROM transaction t JOIN user u ON u.id = t.user_id";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setId(rs.getInt("id"));
                    transaction.setPayment(rs.getInt("payment"));
                    transaction.setDate(rs.getTimestamp("trans_date"));

                    User user = new User();
                    user.setName(rs.getString("username"));
                    transaction.setUserId(user);
                    transactions.add(transaction);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return transactions;
    }

    @Override
    public Transaction getData(Transaction id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
