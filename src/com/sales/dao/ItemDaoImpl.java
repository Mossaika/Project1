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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                String query = "INSERT INTO Item(id,name,price,stock)"
                        + "VALUES (?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getId());
                ps.setString(2, object.getName());
                ps.setInt(3, object.getPrice());
                ps.setInt(4, object.getStock());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ItemDaoImpl.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int deleteData(Item object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Item object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> showAllData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
