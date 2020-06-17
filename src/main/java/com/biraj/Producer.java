package com.biraj;



import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<ResponseMessage> queue;

    public Producer(BlockingQueue<ResponseMessage> q){
        this.queue=q;
    }
    @Override
    public void run() {
        //produce ResponseMessages
        for(int i=0; i<100; i++){
            ApiResponse response1= new ApiResponse();
            ApiResponse response2= new ApiResponse();
            ResponseMessage msg = new ResponseMessage(response1,response2);
            try {
                queue.put(msg);
              /*  System.out.println("Produced "+msg.getMsg());*/
            } catch (InterruptedException e) {
                System.err.println("Error occurred in producer");
                e.printStackTrace();
            }

        }
        ResponseMessage stopMessage = new ResponseMessage(true);
        try {
            queue.put(stopMessage);
        } catch (InterruptedException e) {
            System.err.println("Error occurred in ending producer");
            e.printStackTrace();
        }

    }

}
