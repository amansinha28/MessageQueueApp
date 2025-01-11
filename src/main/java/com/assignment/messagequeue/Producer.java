package com.assignment.messagequeue;

import java.util.Random;

/**
 * A producer that generates messages and adds them to the message queue.
 */
public class Producer implements Runnable {
    private final MessageQueue messageQueue;
    private final int messageCount;

    /**
     * Constructor for the Producer.
     * @param messageQueue The message queue to which messages will be added.
     * @param messageCount The number of messages to produce.
     */
    public Producer(MessageQueue messageQueue, int messageCount) {
        this.messageQueue = messageQueue;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < messageCount; i++) {
            String message = "Message " + i;
            messageQueue.enqueue(message);
            System.out.println("Produced: " + message);
            try {
                Thread.sleep(random.nextInt(100)); // Simulate variable production time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
