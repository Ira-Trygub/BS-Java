package praktikum.smoker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static int deal_max_num;
    private static int smo_max_num;
    public static Table table;

    public static void main(String[] args) {
        int deal_max_num = 2;
        int smo_max_num = 3;
        Table table = new Table();
        createDealers();
        createSmokers();




    }

    static List<Dealer> createDealers() {
        return IntStream.range(0,deal_max_num).boxed().map(i -> new Dealer("dealer" + i, table)).collect(Collectors.toList());
    }

    static List<Smoker> createSmokers() {
        return IntStream.range(0,smo_max_num).boxed().map(i -> new Smoker("dealer" + i, table)).collect(Collectors.toList());
    }
}