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

    public Kasse chooseKasse(Student s) throws InterruptedException {
Kasse minKasse = allKasse.get(0);
     for (int i = 0; i< allKasse.size(); i++) {
         if (allKasse.get(i).queueLength() < minKasse ) {

         }
     }


    }

    public void interrupt() {
        for (var k : allKasse) {
            k.interrupt();
        }
    }
}

//return kasse
//pay Methode in Kasse mit eigenem kassenLock
//in Kasse Zäxhler, wir viel St
//Zähler im Enter Methode erhöhen
//Methode
