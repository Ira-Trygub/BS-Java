package praktikum.mensa;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    private final int maxQsize;
    private final java.util.Queue<Consumer> javaQueue;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public Queue(int maxQsize) {
        this.maxQsize = maxQsize;
        javaQueue = new LinkedList<>();
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public int size() throws InterruptedException {
        int res;
        lock.lockInterruptibly();
        try {
            res = javaQueue.size();
        } finally {
            lock.unlock();
        }
        return res;
    }

    public void enter(Consumer consumer) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (javaQueue.size() == maxQsize)
                notFull.await();

            javaQueue.add(consumer);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
        consumer.awaitPayment();
    }

    public void serve() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (javaQueue.size() == 0)
                notEmpty.await();

            var consumer = javaQueue.remove();
            consumer.pay();

            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
