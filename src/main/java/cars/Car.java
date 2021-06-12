package cars;

public class Car extends Thread {
    public final String myName;
    private final int roundAmount;
    volatile int timer = 0;
    int proportion = 100;

    public Car(String myName, int roundAmount) {
        this.myName = myName;
        this.roundAmount = roundAmount;
    }

    public int getTimer() {
        return timer;
    }

    public void run() {
        for (int i = 0; i < roundAmount; i++) {
            var timeRound = (int) (Math.random() * proportion);
            try {
                Thread.sleep(timeRound);
            } catch (InterruptedException e) {
            }
            timer += timeRound;
        }
    }
}
