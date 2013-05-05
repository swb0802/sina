package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import util.DB;

import model.Status;

public class StatusDAO {

	public List<Status> getStatus()
	{
		List<Status> list = new LinkedList<Status>();
		Connection conn = null;
		Statement stmt = null;
		String sql = "select sid from status";
		ResultSet rs = null;
		Status s = null;
		
		try {
			conn = DB.getConn();
			stmt = DB.getStmt(conn);
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				s = new Status();
				s.setSid(rs.getString(1));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null)
			{
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static void main(String args[])
	{
//		StatusDAO sd = new StatusDAO();
//		List<Status> list = sd.getStatus();
//		System.out.println(list.size());
	}
	
}
