package com.oovdev.volatile0;

public class VisibilityProblem {
    private boolean flag = false;   // volatile이 없는 일반 변수

    public void writer() {
        flag = true;
        System.out.println("Flag set to true");
    }

    public void reader() {
        while (!flag) {
            // flag값이 캐시에서만 읽히고 메인메모리에서 갱신되지 않을 수도 있음
        }

        System.out.println("Flag was changed!");
    }
}
