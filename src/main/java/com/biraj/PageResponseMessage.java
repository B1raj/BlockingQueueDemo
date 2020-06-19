package com.biraj;

public class PageResponseMessage {
	private String request1;
	private String request2;
	private PageResponeBeans response1;
	private PageResponeBeans response2;
	private boolean stopSignal;

	public PageResponseMessage() {
		// TODO Auto-generated constructor stub
	}

	public PageResponseMessage(boolean stopSignal) {
		this.stopSignal = stopSignal;
	}

	public PageResponseMessage(PageResponeBeans response1, PageResponeBeans response2) {
		this.response1 = response1;
		this.response2 = response2;
	}

	public String getRequest1() {
		return request1;
	}

	public void setRequest1(String request1) {
		this.request1 = request1;
	}

	public String getRequest2() {
		return request2;
	}

	public void setRequest2(String request2) {
		this.request2 = request2;
	}

	public PageResponeBeans getResponse1() {
		return response1;
	}

	public void setResponse1(PageResponeBeans response1) {
		this.response1 = response1;
	}

	public PageResponeBeans getResponse2() {
		return response2;
	}

	public void setResponse2(PageResponeBeans response2) {
		this.response2 = response2;
	}

	public boolean isStopSignal() {
		return stopSignal;
	}

	public void setStopSignal(boolean stopSignal) {
		this.stopSignal = stopSignal;
	}
}
