package com.rahcode.typespeedjava;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static String fetchAPI(String fetchURL) {
		try {
			URL url = new URL(fetchURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			StringBuilder response = new StringBuilder();
			
			while((output = br.readLine()) != null) {
				response.append(output);
			}
			
			conn.disconnect();
			return response.toString();
		} catch (Exception e) {
            e.printStackTrace();
        }
		return fetchURL;
	}

	public static void main(String[] args) {
		String response = fetchAPI("https://api.quotable.io/random?tags=technology");
		System.out.println(response);
	}

}
