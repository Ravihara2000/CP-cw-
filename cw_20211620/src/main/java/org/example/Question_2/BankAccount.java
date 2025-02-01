package org.example.Question_2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {
    private final int id;
    private double balance;
    private final ReadWriteLock lock;

    public BankAccount(int id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
        this.lock= new ReentrantReadWriteLock(true);
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        lock.readLock().lock(); //Acquire read lock only for safe concurrent reads
        try{
            return balance;
        }finally {
            lock.readLock().unlock();
        }
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance-=amount;
    }

    //Locks
    public void lockWrite(){
        lock.writeLock().lock();
    }
    public void unlockWrite(){
        lock.writeLock().unlock();
    }
}
