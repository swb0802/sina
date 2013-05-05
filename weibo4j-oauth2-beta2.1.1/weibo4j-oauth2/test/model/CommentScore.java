package model;

public class CommentScore {

	private String cid; 			//评论id
	private float score;			//评论得分
	
	public CommentScore()
	{}
	
	public CommentScore(String cid, float score)
	{
		this.cid = cid;
		this.score = score;
	}
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
}
//create table comment_score
//(
//	cid varchar(32) primary key,
//	score float  
//);