package com.oovdev.reentrantLockAndExplicitLock;

import javax.security.auth.login.AccountException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountWithLock {
    private static class Account {}

    private final ReentrantLock lock = new ReentrantLock();
    private double balance = 0.0;

    public void deposit(double amount) {
        // 락을 획득하려고 시도
        lock.lock();
        try {
            // 임계영역
            balance += amount;
        } finally {
            // 반드시 락을 해제해야 한다.
            lock.unlock();
        }
    }

    public boolean withdraw(double amount) {
        // 락을 획득하려고 시도하되, 다른 스레드가 이미 락이라면 false 반환
        if (!lock.tryLock()) {
            return false;
        }
        try {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    /**
     * ReentrantLock의 주요 특징
     */

    // 1. 타임아웃 설정
    public boolean transferMoney(Account to, double amount) {
        // 2초 동안만 락 획득을 시도
        try {
            if(lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    balance -= amount;
                    return true;
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return false;  // 타임아웃 발생
    }

    // 2. 인터럽트 응답 가능
    public void depositWithInterruption(double amount) {
        try {
            // 인터럽트에 응답 가능한 락 획득
            lock.lockInterruptibly();
            try {
                balance += amount;
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            // 인터럽트 발생 시 처리
            System.out.println("락 획득 대기 중 인터럽트 발생");
        }
    }

    // 3. 공정성 설정
    // 공정한 락 생성 (FIFO 순서 보장)
    private final ReentrantLock fairLock = new ReentrantLock(true);
}
