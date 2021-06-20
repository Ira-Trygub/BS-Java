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
                Thread.sleep((int) ((Math.random() * (1000 - 100)) + 100));
                System.err.println(name + "  finished smoking!");
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void smoke() {
        System.err.println(name + "  is smoking" + "~".repeat((int) ((Math.random() * (50 - 2)) + 2)));
    }

    public Stuff getHas() {
        return has;
    }
}
