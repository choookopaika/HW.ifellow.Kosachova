import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Audi("A6", 2021, "Синий", "Автомат", "Бензин", 2.0));
        cars.add(new Audi("Q7", 2020, "Белый", "Автомат", "Дизель", 3.0));

        cars.add(new Suzuki("Swift", 2004, "Зеленый", "Механическая", "Бензин", 1.2));
        cars.add(new Suzuki("Vitara", 2003, "Красный", "Автомат", "Бензин", 1.6));

        cars.add(new Toyota("Camry", 2021, "Черный", "Автомат", "Бензин", 2.5));
        cars.add(new Toyota("Corolla", 2005, "Белый", "Механическая", "Бензин", 1.8));

        cars.add(new BMW("X5", 2019, "Белый", "Автомат", "Дизель", 3.0));
        cars.add(new BMW("3 Series", 2020, "Зеленый", "Механическая", "Бензин", 2.0));

        cars.add(new Mercedes("E-Class", 2022, "Черный", "Автомат", "Бензин", 2.0));
        cars.add(new Mercedes("C-Class", 2021, "Белый", "Автомат", "Дизель", 2.2));

        System.out.println("Информация об автомобилях, выпущенных после 2006 года:");
        carsAfter2006(cars);

        System.out.println("Изменение цвета авто с зеленого на красный:");
        changeColor(cars);

        System.out.println("Увеличение объема двигателя у дизельных авто:");
        upgradeEngine(cars);

        System.out.println("Вывод всех авто:");
        allCars(cars);
    }

    public static void carsAfter2006(List<Car> cars) {
        for (Car car : cars) {
            if (car.yearOfIssue > 2006) {
                car.getInfo();
                car.specialFeature();
                System.out.println(" ");
            }
            else {
                System.out.println(car.brand + " " + car.modelName + " - устаревшее авто.");
                System.out.println(" ");
            }
        }
    }

    public static void changeColor(List<Car> cars) {
        for (Car car : cars) {
            if (car.color.equalsIgnoreCase("Зеленый")) {
                car.color = "Красный";
                System.out.println(car.brand + " " + car.modelName + " перекрашен в красный.");
            }
        }
        System.out.println(" ");
    }

    public static void  upgradeEngine(List<Car> cars) {
        for (Car car : cars) {
            if (car.engineType.equalsIgnoreCase("Дизель")) {
                car.engineDisplacement += 0.3;
                System.out.println(car.brand + " " + car.modelName + " теперь с объемом двигателя " + car.engineDisplacement + " л");
            }
        }
        System.out.println(" ");
    }

    public static void allCars(List<Car> cars) {
        for (Car car : cars) {
            car.getInfo();
            car.specialFeature();
            System.out.println(" ");
        }
    }
}