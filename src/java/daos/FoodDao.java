/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.FoodDto;
import dtos.OrderDetailDto;
import dtos.OrdersDto;
import dtos.QuantityDto;
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
public class FoodDao {

    private static final Logger LOGGER = Logger.getLogger(FoodDao.class);
    public static List<FoodDto> getAllFood() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<FoodDto> list = new ArrayList<>();
        try {
            cn = utils.MyConnection.getConnection();
            if (cn != null) {
                String sql = "select foodid,name,quantity,price,c.categoryName,images,dateAddNew,status\n"
                        + "from tblFood f join tblCategory c\n"
                        + "on f.categoryID = c.categoryID\n"
                        + "where status = 'true'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("foodid");
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

    public static String getCategory() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String s = "No thing";
        try {
            cn = utils.MyConnection.getConnection();
            if (cn != null) {
                String sql = "select categoryName\n"
                        + "from tblCategory";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String result = rs.getString("categoryName");
                    s = s + " - " + result;
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
        return s;
    }

    public static List<FoodDto> search(int index, String name, String category, String priceFrom, String priceTo) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<FoodDto> list = new ArrayList<>();
        if ("No thing".equals(category)) {
            category = "";
        }
        String SQLWhere = getSQLWhere(name, category, priceFrom, priceTo);
        try {
            cn = utils.MyConnection.getConnection();
            if (cn != null) {
                String sql = "select r,foodID,name,quantity,price,categoryName,images,dateAddNew,status from(\n"
                        + "select ROW_NUMBER() over (order by dateAddNew) as r,foodid, name, quantity, price,c.categoryName,images,dateAddNew,status\n"
                        + "from tblFood f join tblCategory c\n"
                        + "on f.categoryID = c.categoryID\n"
                        + "" + SQLWhere + " and status = 'true') as x\n"
                        + "where r between " + index + " *20 - 19 and " + index + "* 20\n";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("foodid");
                    float price = rs.getFloat("price");
                    String foodName = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    String categoryName = rs.getString("categoryName");
                    String images = rs.getString("images");
                    String dateAddNew = rs.getString("dateAddNew");
                    String status = rs.getString("status");
                    list.add(new FoodDto(id, foodName, quantity, price, categoryName, images, dateAddNew, status));
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

    public static List<OrderDetailDto> getHistory(String userID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDetailDto> list = new ArrayList<>();
        try {
            cn = utils.MyConnection.getConnection();
            if (cn != null) {
                String sql = "select detailID,f.name,b.quantity,f.price,orderID\n"
                        + "from tblFood f join (select d.detailID, foodID, quantity, price, o.orderID\n"
                        + "from tblOrderDetails d join tblOrders o\n"
                        + "on d.orderID = o.orderID\n"
                        + "where o.userID = '"+userID+"') b\n"
                        + "on f.foodID = b.foodID";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int detailID = rs.getInt("detailID");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    int orderID = rs.getInt("orderID");
                    list.add(new OrderDetailDto(detailID, name, quantity, price, orderID));
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
                String sql = "select COUNT(*) from tblFood f join tblCategory c on f.categoryID = c.categoryID where status = 'true'";
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

    public static int countTableForSearch(String name, String category, String priceFrom, String priceTo) throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        if ("No thing".equals(category)) {
            category = "";
        }
        String SQLWhere = getSQLWhere(name, category, priceFrom, priceTo);
        try {
            cn = MyConnection.getConnection();
            String s = "";
            if (cn != null) {
                String sql = "select COUNT(*) \n"
                        + "from tblFood f join tblCategory c \n"
                        + "on f.categoryID = c.categoryID \n"
                        + "" + SQLWhere + " and status = 'true'";
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
                        + "on f.categoryID = c.categoryID\n"
                        + "where status = 'true') as x\n"
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

    public static List<QuantityDto> getQuantity() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<QuantityDto> list = new ArrayList<>();
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select foodId,quantity\n"
                        + "from tblFood";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("foodId");
                    int quantity = rs.getInt("quantity");
                    list.add(new QuantityDto(id, quantity));
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

    public static int getOrderID() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1 orderID as result FROM tblOrders ORDER BY orderID DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("result");
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

    public static String getUserID(String userName) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String result = "";
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select userID\n"
                        + "from tblUser\n"
                        + "where name = '" + userName + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("userID");
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

    public static List<OrdersDto> searchHistoryByName(String name) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrdersDto> list = new ArrayList();
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select orderID,userID,total,dateBuy\n"
                        + "from tblOrders\n"
                        + "where userID = '" + name + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String userID = rs.getString("userID");
                    float total = rs.getFloat("total");
                    String dateBuy = rs.getString("dateBuy");
                    list.add(new OrdersDto(orderID, userID, total, dateBuy));
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

    public static List<OrdersDto> searchHistoryByDateBuy(String dateBuy) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrdersDto> list = new ArrayList();
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select orderID,userID,total,dateBuy\n"
                        + "from tblOrders\n"
                        + "where dateBuy = '" + dateBuy + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String userID = rs.getString("userID");
                    float total = rs.getFloat("total");
                    String date = rs.getString("dateBuy");
                    list.add(new OrdersDto(orderID, userID, total, date));
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

    public static void addCart1(String userId, float total, String dateBuy) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "Insert into tblOrders(userID,total,dateBuy) \n"
                        + "Values('" + userId + "','" + total + "','" + dateBuy + "')\n";
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

    public static void addCart2(String foodID, int quantity, float price, int orderID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "Insert into tblOrderDetails(foodID,quantity,price,orderID) \n"
                        + "Values('" + foodID + "', '" + quantity + "','" + price + "'," + orderID + ")";
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

    public static String getSQLWhere(String name, String category, String priceFrom, String priceTo) {
        String s = "";
        if (!name.trim().isEmpty() && !category.trim().isEmpty() && !priceFrom.trim().isEmpty() && !priceTo.trim().isEmpty()) {
            s = "where (name like '%" + name + "%' or c.categoryName like '%" + category + "%' or (price > " + priceFrom + " and price < " + priceTo + "))";
        }
        if (!name.trim().isEmpty() && !category.trim().isEmpty() && priceFrom.trim().isEmpty() && priceTo.trim().isEmpty()) {
            s = "where (name like '%" + name + "%' or c.categoryName like '%" + category + "%')";
        }
        if (!name.trim().isEmpty() && category.trim().isEmpty() && !priceFrom.trim().isEmpty() && !priceTo.trim().isEmpty()) {
            s = "where (name like '%" + name + "%' or (price > " + priceFrom + " and price < " + priceTo + "))";
        }
        if (!name.trim().isEmpty() && category.trim().isEmpty() && priceFrom.trim().isEmpty() && priceTo.trim().isEmpty()) {
            s = "where (name like '%" + name + "%')";
        }
        if (name.trim().isEmpty() && !category.trim().isEmpty() && priceFrom.trim().isEmpty() && priceTo.trim().isEmpty()) {
            s = "where (c.categoryName like '%" + category + "%')";
        }
        if (name.trim().isEmpty() && category.trim().isEmpty() && !priceFrom.trim().isEmpty() && !priceTo.trim().isEmpty()) {
            s = "where (price > " + priceFrom + " and price < " + priceTo + ")";
        }
        if (name.trim().isEmpty() && !category.trim().isEmpty() && !priceFrom.trim().isEmpty() && !priceTo.trim().isEmpty()) {
            s = "where (c.categoryName like '%" + category + "%' or (price > " + priceFrom + " and price < " + priceTo + "))";
        }
        return s;
    }

}
