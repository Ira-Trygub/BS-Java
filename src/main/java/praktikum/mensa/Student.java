package praktikum.mensa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Student implements Consumer {
    private final Mensa mensa;
    private final String name;
    private final long maxIdleMillis;
    private final Thread thread;
    private final ReentrantLock lock;
    private final Condition inQueue;

    public Student(Mensa mensa, String name, long maxIdleMillis) {
        this.mensa = mensa;
        this.name = name;
        this.maxIdleMillis = maxIdleMillis;
        lock = new ReentrantLock();
        inQueue = lock.newCondition();
        thread = new Thread(() -> { //даём потоку работу через лямбда функцию () -> {}
            while (true) {   // while (!isInterrupted()) {
                try {
                    enterAndPay(); // зайти в столовую
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

    public void interrupt() {
        thread.interrupt();
    }

    private void enterAndPay() throws InterruptedException {
        mensa.enter(this);
    }

    public void awaitPayment() throws InterruptedException {
        System.err.println(name + " awaitPayment");
        lock.lockInterruptibly();
        try {
            inQueue.await();
        } finally {
            lock.unlock();
        }
    }

    public void pay() throws InterruptedException {
        System.err.println("Student " + name + " is paying!");
        lock.lockInterruptibly();
        try {
            inQueue.signal();
        } finally {
            lock.unlock();
        }
    }

    private void eat() throws InterruptedException {
        System.err.println(name + " eating");
        Thread.sleep((long) (maxIdleMillis * Math.random()));
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
