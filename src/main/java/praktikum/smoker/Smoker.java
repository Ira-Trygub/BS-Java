package praktikum.smoker;

public class Smoker {
    String name;




    private void putStuff(Table table) throws InterruptedException {
        while(table.tableList.size() ==0){
            wait();
        }
        smoke();
        notifyAll();
    }

    private void smoke() {
        System.err.println(name + "is smoking" +  "~".repeat((int) ((Math.random() * (20 - 2)) + 2)));
    }
}
