package jankowiak.kamil.persistence.model;

import java.util.*;


public class Cars {

    private List<Car> cars;

    public Cars() {
    }

    public Cars(List<Car> cars) {
        this.cars = cars;
    }


    public List<Car> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        return "Cars: " + cars +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars1 = (Cars) o;
        return Objects.equals(cars, cars1.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}
