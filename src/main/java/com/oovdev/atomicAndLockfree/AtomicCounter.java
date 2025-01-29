package com.oovdev.atomicAndLockfree;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        // 내부적으로 CAS 연산을 사용하여 원자적으로 증가
        count.incrementAndGet();
    }

    public void decrement() {
        count.decrementAndGet();
    }

    public int getValue() {
        return count.get();
    }

    // 조건부 업데이트의 예
    public void incrementIfLessThan(int threshold) {
        // 현재 값이 threshold보다 작을 때만 증가
        count.updateAndGet(current -> current < threshold ? current + 1 : current);
    }
}
