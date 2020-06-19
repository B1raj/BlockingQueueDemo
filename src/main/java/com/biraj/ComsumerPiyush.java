package com.biraj;

import java.util.concurrent.BlockingQueue;

public class ComsumerPiyush implements Runnable {

	private BlockingQueue<ResponseMessage1> queue;

	public ComsumerPiyush(BlockingQueue<ResponseMessage1> queue) {
		this.queue = queue;
	}

	public void run() {
		ResponseMessage1 message = null;
		try {
			while (!(message = queue.take()).isStopSignal()) {
				try {
					compareResponse(message);
				} catch (NullPointerException e) {

				}
			}
		} catch (InterruptedException e) {
			System.err.println("Error occurred in consumer");
			e.printStackTrace();
		}
	}

	private void compareResponse(ResponseMessage1 message) {
		ResponseBeans response1 = message.getResponse1();
		ResponseBeans response2 = message.getResponse2();

		PageResponeBeans pageResponse1 = message.getPageResponse1();
		PageResponeBeans pageResponse2 = message.getPageResponse2();

		if (response1.equals(response2) || pageResponse1.equals(pageResponse2)) {
			print(message, " equals ");
		} else {
			print(message, " not equals ");
		}
	}

	private void print(ResponseMessage1 element, String decider) {
		System.out.println(element.getRequest1() + decider + element.getRequest2());
	}
}
