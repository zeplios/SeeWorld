package edu.tju.ina.seeworld.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PtConnector {
	
	public static String PT_URL = "http://202.113.13.170/api_login.php";
	
	/**
	 * 向pt发请求验证用户名密码
	 * @param url 请求的URI地址，现在就用本类的PT_URL就可以了
	 * @param param 问号之后的参数字符串
	 * @return 是否匹配
	 * @author zhfch
	 */
	public static Boolean query(String url, String param){
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			URL u = new URL(urlName);
			URLConnection connection = u.openConnection();
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(result.equals("true")){
			return true;
		}
		else{
			return false;
		}
	}
	
}
