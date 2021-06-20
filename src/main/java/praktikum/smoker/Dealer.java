package praktikum.smoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dealer extends Thread {
    String name;
    Table table;

    public Dealer(String name, Table table) {
        this.name = name;
        this.table = table;
    }

    public void run() {
        while (true) {
            try {
                table.produce(this);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public List<Stuff> getStuff() {
        System.err.println(name + " offered");
        List<Stuff> newItems = new ArrayList<>(Arrays.asList(Stuff.values()));
        newItems.remove((int) ((Math.random() * 3)));
        return newItems;
    }
}
