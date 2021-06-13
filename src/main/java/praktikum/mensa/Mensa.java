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

    public void enter(Student s) throws InterruptedException {
        final boolean[] interrupted = {false};
        allKasse
                .stream()
                .min(Comparator.comparing(Kasse::queueLength))
                .ifPresent(k -> {
                    try {
                        System.err.println(s + " go to " + k);
                        k.enter(s);
                    } catch (InterruptedException e) {
                        interrupted[0] = true;
                    }
                });
        if (interrupted[0]) throw new InterruptedException();
    }

    public void interrupt() {
        for (var k : allKasse) {
            k.interrupt();
        }
    }
}

//public class Mensa {
//    int kasse_num;
//    int stud_num;
//    private LinkedList<Kasse> allKasse;
//    private LinkedList<Student> allStudents;
//
//    Mensa(int k,int s) {
//        kasse_num = k;
//        stud_num = s;
//
//        public void startSimulation() {
//            allKasse = new LinkedList<Kasse>();
//            allStudents = new LinkedList<Student>();
//        }
//        for (int i = 1; i <= s; i++) {
//            Student current = new Student();
//            current.setName("Verbraucher " + i);
//            consumerList.add(current);
//            current.start();
//        }
//
//        // Erzeuger - Threads erzeugen
//        for (int i = 1; i <= NO_PRODUCER; i++) {
//            Producer current = new Producer(buffer);
//            current.setName("Erzeuger " + i);
//            producerList.add(current);
//            current.start();
//        }


//
//
//    }
//
//
//
//}
