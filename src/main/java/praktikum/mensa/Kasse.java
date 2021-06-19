package praktikum.mensa;

public class Kasse {
    private final String name;
    private int queueLength; // TODO: 19.06.21 not thread-safe, make it atomic!

    public Kasse(String name) {
        this.name = name;
        queueLength = 0;
    }

    @Override
    public String toString() {
        return "Kasse(" + name + ")";
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void increaseQueueLength() {
        queueLength++;
    }

    public void decreaseQueueLength() {
        queueLength--;
    }

    public String getName() {
        return name;
    }
}
