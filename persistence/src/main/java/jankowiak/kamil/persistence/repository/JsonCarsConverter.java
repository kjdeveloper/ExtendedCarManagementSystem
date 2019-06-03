package jankowiak.kamil.persistence.repository;

import jankowiak.kamil.persistence.model.Car;

import java.util.List;

public class JsonCarsConverter extends JsonConverter<List<Car>> {
    public JsonCarsConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
