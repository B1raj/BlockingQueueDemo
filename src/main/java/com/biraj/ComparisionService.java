package com.biraj;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ComparisionService implements Runnable {

	String outputFile1;
	String outputFile2;
	String urlFile1;
	String urlFile2;
	ComparisionResult result;

	public ComparisionService(String urlFile1, String urlFile2, String outputFile1, String outputFile2,
			ComparisionResult result) {
		this.outputFile1 = outputFile1;
		this.outputFile2 = outputFile2;
		this.urlFile1 = urlFile1;
		this.urlFile2 = urlFile2;
		this.result = result;
	}

	public void run() {
		System.out.println(String.format("starting expensive task thread %s", Thread.currentThread().getName()));
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (urlFile1.contains("?page=") && urlFile2.contains("?page=")) {
				PageResponeBeans pageValueFile1 = mapper.readValue(outputFile1, PageResponeBeans.class);
				PageResponeBeans pageValueFile2 = mapper.readValue(outputFile2, PageResponeBeans.class);
				if (pageValueFile1.equals(pageValueFile2)) {
					result.setFlag(true);
					// flag = true;
				}

			} else if (!urlFile1.contains("?page=") && !urlFile2.contains("?page=")) {
				ResponseBeans valueFile1 = mapper.readValue(outputFile1, ResponseBeans.class);
				ResponseBeans valueFile2 = mapper.readValue(outputFile2, ResponseBeans.class);
				if (valueFile1.equals(valueFile2)) {
					result.setFlag(true);
				}
			} else {
				result.setFlag(false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
