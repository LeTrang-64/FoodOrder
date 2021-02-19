/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trang.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.trang.test.model.Food;
import org.trang.test.model.User_food;

/**
 *
 * @author admin
 */
public class FoodDAO {

    PreparedStatement pst;
    Connection conn = null;

    /*--------------- CONNECT TO DB ---------------*/
    public FoodDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/foodorderapp", "root", "4869");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Lỗi connect DB");
            throw new Error(e);
        }
    }

    /*--------------- ADD TO CART ---------------*/
    public void sendData(int user_id, int food_id) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT EXISTS (select * from user_food where food_id =" + food_id + ")");
            if (rs.next()) {
                String query = "";
                if (rs.getInt(1) == 0) {
                    query = "insert into user_food values(" + (user_id + ",") + (food_id + ",") + "1)";
                } else {

                    rs = st.executeQuery("select quantity from user_food where food_id =" + food_id + ";");
                    if (rs.next()) {
                        int quantity = rs.getInt(1) + 1;
                        query = "update user_food set quantity=" + quantity + " where food_id=" + food_id + ";";
                    }
                }

                pst = conn.prepareStatement(query);
                pst.execute();
            }
        } catch (Exception e) {
            System.out.println("Loi sendData");
            throw new Error(e);
        }
    }

    /*--------------- LOAD FOOD LIST ---------------*/
    public ArrayList readData() {
        ArrayList<Food> foods = new ArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from food");
            while (rs.next()) {

                foods.add(new Food(
                		rs.getInt("food_id"),
                        rs.getString("food_name"),
                        rs.getFloat("food_price"),
                        rs.getString("img"),
                        rs.getString("info")			
                ));
            }
        } catch (Exception e) {
            System.out.println("Loi readData");
            throw new Error(e);
        }
        return foods;
    }

    /*--------------- LOAD CART ---------------*/

    public ArrayList cart(int user_id) {
        ArrayList<User_food> userFood = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM  user_food where user_id="+user_id+";");
            while (rs.next()) {
                userFood.add(new User_food(rs.getInt("user_id"), rs.getInt("food_id"), rs.getInt("quantity")));
            }
        } catch (Exception e) {
            System.out.println("Loi cart");
        }
        return userFood;
    }
    // load food infomation
    public Food loadFoodInfo(int food_id) {
        Food food = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM food WHERE food_id = " + food_id + ";");
            while (rs.next()) {
                food = new Food(rs.getInt("food_id"),
                        rs.getString("food_name"),
                        rs.getFloat("food_price"),
                        rs.getString("img"),
                        rs.getString("info")
                );
            }
        } catch (Exception ex) {
            System.out.println("Loi load foodInfo");
            throw new Error(ex);
        }
        return food;
    }

    /*--------------- DELETE FOOD ---------------*/
    public void delete(int food_id) {
        try {
            String query = "DELETE FROM user_food where food_id = " + food_id + ";";
            Statement st = conn.createStatement();
            int rs = st.executeUpdate(query);
            System.out.println("Đã xóa item food_id = " + food_id);
        } catch (SQLException ex) {
            System.out.println("Loi delete item");
            throw new Error(ex);
        }
    }

    /*--------------- PAYMENT ---------------*/
    public void pay(int user_id) {
        try {
        	String query = "DELETE FROM user_food where user_id = " + user_id + ";";
            Statement st = conn.createStatement();
            int rs = st.executeUpdate(query);
            System.out.println("Đã thanh toán thành công");
        } catch (SQLException ex) {
            System.out.println("Loi pay");
            throw new Error(ex);
        }
    }
}
