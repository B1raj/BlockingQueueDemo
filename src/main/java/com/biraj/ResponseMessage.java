package com.biraj;

import java.util.Comparator;

public class ResponseMessage  implements Comparator {
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


    @Override
    public int compare(Object o1, Object o2) {

        ApiResponse s1=(ApiResponse)o1;
        ApiResponse s2=(ApiResponse)o2;
        if((s1.getId()==s2.getId())&& s1.getPage() == s2.getPage())
            return 0;
        else
            return -1;
    }
}