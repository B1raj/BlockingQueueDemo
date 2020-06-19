package com.biraj;

import java.util.concurrent.BlockingQueue;

public class ComsumerPiyush implements Runnable {

	private BlockingQueue<ResponseMessage1> queue;
	private BlockingQueue<PageResponseMessage> pageQueue;

	public ComsumerPiyush(BlockingQueue<ResponseMessage1> queue, BlockingQueue<PageResponseMessage> pageQueue) {
		this.queue = queue;
		this.pageQueue = pageQueue;
	}

	public void run() {
		ResponseMessage1 message = null;
		PageResponseMessage pageMessage = null;
		try {
			while (!(message = queue.take()).isStopSignal() || !(pageMessage = pageQueue.take()).isStopSignal()) {
				try {
				compareResponse(message, pageMessage);
				}catch (NullPointerException e) {
					
				}
			}
		} catch (InterruptedException e) {
			System.err.println("Error occurred in consumer");
			e.printStackTrace();
		}
	}

	private void compareResponse(ResponseMessage1 message, PageResponseMessage pageMessage) {
		ResponseBeans response1 = message.getResponse1();
		ResponseBeans response2 = message.getResponse2();

		PageResponeBeans pageResponse1 = pageMessage.getResponse1();
		PageResponeBeans pageResponse2 = pageMessage.getResponse2();

		if (response1.equals(response2)) {
			print(message, " equals ");
		} else if (pageResponse1.equals(pageResponse2)) {
			print(pageMessage, " equals ");
		} else {
			print(pageMessage, " not equals ");
		}
	}

	private void print(ResponseMessage1 element, String decider) {
		System.out.println(element.getRequest1() + decider + element.getRequest2());
	}

	private void print(PageResponseMessage element, String decider) {
		System.out.println(element.getRequest1() + decider + element.getRequest2());
	}
}
