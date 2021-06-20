package praktikum.smoker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table {
    List<Stuff> items;

    public Table() {
        items = new LinkedList<>();
    }

    public synchronized void produce(Dealer dealer) throws InterruptedException {
        while (!items.isEmpty()) wait();

        items.addAll(dealer.getStuff());
        notifyAll();
    }

    public synchronized void consume(Smoker smoker) throws InterruptedException {
        var need = new ArrayList<>(List.of(Stuff.values()));
        need.remove(smoker.getHas());

        while (!items.containsAll(need)) wait();

        items.removeAll(need);

        smoker.smoke();

        notifyAll();
    }
}
