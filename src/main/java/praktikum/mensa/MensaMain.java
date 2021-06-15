package praktikum.mensa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MensaMain {
    public static void main(String[] args) {
        var numStudents = 20;
        var mensa = new Mensa(3);
        var students = createStudents(numStudents, mensa);

        pauseMainThread();
        System.err.println("Main stop");
        interruptAllThreads(mensa, students);
    }

    static List<Student> createStudents(int numStudents, Mensa mensa) {
        return IntStream
                .range(0, numStudents)
                .boxed()
                .map(i -> new Student(mensa, "student-" + i, 1000))
                .collect(Collectors.toList());
    }

    static void pauseMainThread() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
    }


    static void startAllThreads(Mensa mensa, List<Student> students) {
        for (var s : students) {
            s.interrupt();
        }
        mensa.interrupt();
    }


    static void interruptAllThreads(Mensa mensa, List<Student> students) {
        for (var s : students) {
            s.interrupt();
        }
        mensa.interrupt();
    }
}
