package io.hyz.designpatterns.consumer_producer;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        Thing thing = new Thing();
        Producer producer = new Producer(1, thing);
        Consumer consumer = new Consumer(7, thing);
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();
    }
}
