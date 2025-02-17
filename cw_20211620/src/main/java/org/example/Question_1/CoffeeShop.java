package org.example.Question_1;

import java.util.LinkedList;
import java.util.Queue;

public class CoffeeShop {
    private final int capacity;
    private Queue<String> orderQueue;

    public CoffeeShop(int capacity) {
        this.capacity = capacity;
        this.orderQueue = new LinkedList<>();
    }

    //
    public synchronized void takeCoffeeOrder(int order){
        while(orderQueue.size()==capacity){
            try {
                System.out.println("Coffee shop order queue is full");
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderQueue.offer("added Order "+order);
        System.out.println("Order "+order+" added to queue");
        notifyAll();
    }

    public synchronized void serveCoffee(){
        while(orderQueue.isEmpty()){
            try {
                System.out.println("Coffee shop order queue is empty");
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String order = orderQueue.poll();
        System.out.println("Serving "+order);
        notifyAll();
    }
}
