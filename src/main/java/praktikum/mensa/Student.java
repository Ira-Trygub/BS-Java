package praktikum.mensa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Student implements Consumer {
    private final Mensa mensa;
    private final String name;
    private final long maxIdleMillis;
    private final Thread thread;
    private final ReentrantLock studentLock;
    private final Condition inQueue;

    public Student(Mensa mensa, String name, long maxIdleMillis) {
        this.mensa = mensa;
        this.name = name;
        this.maxIdleMillis = maxIdleMillis;
        studentLock = new ReentrantLock();
        inQueue = studentLock.newCondition();
        thread = new Thread(() -> { //даём потоку работу через лямбда функцию () -> {}
            while (true) {   // while (!isInterrupted()) {
                try {
                    System.err.println(this.name + " idle");
                    Thread.sleep((long) (maxIdleMillis * Math.random()));
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
        });

        thread.start();
    }

    @Override
    public String toString() {
        return "Student(" + name + ")";
    }

    public void interrupt() {
        thread.interrupt();
    }

    private void enter() throws InterruptedException {
        studentLock.lockInterruptibly();
        try {
            mensa.enter(this);
            System.err.println(name + " waiting in queue");
            inQueue.await();
            System.err.println(name + " can eat");
        } finally {
            studentLock.unlock();
        }
    }

    public void pay() throws InterruptedException {
        studentLock.lockInterruptibly();
        try {
            System.err.println(name + " is paying!");
            Thread.sleep(1000);
            System.err.println(name + " payed!");
            inQueue.signal();
        } finally {
            studentLock.unlock();
        }
    }

    private void eat() {
        System.err.println(name + " eating");
    }
}

//public class Student extends Thread {
//    public final int MAX_IDLE_TIME = 100;
//    int stnumber;
//    Kasse currentKasse;
//    private Student item;

//
//    public void run() {
//        try {
//            while (!isInterrupted()) {
//                wishKasse();
//                item = currentKasse.remove(); ///?????????
//                pause();
//            }
//        } catch (InterruptedException e) {
////                    e.printStackTrace();
//            System.err.println(this.getName() + "  was interrupted!");
//        }
//    }
//
//
//    public void wishKasse() {
//        System.err.println("Student" + this.stnumber + "  want to the checkout!");
//    }
//
//    public void pause() throws InterruptedException {
//        int sleepTime = (int) (MAX_IDLE_TIME * Math.random());
//        Thread.sleep(sleepTime);
//    }
//
//}
