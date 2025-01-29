# volatile 키워드와 가시성 문제

- CPU 캐시와 메인 메모리의 관계를 이해해야 한다.
- 현대 컴퓨터에서는 성능 향상을 위해 각 CPU 코어가 자신만의 캐시를 가진다.
- 이로 인해 한 스레드가 변수를 수정했을 때, 다른 스레드가 그 변경사항을 죽시 보지 못하는 '가시성 문제'가 발생할 수 있다.

## volatile의 주요 특징
1. 변수의 읽기/쓰기가 항상 메인 메모리에서 직접 이루어진다.
2. 한 스레드가 volatile 변수를 수정하면, 다른 모든 스레드의 캐시가 무효화된다.
3. volatile은 단일 변수의 읽기/쓰기에 대한 원자성만 보장한다.

## volatile 사용 시 주의할 점
- volatile은 단순히 변수의 가시성만을 보장
- 복합 연상(예: i++)의 원자성은 보장하지 않는다.
- 여러 변수의 일관성이 필요한 경우에는 synchronized를 사용해야 한다.
- 예를 들어 다음과 같은 상황에선 volatile만으론 부족하다.
```java
public class Counter {
    private volatile int count = 0; // volatile이어도 안전하지 않다.
    
    public void increment() {
        count++; // 이 연산은 실제로 읽기-수정-쓰기의 세 단계로 이루어진다.
    }
}
```
- 위의 경우, synchronized나 atomic 클래스를 사용해야 한다.