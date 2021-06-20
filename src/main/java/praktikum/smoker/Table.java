package praktikum.smoker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table {
    List<Stuff> items;

    public Table() {
        items = new LinkedList<>();
    }

    public synchronized void offer(Dealer d) throws InterruptedException {
        while (!items.isEmpty()) wait();

        items.addAll(d.getStuf());
        notifyAll();
    }

    // prüfen ob Tisch leer gemacht ist
    public synchronized void acquireStuff(Smoker s) throws InterruptedException {
        var need = new ArrayList<>(List.of(Stuff.values()));
        need.remove(s.getHas());

        while (!items.containsAll(need)) wait();

        items.removeAll(need);

        s.smoke();

        notifyAll();
    }
}
