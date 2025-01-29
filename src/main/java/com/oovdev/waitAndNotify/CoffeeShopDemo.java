package com.oovdev.waitAndNotify;

public class CoffeeShopDemo {
    public static void main(String[] args) {
        CoffeeShop coffeeShop = new CoffeeShop();

        // 손님 스레드
        new Thread(() -> {
            System.out.println("손님: 주문했습니다.");
            coffeeShop.waitForCoffee();
        }).start();

        // 잠시 후 바리스타가 커피를 완성
        try {
            Thread.sleep(2_000); // 커피 제조시간
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        coffeeShop.completeCoffee();
    }
}
