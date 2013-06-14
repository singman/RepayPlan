package com.generalsoft.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBHelper {
	/*private static String URL = "jdbc:oracle:thin:@localhost:1521:DataTP";
	private static String USERNAME = "yizhi";
	private static String PASSWORD = "yizhi";*/
	private static String URL = "jdbc:oracle:thin:@10.3.8.59:1521:jsbase";
	private static String USERNAME = "bzqcjr";
	private static String PASSWORD = "password";
	public static Connection getCon(){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection con,Statement sm,ResultSet rs){
		try {
			if(con!=null){
				con.close();
			}
			if(sm!=null){
				sm.close();
			}
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

