package download;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DB;
import weibo4j.Comments;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Paging;
import weibo4j.model.WeiboException;

public class CommentFetcher {
	
	/*
	 * 
	 * */
	public long getComment(String access_token, String id, 
			Paging page) throws WeiboException 
	{
		Comments cm =new Comments();
		long totalNum = 0;		
		String sql = "insert into comment(created_at, cid, ctext, source, mid, uid, sid)  values(?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pStmt = null;
		File log = null;
		BufferedWriter bw = null;
		
		log = new File("D:\\log.txt");

		try {
			bw = new BufferedWriter(new FileWriter(log));	
			cm.client.setToken(access_token);
			CommentWapper comment = null;
			comment = cm.getCommentById(id, page, 0);
			conn = DB.getConn();		
			conn.setAutoCommit(false);
			pStmt = DB.getpStmt(conn, sql);
			for(Comment c : comment.getComments()){
				pStmt.setLong(1, c.getCreatedAt().getTime());
				pStmt.setString(2, String.valueOf(c.getId()));
				pStmt.setString(3, c.getText());
				pStmt.setString(4, c.getSource());
				pStmt.setString(5, c.getMid());
				pStmt.setString(6, c.getUser().getId());
				pStmt.setString(7, c.getStatus().getId());
				pStmt.addBatch();
			}
			pStmt.executeBatch();
			conn.commit();

			System.out.println(comment.getTotalNumber());
			System.out.println(comment.getNextCursor());
			System.out.println(comment.getNextCursor());
			System.out.println(comment.getHasvisible());
			totalNum = comment.getTotalNumber();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WeiboException e) { //接口调用频率达到上限
			//TODO
			e.printStackTrace();
			try {
				bw.write(id); //记录目前的微博id
				System.out.println("id：" + id);
				bw.newLine();
				bw.write(page.getPage()); //记录目前的评论所在页码
				System.out.println("页码：" + page.getPage());
				bw.newLine();
				throw e;
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(bw!=null)
				{
					bw.close();
				}
				if(pStmt!=null)
				{
					pStmt.close();
				}
				if(conn!=null)
				{
					conn.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return totalNum;
	}

}
