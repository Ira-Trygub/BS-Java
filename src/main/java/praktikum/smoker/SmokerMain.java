package praktikum.smoker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmokerMain {
    public static void main(String[] args) {
        int dealerNum = 2;

        var table = new Table();
        var smokers = List.of(
                new Smoker("PapierSmoker", Stuff.PAPIER, table),
                new Smoker("TabakSmoker", Stuff.TABAK, table),
                new Smoker("StreichhoelerSmoker", Stuff.STREICHHOELZER, table)
        );

        var dealers = createDealers(dealerNum, table);
        startAllThreads(smokers, dealers);
        pauseMainThread(5000);
        interruptAllThreads(smokers, dealers);
    }

    static List<Dealer> createDealers(int dealerNum, Table table) {
        return IntStream.range(0, dealerNum).boxed().map(i -> new Dealer("dealer" + i, table)).collect(Collectors.toList());
    }


    static void pauseMainThread(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    static void startAllThreads(List<Smoker> smokers, List<Dealer> dealers) {
        smokers.forEach(Thread::start);
        dealers.forEach(Thread::start);
    }

    static void interruptAllThreads(List<Smoker> smokers, List<Dealer> dealers) {
        smokers.forEach(Thread::interrupt);
        dealers.forEach(Thread::interrupt);
    }
}
