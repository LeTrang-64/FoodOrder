package org.trang.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.trang.test.model.Food;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table Food in the database.
 * 
 * @author www.codejava.net
 *
 */
public class DBFood {

	public static boolean addNewFood(Food food) throws ClassNotFoundException {
		boolean rowInserted = false;

		String sql = "INSERT INTO Food ( food_name, food_price,img,info) VALUES ( ?, ?,?,?)";
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false",
				"root", "4869");
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, food.getFoodName());
			preparedStatement.setFloat(2, food.getPrice());
			preparedStatement.setString(3, food.getImg());
			preparedStatement.setString(4, food.getInfo());

			rowInserted = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return rowInserted;
	}

	public static List<Food> listAllFoods() throws ClassNotFoundException {
		List<Food> listFood = new ArrayList<>();

		String INSERT_USERS_SQL = "SELECT * FROM Food";

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false",
				"root", "4869");
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

			ResultSet rs = preparedStatement.executeQuery(); // return resultset
																// ResultSet
			while (rs.next()) {
				int id = rs.getInt("food_id");
				String name = rs.getString("food_name");
				float price = rs.getFloat("food_price");
				String img=rs.getString("img");
				String info=rs.getString("info");
				
				Food food = new Food(id, name, price, img, info);
				listFood.add(food);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}

		return listFood;
	}

    public static boolean deleteFood(int foodID) throws SQLException, ClassNotFoundException {
//        String sql = "DELETE FROM Food where food_id = ?";
//         
//        connect();
//         
//        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
//        statement.setInt(1, foodID);
//         
//        boolean rowDeleted = statement.executeUpdate() > 0;
//        statement.close();
//        disconnect();
//        return rowDeleted;     
    	
    	boolean isDelete = false;

		String sql = "DELETE FROM Food where food_id = ?";
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false",
				"root", "4869");
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, foodID);
			

			isDelete = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return isDelete;
    }
	
//     
    public static boolean updateFood(Food food) throws  ClassNotFoundException {
//        String sql = "UPDATE Food SET food_name = ?, food_price = ?";
//        sql += " WHERE food_id = ?";
//        connect();
//         
//        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
//        statement.setString(1, food.getFoodName());
//        statement.setFloat(2, food.getPrice());
//        statement.setInt(3, food.getFoodID());
//         
//        boolean rowUpdated = statement.executeUpdate() > 0;
//        statement.close();
//        disconnect();
//        return rowUpdated;     
    	
    	boolean isUpdate = false;

		String sql = "UPDATE Food SET food_name = ?, food_price = ?,  img=?,  info=?"+"WHERE food_id = ?";
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false",
				"root", "4869");
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, food.getFoodName());
			preparedStatement.setFloat(2, food.getPrice());			
			preparedStatement.setString(3, food.getImg());
			preparedStatement.setString(4, food.getInfo());
			
			preparedStatement.setInt(5, food.getFoodID());

			isUpdate = preparedStatement.executeUpdate() > 0;
			preparedStatement.close();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return isUpdate;
    }
     
	public static Food getFoodbyID(int id) throws  ClassNotFoundException {
		Food food = null;
		String sql = "SELECT * FROM Food WHERE food_id = ?";
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false",
				"root", "4869");
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int idfood = resultSet.getInt("food_id");
				String name = resultSet.getString("food_name");
				float price = resultSet.getFloat("food_price");
				String img=resultSet.getString("img");
				String info=resultSet.getString("info");
				
				food = new Food(idfood, name, price, img, info);
			}

			resultSet.close();
			preparedStatement.close();


		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}

		return food;

	}

	private static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}