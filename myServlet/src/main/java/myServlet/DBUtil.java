package myServlet;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	// table
		public static final String TABLE_USER = "user";
		public static final String TABLE_INFO = "info";
		public static final String TABLE_PICTURE = "picture";
	 
		// connect to MySql database
		public static Connection getConnect() {
			String url = "jdbc:mysql://localhost:3306/user"; // 数据库的Url
			Connection connecter = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); // java反射，固定写法
				connecter = (Connection) DriverManager.getConnection(url, "root", "123000");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
			return connecter;
		}

}
