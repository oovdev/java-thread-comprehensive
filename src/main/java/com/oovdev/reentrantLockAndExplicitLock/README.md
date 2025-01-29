syncrhonized가 암시적 락이라면, ReentrantLock은 명시적 락이다.
마치 syncrhonized가 자동문이라면, reentrantLock은 수동으로 열고 닫는 문이라고 생각하며 됨.
이러한 수동 제어는 더 복잡하지만 그만큼 더 세밀한 제어가 가능하다.

# ReentrantLock이 synchronized 보다 나은 점
1. 더 유연한 락 획득 시도 (tryLock)
2. 인터럽트 가능한 락 획득 시도
3. 공정성 설정 가능
4. 타임아웃 설정 가능
5. 현재 대기 중인 스레드 수 확인 가능

# 주의점
1. 반드시 try-finally 블록으로 unlock을 보장해야 함
2. synchronized 보다 코드가 복잡해질 수 있다
3. unlock 호출을 안할 시 데드락 발생할 수 있다