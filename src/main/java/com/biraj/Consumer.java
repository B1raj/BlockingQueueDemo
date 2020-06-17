package com.biraj;


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

    private void compareResponse(ResponseMessage element) {

        ApiResponse response1 = element.getResponse1();
        ApiResponse response2 = element.getResponse2();

        //may need tweaking ... will see later
        if(response1.equals(response2)){
           print(element, "equals");
        }else{
            print(element,"not equals");
        }
    }

    private void print(ResponseMessage element, String decider) {
        System.out.println(element.getRequest1() + decider + element.getRequest2());
    }
}
