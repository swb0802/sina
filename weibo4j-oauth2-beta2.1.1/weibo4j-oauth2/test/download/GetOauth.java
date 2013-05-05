package download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.examples.oauth2.Log;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class GetOauth {
//
//	public static void main(String args[]) throws WeiboException, IOException{
//		System.out.println(getAccessToken());
//		
//	}
	
	
	public AccessToken getAccessToken() throws WeiboException, IOException
	{
		Oauth oauth = new Oauth();
		System.out.println(oauth.authorize("code", "", ""));
		BareBonesBrowserLaunch.openURL(oauth.authorize("code", "", ""));
		System.out.println(oauth.authorize("code", "", ""));
		System.out.println("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		Log.logInfo("code:" + code);
		try{
			
			return oauth.getAccessTokenByCode(code);
		} catch(WeiboException e){
			if(401 == e.getStatusCode())
			{
				Log.logInfo("Unable to get the access token.");
			}
			else
			{
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	public static void main(String args[])
	{
		GetOauth go = new GetOauth();
		try {
			go.getAccessToken();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

/*

public class OAuth4Code {
	public static void main(String [] args) throws WeiboException, IOException{
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code",args[0],args[1]));
		System.out.println(oauth.authorize("code",args[0],args[1]));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		Log.logInfo("code: " + code);
		try{
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				Log.logInfo("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		}
	}

}


*/