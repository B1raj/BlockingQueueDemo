package com.biraj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Utility {
	public BufferedReader readFiles(String filePath) {
		FileReader reader;
		BufferedReader br = null;
		try {
			reader = new FileReader(filePath);

			br = new BufferedReader(reader);
		} catch (IOException io) {
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
		if(!validateURL(url)) {
			return output;
		}
		try {
			response = httpClient.execute(getRequest);
			br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String line = "";
			while ((line = br.readLine()) != null) {
				output = output + line;
			}
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
