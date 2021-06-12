package praktikum.mensa;

public class Kasse  {
    public Queue hungry_students;
    String name;

    public Queue getHungry_students() {
        return hungry_students;
    }

    Kasse(String name) {
        this.name = name;
    }


}

//public class Kasse {
//    private int kasse_max_size;
//    private LinkedList<Student> kasse_list_students;
//    private final Lock kasseLock = new ReentrantLock();
//    private final Condition notFull = kasseLock.newCondition();
//    private final Condition notEmpty = kasseLock.newCondition();
//
//
////    Konstruktor
//    public Kasse(int n) {
//      kasse_max_size = n;
//      kasse_list_students = new LinkedList<>();
//    }
//
//    public void enter(Student st) throws InterruptedException {
//        kasseLock.lockInterruptibly();
//        try {
//            while (kasse_list_students.size() == kasse_max_size) {
//                notFull.await();
//            }
//        kasse_list_students.add(st);
//            System.err.println("        Enter:" + Thread.currentThread().getName() + "is on the Kasse");
//            notEmpty.signal();
//        } finally {
//            kasseLock.unlock();
//                    }
//    }
//
//    public Student remove() throws InterruptedException {
//        Student st = null;
//        kasseLock.lockInterruptibly();
//        try {
//            while (kasse_list_students.size() == 0) {
//                notEmpty.await();
//            }
//            st = kasse_list_students.removeFirst();
//            System.err.println("        remove:" + Thread.currentThread().getName() + "removes the Kasse");
//            notFull.signal();
//        } finally {
//            kasseLock.unlock();
//        }
//        return st;
//    }
//}
