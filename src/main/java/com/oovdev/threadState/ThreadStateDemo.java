package com.oovdev.threadState;

public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        Thread thread = new Thread(() -> {
           synchronized (lock) {
               try {
                   lock.wait(); // WAITING 상태 진입
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
        });

        // NEW 상태
        System.out.println("1) NEW State: " + thread.getState());

        thread.start();
        // RUNNABLE 상태
        System.out.println("2) RUNNABLE State After start(): " + thread.getState());

        Thread.sleep(100); // 스레드가 wait()를 호출할 시간을 줌
        // WAITING 상태
        System.out.println("3) WAITING State After wait(): "  + thread.getState());

        synchronized (lock) {
            lock.notify();  // waiting 상태 깨움
        }

        thread.join();  // 스레드가 종료될 때까지 대기
        // TERMINATED 상태
        System.out.println("4) After Thread Terminated: " + thread.getState());
    }
}
