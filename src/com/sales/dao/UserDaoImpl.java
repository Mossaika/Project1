/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.dao;

import com.sales.entity.User;
import com.sales.utility.DaoService;
import java.util.List;

/**
 *
 * @author Joses
 */
public class UserDaoImpl implements DaoService<User> {

    @Override
    public int addData(User object) {
//        int result = 0;
//        try {
//            try (Connection connection = DBUtil.createMySQLConnection()) {
//                connection.setAutoCommit(false);
//                String query = "INSERT INTO Item(id,name,price,stock)"
//                        + "VALUES (?,?,?,?)";
//                PreparedStatement ps = connection.prepareStatement(query);
//                ps.setInt(1, object.getId());
//                ps.setString(2, object.getName());
//                ps.setInt(3, object.getPrice());
//                ps.setInt(4, object.getStock());
//                if (ps.executeUpdate() != 0) {
//                    connection.commit();
//                    result = 1;
//                } else {
//                    connection.rollback();
//                }
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println(ex);
//        }
//        return result;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteData(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> showAllData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
