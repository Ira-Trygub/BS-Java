package praktikum.mensa;

import java.util.concurrent.locks.ReentrantLock;

public class Student extends Thread {
    private final Mensa mensa;
    private final long maxIdleMillis;
    private static ReentrantLock studentLock; // eine Sperre für alle

    public Student(Mensa mensa, String name, long maxIdleMillis) {
        super(name);
        this.mensa = mensa;
        this.maxIdleMillis = maxIdleMillis;
        studentLock = new ReentrantLock();
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
                return; // more reliable than check isInterrupted()
            }
        }

    }


    private void enter() throws InterruptedException {
        studentLock.lockInterruptibly();
        try {
            mensa.chooseKasse().ifPresent(selectedKasse -> {
                System.err.println(getName() + " is in queue on Kasse " + selectedKasse.getName());
                selectedKasse.increaseQueueLength();
                System.err.println(getName() + " can eat");
            });
        } finally {
            studentLock.unlock();
        }
    }

    private void eat() throws InterruptedException {
        System.err.println(getName() + " eating");
        Thread.sleep((long) (Math.random() * maxIdleMillis));
    }
}
