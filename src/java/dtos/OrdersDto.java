/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Admin
 */
public class OrdersDto {
    int orderId;
    String userID;
    float total;
    String dateBuy;

    public OrdersDto() {
    }

    public OrdersDto(int orderId, String userID, float total, String dateBuy) {
        this.orderId = orderId;
        this.userID = userID;
        this.total = total;
        this.dateBuy = dateBuy;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(String dateBuy) {
        this.dateBuy = dateBuy;
    }
    
    
}
