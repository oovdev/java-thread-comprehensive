package com.oovdev.interrupt;

public class InterruptExample2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("1) thread. " + Thread.currentThread());
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted");
                }
            }
        });

        thread.start();
        thread.interrupt();

        System.out.println("Main thread interrupted");
    }
}
