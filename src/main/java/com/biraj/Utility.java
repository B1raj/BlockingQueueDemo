package com.biraj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Utility {
	public BufferedReader readFiles(String filePath) {
		Map<String, String> map = new HashMap<String, String>();
		FileReader reader;
		BufferedReader br = null;
		try {
			reader = new FileReader(filePath);
			br = new BufferedReader(reader);
			/*String line;
			int count = 1;
			System.out.println("URL is>>>>>"+br.readLine());*/
			/*
			 * while ((line = br.readLine()) != null) { if (validateURL(line)) {
			 * map.put("line" + count, getResponse(line)); } else { map.put("line" + count,
			 * "Not Valid URL"); } count++; }
			 */
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return br;
	}

	public boolean validateURL(String url) {
		try {
			URL obj = new URL(url);
			obj.toURI();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (URISyntaxException e) {
			return false;
		}
	}

	public String getResponse(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		BufferedReader br = null;
		String output = "";
		try {
			response = httpClient.execute(getRequest);
			br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			System.out.println("Output from Server ....");
			String line = "";
			while ((line = br.readLine()) != null) {
				output = output + line;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
}
