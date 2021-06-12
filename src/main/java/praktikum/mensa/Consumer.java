package praktikum.mensa;

public interface Consumer {
    void awaitPayment() throws InterruptedException;

    void pay() throws InterruptedException;
}
