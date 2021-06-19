package praktikum.mensa;

import java.util.concurrent.locks.ReentrantLock;

public class Student extends Thread {
    private final Mensa mensa;
    private final long maxIdleMillis;

    // TODO: 19.06.21 as we have Mensa, move lock there
    private static ReentrantLock studentLock; // eine Sperre für alle

    public Student(Mensa mensa, String name, long maxIdleMillis) {
        super(name);
        this.mensa = mensa;
        this.maxIdleMillis = maxIdleMillis;
        studentLock = new ReentrantLock();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                buy();
                eat();
            } catch (InterruptedException e) {
                System.err.println(getName() + " stop");
                return; // more reliable than check isInterrupted()
            }
        }
    }

    private void buy() throws InterruptedException { // most important things happen here
        studentLock.lockInterruptibly();
        var kasse = mensa.chooseKasse();
        kasse.increaseQueueLength();
        studentLock.unlock();

        kasse.pay(this);

        studentLock.lockInterruptibly();
        kasse.decreaseQueueLength();
        studentLock.unlock();
    }

    public void pay() throws InterruptedException {
        System.err.println(getName() + " paying");
        Thread.sleep((long) (Math.random() * maxIdleMillis));
    }

    private void eat() throws InterruptedException {
        System.err.println(getName() + " eating");
        Thread.sleep((long) (Math.random() * maxIdleMillis));
    }
}
