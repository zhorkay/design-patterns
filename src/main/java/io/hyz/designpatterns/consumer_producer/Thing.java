package io.hyz.designpatterns.consumer_producer;

public class Thing {
    private String message;
    private boolean empty = true;

    public synchronized String poll() {
        while (this.empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.empty = true;
        notifyAll();
        return this.message;

    }

    public synchronized void put(String message) {
        while (!this.empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.message = message;
        this.empty = false;
        notifyAll();
    }
}
