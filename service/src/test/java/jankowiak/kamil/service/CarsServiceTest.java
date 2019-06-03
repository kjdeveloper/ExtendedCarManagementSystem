package jankowiak.kamil.service;


import jankowiak.kamil.exceptions.MyException;
import jankowiak.kamil.persistence.enums.SortType;
import jankowiak.kamil.persistence.repository.impl.JsonCarsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarsServiceTest {

    private JsonCarsRepository jsonCarsRepository = new JsonCarsRepository();
    private DataGeneratorForCarPropertiesService dataGeneratorForCarPropertiesService = new DataGeneratorForCarPropertiesService();

    @Test
    @DisplayName("Sort by model descending")
    public void Test1() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var sortType = SortType.MODEL;

        var cars = new CarsService(jsonFilename).sortBy(sortType, true);

        assertEquals("SKODA", cars.get(0).getModel(), "TEST 1 FAILED");
    }

    @Test
    @DisplayName("Sort by model ascending")
    public void Test2() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var sortType = SortType.MODEL;

        var cars = new CarsService(jsonFilename).sortBy(sortType, false);

        assertEquals("AUDI", cars.get(0).getModel(), "TEST 2 FAILED");
    }

    @Test
    @DisplayName("Sort by color descending")
    public void Test3() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var sortType = SortType.COLOR;

        var cars = new CarsService(jsonFilename).sortBy(sortType, true);

        assertEquals("HONDA", cars.get(0).getModel(), "TEST 3 FAILED");
    }

    @Test
    @DisplayName("Sort by color ascending")
    public void Test4() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var sortType = SortType.COLOR;

        var cars = new CarsService(jsonFilename).sortBy(sortType, false);

        assertEquals("BMW", cars.get(0).getModel(), "TEST 4 FAILED");
    }

    @Test
    @DisplayName("Sort by price descending")
    public void Test5() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var sortType = SortType.PRICE;

        var cars = new CarsService(jsonFilename).sortBy(sortType, true);

        assertEquals("SKODA", cars.get(0).getModel(), "TEST 5 FAILED");
    }

    @Test
    @DisplayName("Sort by price ascending")
    public void Test6() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var sortType = SortType.PRICE;

        var cars = new CarsService(jsonFilename).sortBy(sortType, false);

        assertEquals("MAZDA", cars.get(0).getModel(), "TEST 6 FAILED");
    }

    @Test
    @DisplayName("Sort by mileage descending")
    public void Test7() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var sortType = SortType.MILEAGE;

        var cars = new CarsService(jsonFilename).sortBy(sortType, true);

        assertEquals("MAZDA", cars.get(0).getModel(), "TEST 7 FAILED");
    }

    @Test
    @DisplayName("Sort by mileage ascending")
    public void Test8() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var sortType = SortType.MILEAGE;

        var cars = new CarsService(jsonFilename).sortBy(sortType, false);

        assertEquals("HONDA", cars.get(0).getModel(), "TEST 8 FAILED");
    }

    @Test
    @DisplayName("List size with cars above some mileage")
    public void Test9() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var mileage = 600;

        var cars = new CarsService(jsonFilename).mileageAboveX(mileage);

        assertEquals(1, cars.size(), "TEST 9 FAILED");
    }

    @Test
    @DisplayName("Exception throw when mileage is less than 0")
    public void Test10() {
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var mileage = -10;
        var throwable = Assertions.assertThrows(
                MyException.class,
                () -> new CarsService(jsonFilename).mileageAboveX(mileage));

        assertEquals( "MAXMILEAGE CANNOT BE LESS THAN 0", throwable.getMessage(), "TEST 10 FAILED");
    }

    @Test
    @DisplayName("Color keySet size")
    public void Test11(){
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest11.json";

        var cars = new CarsService(jsonFilename).carsQuantityGroupedByColor();

        assertEquals(3, cars.keySet().size(), "TEST 11 FAILED");
    }

    @Test
    @DisplayName("Most expensive car in Honda")
    public void Test12(){
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var cars = new CarsService(jsonFilename).mostExpensiveCarByModel();

        assertEquals(new BigDecimal(230000), cars.get("HONDA").getPrice(), "TEST 12 FAILED");
    }

    @Test
    @DisplayName("The cheapest car")
    public void Test13(){
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var cars = new CarsService(jsonFilename).mostExpensiveCarByModel();

        assertEquals("SKODA", cars.keySet().stream().findFirst().get(), "TEST 12 FAILED");
    }

    @Test
    @DisplayName("Car with biggest price")
    public void Test14(){
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var cars = new CarsService(jsonFilename).carWithBiggestPrice();

        assertEquals("SKODA", cars.get().getModel(), "TEST 14 FAILED");
    }


    @Test
    @DisplayName("Cars quantity with specific components => AIR CONDITIONING")
    public void Test15(){
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var cars = new CarsService(jsonFilename).componentsAndListOfCarsContainsThisComponent();

        assertEquals(4, cars.get("AIR CONDITIONING").size(), "TEST 15 FAILED");
    }

    @Test
    @DisplayName("Cars with specific components => ABS")
    public void Test16(){
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var cars = new CarsService(jsonFilename).componentsAndListOfCarsContainsThisComponent();

        assertEquals(2, cars.get("ABS").size(), "TEST 16 FAILED");
    }

    @Test
    @DisplayName("Cars with specific price")
    public void Test17(){
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var lowerPrice = new BigDecimal(100000);
        var higherPrice = new BigDecimal(300000);

        var cars = new CarsService(jsonFilename).carsWithSPecificPrice(lowerPrice, higherPrice);

        assertEquals(4, cars.size(), "TEST 17 FAILED");
    }

    @Test
    @DisplayName("Cars with specific price exception")
    public void Test18(){
        var jsonFilename = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\service\\src\\test\\java\\jankowiak\\kamil\\service\\resources\\carsForTest.json";

        var lowerPrice = new BigDecimal(1100000);
        var higherPrice = new BigDecimal(300000);

        var throwable = Assertions.assertThrows(
                MyException.class,
                () ->new CarsService(jsonFilename).carsWithSPecificPrice(lowerPrice, higherPrice));

        assertEquals("LOWER PRICE CANNOT BE BIGGER THAN HIGHER PRICE", throwable.getMessage(), "TEST 18 FAILED");
    }

}

