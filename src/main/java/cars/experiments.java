//import java.util.ArrayList;
//import java.util.Comparator;
//
//public class experiments {
//
//    import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//    public class SimRace {
//        private static final int carsAmount = 5;
//        private static final int roundsAmount = 10;
//
//        public static void main(String[] args) {
//            (new SimRace()).performSimRace();
//        }
//
//        public void performSimRace() {
//            var cars = new ArrayList<Car>();
////        ArrayList<Car> cars = new ArrayList<Car>();
//
//
//            for (int i = 0; i < carsAmount; i++) {
//                var car = new Car(String.valueOf(i), roundsAmount);
//                cars.add(car);
//                car.start();
//            }
//
//            for (Car c : cars) {
//                try {
//                    c.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            cars.sort(Comparator.comparing(Car:: getTimer));
//            for (Car c: cars) {
//                System.out.println("Platz:" + cars.indexOf(c) + " Wagen " + c.myName + " Zeit: " + c.timer);
//            }
//
//
////        Car[] cars = new Car[carsAmount];
////        for (int i = 0; i < carsAmount; i++) {
////            cars[i] = new Car(String.valueOf(i), roundsAmount);
////            cars[i].start();
////        }
////        for (int i = 0; i < carsAmount; i++) {
////            Car car = cars[i];
////            try {
////                car.join();
////            } catch (InterruptedException e) {
////            }
//////            System.out.println((i+1) + ". Platz:"+ " Wagen " + car.myName +  " Zeit: " + car.timer);
////        }
//
//
//
//
//
//
//
//
////        ++++++++++++++++++++++++++++++++++++++++++++++
////        var cars = IntStream.range(0, carsAmount).boxed().map(i -> new Car(String.valueOf(i), roundsAmount)).collect(Collectors.toList());
////      ___  cars.stream().map(c -> c.timer).collect(Collectors.)
////        for (var car : cars) {
////            car.start();
////        }
////        for (var car : cars) {
////            try {
////                car.join();
////            } catch (InterruptedException e) {
////            }
////        }
////        cars.sort(Comparator.comparing(Car::getTimer));
////        for (var car : cars) {
////            System.out.println("Platz:" + " Wagen " + car.myName + " Zeit: " + car.timer);
////        }
//        }
//    }
//
//
//
//}
