package com.oovdev.interrupt;

/**
 * 인터럽트를 이해하기 위해 현실세계 비유
 * 회의 중인 직원(스레드)를 생각해보자. 이 직원이 긴 회의(작업)을 하고 있을 때:
 *
 * 1. 누군가 문을 두드린다(인터럽트 발생).
 * 2. 직원은 두가지 선택을 할 수 있다.
 *      - 지금 회의를 중단하고 응답한다 (인터럽트 처리)
 *      - 나중에 응답하기 위해 "누군가 찾았었다"는 사실을 메모해둔다 (인터럽트 플래그 설정)
 *
 * 자바에서 interrupt()는 이런 "문 두드리기"와 같다. 스레드에게 "너의 작업을 중단해도 될까?" 라고 정중하게 요청하는 것.
 * 강제로 중단시키는 것이 아니다.
 * */

public class InterruptExample {
    public static void main(String[] args) {
        try {
            Thread.sleep(1000); // 긴 희의 중..
        } catch (InterruptedException e) {
            // 여기서 인터럽트 상태가 자동으로 초기화됨
            e.printStackTrace();
            Thread.currentThread().interrupt(); // 인터럽트 상태를 다시 설정
        } finally {
            System.out.println("done");
        }
        /**
         * 왜 Thread.currentThread().interrupt()를 다시 호출할까?
         *
         * sleep(), wait()같은 메서드들은 InterruptedException을 더닞ㄹ 때 스레드의 인터럽트 상태를 초기화해버린다.
         * 마치 "문 두드린 기록"을 지워버리는 것과 같다.
         *
         * 하지만 현재 메서드를 호출한 상위코드에서도 이 스레드가 인터럽트 되었다는 사실을 알아야 할 수 있다.
         * 그래서 우리는 interrupt()를 다시 호출해 그 "기록"을 복원하는 것.
         *
         * 예제로 이해해보자.
         * */
    }

    private boolean isDone = false;

    public void someMethod() {
        try {
            while (!isDone) {
                // 긴 작업 수행
                Thread.sleep(100); // 여기서 interruptedException 발생 가능
            }
        } catch (InterruptedException e) {
            // 인터럽트 상태 복원
            Thread.currentThread().interrupt();
            // 작업 중단 처리
            return;
        }
    }
    /**
     * 이렇게 인터럽트는 스레드 간의 협력적인 중단 메커니즘을 제공한다. 강제로 중단하는 것이 아니라,
     * "중단해도 될까요?" 라고 요청하고, 적절히 처리할 수 있게 해주는 것.
     * */
}
