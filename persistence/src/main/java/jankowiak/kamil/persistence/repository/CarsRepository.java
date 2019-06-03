package jankowiak.kamil.persistence.repository;


import jankowiak.kamil.persistence.model.Car;

import java.util.List;

public interface CarsRepository {

    void saveAll(String jsonFilename, List<Car> cars);

    List<Car> findAll(String jsonFilename);
}
