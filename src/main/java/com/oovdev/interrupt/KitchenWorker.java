package com.oovdev.interrupt;
/**
 * 이 코드에서 인터럽트 상태를 표시해두는 이유는 크게 세가지다.
 *
 * 1. 정보 전달
 *      - 다른 메서드들(checkIngredients, cleanup 등)이 "이 작업이 중단 요청을 받았다"는 사실을 알 수 있다.
 *      - 마치 요리사가 "나 곧 퇴근해야 해"라는 표시를 달고 다니는 것과 같다.
 * 2. 단계적 종료:
 *      - 모든 작업을 즉시 중단하는 것이 아니라, 적절한 시점에 안전하게 종료할 수 있다.
 *      - 요리사가 불을 끄고, 재료를 정리하고, 주방을 청소하는 것과 같이.
 * 3. 작업 맥락 유지:
 *      - 상위 메서드나 다른 협력 스레드들이 이 스레드의 상태를 확인할 수 있다.
 *      - 주방장이 "저 요리사가 마감 요청을 받았구나"라고 알 수 있는 것과 같다.
 * */
public class KitchenWorker implements Runnable {
    @Override
    public void run() {
        try {
            prepareDish(); // 요리 준비
        } catch (InterruptedException e) {
            // 인터럽트를 받았을 때 상태를 다시 표시
            Thread.currentThread().interrupt();
            // 주방정리 등 마무리 작업 수행
            cleanup();
        }
    }

    private void prepareDish() throws InterruptedException {
        while (!isKitchenClosed()) {
            Thread.sleep(1_000); // 요리 중..
            checkIngredients(); // 재료 확인
            cookFood(); // 요리 진행
        }
    }

    private void checkIngredients() {
        // 만약 이 메서드가 인터럽트 상태를 확인한다면?
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("재료 확인 중단 - 주방 마감 요청 받음");
            return;
        }
        // 재료 확인 로직...
    }

    private void cookFood() {
        System.out.println("cooking Food......");
    }

    private void cleanup() {
        // 마찬가지로 인터럽트 상태를 확인할 수 있다.
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("빠른 정리 필요 - 긴급 마감");
        } else {
            System.out.println("정상적인 마감 정리");
        }
    }


    private boolean isKitchenClosed() {
        return false;
    }
}
