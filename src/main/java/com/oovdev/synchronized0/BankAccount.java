package com.oovdev.synchronized0;

public class BankAccount {
    private int balance = 0;

    public void deposit(int amount) {
        int newBalance = balance + amount;
        balance = newBalance;
    }

    public int getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(100);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(100);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("final balance = " + account.getBalance());
    }
}
