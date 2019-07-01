package ouc.javaweb.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 数据库操作工具类
 */
public class DatabaseUtil {

	public static String URL;
	public static String USERNAME;
	public static String PASSWORD;
	public static String DRIVER;
	private static ResourceBundle rb = ResourceBundle.getBundle("ouc.javaweb.util.db.db-config");

	private DatabaseUtil() {
	}

	static {
		URL = rb.getString("jdbc.url");
		USERNAME = rb.getString("jdbc.username");
		PASSWORD = rb.getString("jdbc.password");
		DRIVER = rb.getString("jdbc.driver");

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接的方法
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Get Database Connection failed.");
		}

		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 */
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		
		try {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
