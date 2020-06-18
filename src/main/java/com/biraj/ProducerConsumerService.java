package com.biraj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProducerConsumerService {

    //4. Run this main method to test your code.
    public static void main(String[] args) throws IOException {
       /* //Creating BlockingQueue of size 10
        BlockingQueue<ResponseMessage> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        //starting producer to produce messages in queue
        new Thread(producer).start();
        //starting consumer to consume messages from queue
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");*/
    	String basePath = System.getProperty("user.dir");
		String seperator = System.getProperty("file.separator");
		String file1Path = basePath + seperator + "resources" + seperator + "Test1.txt";
		String file2Path = basePath + seperator + "resources" + seperator + "Test2.txt";
		int linesInFile = getFileLinesCount(file1Path);
		Utility util = new Utility();
		ObjectMapper mapper = new ObjectMapper();
		PageResponeBeans pageValue = null;
		ResponseBeans value = null;
		BufferedReader br1 = util.readFiles(file1Path);
		BufferedReader br2 = util.readFiles(file2Path);
		for (int i = 0; i < linesInFile; i++) {
			String url = br1.readLine();
			System.out.println("URL::::"+url+" "+url.contains("?page="));
			String output = util.getResponse(url);
			if (url.contains("?page=")) {
				pageValue = mapper.readValue(output, PageResponeBeans.class);
				System.out.println("OBJECT from Line " + i + " = " + pageValue);
			} else {
				value = mapper.readValue(output, ResponseBeans.class);
				System.out.println("OBJECT from Line " + i + " = " + value);
			}
			
		}
	}

	public static int getFileLinesCount(String filePath) {
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

}
