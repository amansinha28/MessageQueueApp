package com.assignment.messagequeue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A simple thread-safe message queue implementation.
 */
public class MessageQueue {
    private final Queue<String> queue = new LinkedList<>();

    /**
     * Adds a message to the queue.
     * @param message The message to be added.
     */
    public synchronized void enqueue(String message) {
        queue.add(message);
        notifyAll(); // Notify consumers that a new message is available
    }

    /**
     * Retrieves and removes a message from the queue, waiting if necessary.
     * @return The message from the queue.
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public synchronized String dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait until a message is available
        }
        return queue.poll();
    }
}