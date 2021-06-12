package praktikum.mensa;

import java.util.LinkedList;

public class Main {


    public static void main(String[] args) {
        int num_students = 20;
        var mensa = new Mensa(3);
        var students = new LinkedList<Student>();

        for (int i = 1; i <= num_students; i++) {
            var s = new Student(mensa, "Student " + i);
            students.add(s);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        System.err.println("stop");
        for (var s: students) {
            s.interrupt();
        }
    }

}
