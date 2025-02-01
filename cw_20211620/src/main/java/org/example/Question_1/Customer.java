package org.example.Question_1;

public class Customer implements Runnable{
    private CoffeeShop coffeeShop;

    public Customer(CoffeeShop coffeeShop) {
        this.coffeeShop = coffeeShop;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 10; i++) {
            coffeeShop.serveCoffee();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
