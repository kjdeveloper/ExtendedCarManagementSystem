package jankowiak.kamil.service;

import jankowiak.kamil.exceptions.MyException;
import jankowiak.kamil.persistence.enums.Color;
import jankowiak.kamil.persistence.enums.SortType;
import jankowiak.kamil.persistence.model.Car;
import jankowiak.kamil.persistence.repository.impl.JsonCarsRepository;
import jankowiak.kamil.validation.validators.impl.CarValidator;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarsService {

    private List<Car> cars;

    public CarsService(String filename) {
        var atomicInteger = new AtomicInteger(1);
        var carValidator = new CarValidator();

        cars = new JsonCarsRepository()
                .findAll(filename)
                .stream()
                .filter(car -> {
                    Map<String, String> errors = carValidator.validate(car);

                    if (carValidator.hasErrors()) {
                        System.out.println("ORDER NO: " + atomicInteger.get());
                        System.out.println("----------- VALIDATION ERRORS -----------");
                        errors.forEach((k, v) -> System.out.println(k + " -> " + v));
                        System.out.println("-------------------------------------------");
                    }

                    atomicInteger.incrementAndGet();

                    return !carValidator.hasErrors();
                }).collect(Collectors.toList());
    }

    //1
    public List<Car> sortBy (SortType sortType, boolean descending){
        Stream<Car> carStream = null;
        switch (sortType){
            case MODEL:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getModel));
                break;
            case COLOR:
                carStream = cars.stream().sorted(Comparator.comparing(c -> c.getColor().toString()));
                break;
            case PRICE:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getPrice));
                break;
            case MILEAGE:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getMileage));
                break;
        }

        List<Car> sortedCars = carStream.collect(Collectors.toList());

        if (descending){
            Collections.reverse(sortedCars);
        }
        return sortedCars;
    }

    //2
    public List<Car> mileageAboveX (int maxMileage){

        if (maxMileage < 0){
            throw new MyException("MAXMILEAGE CANNOT BE LESS THAN 0");
        }

        return cars.stream()
                .filter(km -> km.getMileage() > maxMileage)
                .collect(Collectors.toList());
    }

    //3
    public Map<Color, Long> carsQuantityGroupedByColor(){
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()));
    }
    //4
    public Map<String, Car> mostExpensiveCarByModel() {

        return cars
                .stream()
                .collect(Collectors.groupingBy(
                        Car::getModel,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Car::getPrice)), carOp -> carOp.orElseThrow(() -> new MyException("CAR EXCEPTION")))));
    }

    //5
    public void statisticByPriceAndMileage (){
        IntSummaryStatistics byPrice = cars.stream()
                .collect(Collectors.summarizingInt(p -> p.getPrice().intValue()));
        IntSummaryStatistics byMileage = cars.stream()
                .collect(Collectors.summarizingInt(Car::getMileage));

        System.out.println("By price: MIN: " + byPrice.getMin() + ", MAX: " + byPrice.getMax() + ", AVG: " + byPrice.getAverage() + ", SUM: " + byPrice.getSum());
        System.out.println("\nBy mileage: MIN: " + byMileage.getMin() + ", MAX: " + byMileage.getMax() + ", AVG: " + byMileage.getAverage() + ", SUM: " + byMileage.getSum() +  "\n");
    }
    //6
    public Optional<Car> carWithBiggestPrice(){
        return cars.stream()
                .max(Comparator.comparing(Car::getPrice));

    }

    //7
    public List<Car> sortComponents() {
        return cars
                .stream()
                .peek(car -> car.setComponents(car.getComponents()
                        .stream()
                        .sorted()
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    //8
    public Map<String, List<Car>> componentsAndListOfCarsContainsThisComponent(){
        return cars
                .stream()
                .flatMap(car -> car.getComponents().stream())
                .distinct()
                .collect(Collectors.toMap(
                        comp -> comp,
                        comp -> cars.stream().filter(car -> car.getComponents().contains(comp)).collect(Collectors.toList())
                ))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    //9
    public List<Car> carsWithSPecificPrice (BigDecimal lowerPrice, BigDecimal higherPrice){
        if (lowerPrice.compareTo(higherPrice) > 0){
            throw new MyException("LOWER PRICE CANNOT BE BIGGER THAN HIGHER PRICE");
        }

        return cars.stream()
                .filter(p -> p.getPrice().compareTo(lowerPrice) > 0 && p.getPrice().compareTo(higherPrice) < 0)
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    //10
    public void showAllCarsFromList(){
        for (Car car : cars) {
            System.out.println(car);
        }
    }

}

