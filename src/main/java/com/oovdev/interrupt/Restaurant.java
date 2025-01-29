package com.oovdev.interrupt;

public class Restaurant {
    public static void main(String[] args) {
        Thread chef = new Thread(new KitchenWorker());
        chef.start();

        // 잠시 후...
        chef.interrupt(); // 주방장이 요리사에게 마감을 요청함

        // 다른 곳에서 요리사의 상태를 확인할 수 있음.
        if (chef.isInterrupted()) {
            System.out.println("요리사가 마감 처리 중입니다.");
            // 추가 정리 작업 지시 가능.
        }
    }
    /**
     * 이처럼 인터럽트 상태를 표시해두는 것은 스레드 간의 원활한 협력과 안전한 종료를 위한 중요한 메커니즘이다.
     * 마치 "방해하지 마세요" 팻말을 걸어두는 것처럼, 다른 코드들이 이 스레드의 상태를 이해하고 적절히 대응할 수 있게 해준다.
     * */
}
