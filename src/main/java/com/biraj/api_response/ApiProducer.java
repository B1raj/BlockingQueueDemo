package com.biraj.api_response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ApiProducer implements Runnable {

    private BlockingQueue<ResponseMessage> queue;

    public ApiProducer(BlockingQueue<ResponseMessage> q){
        this.queue=q;
    }
    @Override
    public void run() {
        //start signal
        prccessUrl();
        //stop signal
        endProcess();
    }

    private void endProcess() {
        ResponseMessage stopMessage = new ResponseMessage(true);
        try {
            queue.put(stopMessage);
        } catch (InterruptedException e) {
            System.err.println("Error occurred in ending producer");
            e.printStackTrace();
        }
    }

    private void prccessUrl() {

        long startTime = System.nanoTime();
        String basePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String file1Path = basePath + separator + "resources" + separator + "Test1.txt";
        String file2Path = basePath + separator + "resources" + separator + "Test2.txt";
        int linesInFile = getFileLinesCount(file1Path);
        HttpUtility util = new HttpUtility();
        BufferedReader br1 = util.readFiles(file1Path);
        BufferedReader br2 = util.readFiles(file2Path);


        for (int i = 0; i < linesInFile; i++) {

            String urlFile1 = null;
            String urlFile2 = null;
            try {
                urlFile1 = br1.readLine();
                urlFile2 = br2.readLine();
            } catch (IOException e) {
                System.err.println("Error occurred while reading file.");
                e.printStackTrace();
            }

            String response1 = util.getResponse(urlFile1);
            String response2 = util.getResponse(urlFile2);

            boolean response = false;
            if (urlFile1.equals(urlFile2)) {
                response = true;
            }
            ResponseMessage responseMessage = new ResponseMessage(urlFile1, urlFile2, response1, response2, false, response);
            try {
                queue.put(responseMessage);
            } catch (InterruptedException e) {
                System.err.println("Error occurred in creating producer");
                e.printStackTrace();
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
            System.err.println("Error occurred while reading file.");
            e.printStackTrace();
        }
        return lines;
    }


}
