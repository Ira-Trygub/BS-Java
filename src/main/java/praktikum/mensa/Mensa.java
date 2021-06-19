package praktikum.mensa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mensa {
    private final List<Kasse> allKasse;

    public Mensa(int numKasse) {
        allKasse =
                IntStream
                        .range(0, numKasse)
                        .boxed()
                        .map(i -> new Kasse("kasse-" + i))
                        .collect(Collectors.toList());
    }

    public Kasse chooseKasse() {
        return allKasse
                .stream()
                .min(Comparator.comparing(Kasse::getQueueLength))
                .get();
    }
}
