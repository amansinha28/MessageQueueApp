package com.assignment.messagequeue;

/**
 * A consumer that processes messages from the message queue.
 */
public class Consumer implements Runnable {
    private final MessageQueue messageQueue;
    private int successCount = 0;
    private int errorCount = 0;

    /**
     * Constructor for the Consumer.
     * @param messageQueue The message queue from which messages will be consumed.
     */
    public Consumer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = messageQueue.dequeue();
                processMessage(message);
                successCount++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Exit on interrupt
            } catch (Exception e) {
                errorCount++;
                System.err.println("Error processing message: " + e.getMessage());
            }
        }
    }

    /**
     * This is important for ensuring that the application can recover from 
     * failures gracefully and continue processing other messages.
     */
    private void processMessage(String message) throws Exception {
        if (Math.random() < 0.1) { // Simulate a 10% chance of failure
            throw new Exception("Processing failed for " + message);
        }
        System.out.println("Consumed: " + message);
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getErrorCount() {
        return errorCount;
    }
}
