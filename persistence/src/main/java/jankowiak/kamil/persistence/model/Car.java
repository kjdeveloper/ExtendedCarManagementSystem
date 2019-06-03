package jankowiak.kamil.persistence.model;

import jankowiak.kamil.persistence.enums.Color;

import java.math.BigDecimal;
import java.util.List;


public class Car {

    private String model;
    private BigDecimal price;
    private Color color;
    private int mileage;
    private List<String> components;

    public Car() {
    }

    private Car(CarBuilder carBuilder) {
        this.model = carBuilder.model;
        this.price = carBuilder.price;
        this.color = carBuilder.color;
        this.mileage = carBuilder.mileage;
        this.components = carBuilder.components;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Color getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "[ Samochód: " +
                " marka: " + model +
                ", cena: " + price +
                ", color: " + color +
                ", przebieg: " + mileage +
                ", wyposażenie: " + components + "] " + "\n";
    }

    public static class CarBuilder {

        private String model;
        private BigDecimal price;
        private Color color;
        private int mileage;
        private List<String> components;

        public CarBuilder(String model, BigDecimal price, Color color, int mileage, List<String> components) {
            this.model = model;
            this.price = price;
            this.color = color;
            this.mileage = mileage;
            this.components = components;
        }

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public CarBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public CarBuilder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public CarBuilder components(List<String> components) {
            this.components = components;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

}
