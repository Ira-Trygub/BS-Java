package praktikum.mensa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MensaMain {
    public static void main(String[] args) {
        int numStudents = 20;
        var mensa = new Mensa(3);

        var students = createStudents(numStudents, mensa);
        startStudents(students);

//        for (int i = 0; i < numStudents; i++) {
//            students.get(i).run();
//        }

        pauseMainThread();

        students.forEach(Thread::interrupt);


        interruptAllThreads(mensa, students);
        System.err.println("Main stop");

    }

    static List<Student> createStudents(int numStudents, Mensa mensa) {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            Student s = new Student(mensa, "Student"+i, (int)(Math.random()*10000));
            students.add( s);
        }
        return students;
    }

//    static List<Student> createStudents(int numStudents, Mensa mensa) {
//        return IntStream
//                .range(0, numStudents)
//                .boxed()
//                .map(i -> new Student(mensa, "student-" + i, 1000))
//                .collect(Collectors.toList());
//    }


    static void pauseMainThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }


    static void startStudents(List<Student> students) {
        for (var s : students) {
            s.start();
        }
    }


    static void interruptAllThreads(Mensa mensa, List<Student> students) {
        for (var s : students) {
            s.interrupt();
        }
        mensa.interrupt();

    }
}
