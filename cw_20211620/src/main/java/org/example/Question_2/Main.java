package org.example.Question_2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BankAccount ac1 = new BankAccount(1,1000);
        BankAccount ac2 = new BankAccount(2,1000);
        BankAccount ac3 = new BankAccount(3,1000);

        Transaction transaction = new Transaction(Arrays.asList(ac1,ac2,ac3));

        System.out.println("-----------------------------------------------------");
        System.out.println("Balances Before Transactions: ");
        transaction.printAccountBalance();
        System.out.println("-----------------------------------------------------");

        Thread t1 = new Thread(()->{
            transaction.transfer(1,2,100);
            System.out.println("Successfully transferred");
        });

        Thread t2 = new Thread(()->{
            transaction.transfer(2,3,100);
            System.out.println("Successfully transferred");
        });

        Thread t3 = new Thread(()->{
            transaction.transfer(3,1,50);
            System.out.println("Successfully transferred");
        });

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Balances After Transactions: ");
        transaction.printAccountBalance();
        System.out.println("-----------------------------------------------------");
    }
}
