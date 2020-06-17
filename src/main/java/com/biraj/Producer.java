package com.biraj;



import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<ResponseMessage> queue;

    public Producer(BlockingQueue<ResponseMessage> q){
        this.queue=q;
    }
    @Override
    public void run() {
        // 1. we need to run this loop for the number of recoreds in the file.
        for(int i=0; i<100; i++){
            // 2. make rest api call here for file 1 and deserealize the json to correct pojo
            ApiResponse response1= new ApiResponse();
            // 3. make rest api call here for file 2 and deserealize the json to correct pojo
            ApiResponse response2= new ApiResponse();
            ResponseMessage msg = new ResponseMessage(response1,response2);
            try {
                queue.put(msg);
            } catch (InterruptedException e) {
                System.err.println("Error occurred in producer");
                e.printStackTrace();
            }
        }
        //stop signal
        ResponseMessage stopMessage = new ResponseMessage(true);
        try {
            queue.put(stopMessage);
        } catch (InterruptedException e) {
            System.err.println("Error occurred in ending producer");
            e.printStackTrace();
        }

    }

}
