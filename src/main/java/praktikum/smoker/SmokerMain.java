package praktikum.smoker;

import praktikum.mensa.Mensa;
import praktikum.mensa.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmokerMain {


    public static void main(String[] args) {
        int dealNum = 2;

        var table = new Table();
        var smokers = List.of(new Smoker("TabakSmoker", List.of(Stuff.PAPIER, Stuff.STREICHHOELZER), table),
                new Smoker("TabakPapier", List.of(Stuff.TABAK, Stuff.STREICHHOELZER), table),
                new Smoker("TabakStreichhoeler", List.of(Stuff.PAPIER, Stuff.TABAK), table));

        var dealers = createDealers(dealNum, table);
        pauseMainThread();

//

//        int smo_max_num = 3;
//        Table table = new Table();
//        createDealers();
//        createSmokers();
    }

    static List<Dealer> createDealers(int dealMaxNum, Table table) {
        return IntStream.range(0, dealMaxNum).boxed().map(i -> new Dealer("dealer" + i, table)).collect(Collectors.toList());
    }


    static void pauseMainThread() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
    }


    static void interruptAllThreads( List<Smoker> smokers) {
        for (var s : smokers) {
            s.interrupt();
        }
    }

//
//    static List<Smoker> createSmokers() {
//        return IntStream.range(0,smo_max_num).boxed().map(i -> new Smoker("dealer" + i, table)).collect(Collectors.toList());
//    }
}
