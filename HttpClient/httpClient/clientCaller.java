package httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;





public class clientCaller {
	
	public String doThehttpCalltest1(String p1, String p2) {

		String urlString = String.format(
				"http://localhost:8000/test1?p1=%s&p2=%s", p1, p2);
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
			
				return inline;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	
	public String sendHttpRequest(String p1, String p2, String p3) {
		String urlString = String.format(
				"http://localhost:8000/sendorder?p1=%s&p2=%s&p3=%s", p1, p2, p3);
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				//Problem with sending 2 HTTP requests fixed here
				// On line 55, changed url.openStream() to conn.getInputStream()
				Scanner sc = new Scanner(conn.getInputStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				
				System.out.println("Value of Inline" + inline);
			
				return inline;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	

}
