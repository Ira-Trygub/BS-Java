package praktikum.mensa;

public class Kasse {
    private final String name;

     int counter;

    public Kasse(String name) {
        this.name = name;
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
    public void pay() throws InterruptedException {
        KassenLock.lockInterruptibly();
        try {
            System.err.println(name + " is paying!");
            Thread.sleep(1000);
            System.err.println(name + " payed!");
            inQueue.signal();
        } finally {
            studentLock.unlock();
        }
    }



}

