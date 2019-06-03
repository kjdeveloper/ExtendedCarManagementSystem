package jankowiak.kamil.persistence.repository.impl;

import jankowiak.kamil.persistence.model.Car;
import jankowiak.kamil.persistence.model.Cars;
import jankowiak.kamil.persistence.repository.CarsRepository;
import jankowiak.kamil.persistence.repository.JsonCarsConverter;

import java.util.Collections;
import java.util.List;

public class JsonCarsRepository implements CarsRepository {

    @Override
    public void saveAll(String jsonFilename, List<Car> cars) {
        JsonCarsConverter jsonCarsConverter = new JsonCarsConverter(jsonFilename);
        Cars cars1 = new Cars(cars);
        jsonCarsConverter.toJson(cars1.getCars());
    }

    @Override
    public List<Car> findAll(String jsonFilename) {
        return new JsonCarsConverter(jsonFilename)
                .fromJson()
                .orElse(Collections.emptyList());
    }
}
