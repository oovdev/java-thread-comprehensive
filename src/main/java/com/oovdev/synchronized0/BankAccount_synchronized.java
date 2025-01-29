package com.oovdev.synchronized0;

public class BankAccount_synchronized {
    private int balance = 0;

    // synchronized로 메소드 전체 동기화
    public synchronized void deposit(int amount) {
        balance += amount;
    }

    // 또는 특정 블록만 동기화 가능
    public void depositWithBlock(int amount) {
        synchronized (this) {
            balance += amount;
        }
    }

    public static void main(String[] args) {
        BankAccount_synchronized account = new BankAccount_synchronized();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    account.depositWithBlock(1000);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    account.depositWithBlock(1000);
                }
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

        System.out.println("final balance = " + account.balance);
    }
}
