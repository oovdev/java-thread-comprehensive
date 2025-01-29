package com.oovdev.atomicAndLockfree;

import java.util.concurrent.atomic.AtomicInteger;

public class CASExample {
    private AtomicInteger value = new AtomicInteger(0);

    public void updateValue(int expectedValue, int newValue) {
        // CAS 연산: 예상값이 맞을 때만 새 값으로 변경
        boolean success = value.compareAndSet(expectedValue, newValue);

        if (success) {
            System.out.println("값 변경 성공");
        } else {
            System.out.println("다른 스레드가 이미 값을 변경했습니다.");
        }
    }
}
