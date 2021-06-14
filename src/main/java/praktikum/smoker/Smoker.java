package praktikum.smoker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Smoker {
    private final String name;
    private final Table table;
    private final Thread thread;
    private final List<Stuff> need;

    public Smoker(String name, List<Stuff> need, Table table) {
        this.name = name;
        this.table = table;
        this.need = need;

        thread = new Thread(() -> {
            while (true) {
                try {
                    System.err.println(name + "  is waiting" + ".".repeat((int) ((Math.random() * (20 - 2)) + 2)));
                    table.acquireStuff(need);
                    System.err.println(name + "  is smoking" + "~".repeat((int) ((Math.random() * (50 - 2)) + 2)));
                    Thread.sleep((int) ((Math.random() * (1000 - 100)) + 100));
                    System.err.println(name + "  finished smoking!");

                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        thread.start();
    }

    public void interrupt() {
        thread.interrupt();
    }

//    private void getStuffSmoke() throws InterruptedException {
//            try {
//            mensa.enter(this);
//            System.err.println(name + " waiting in queue");
//            inQueue.await();
//            System.err.println(name + " can eat");
//        } finally {
//            studentLock.unlock();
//        }
//    }


//    private void getStuffSmoke(Table table) throws InterruptedException {
//        Stuff elem = randomStuff();
//        while ((table.tableList.size() == 0) && !(table.stuffList).equals(table.tableList.get(0).add(elem)) || !(table.stuffList).equals(table.tableList.get(1).add(elem))) {
//            wait();
//        }
//        smoke();
//
//        notifyAll();
//    }
//
//    private Stuff randomStuff() {
//        var ind = (int) ((Math.random() * (20 - 2)) + 2);
//        Stuff elem = Table.stuffList.get(ind);
//        return elem;
//    }
//
//    private void smoke() throws InterruptedException {
//        System.err.println(name + "is smoking" + "~".repeat((int) ((Math.random() * (20 - 2)) + 2)));
//        Thread.sleep(100);
//
//
//    }
}
