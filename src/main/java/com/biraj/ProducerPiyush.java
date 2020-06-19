package com.biraj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProducerPiyush implements Runnable {
	private BlockingQueue<ResponseMessage1> queue;
	// private BlockingQueue<PageResponseMessage> pageQueue;

	public ProducerPiyush(BlockingQueue<ResponseMessage1> queue) {
		this.queue = queue;
	}

	public int getFileLinesCount(String filePath) {
		BufferedReader reader;
		int lines = 0;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			while (reader.readLine() != null)
				lines++;
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}

	public void run() {
		String basePath = System.getProperty("user.dir");
		String seperator = System.getProperty("file.separator");
		String file1Path = basePath + seperator + "resources" + seperator + "Test1.txt";
		String file2Path = basePath + seperator + "resources" + seperator + "Test2.txt";
		int linesInFile = getFileLinesCount(file1Path);
		ObjectMapper mapper = new ObjectMapper();
		Utility util = new Utility();
		BufferedReader br1 = util.readFiles(file1Path);
		BufferedReader br2 = util.readFiles(file2Path);
		ResponseMessage1 single = new ResponseMessage1();
		for (int i = 0; i < linesInFile; i++) {
			String urlFile1 = null;
			String urlFile2 = null;
			PageResponeBeans pageValueFile1 = null;
			PageResponeBeans pageValueFile2 = null;
			ResponseBeans valueFile1 = null;
			ResponseBeans valueFile2 = null;
			try {
				urlFile1 = br1.readLine();
				String outputFile1 = util.getResponse(urlFile1);
				single.setRequest1(urlFile1);
				if (urlFile1.contains("?page=")) {
					pageValueFile1 = mapper.readValue(outputFile1, PageResponeBeans.class);
				} else if (!urlFile1.contains("?page=")) {
					valueFile1 = mapper.readValue(outputFile1, ResponseBeans.class);
				}
				urlFile2 = br2.readLine();
				String outputFile2 = util.getResponse(urlFile2);
				single.setRequest2(urlFile2);
				if (urlFile2.contains("?page=")) {
					pageValueFile2 = mapper.readValue(outputFile2, PageResponeBeans.class);
				} else if (!urlFile1.contains("?page=")) {
					valueFile2 = mapper.readValue(outputFile2, ResponseBeans.class);
				}
				ResponseMessage1 message = new ResponseMessage1(valueFile1, valueFile2, pageValueFile1, pageValueFile2);
				queue.put(message);
				// pageQueue.put(pageMessage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.err.println("Error occurred in producer");
				e.printStackTrace();
			}

		}

		ResponseMessage1 stopMessage = new ResponseMessage1(true);
		try {
			queue.put(stopMessage);
			// pageQueue.put(pageStopMessage);
		} catch (InterruptedException e) {
			System.err.println("Error occurred in ending producer");
			e.printStackTrace();
		}
	}
}
