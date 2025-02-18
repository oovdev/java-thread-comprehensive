java.util.concurrent.atomic 패키지는 원자적 연산을 지원하는 클래스들을 제공한다.
이 클래스들은 CPU의 특별한 명령어(Compare-And-Swap, CAS)를 활용하여 동기화를 구현하기 때문에
synchronized보다 더 나은 성능을 제공할 수 있다.

# CAS(Compare-And-Swap) 연산의 작동방식
1. 당신이 차 열쇠를 바꾸려고 한다(값 변경).
2. 먼저 현재 차 열쇠의 일련번호를 확인한다(예상값 확인).
3. 만약 확인한 일련번호가 맞다면 새 열쇠로 바꾼다(값 변경 성공).
4. 만약 일련번호가 다르다면(다른 사람이 이미 열쇠를 바꿨다면), 처음부터 다시 시도한다.

# Atomic 클래스의 장점:
1. synchronized보다 성능이 좋다.
2. 데드락이 발생하지 않는다.
3. 세밀한 단위의 동기화가 가능하다.

# Atomic 클래스의 한계
1. 단일 변수에 대한 연산만 가능하다.
2. 여러 변수를 한 번에 원자적으로 업데이트할 수 없다.
3. CAS 실패 시 재시도로 인한 CPU 부하가 발생할 수 있다.

---

### threshold
- ___임계점___, 한계점, 문지방
- 특정 자료구조의 현재 용량과 지정된 로드팩터를 나오는 결과값을 threshold(임계점)이라고 한다.
- 해당 임계점에 도달했을 때, 내부적으로 사이즈를 늘리는 로직이 실행된다.

### Load Factor
- 용량 대비 데이터가 어느정도 찼을 때 내부적으로 사이즈 확장을 필요로하는 자료구조에서 사용되는 개념
- - 언제 사이즈를 늘려야 하는지, 즉, 몇 번째 데이터를 추가할 때, 사이즈를 늘려야 하는지를 결정하는 척도, 기준
- 언제 사용하나? 사이즈가 가변적인 자료구조에 쓰이며 current capacity와 곱을 이뤄,
- 사이즈를 증가시켜야 하는 시기(임계점)을 결정하는데 사용도니다.

### 로드팩터가 존재하는 대표적인 자바 컬렉션 프레임워크
- ArrayList
- HashMap
- Hashtable
- ConcurrentHashMap
- HashSet
- ....

일반적으로 default load factor는 0.75이지만, ArrayList의 로드팩터는 1이다.

### throughput(쓰로풋)
- 시간당 처리량(처리율)을 의미함.
- 얼마나 많은 데이터가 단위 시간 내 목적지에 전달 될 수 있는지에 대한 지표.
- 웹 애플리케이션 성능 지표.