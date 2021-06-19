package praktikum.mensa;

import java.util.concurrent.locks.ReentrantLock;

public class Kasse {
    private final ReentrantLock KassenLock;
    public String name;
    int counter;

    public Kasse(String name) {
        this.name = name;
        KassenLock = new ReentrantLock(true);
        counter = 0;
    }

    @Override
    public String toString() {
        return "Kasse(" + name + ")";
    }

    public int queueLength() {
        return counter;
    }

    public void increaseQueueLength() throws InterruptedException {
        counter++;
    }
    public void decreaseQueueLength() throws InterruptedException {
        counter--; //mit studentenLock im Student aufrufen
    }
//    public void pay() throws InterruptedException {
//        KassenLock.lockInterruptibly();
//        try {
//            System.err.println(name + " is paying!");
//            Thread.sleep(1000);
//            System.err.println(name + " payed!");
//            inQueue.signal();
//        } finally {
//            studentLock.unlock();
//        }
//    }


    public void interrupt() {
    }

    public String getName() {
        return name;
    }

}

