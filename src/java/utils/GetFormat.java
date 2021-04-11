/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dtos.CartDto;
import dtos.FoodDto;
import dtos.QuantityDto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public class GetFormat {

    public static String getString(String content, String format) {
        if (content.matches(format)) {
            return content;
        }
        return "";
    }

    public static int getInt(String number) {
        int result = 0;
        try {
            result = Integer.parseInt(number);
            if (result > 0) {
                return result;
            } else {
                result = 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    public static float getFloat(String number) {
        float result = 0;
        try {
            result = Float.parseFloat(number);
            if (result > 0) {
                return result;
            } else {
                result = 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    public static String checkQuantity(CartDto cart) throws SQLException {
        String check = "";
        List<QuantityDto> list = daos.FoodDao.getQuantity();
        for (FoodDto food : cart.getCart().values()) { //Vòng này chạy hết các dto của cái FoodDto
            String a1 = food.getId();
            for (int i = 0; i < list.size(); i++) {
                String a2 = list.get(i).getFoodId();
                if (food.getId().trim().equals(list.get(i).getFoodId().trim())) {
                    if (food.getQuantity() > list.get(i).getQuantity()) {
                        check += food.getName() + " ";
                    }
                }
            }
        }
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = 0; j < list1.size(); j++) {
//                if (list.get(i).getId() == list1.get(j).getFoodId()) {
//                    if (list.get(i).getQuantity() > list1.get(j).getQuantity()) {
//                        check = "the the order's quantity is larger than the existing quantity: " + list.get(i).getName();
//                    }
//                }
//            }
//        }
        return check;
    }
}
