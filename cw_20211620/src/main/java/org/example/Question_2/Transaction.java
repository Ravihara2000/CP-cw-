package org.example.Question_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
    private final Map<Integer, BankAccount> accounts;

    //initialize the system with a list of bank accounts.
    public Transaction(List<BankAccount> accountList){
        accounts = new HashMap<>();
        for(BankAccount account : accountList){
            accounts.put(account.getId(), account);
        }
    }
    // Transfers money between two accounts in a thread-safe manner.
    public boolean transfer(int fromAccountId, int toAccountId, double amount){
        BankAccount fromAccount = accounts.get(fromAccountId);
        BankAccount toAccount = accounts.get(toAccountId);

        //some validations
        if(fromAccount == null || toAccount == null){
            throw new IllegalArgumentException("No Account IDs provided");
        }
        // Lock accounts in a consistent order to prevent deadlock.
        BankAccount firstAccount = fromAccountId < toAccountId ? fromAccount : toAccount;
        BankAccount secondAccount = fromAccountId < toAccountId ? toAccount : fromAccount;

        firstAccount.lockWrite();
        secondAccount.lockWrite();

        try {
            if (fromAccount.getBalance() < amount) {
                System.out.println("Insufficient funds for transfer from Account " + fromAccountId);
                return false;
            }

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer successful: $" + amount + " from Account " + fromAccountId + " to Account " + toAccountId);
            System.out.println(" ** current balance of account "+ fromAccountId+ " is " + firstAccount.getBalance());
            System.out.println(" ** current balance of account "+ toAccountId+ " is " + secondAccount.getBalance());
            return true;
        }catch (Exception e){
            System.out.println("Transaction failed.Rollback transfer from Account "+fromAccountId+" to Account "+toAccountId);
            reverseTransaction(fromAccountId,toAccountId,amount);
            return false;

        }finally{
            firstAccount.unlockWrite();
            secondAccount.unlockWrite();
        }
    }

    public void printAccountBalance(){
        for(BankAccount account : accounts.values()){
            System.out.println("Account " + account.getId() + " Balance: $" + account.getBalance());
        }
    }
    // Reverses a previously failed transaction by transferring the amount back.
    public void reverseTransaction(int fromAccountId, int toAccountId, double amount){
        System.out.println("Reversing transaction: Transferring $" + amount + " back from Account " + toAccountId + " to Account " + fromAccountId);
        transfer(toAccountId, fromAccountId, amount);
    }
}
