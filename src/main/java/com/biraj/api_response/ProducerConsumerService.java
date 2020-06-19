package com.biraj.api_response;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerService {


	// 4. Run this main method to test your code.
	public static void main(String[] args) throws IOException {

		// Creating BlockingQueue of size 10
		BlockingQueue<ResponseMessage> queue = new ArrayBlockingQueue<>(10);
		ApiProducer producer = new ApiProducer(queue);
		ApiConsumer consumer = new ApiConsumer(queue); // starting producer to producemessages in queue
		new Thread(producer).start(); // starting consumer to
		// consume messages from queue
		new Thread(consumer).start();
		System.out.println("Producer and Consumer has been started");
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
