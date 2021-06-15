package praktikum.mensa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Student extends Thread  implements Consumer {
    private final Mensa mensa;
    private final String name;
    private final long maxIdleMillis;
    Kasse selectedKasse;
//    private final Thread thread;
     private static  ReentrantLock studentLock = new ReentrantLock();//eine Sperre für alle
//    private final Condition inQueue;

    public Student(Mensa mensa, String name, long maxIdleMillis) {
        this.mensa = mensa;
        this.name = name;
        this.maxIdleMillis = maxIdleMillis;
//        studentLock = new ReentrantLock();
//        inQueue = studentLock.newCondition();
//        thread = new Thread(() -> { //даём потоку работу через лямбда функцию () -> {}


    }

    @Override
    public String toString() {
        return "Student(" + name + ")";
    }

    public void run(){
        while (!isInterrupted()) {   // while (!isInterrupted()) {
            try {
                System.err.println(this.name + " wanna eat");
                enter(); // зайти в столовую
                // enter will be locked until we can pay
                // payment will be done in Kasse thread
                eat(); // уйти кушать
            } catch (InterruptedException e) {
                System.err.println(this.name + " stop");
                return;
            }
        }

    }



    private void enter() throws InterruptedException {
        studentLock.lockInterruptibly();
        try {
            selectedKasse = mensa.chooseKasse(this);
            System.err.println(name + " waiting for Kasse");
         selectedKasse.increaseQueueLength();
            System.err.println(name + " can eat");
        } finally {
            studentLock.unlock();
        }
    }



    private void eat() {
        System.err.println(name + " eating");
    }
}
