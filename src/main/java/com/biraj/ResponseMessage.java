package com.biraj;

import java.util.Comparator;

public class ResponseMessage {
    private String request1;
    private String request2;
    private ApiResponse response1;
    private ApiResponse response2;
    private boolean stopSignal;

    public ResponseMessage(){

    }

    public ResponseMessage(boolean stopSignal){
        this.stopSignal = stopSignal;
    }

    public ResponseMessage(ApiResponse response1, ApiResponse response2){
        this.response1=response1;
        this.response2=response2;
    }


    public ApiResponse getResponse1() {
        return response1;
    }

    public ApiResponse getResponse2() {
        return response2;
    }


    public String getRequest1() {
        return request1;
    }

    public String getRequest2() {
        return request2;
    }

    public void setStopSignal(boolean stopSignal) {
        this.stopSignal = stopSignal;
    }

    public boolean getStopSignal() {
        return stopSignal;
    }



}