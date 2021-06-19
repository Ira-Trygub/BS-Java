package praktikum.mensa;

import java.util.concurrent.locks.ReentrantLock;

public class Student extends Thread {
    private static ReentrantLock studentLock; // eine Sperre für alle
    private final Mensa mensa;
    //    private final String name;
    private final long maxIdleMillis;
    Kasse selectedKasse;
//    private final Condition inQueue;

    public Student(Mensa mensa, String name, long maxIdleMillis) {
        super(name);
        this.mensa = mensa;
//        this.name = name;
        this.maxIdleMillis = maxIdleMillis;
        studentLock = new ReentrantLock();
//        studentLock = new ReentrantLock();
//        inQueue = studentLock.newCondition();
//        thread = new Thread(() -> { // даём потоку работу через лямбда функцию () -> {}
    }

    @Override
    public String toString() {
        return "Student(" + getName() + ")";
    }

    @Override
    public void run() {
        while (!isInterrupted()) {   // while (!isInterrupted()) {
            try {
//                System.err.println(this.name + " wanna eat");
                enter();
                // enter will be locked until we can pay
                // payment will be done in Kasse thread
                eat(); // уйти кушать
            } catch (InterruptedException e) {
                System.err.println(getName() + " stop");
                return;
            }
        }

    }


    private void enter() throws InterruptedException {
        studentLock.lockInterruptibly();
        try {
            selectedKasse = mensa.chooseKasse();
            System.err.println(getName() + " is in queue on Kasse " + selectedKasse.getName());
            selectedKasse.increaseQueueLength();
            System.err.println(getName() + " can eat");
        } finally {
            studentLock.unlock();
        }
    }

//public void interrupt() {
//        Thread.interrupt();
//}

    private void eat() {
        System.err.println(getName() + " eating");
    }
}
