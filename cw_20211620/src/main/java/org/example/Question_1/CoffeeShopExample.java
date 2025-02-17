package org.example.Question_1;

public class CoffeeShopExample {
    public static void main(String[] args) {
        CoffeeShop coffeeShop = new CoffeeShop(5);

        Thread t1 = new Thread(new Barista(coffeeShop));
        Thread t2 = new Thread(new Customer(coffeeShop));

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
