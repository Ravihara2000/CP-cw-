package org.example.Question_1;

public class Barista implements Runnable{
    private CoffeeShop coffeeShop;

    public Barista(CoffeeShop coffeeShop) {
        this.coffeeShop = coffeeShop;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 10; i++) {
            coffeeShop.takeCoffeeOrder(i);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
