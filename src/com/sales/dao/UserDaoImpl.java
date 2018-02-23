/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.dao;

import com.sales.entity.Role;
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
public class UserDaoImpl implements DaoService<User> {

    @Override
    public int addData(User object) {
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO User(name,username,password,role_id)"
                        + "VALUES (?,?,?,2)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getName());
                ps.setString(2, object.getUsername());
                ps.setString(3, object.getPassword());
//                ps.setInt(4, object.getRoleId().getId());
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
    public int deleteData(User object) {
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "DELETE FROM User WHERE id=?";
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
    public int updateData(User object) {
        int result = 0;
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "UPDATE User SET name=?,password=? WHERE id=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getName());
                ps.setString(2, object.getPassword());
                ps.setInt(3, object.getId());
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
    public List<User> showAllData() {
        ObservableList<User> categories = FXCollections
                .observableArrayList();
        try {
            try (Connection connection = DBUtil.createMySQLConnection()) {
                String query = "SELECT id, username, name, role_id FROM User";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    Role role = new Role();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setName(rs.getString("name"));
                    role.setId(rs.getInt("role_id"));
                    user.setRoleId(role);
                    categories.add(user);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return categories;
    }

    @Override
    public User getData(User id) {
        try (Connection connection = DBUtil.createMySQLConnection()) {

            String query
                    = "SELECT u.username, u.password, u.role_id, r.name FROM user u join role r on u.role_id = r.id WHERE u.username=? AND u.password=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id.getUsername());
            ps.setString(2, id.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("u.username"));

                user.setPassword(rs.getString("u.password"));
//                    user.setRole_id_Role(rs.get);
                Role role = new Role();
                role.setId(rs.getInt("u.role_id"));
                role.setName(rs.getString("r.name"));
                user.setRoleId(role);
                return user;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return (null);
    }

}
