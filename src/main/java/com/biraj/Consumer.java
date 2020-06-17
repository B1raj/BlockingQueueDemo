package com.biraj;



import java.util.Objects;
import java.util.concurrent.BlockingQueue;


public class Consumer implements Runnable{

    private BlockingQueue<ResponseMessage> queue;

    public Consumer(BlockingQueue<ResponseMessage> q){
        this.queue=q;
    }

    @Override
    public void run() {
        try{
            ResponseMessage element = null;
            ApiResponse response1 = null;
            ApiResponse response2 = null;

            //consuming ResponseMessages until stop ResponseMessage is received
            while(!(element = queue.take()).getStopSignal()){
                compareResponse(element);
            }

            System.out.println("Stop Consumer");
        }catch(InterruptedException e) {
            System.err.println("Error occurred in consumer");
            e.printStackTrace();
        }
    }

    private boolean compareResponse(ResponseMessage element) {

        ApiResponse response1 = element.getResponse1();
        ApiResponse response2 = element.getResponse2();

        if(Objects.equals(response1,response2)){
           print(element, "equals");
        }else{
            print(element,"not equals");
        }

        return false;


    }

    private void print(ResponseMessage element, String decider) {
        System.out.println(element.getRequest1() + decider + element.getRequest2());
    }
}
