package praktikum.mensa;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    private final int maxQsize;
    private final String name;
    private final java.util.Queue<Consumer> javaQueue;
    private final ReentrantLock queueLock;
    private final Condition notFull;
    private final Condition notEmpty;

    public Queue(int maxQsize, String name) {
        this.maxQsize = maxQsize;
        this.name = name;
        javaQueue = new LinkedList<>();
        queueLock = new ReentrantLock();
        notFull = queueLock.newCondition();
        notEmpty = queueLock.newCondition();
    }

    public int size() {
        int res;
        queueLock.lock();
        try {
            res = javaQueue.size();
        } finally {
            queueLock.unlock();
        }
        return res;
    }

    public void enter(Consumer consumer) throws InterruptedException {
        queueLock.lockInterruptibly();
        try {
            while (javaQueue.size() == maxQsize) {
                System.err.println(name + " is full");
                notFull.await();
            }

            javaQueue.add(consumer);
            System.err.println(name + " has size " + javaQueue.size());
            notEmpty.signal();
        } finally {
            queueLock.unlock();
        }
    }

    public void serve() throws InterruptedException {
        queueLock.lockInterruptibly();
        try {
            while (javaQueue.size() == 0) {
                System.err.println(name + " is empty");
                notEmpty.await();
            }

            var consumer = javaQueue.remove();
            consumer.pay();

            notFull.signal();
        } finally {
            queueLock.unlock();
        }
    }
}
