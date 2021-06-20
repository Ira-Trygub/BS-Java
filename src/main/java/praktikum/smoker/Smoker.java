package praktikum.smoker;

public class Smoker extends Thread {
    String name;
    Table table;
    Stuff has;

    public Smoker(String name, Stuff has, Table table) {
        this.name = name;
        this.table = table;
        this.has = has;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.err.println(name + "  is waiting" + ".".repeat((int) ((Math.random() * (20 - 2)) + 2)));
                table.consume(this);
                smoke();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void canStartSmoking() {
        System.err.println(name + "  is starting to smoke" + "~".repeat((int) ((Math.random() * (50 - 2)) + 2)));
    }

    private void smoke() throws InterruptedException {
        Thread.sleep((int) ((Math.random() * (1000 - 100)) + 100));
        System.err.println(name + "  finished smoking!");
    }

    public Stuff getHas() {
        return has;
    }
}
