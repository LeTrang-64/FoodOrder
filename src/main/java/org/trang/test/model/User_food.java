package org.trang.test.model;

public class User_food {
    private int user_id;
    private int food_id;
    private int quantity;

    public User_food() {
    }

    public User_food(int user_id, int food_id, int quantity) {
        this.user_id = user_id;
        this.food_id = food_id;
        this.quantity = quantity;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
