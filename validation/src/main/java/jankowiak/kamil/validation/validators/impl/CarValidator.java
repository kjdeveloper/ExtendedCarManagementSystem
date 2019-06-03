package jankowiak.kamil.validation.validators.impl;

import jankowiak.kamil.persistence.model.Car;
import jankowiak.kamil.validation.validators.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarValidator implements Validator<Car> {

    private final String MODEL_NAME_REGEX = "[A-Z ]+";
    private final String COMPONENTS_NAME_REGEX = "[A-Z ]+";

    private Map<String, String> errors = new HashMap<>();

    @Override
    public Map<String, String> validate(Car car) {
        errors.clear();

        if (car == null){
            errors.put("car", "null");
        }

        if (!isModelValid(car)){
            errors.put("model", "model is not valid " + car.getModel());
        }

        if (!isPriceValid(car)){
            errors.put("price", "price is not valid " + car.getPrice());
        }

        if (!isMileageValid(car)){
            errors.put("mileage", "mileage is not valid " + car.getMileage());
        }

        if (!isComponentsValid(car)){
            errors.put("components", "components is not valid " + car.getComponents());
        }

        return errors;
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private boolean isModelValid(Car car){
        return car.getModel().matches(MODEL_NAME_REGEX);
    }

    private boolean isPriceValid(Car car){
        return car.getPrice().compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean isMileageValid(Car car){
        return car.getMileage() >= 0;
    }

    private boolean isComponentsValid(Car car){
        List<String> components = car.getComponents();
        for (String com: components) {
            if (!com.matches(COMPONENTS_NAME_REGEX)){
                return false;
            }
        }
        return true;
    }
}
