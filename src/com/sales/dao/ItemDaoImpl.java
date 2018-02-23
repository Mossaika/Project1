/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.dao;

import com.sales.entity.Item;
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
public class ItemDaoImpl implements DaoService<Item> {

    @Override
    public int addData(Item object) {
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query = "INSERT INTO Item(name,price,stock)"
                        + "VALUES (?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getName());
                ps.setInt(2, object.getPrice());
                ps.setInt(3, object.getStock());
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
    public int deleteData(Item object) {
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "DELETE FROM Item WHERE id=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getId());
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
    public int updateData(Item object) {
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "UPDATE Item SET name=?, price=?, stock=? WHERE id=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getName());
                ps.setInt(2, object.getPrice());
                ps.setInt(3, object.getStock());
                ps.setInt(4, object.getId());
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
    public List<Item> showAllData() {
        ObservableList<Item> categories = FXCollections
                .observableArrayList();
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                String query = "SELECT id, name, price, stock FROM Item";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setPrice(rs.getInt("price"));
                    item.setStock(rs.getInt("stock"));
                    categories.add(item);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return categories;
    }

    @Override
    public Item getData(Item id) {
        try (Connection connection = DBUtil.createMySQLConnection()) {

            String query
                    = "SELECT i.stock FROM item i WHERE i.id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id.getStock());
            ps.setInt(2, id.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Item item = new Item();
                item.setStock(rs.getInt("i.stock"));

                item.setId(rs.getInt("i.id"));
                return item;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return (null);
    }

}
