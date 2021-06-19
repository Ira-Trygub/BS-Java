package praktikum.mensa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MensaMain {
    public static void main(String[] args) {
        var mensa = new Mensa(3);

        var students = createStudents(20, mensa);
        startStudents(students);

        pauseMainThread();

        interruptAllThreads(students);
        System.err.println("Main stop");
    }

    static List<Student> createStudents(int numStudents, Mensa mensa) {
        return IntStream.range(0, numStudents)
                .boxed()
                .map(i -> new Student(mensa, "Student" + i, (int) (Math.random() * 10000)))
                .collect(Collectors.toList());
    }

    static void pauseMainThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }


    static void startStudents(List<Student> students) {
        students.forEach(Student::start);
    }


    static void interruptAllThreads(List<Student> students) {
        students.forEach(Thread::interrupt);
    }
}
