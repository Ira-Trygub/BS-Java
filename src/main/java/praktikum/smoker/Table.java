package praktikum.smoker;

import java.util.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Table {
    private final List<Stuff> items;

    public Table() {
        items = new LinkedList<>();
    }

    public synchronized void acquireStuff(List<Stuff> need) throws InterruptedException {
        while (!items.containsAll(need)) {
            wait();
        }
        items.removeAll(need);
        notifyAll();

    }

    public synchronized void offer(List<Stuff> newItems) {

    }
}

//    LinkedList<Dealer> dealerList;
//    LinkedList<Smoker> smokerList;
//    int deal_max_num; //максимальное количество диллеров
//    int smo_max_num; //максимальное количество курильщиков
//    static ArrayList<Stuff> stuffList; // бумага, табак, спички вместе
//
//    public Table() {
//
//        var tableList = new LinkedList();
//        ArrayList<Stuff> stuffList = new ArrayList<Stuff>() {{ add(new Papier()); add( new Tabak()); add( new Streichhoelzer());}};
//    }
//
//    public int getDeal_max_num() {
//        return deal_max_num;
//    }
//
//    public int getSmo_max_num() {
//        return smo_max_num;
//    }
//
//    public ArrayList<Stuff> getStuffList() {
//        return stuffList;
//    }


//    public synchronized void putStuff(Dealer dealer) throws InterruptedException {
//
//        while (tableList.size() == deal_max_num) {
//            wait();
//        }
//        dealer.giveStuff();
//        System.err.println();
//        this.notifyAll();
//
//    }

//}
