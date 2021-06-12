package cars;

import java.util.ArrayList;
import java.util.Comparator;

public class SimRace {
    private static final int carsAmount = 5;
    private static final int roundsAmount = 10;

    public static void main(String[] args) {
        (new SimRace()).performSimRace();
    }

    public void performSimRace() {
        var cars = new ArrayList<Car>();
//      ArrayList<Car> cars = new ArrayList<Car>();


        for (int i = 0; i < carsAmount; i++) {
            var car = new Car(String.valueOf(i), roundsAmount);
            cars.add(car);
            car.start();
        }

        for (Car c : cars) {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cars.sort(Comparator.comparing((Car::getTimer))); // cars.sort(Comparator.comparing((car -> { return car.getTimer();})));
        for (Car c: cars) {
            System.out.println("Platz:" + cars.indexOf(c) + " Wagen " + c.myName + " Zeit: " + c.timer);
        }
   }
}
