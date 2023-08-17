package javaRelated;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MultipleThreads {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        // 1
        Thread t = new MyThread();

        // 2
        Thread myRunnable = new Thread(new MyRunnable());

        //3
        Thread lambda = new Thread(() -> {
            System.out.println("this is a lambda");
        });

        t.setDaemon(true);
        t.start();
        myRunnable.start();
        lambda.start();
    }
}

class MyThread extends Thread {
    String text = "HHHH";

    @Override
    public void run() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            System.out.println("error!!!");
        }
        System.out.printf("jjjjjj %s \n", this.getId());
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("this is a runnable");
    }
}

class TestLock {
    public static void main(String[] args) throws InterruptedException {
        Person ming = new Person(10);
        System.out.println(ming.getName());
        Thread t = new Thread(() -> {
            ming.makeMoney();
            ming.makeMoney();
            ming.makeMoney();
            ming.makeMoney();
        });

        Thread t2 = new Thread(() -> {
            ming.spendMoney();
            ming.spendMoney();
            ming.spendMoney();
            ming.spendMoney();
            ming.spendMoney();
        });

        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(ming.getMoney());
    }
}

@Data
@AllArgsConstructor
class Person {
    private String name = "xx";
    private int age = 20;
    private int money;

    public Person(int money) {
        this.money = money;
    }

    void makeMoney() {
        synchronized (this) {
            money++;
        }
    }

    void spendMoney() {
        synchronized (this) {
            money--;
        }
    }

//    void makeMoney() {
//        money++;
//    }
//
//    void spendMoney() {
//        money--;
//    }

}
