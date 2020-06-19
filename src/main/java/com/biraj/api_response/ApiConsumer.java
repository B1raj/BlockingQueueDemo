package com.biraj.api_response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;


public class ApiConsumer implements Runnable{

    private BlockingQueue<ResponseMessage> queue;

    public ApiConsumer(BlockingQueue<ResponseMessage> q){
        this.queue=q;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();
        System.out.println("Consumer started" +(startTime));
        try{
            ResponseMessage element = null;
            //consuming ResponseMessages until stop ResponseMessage is received
            while(!(element = queue.take()).getStopSignal()){
                compareResponse(element);
            }

            System.out.println("Stop Consumer");

            long stopStart = System.currentTimeMillis();

            System.out.println("Consumer ended" +(stopStart-startTime));
        }catch(InterruptedException e) {
            System.err.println("Error occurred in consumer");
            e.printStackTrace();
        }
    }

    private void compareResponse(ResponseMessage element) {

        if(element.isResponse()){
            print(element, " equals ");
        }
        else if ((Objects.isNull(element.getResponse1()) && Objects.nonNull(element.getResponse2()))
        || (Objects.nonNull(element.getResponse1()) && Objects.isNull(element.getResponse2()))
        || (element.getRequest1().contains("?page=") && !element.getRequest2().contains("?page="))
                || (!element.getRequest1().contains("?page=") && element.getRequest2().contains("?page="))
        ){
            print(element, " not equals ");
        }else{
            compareObjects(element);
        }
    }

    private void compareObjects(ResponseMessage element) {

        String urlFile1 = element.getRequest1();
        String urlFile2 = element.getRequest2();
        ObjectMapper mapper = new ObjectMapper();

        try {
        if(urlFile1.contains("?page=")){
                PageResponeBeans pageValueFile1 = mapper.readValue(element.getResponse1(), PageResponeBeans.class);
                PageResponeBeans pageValueFile2 = mapper.readValue(element.getResponse2(), PageResponeBeans.class);
                print(element, pageValueFile1.equals(pageValueFile2) ? " equals ":" not equals ");

        }else {
                ResponseBeans valueFile1 = mapper.readValue(element.getResponse1(), ResponseBeans.class);
                ResponseBeans valueFile2 = mapper.readValue(element.getResponse2(), ResponseBeans.class);
                print(element, valueFile1.equals(valueFile2) ? " equals ":" not equals ");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print(ResponseMessage element, String decider) {
        System.out.println(element.getRequest1() + decider + element.getRequest2());
    }
}
