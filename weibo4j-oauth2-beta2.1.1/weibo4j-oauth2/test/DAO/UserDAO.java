package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DB;

public class UserDAO {

	public void test()
	{
		Connection conn = null;
		PreparedStatement pStmt = null;
		String sql = "insert into user(uid, screen_name) values(?, ?)";
		
		try {
			conn = DB.getConn();
			conn.setAutoCommit(false);
			pStmt = DB.getpStmt(conn, sql);
			
			pStmt.setString(1, "111111");
			pStmt.setString(2, "aaaaaa");
			pStmt.addBatch();
			
			pStmt.setString(1, "222222");
			pStmt.setString(2, "bbbbbb");
			pStmt.addBatch();
			
			pStmt.setString(1, "333333");
			pStmt.setString(2, "cccccc");
			pStmt.addBatch();
			
			pStmt.setString(1, "333333");
			pStmt.setString(2, "dddddd");
			pStmt.addBatch();
						
			pStmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				if(pStmt != null)
				{
					pStmt.close();
				}
				if(conn != null)
				{
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String args[])
	{
		UserDAO userDao = new UserDAO();
		userDao.test();
	}
}

//create table user
//(
//	uid varchar(32) primary key, 	#用户id
//	screen_name varchar(32),		#昵称
//	provience varchar(32),			#省份
//	city varchar(32),				#城市
//	url TEXT,						#用户博客地址
//	gender varchar(2),				#性别
//	follower_count int,				#粉丝数
//	friends_count int,				#关注数
//	status_count int,				#微博数
//	favourites_count int,			#收藏数
//	created_at DATETIME,			#账户创建时间
//	bi_followers_count int			#互粉数
//);