package jankowiak.kamil.service;


import jankowiak.kamil.persistence.enums.Color;
import jankowiak.kamil.persistence.model.Car;
import jankowiak.kamil.persistence.repository.JsonCarsConverter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGeneratorForCarPropertiesService {

    private final List<Car> carList;

    public DataGeneratorForCarPropertiesService() {
        this.carList = generateCarList();
    }

    public List<Car> getCarList() {
        return carList;
    }

    private List<Car> generateCarList(){

        return Arrays.asList(
                new Car.CarBuilder("HONDA", new BigDecimal(120000), Color.BLACK, 25, new ArrayList<>(Arrays.asList("ALLOY WHEEL", "AIR CONDITIONING"))).build(),
                new Car.CarBuilder("HONDA", new BigDecimal(230000), Color.SILVER, 250, new ArrayList<>(Arrays.asList("RADIO", "PARKING ASSISTANT"))).build(),
                new Car.CarBuilder("AUDI", new BigDecimal(220000), Color.GOLD, 45, new ArrayList<>(Arrays.asList("PIONEER SYSTEM SOUND", "AUTOMATIC TRANSMISSION", "AIR CONDITIONING"))).build(),
                new Car.CarBuilder("BMW", new BigDecimal(150000), Color.BLACK, 120, new ArrayList<>(Arrays.asList("ABS", "ALLOY WHEEL", "GPS"))).build(),
                new Car.CarBuilder("SKODA", new BigDecimal(560000), Color.BLUE, 450, new ArrayList<>(Arrays.asList("ABS", "ALLOY WHEEL", "AIR CONDITIONING"))).build(),
                new Car.CarBuilder("MAZDA", new BigDecimal(60000), Color.RED, 800, new ArrayList<>(Arrays.asList("ELECTRIC MIRRORS", "CRUISE CONTROL", "AIR CONDITIONING"))).build()
        );
    }

    public void saveToFile(String filename){
        JsonCarsConverter jsonCarsConverter = new JsonCarsConverter(filename);
        jsonCarsConverter.toJson(carList);
    }


}
