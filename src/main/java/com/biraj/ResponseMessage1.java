package com.biraj;

public class ResponseMessage1 {
	private String request1;
	private String request2;
	private ResponseBeans response1;
	private ResponseBeans response2;
	private PageResponeBeans pageResponse1;
	private PageResponeBeans pageResponse2;

	public PageResponeBeans getPageResponse1() {
		return pageResponse1;
	}

	public ResponseMessage1(ResponseBeans response1, ResponseBeans response2, PageResponeBeans pageResponse1,
			PageResponeBeans pageResponse2) {
		this.response1 = response1;
		this.response2 = response2;
		this.pageResponse1 = pageResponse1;
		this.pageResponse2 = pageResponse2;
	}
	
	public ResponseMessage1(boolean stopSignal) {
		this.stopSignal = stopSignal;
	}

	public void setPageResponse1(PageResponeBeans pageResponse1) {
		this.pageResponse1 = pageResponse1;
	}

	public PageResponeBeans getPageResponse2() {
		return pageResponse2;
	}

	public void setPageResponse2(PageResponeBeans pageResponse2) {
		this.pageResponse2 = pageResponse2;
	}

	private boolean stopSignal;

	public ResponseMessage1() {
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

	public ResponseBeans getResponse1() {
		return response1;
	}

	public void setResponse1(ResponseBeans response1) {
		this.response1 = response1;
	}

	public ResponseBeans getResponse2() {
		return response2;
	}

	public void setResponse2(ResponseBeans response2) {
		this.response2 = response2;
	}

	public boolean isStopSignal() {
		return stopSignal;
	}

	public void setStopSignal(boolean stopSignal) {
		this.stopSignal = stopSignal;
	}
}
