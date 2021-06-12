package praktikum.mensa;

public class Student {
    Mensa mensa;
    String name;
    Thread thread;

    Student(Mensa mensa, String s) {
        this.mensa = mensa;
        name = s;
        thread = new Thread(() -> { //даём потоку работу через лямбда функцию () -> {}
            while (true) {
                try {
                    enter();
                    // enter will be locked until we can pay
                    pay();
                    eat();
                } catch (InterruptedException e) {
                    System.err.println(this.name + " stop");
                    return;
                }
            }
        });

        thread.start();
    }

    void interrupt() {
        thread.interrupt();
    }

//    void runLoop() {
//        while (true) {
//            enter();
//            // enter will be locked until we can pay
//            pay();
//            eat();
//        }
//    }

    private void enter() {
    }

    private void pay() {
    }

    private void eat() throws InterruptedException {
        System.err.println(this.name + " eating");
        Thread.sleep(3000);
    }
}

//public class Student extends Thread {
//    public final int MAX_IDLE_TIME = 100;
//    int stnumber;
//    Kasse currentKasse;
//    private Student item;

//
//    public void run() {
//        try {
//            while (!isInterrupted()) {
//                wishKasse();
//                item = currentKasse.remove(); ///?????????
//                pause();
//            }
//        } catch (InterruptedException e) {
////                    e.printStackTrace();
//            System.err.println(this.getName() + "  was interrupted!");
//        }
//    }
//
//
//    public void wishKasse() {
//        System.err.println("Student" + this.stnumber + "  want to the checkout!");
//    }
//
//    public void pause() throws InterruptedException {
//        int sleepTime = (int) (MAX_IDLE_TIME * Math.random());
//        Thread.sleep(sleepTime);
//    }
//
//}
