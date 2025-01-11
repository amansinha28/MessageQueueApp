package com.assignment.messagequeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagequeueApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagequeueApplication.class, args);
		
		//to run the messageQueueJob
		runMessageQueue();
	}

	public static void runMessageQueue() {
		MessageQueue messageQueue = new MessageQueue();
        Producer producer = new Producer(messageQueue, 20);
        Consumer consumer = new Consumer(messageQueue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join(); // Wait for the producer to finish
            consumerThread.interrupt(); // Stop the consumer
            consumerThread.join(); // Wait for the consumer to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Total messages processed successfully: " + consumer.getSuccessCount());
        System.out.println("Total errors encountered: " + consumer.getErrorCount());
	}
}
