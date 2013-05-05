package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import model.CommentScore;
import util.DB;

public class CommentScoreDAO {
	public void save(List<CommentScore> cslist)
	{
		Connection conn = null;
		PreparedStatement pStmt = null;
		String sql = "insert into comment_score(cid, score) values(?, ?)";
		CommentScore commentScore = null;
		Iterator<CommentScore> iter = null;
		
		try {
			conn = DB.getConn();
			conn.setAutoCommit(false);
			pStmt = DB.getpStmt(conn, sql);
			iter = cslist.iterator();
			while(iter.hasNext())
			{
				commentScore = iter.next();
				pStmt.setString(1, commentScore.getCid());
				pStmt.setFloat(2, commentScore.getScore());
				pStmt.addBatch();
			}
			pStmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
