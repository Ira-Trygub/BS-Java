package praktikum.mensa;

import java.util.LinkedList;

public class Mensa {
    LinkedList<Kasse> all_kasse;

    Mensa(int num_kasse) {
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
