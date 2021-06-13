package praktikum.smoker;

import java.util.ArrayList;

public class Dealer {
    String name;

    Dealer(String name) {
        this.name = name;
    }


    ArrayList<Stuff> giveStuff() {
        var dealerStuffList = Table.stuffList;
        int ran = (int) ((Math.random() * 2));  // int ran = (int) ((Math.random() * (2 - 0)) + 0);
        dealerStuffList.remove(ran);
        return dealerStuffList;
    }


    private void putStuff(Table table) throws InterruptedException {
        while (table.tableList.size() == table.deal_max_num) {
            wait();
        }
        this.giveStuff();
        System.err.println();
        this.notifyAll();
    }


}
