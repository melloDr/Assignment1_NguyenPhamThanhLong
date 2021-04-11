/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.FoodDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import utils.MyConnection;

/**
 *
 * @author Admin
 */
public class AdminDAO {

    private static final Logger LOGGER = Logger.getLogger(AdminDAO.class);

    public static void deleteFoodAsAdmin(String foodID, String dateUpdate) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "update tblFood\n"
                        + "set status = 'false', dateOfUpdate = '" + dateUpdate + "'\n"
                        + "where foodID = '" + foodID + "'";
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

    public static void resetFoodAsAdmin(String foodID, String dateUpdate) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "update tblFood\n"
                        + "set status = 'true', dateOfUpdate = '" + dateUpdate + "'\n"
                        + "where foodID = '" + foodID + "'";
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

    public static boolean updateFoodAsAdmin(String foodID, String foodName, int quantity, float price, String categoryID, String dateToUpdate) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "update tblFood\n"
                        + "set name = '" + foodName + "', quantity = '" + quantity + "', price = '" + price + "', categoryID = '" + categoryID + "', dateOfUpdate = '" + dateToUpdate + "'\n"
                        + "where foodID = '" + foodID + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public static String getCategoryID(String categoryName) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String result = "";
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select categoryID\n"
                        + "from tblCategory\n"
                        + "where categoryName = '" + categoryName + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("categoryID");
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
        return result;
    }

    public static void addFoodAsAdmin(String foodID, String name, int quantity, float price, String categoryID, String images, String dateAddNew, String status) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "Insert into tblFood(foodID, name, quantity, price, categoryID, images, dateAddNew,status, dateOfUpdate) \n"
                        + "Values('" + foodID + "','" + name + "','" + quantity + "','" + price + "','" + categoryID + "','" + images + "','" + dateAddNew + "','" + status + "',null)";
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

    public static List<FoodDto> getAllFood() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<FoodDto> list = new ArrayList<>();
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select foodID, name, quantity,price,c.categoryName,images,dateAddNew,status\n"
                        + "from tblFood f join tblCategory c\n"
                        + "on f.categoryID = c.categoryID\n"
                        + "where quantity > 0 ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("foodID");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    String categoryName = rs.getString("categoryName");
                    String images = rs.getString("images");
                    String dateAddNew = rs.getString("dateAddNew");
                    String status = rs.getString("status");
                    list.add(new FoodDto(id, name, quantity, price, categoryName, images, dateAddNew, status));
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
        return list;
    }

    public static List<FoodDto> get20Foods(int index) throws SQLException { // lúc này size mặc định là 20
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<FoodDto> list = new ArrayList<>();
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select foodID,categoryName,name,quantity,price,images,dateAddNew,status from\n"
                        + "(select ROW_NUMBER() over (order by dateAddNew) as r,foodid, name, quantity, price,c.categoryName,images,dateAddNew,status\n"
                        + "from tblFood f join tblCategory c\n"
                        + "on f.categoryID = c.categoryID) as x\n"
                        + "where r between " + index + " *20 - 19 and " + index + " * 20";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("foodId");
                    String categoryID = rs.getString("categoryName");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    String images = rs.getString("images");
                    String dateAddNew = rs.getString("dateAddNew");
                    String status = rs.getString("status");
                    list.add(new FoodDto(id, name, quantity, price, categoryID, images, dateAddNew, status));
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
        return list;
    }

    public static int countTable() throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select COUNT(*) from tblFood f join tblCategory c on f.categoryID = c.categoryID ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    result = rs.getInt(1);
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
        return result;
    }
}
