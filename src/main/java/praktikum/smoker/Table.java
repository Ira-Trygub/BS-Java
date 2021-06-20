package praktikum.smoker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table {
    List<Stuff> items;

    public Table() {
        items = new LinkedList<>();
    }

    public synchronized void offer(List<Stuff> newItems) throws InterruptedException {
        while (!items.isEmpty()) wait();

        items.addAll(newItems);
        notifyAll();
    }

    // prüfen ob Tisch leer gemacht ist
    public synchronized void acquireStuff(Stuff has) throws InterruptedException {
        var need = new ArrayList<>(List.of(Stuff.values()));
        need.remove(has);

        while (!items.containsAll(need)) wait();

        items.removeAll(need);
        notifyAll();
    }
}
