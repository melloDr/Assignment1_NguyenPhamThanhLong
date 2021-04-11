/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class UserDao {
    private static final Logger LOGGER = Logger.getLogger(FoodDao.class);

    public static String checkLogin(String ID, String pass) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String name = null;
        try {
            cn = utils.MyConnection.getConnection();
            if (cn != null) {
                String sql = "select name\n"
                        + "from tblUser\n"
                        + "where userID = ? and password = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, ID);
                pst.setString(2, pass);
                rs = pst.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return name;
    }

    public static String getUserId(String name) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String id = null;
        try {
            cn = utils.MyConnection.getConnection();
            if (cn != null) {
                String sql = "select userID\n"
                        + "from tblUser\n"
                        + "where name = '" + name + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    id = rs.getString("userID");
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return id;
    }

    public static void addUser(String userID, String password, String role, String name, String email) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = utils.MyConnection.getConnection();
            if (cn != null) {
                String sql = "Insert into tblUser(userID, password, role,name, email)\n"
                        + "Values('" + userID + "','" + password + "','" + role + "','" + name + "','" + email + "')";
                pst = cn.prepareStatement(sql);
                pst.executeQuery();
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public static String checkRole(String ID) throws SQLException {
        String role = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = utils.MyConnection.getConnection();
            if (cn != null) {
                String sql = "select role\n"
                        + "from tblUser\n"
                        + "where userID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, ID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return role;
    }
}
