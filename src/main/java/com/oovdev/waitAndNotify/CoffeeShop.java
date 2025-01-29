package com.oovdev.waitAndNotify;
/**
 * wait()과 notify() 메서드를 이해하기 위해 실제 생활의 예시로 설명.
 *
 * 커피숍에서 일어나는 상황을 상상해보자.
 *
 * - 바리스타(스레드-1)가 커피를 만든다.
 * - 손님(스레드-2)가 커피를 기다린다.
 * */
public class CoffeeShop {
    private boolean isComplete = false;

    public synchronized void waitForCoffee() {
        while (!isComplete) {
            try {
                System.out.println("손님: 커피가 완성될 때까지 기다립니다.");
                wait(); // 손님 스레드가 대기 상태로 들어간다.
                // wait()을 호출하면 이 스레드는 다른 스레드가 notify()로 호출할 때까지
                // 잠들어 있게 된다.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("손님: 커피를 받았습니다!");
    }

    public synchronized void completeCoffee() {
        System.out.println("바리스타: 커피 제조를 완료했습니다.");
        isComplete = true;
        notify();
    }
}
/**
 * 중요 포인트
 *
 * 1. synchronized 키워드
 *  - wiat()과 notify()는 반드시 synchronized 블록 안에서 호출해야 한다.
 *  - 이는 마치 커피숍에서 주문을 받고 전달하는 카운터와 같다. 한번에 한 사람만 접근할 수 있는 공간이다.
 *
 * 2. while 루프로 조건 체크
 *  - if가 아닌 while을 사용하는 것이 중요하다. 이를 "spurious wakeup" 방지라고 한다.
 *  - 손님이 실수로 깨어났을 때도 다시 한번 커피가 정말 완성되었는지 확인할 수 있게 하는 것.
 *
 * 3. wait()
 *  - wait()을 호출하면 두가지 일이 발생한다.
 *    - 현재 스레드가 대기 상태로 들어간다.
 *    - synchronized 락을 일시적으로 해제한다 (다른 스레드가 접근할 수 있게)
 *  - 마치 손님이 진동벨을 받고 자리에 가서 앉아있는 것과 같다. 카운터는 다른 손님이 사용할 수 있게 된다.
 *
 * 4. notify()
 *  - notify()는 대기 중인 스레드 중 하나를 깨운다.
 *  - notifyAll()은 모든 대기 스레드를 깨운다.
 *  - 카페의 진동벨과 같다.
 *
 * */
