package praktikum.smoker;

import java.util.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Table {
    LinkedList<ArrayList<Stuff>> tableList;
    LinkedList<Dealer> dealerList;
    LinkedList<Smoker> smokerList;
    int deal_max_num;
    int smo_max_num;
    static ArrayList<Stuff> stuffList;

    public Table() {
        int deal_max_num = 2;
        int smo_max_num = 3;
        var tableList = new LinkedList();
        ArrayList<Stuff> stuffList = new ArrayList<Stuff>() {{ add(new Papier()); add( new Tabak()); add( new Streichhoelzer());}};
    }

    public int getDeal_max_num() {
        return deal_max_num;
    }

    public int getSmo_max_num() {
        return smo_max_num;
    }

    public ArrayList<Stuff> getStuffList() {
        return stuffList;
    }

    //    public Table(LinkedList<ArrayList<Stuff>> tableList, int deal_max_num, int smo_max_num) {
//        this.tableList = tableList;
//        this.deal_max_num = deal_max_num;
//        this.smo_max_num = smo_max_num;
//    }

    public synchronized void putStuff(Dealer dealer) throws InterruptedException {

        while (tableList.size() == deal_max_num) {
            wait();
        }
        dealer.giveStuff();
        System.err.println();
        this.notifyAll();

    }

}
