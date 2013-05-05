package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Comment;
import model.CommentScore;
import model.Status;
import model.StatusScore;
import util.RateScore;
import DAO.CommentDAO;
import DAO.StatusDAO;

public class Test {

	public static void main(String args[])
	{
		test3();
		
	}
	
	public static void test()
	{
		try {
			URL url = new URL("https://api.weibo.com/oauth2/authorize?client_id=3233936646&redirect_uri=http://apps.weibo.com/datasetswbb&response_type=code&state=&scope=");  
			URLConnection uconn = url.openConnection();
			//HttpUrlConnection huconn = (HttpUrlConnection) uconn;
			//uconn.connect();
			//Map<String, List<String>> map = uconn.getHeaderFields();
			//Map<String, List<String>> map2 = uconn.getRequestProperties();
			uconn.setDoOutput(true);
			//uconn.setRequestMethod("");
			Object o = uconn.getContent();
			System.out.println(uconn.getRequestProperty("code"));
			//System.out.println(uconn.getHeaderField("code"));
			//System.out.println(uconn.getURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test2()
	{
		try {
			URL url = new URL("https://api.weibo.com/oauth2/authorize?client_id=3233936646&redirect_uri=http://apps.weibo.com/datasetswbb&response_type=code&state=&scope=");
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection)urlConnection;
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.connect();
			int len = urlConnection.getContentLength();
			if(len > 0)
			{
				InputStream input = urlConnection.getInputStream();
				int i = len;
				int c = 0;
				while((c=input.read())!=-1 && (--i>0))
				{
					System.out.print((char)c);
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test3()
	{
		try {
			URL url = new URL("https://api.weibo.com/oauth2/authorize?client_id=3233936646&redirect_uri=http://apps.weibo.com/datasetswbb&response_type=code&state=&scope=");  
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection)urlConnection;
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.connect();
			OutputStreamWriter out = new OutputStreamWriter(httpUrlConnection.getOutputStream(), "utf-8");  
			out.write("action=login&" +
					"display=default&" +
					"withOfficalFlag=0&" +
					"withOfficalAccount=&" +
					"scope=&" +
					"ticket=&" +
					"isLoginSina=&" +
					"response_type=code&" +
					"regCallback=https%3A%2F%2Fapi.weibo.com%2F2%2Foauth2%2Fauthorize%3Fclient_id%3D3233936646%26response_type%3Dcode%26display%3Ddefault%26redirect_uri%3Dhttp%3A%2F%2Fapps.weibo.com%2Fdatasetswbb%26from%3D%26with_cookie%3D&" +
					"redirect_uri=http://apps.weibo.com/datasetswbb&" +
					"client_id=3233936646&" +
					"appkey62=5dgw6i&" +
					"state=&" +
					"verifyToken=null&" +
					"from=&" +
					"userId=swb0802@sina.com&" +
					"passwd=swbjiusw321"); // 向页面传递数据。post的关键所在！  
			out.flush();  
			out.close();  
			// 一旦发送成功，用以下方法就可以得到服务器的回应：  
			String sCurrentLine;  
			String sTotalString;  
			sCurrentLine = "";  
			sTotalString = "";  
			InputStream l_urlStream;  
			l_urlStream = httpUrlConnection.getInputStream();  
			// 传说中的三层包装阿！  
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(  
			        l_urlStream));  
			while ((sCurrentLine = l_reader.readLine()) != null) {  
			    sTotalString += sCurrentLine + "\r\n";  
  
			}  
			System.out.println(sTotalString);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
