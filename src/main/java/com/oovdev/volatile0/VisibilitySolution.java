package com.oovdev.volatile0;

public class VisibilitySolution {
    private volatile boolean flag = false;

    public void writer() {
        // 이제 이 변경사항이 메인메모리에 즉시 기록되고
        // 다른 스레드들의 캐시가 무효화됨
        flag = true;
        System.out.println("Flag set to true");
    }

    public void reader() {
        // flag값을 항상 메인 메모리에서 직접 읽어옴
        while (!flag) {
            // 다른 스레드들의 변경사항이 즉시 보임
        }
        System.out.println("Flag was changed!");
    }
}
