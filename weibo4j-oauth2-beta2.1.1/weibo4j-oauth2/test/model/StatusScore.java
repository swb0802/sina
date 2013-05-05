package model;

public class StatusScore {

	private String sid;					//状态id
	private float score;				//状态分数
	
	public StatusScore()
	{}
	
	public StatusScore(String sid, float score)
	{
		this.sid = sid;
		this.score = score;
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
}

//create table status_score
//(
//	sid varchar(32) primary key,
//	score float
//);
