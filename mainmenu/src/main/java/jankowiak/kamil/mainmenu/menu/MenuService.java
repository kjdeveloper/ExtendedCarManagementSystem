package jankowiak.kamil.mainmenu.menu;

import jankowiak.kamil.persistence.enums.Color;
import jankowiak.kamil.persistence.enums.SortType;
import jankowiak.kamil.persistence.model.Car;
import jankowiak.kamil.service.CarsService;
import jankowiak.kamil.service.DataUserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MenuService {

    private final String filename;
    private final DataUserService dataUserService;
    private final CarsService cars;

    public MenuService(String filename) {
        this.filename = filename;
        cars = new CarsService(filename);
        dataUserService = new DataUserService();
    }

    private void menu() {
        System.out.println("1. Sorting by model, price, color, mileage");
        System.out.println("2. Removes cars below the specific mileage");
        System.out.println("3. Color - count cars");
        System.out.println("4. Model - biggest price");
        System.out.println("5. Statistics by price and mileage");
        System.out.println("6. Car with biggest price");
        System.out.println("7. Sorting components in each car");
        System.out.println("8. Component - cars");
        System.out.println("9. Cars with specific price");
        System.out.println("10. LIST OF CARS");
        System.out.println("0. EXIT");
    }

    public void service() {

        int option;
        do {
            menu();
            option = dataUserService.getInt("Choose option: ");
            switch (option) {
                case 1:

                    var sortType = SortType.COLOR;

                    final List<Car> sorted = option1(sortType, false);
                    System.out.println(sorted);
                    break;

                case 2:
                    final List<Car> carsAboveX = option2(100);
                    System.out.println(carsAboveX);
                    break;

                case 3:
                    Map<Color, Long> carsQuantityGroupedByColor = option3();
                    carsQuantityGroupedByColor.forEach((k,v) -> System.out.println(k + " = " + v));
                    break;

                case 4:
                    Map<String, Car> map = option4();
                    map.forEach((k,v) -> System.out.println(k + " -> " + v));
                    break;

                case 5:
                    option5();
                    break;

                case 6:
                    Optional<Car> carWithBiggestPrice = option6();
                    System.out.println(carWithBiggestPrice);
                    break;

                case 7:
                    List<Car> carsWithSortedComponents = option7();
                    System.out.println(carsWithSortedComponents);
                    break;

                case 8:
                    Map<String, List<Car>> map1 = option8();
                    map1.forEach((k,v) -> System.out.println(k + " -> " + v));
                    break;

                case 9:
                    BigDecimal lower = new BigDecimal(120000);
                    BigDecimal higher = new BigDecimal(250000);
                    List<Car> carsBetweenPrices = option9(lower, higher);
                    System.out.println(carsBetweenPrices);
                    break;

                case 10:
                   option10();
                    break;

                case 0:
                    System.out.println("Bye bye");
                    dataUserService.close();
                    return;
            }
        } while (true);
    }

    private List<Car> option1(SortType sortType, boolean decending){
            return cars.sortBy(sortType, false);
    }

    private List<Car> option2(int x){
        return cars.mileageAboveX(x);
    }

    private Map<Color, Long> option3(){
        return cars.carsQuantityGroupedByColor();
    }

    private Map<String, Car> option4(){
        return cars.mostExpensiveCarByModel();
    }

    private void option5(){
        cars.statisticByPriceAndMileage();
    }

    private Optional<Car> option6(){
        return cars.carWithBiggestPrice();
    }

    private List<Car> option7(){
        return cars.sortComponents();
    }

    private Map<String, List<Car>> option8(){
        return cars.componentsAndListOfCarsContainsThisComponent();
    }

    private List<Car> option9(BigDecimal lower, BigDecimal higher){
        if (lower.compareTo(higher) > 0){
            throw new IllegalArgumentException("Arguments is not correct");
        }
        return cars.carsWithSPecificPrice(lower, higher);

    }

    private void option10(){
        cars.showAllCarsFromList();
    }

}
