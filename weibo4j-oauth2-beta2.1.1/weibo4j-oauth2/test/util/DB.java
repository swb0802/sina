package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sina?useUnicode=true&charsetEncoding=GBK";
	static final String USERNAME = "root";
	static final String PASSWORD = "user";
	
	static{
		try {
			Class.forName(JDBC_DRIVER);
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1){
			e1.printStackTrace();
		}
	}
	
	public static Connection getConn()
	{
		Connection conn = null;
		try {
			conn = java.sql.DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Statement getStmt(Connection conn)
	{
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static PreparedStatement getpStmt(Connection conn,String sql)
	{
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pStmt;
	}
	
	
}

















