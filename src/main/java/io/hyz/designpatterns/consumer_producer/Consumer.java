package io.hyz.designpatterns.consumer_producer;

import java.sql.Timestamp;
import java.util.Random;

public class Consumer implements Runnable {
    private final int maxWaitingSeconds;
    private final Thing thing;

    public Consumer(int maxWaitingSeconds, Thing thing) {
        this.maxWaitingSeconds = maxWaitingSeconds;
        this.thing = thing;
    }

    public void run() {
        Random random = new Random();
        for (String message = thing.poll(); !message.equals("DONE"); message = thing.poll()) {
            System.out.format("MESSAGE RECEIVED: %s at %ts%n", message, new Timestamp(new java.util.Date().getTime()));
            try {
                Thread.sleep(random.nextInt(1000 * this.maxWaitingSeconds));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
