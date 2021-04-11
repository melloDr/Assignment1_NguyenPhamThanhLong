/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartDto {
    private String userName;
    private Map<String, FoodDto> cart;

    public CartDto() {
    }

    public CartDto(String userName, Map<String, FoodDto> cart) {
        this.userName = userName;
        this.cart = cart;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, FoodDto> getCart() {
        return cart;
    }

    public void setCart(Map<String, FoodDto> cart) {
        this.cart = cart;
    }
    
    public void delete(String id){
        if(this.cart == null){
            return;
        }
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
    public void update(FoodDto f){
        if(this.cart != null){
            if(this.cart.containsKey(f.getId())){
                this.cart.replace(f.getId(), f);
            }
        }
    }
    
    public void add(FoodDto f) {
        if (this.cart == null) {
            this.cart = new HashMap<>();

        }
        if (this.cart.containsKey(f.getId())) {
            int quantity = this.cart.get(f.getId()).getQuantity();
            f.setQuantity(quantity + f.getQuantity());
        }
        cart.put(f.getId(), f);
    }
    
}
