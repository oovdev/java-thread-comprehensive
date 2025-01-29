스레드의 생명주기(상태)는 java.lang.Thrad.State 열거형에 정의된 6가지가 있다.

## 1. NEW(갓 태어난 상태)
```java
Thread thread = new Thread(() -> {}); // 아직 start()가 호출되지 않은 상태
```
- 마치 태어난 아기가 아직 걸음마를 시작하지 않은 것곽 ㅏㅌ다.
- 스레드 객체는 생성되었지만, 아직 실행되지 않은 상태

## 2. RUNNABLE (일할 준비가 된 상태)
```java
thread.start(); // 이제 실행 가능한 상태가 됨
```
- 출근해서 일할 준비를 마친 직장인처럼, CPU를 할당받아 실행될 준비가 된 상태.
- 실제로 싱핼 중일 수도 있고, CPU를 기다리고 있을 수도 있다.

## 3. BLOCKED (막혀서 기다리는 상태)
```java
synchronized(lock) {
    // 다른 스레드가 이미 이 락을 보유하고 있다면, BLOCKED 상태가 됨
}
```
- 회의실에 들어가려고 했는데 이미 다른 사람이 사용 중이어서 밖에서 기다리는 것과 같다.
- synchronized 블록에 진입하기 위해 락을 기다리는 상태

## 4. WAITING (무기한 대기 상태)
```java
// 예시 1: Object.wait() 호출
synchronized(obj) {
    obj.wait(); // 다른 스레드가 notify()를 호출할 때까지 대기    
}

// 예시 2: Thread.join() 호출
thread.join();  // 해당 스레드가 종료될 때까지 대기
```
- 식당에서 진동벨을 받고 언제 울릴 지 모른 채 기다리는 것과 같다.
- 다른 스레드가 notify()를 호출할 때까지 계속 대기

## 5. TIMED_WAITING (시간 제한이 있는 대기 상태)

```java
// 예시 1: Thread.sleep() 호출
Thread.sleep(1_000); // 1초동안 대기

// 예시 2: object.wait() with timtout
synchronized(obj){
    obj.wait(1_000);    // 최대 1초당안 대기    
}
```
- 점심시간에 1시간만 쉬는 것처럼, 정해진 시간 동안만 대기하는 상태

## 6. TERMINATED (종료된 상태)
```java
// run() 메서드의 실행이 완료되면 TERMINATED 상태가 됨
```
- 한번 종료된 스레드는 다시 시작할 수 없다.

