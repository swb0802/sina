package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Comment;
import model.CommentScore;
import model.Status;

public class RateScore {
	
	private static Map<String, Integer> dict = new HashMap<String, Integer>();

	public List<CommentScore> rateComment(List<Comment> clist)
	{
		Spliter spliter = null;
		List<String> words = null;
		List<CommentScore> cslist = null;
		CommentScore cs = null;
		Comment c = null;
		float score = 0.0f;
		Iterator<Comment> iter = null;
		
		spliter = new Spliter();
		cslist = new LinkedList<CommentScore>();
		iter = clist.iterator();
		while(iter.hasNext())
		{
			c = iter.next();
			words = spliter.split(c.getCtext());
			score = rateWord(words);
			cs = new CommentScore(c.getCid(), score);
			cslist.add(cs);
		}
		return cslist;
	}
	
	public float rateStatus(List<CommentScore> clist)
	{
		float score = 0.0f;
		Iterator<CommentScore> iter = null;
		CommentScore cs = null;
		
		iter = clist.iterator();
		while(iter.hasNext())
		{
			cs = iter.next();
			if(cs.getScore() > 0)
			{
				score++;
			}
			if(cs.getScore() < 0)
			{
				score--;
			}
		}
		return score;
	}
		
	private float rateWord(List<String> words)
	{
		Iterator<String> iter = null;
		float score = 0.0f;
		String w = null;
		int polarity = 0;
		
//		//******************testing code ********************
//		File file = null;
//		BufferedWriter br = null;
//		try {
//			file = new File("D:\\tmp.txt");
//			br = new BufferedWriter(new FileWriter(file, true));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//******************testing code ********************
		
		iter = words.iterator();
		while(iter.hasNext())
		{
			w = iter.next();
			if(dict.containsKey(w))
			{
				polarity = dict.get(w);
			}
			if(polarity == 1)
			{
				score++;
			}
			else if(polarity == 2)
			{
				score--;
			}
//			//******************testing code ********************
//			try {
//				br.write(w);
//				br.write("\t");
//				//System.out.println("polarty: " + polarity);
//				br.write(String.valueOf(polarity));
//				br.write("\n");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//			//******************testing code ********************
		}

//			try {
//				if(br != null)
//					br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


		return score;
	}
	
	public static void loadDict()
	{
		File file = null;
		BufferedReader br = null;
		String str = null;
		String word = null;
		int polarity = 0;
		int middleIndex = 0;
		
		try {
			file = new File("D:\\dict1.txt");
			br = new BufferedReader(new FileReader(file));
			while((str = br.readLine()) != null)
			{
				middleIndex = str.indexOf('\t');
				word = str.substring(0, middleIndex);
				polarity = Integer.parseInt(str.substring(middleIndex + 1, str.length()));
				dict.put(word, polarity);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String args[])
	{
//		RateScore rs = new RateScore();
//		RateScore.loadDict();
//		System.out.println(RateScore.dict);
//		System.out.println(RateScore.dict.size());
	}
	
}
