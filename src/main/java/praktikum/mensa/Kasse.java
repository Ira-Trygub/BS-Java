package praktikum.mensa;

import java.util.concurrent.locks.ReentrantLock;

public class Kasse {
    private final String name;
    private int queueLength; // TODO: 19.06.21 not thread-safe, make it atomic!
    private final ReentrantLock lock;

    public Kasse(String name) {
        this.name = name;
        queueLength = 0;
        lock = new ReentrantLock();
    }

    public void pay(Student s) throws InterruptedException {
        lock.lockInterruptibly();
        s.pay();
        lock.unlock();
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void increaseQueueLength() {
        queueLength++;
    }

    public void decreaseQueueLength() {
        queueLength--;
    }

    public String getName() {
        return name;
    }
}
