package praktikum.mensa;

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

    public Kasse chooseKasse(Student s) {
        Kasse minKasse = allKasse.get(0);
        for (int i = 0; i < allKasse.size(); i++) {
            if (allKasse.get(i).queueLength() < minKasse.queueLength()) {
                minKasse = allKasse.get(i);
            }
        }
        return minKasse;
    }

    public void interrupt() {
        for (var k : allKasse) {
            k.interrupt();
        }
    }
}
