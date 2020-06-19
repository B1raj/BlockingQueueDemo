package com.biraj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerService {

	static final int MAXTHREADS = 8;
	private static ComparisionResult result = new ComparisionResult();

	// 4. Run this main method to test your code.
	public static void main(String[] args) throws IOException {

		// Creating BlockingQueue of size 10
		BlockingQueue<ResponseMessage> queue = new ArrayBlockingQueue<>(10);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue); // starting producer to producemessages in queue
		new Thread(producer).start(); // starting consumer to
		// consume messages from queue
		new Thread(consumer).start();
		System.out.println("Producer and Consumer has been started");

		long startTime = System.nanoTime();
		String basePath = System.getProperty("user.dir");
		String seperator = System.getProperty("file.separator");
		String file1Path = basePath + seperator + "resources" + seperator + "Test1.txt";
		String file2Path = basePath + seperator + "resources" + seperator + "Test2.txt";
		int linesInFile = getFileLinesCount(file1Path);
		Utility util = new Utility();
		BufferedReader br1 = util.readFiles(file1Path);
		BufferedReader br2 = util.readFiles(file2Path);
		ExecutorService pool = Executors.newFixedThreadPool(MAXTHREADS);
		for (int i = 0; i < linesInFile; i++) {
			String urlFile1 = br1.readLine();
			String urlFile2 = br2.readLine();
			String outputFile1 = util.getResponse(urlFile1);
			String outputFile2 = util.getResponse(urlFile2);
			Runnable worker = new ComparisionService(urlFile1, urlFile2, outputFile1, outputFile2, result);
			pool.execute(worker);
			if (result.isFlag()) {
				System.out.println(urlFile1 + " equals " + urlFile2);
			} else {
				System.out.println(urlFile1 + " not equals " + urlFile2);
			}
		}
		pool.shutdown();
		while (!pool.isTerminated()) {
		}
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Total execution time in millis: " + elapsedTime / 1000000);
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
