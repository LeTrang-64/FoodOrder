package org.trang.test.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.trang.test.model.User;
import org.trang.test.model.Food;

public class DBUser {
	public static Boolean registerUser(User user) throws ClassNotFoundException {

		String INSERT_USERS_SQL = "INSERT INTO user"
				+ "  ( user_email, password,first_name,last_name,phone_number, shipping_address, is_manager,is_active) VALUES "
				+ " ( ?, ?, ?,?,?,?,?,?);";

		Boolean result = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false", "root", "4869");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			// preparedStatement.setInt(1,1);
			preparedStatement.setString(1, user.getUser_email());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirst_name());
			preparedStatement.setString(4, user.getLast_name());
			preparedStatement.setString(5, user.getPhone_number());
			preparedStatement.setString(6, user.getShipping_address());
			preparedStatement.setInt(7, user.getIsManager());
			preparedStatement.setInt(8, user.getIsActive());

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query

			result = preparedStatement.executeUpdate() > 0; // dua ra trang thai

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return result;
	}

	public static User checkLogin(String email, String pass) throws ClassNotFoundException {
		User user = null;
//		if(usename.equals("admin")&&pass.equals("admin")) {
//			status=true;
//		}
		String INSERT_USERS_SQL = "select * from user where user_email = ? and password = ?; ";

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false", "root", "4869");
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, pass);

			ResultSet rs = preparedStatement.executeQuery(); // return resultset
																// ResultSet
			if (rs.next()) {
				int user_id = rs.getInt("user_id");
				String user_email = rs.getString("user_email");
				String password = rs.getString("password");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String sdt = rs.getString("phone_number");
				String shipping = rs.getString("shipping_address");
				int isManager = rs.getInt("is_manager");
				int isActive=rs.getInt("is_active");

				user = new User(user_id, user_email, password, first_name, last_name, sdt, shipping, isManager,isActive);
			}
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return user;

	}

	public static List<User> getAll(int user_id) throws ClassNotFoundException {
		List<User> listCus = new ArrayList<>();

		String INSERT_USERS_SQL = "SELECT * FROM user where user_id != ?";

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false", "root", "4869");
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			
			preparedStatement.setInt(1, user_id);

			ResultSet rs = preparedStatement.executeQuery(); // return resultset
																// ResultSet
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String email = rs.getString("user_email");
				String pass = rs.getString("password");
				String ftname = rs.getString("first_name");
				String ltname = rs.getString("last_name");
				String sdt = rs.getString("phone_number");
				String add = rs.getString("shipping_address");
				int ismanager = rs.getInt("is_manager");
				int isActive=rs.getInt("is_active");

				User user = new User(id, email, pass, ftname, ltname, sdt, add, ismanager,isActive);
				listCus.add(user);
			}
			preparedStatement.close();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}

		return listCus;

	}
	public static User getUserByID(int id) throws ClassNotFoundException {
		User user = null;
//		if(usename.equals("admin")&&pass.equals("admin")) {
//			status=true;
//		}
		String INSERT_USERS_SQL = "select * from user where user_id = ? ";

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false", "root", "4869");
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

			preparedStatement.setInt(1, id);


			ResultSet rs = preparedStatement.executeQuery(); // return resultset
																// ResultSet
			if (rs.next()) {
				int user_id = rs.getInt("user_id");
				String user_email = rs.getString("user_email");
				String password = rs.getString("password");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String sdt = rs.getString("phone_number");
				String shipping = rs.getString("shipping_address");
				int isManager = rs.getInt("is_manager");
				int isActive=rs.getInt("is_active");

				user = new User(user_id, user_email, password, first_name, last_name, sdt, shipping, isManager,isActive);
			}
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return user;

	}
	

	public static Boolean updateUser(User user) throws ClassNotFoundException { // cap nhap thong tin khach hang

		String UPDATE_USERS_SQL = "UPDATE user set"
				+ "  user_email=?, first_name=?,last_name=?,phone_number=?, shipping_address=? " + " where user_id=?;";

		boolean isUpdate = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false", "root", "4869");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
			// preparedStatement.setInt(1,1);
			preparedStatement.setString(1, user.getUser_email());
			preparedStatement.setString(2, user.getFirst_name());
			preparedStatement.setString(3, user.getLast_name());
			preparedStatement.setString(4, user.getPhone_number());
			preparedStatement.setString(5, user.getShipping_address());
			preparedStatement.setInt(6, user.getUser_id());

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query

			isUpdate = preparedStatement.executeUpdate() > 0; // dua ra trang thai

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return isUpdate;
	}

	public static Boolean activeUser(int user_id,int is_active) throws ClassNotFoundException { // khoa tai khoan

		String UPDATE_USERS_SQL = "UPDATE user set" + " is_active=? " + " where user_id=?;";

		boolean isUpdate = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3307/foodorderapp?useSSL=false", "root", "4869");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
			// preparedStatement.setInt(1,1);
			preparedStatement.setInt(1, is_active);
			preparedStatement.setInt(2, user_id);

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query

			isUpdate = preparedStatement.executeUpdate() > 0; // dua ra trang thai

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return isUpdate;
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
