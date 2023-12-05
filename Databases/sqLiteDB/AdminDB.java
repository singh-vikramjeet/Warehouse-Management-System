package sqLiteDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import authentication.User;
import productModule.IProduct;

public class AdminDB {
	// List of users
	private static List<User> usersList = new ArrayList<User>();

	public static List<User> getUsersList() {
		return usersList;
	}

	public static void setUsersList(List<User> usersList) {
		AdminDB.usersList = usersList;
	}

	public static void connect() {
		Connection conn = null;
		try {
			// create a database connection
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/vikramjeetsingh/DBBrowser/AdminDB.db");
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			System.out.println("Connection to SQLite has been established");

			ResultSet rs = statement.executeQuery("select * from users");
			while(rs.next())
			{
				// read the result set
//				System.out.println("name = " + rs.getString("name"));
//				System.out.println("password = " + rs.getInt("password"));

				User oneUser = new User();
				oneUser.setUsername(rs.getString("name"));
				oneUser.setPassword(rs.getInt("password"));
				usersList.add(oneUser);
			}

		} catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

	}


	public static void main(String[] args) {
		connect();
		
	}





}
