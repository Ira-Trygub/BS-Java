package praktikum.smoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dealer {
    final Thread thread;
    String name;
    Table table;

    //
    Dealer(String name, Table table) {
        this.name = name;
        this.table = table;


//
        thread = new Thread(() -> {
            while (true) {
                try {
                    putStuff(table);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });

        thread.start();
    }

    public void putStuff(Table table) throws InterruptedException {
        int ran = (int) ((Math.random() * 3));
        List<Stuff> newItems = new ArrayList<Stuff>(Arrays.asList(Stuff.values()));
        newItems.remove(ran);
        table.offer(newItems);
    }

    public void interrupt() {
        thread.interrupt();
    }
}


//
//
//    public ArrayList<Stuff> giveStuff() {
//        var dealerStuffList = Table.stuffList;
//        int ran = (int) ((Math.random() * 2));  // int ran = (int) ((Math.random() * (2 - 0)) + 0);
//        dealerStuffList.remove(ran);
//        return dealerStuffList;
//    }


//    public void putStuff(Table table) throws InterruptedException {
//        while (table.tableList.size() == table.deal_max_num) {
//            System.err.println(name + "is waiting");
//            wait();
//        }
//        ArrayList<Stuff> dealerStuffList = this.giveStuff();
//        table.tableList.add(dealerStuffList);
//        System.err.println("Do you need " + dealerStuffList + "?");
//        this.notifyAll();
//    }
//
//
//}
