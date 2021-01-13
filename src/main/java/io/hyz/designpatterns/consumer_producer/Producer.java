package io.hyz.designpatterns.consumer_producer;

import java.sql.Timestamp;
import java.util.Random;

public class Producer implements Runnable {

    private static final String[] messages = {
            "START process", "SUSPEND process", "Continue process", "END process"
    };
    private final int maxWaitingSeconds;
    private final Thing thing;

    public Producer(int maxWaitingSeconds, Thing thing) {
        this.maxWaitingSeconds = maxWaitingSeconds;
        this.thing = thing;
    }

    public void run() {
        Random random = new Random();
        for (String message : messages) {
            System.out.format("MESSAGE PREPARED: %s at %ts%n", message, new Timestamp(new java.util.Date().getTime()));
            thing.put(message);
            System.out.format("MESSAGE SENT: %s at %ts%n", message, new Timestamp(new java.util.Date().getTime()));
            try {
                Thread.sleep(1000 * this.maxWaitingSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        thing.put("DONE");
    }
}
