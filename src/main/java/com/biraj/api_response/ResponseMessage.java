package com.biraj.api_response;

public class ResponseMessage {
	private String request1;
	private String request2;
	private String response1;
	private String response2;
	private boolean stopSignal;
	private boolean response;

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public ResponseMessage(String request1, String request2, String response1, String response2, boolean stopSignal, boolean response) {
		this.request1 = request1;
		this.request2 = request2;
		this.response1 = response1;
		this.response2 = response2;
		this.stopSignal = stopSignal;
		this.response = response;
	}










	public ResponseMessage(){

	}

	public ResponseMessage(boolean stopSignal){
		this.stopSignal = stopSignal;
	}

	public ResponseMessage(String response1, String response2){
		this.response1=response1;
		this.response2=response2;
	}

	public String getResponse1() {
		return response1;
	}

	public String getResponse2() {
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