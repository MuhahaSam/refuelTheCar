package refuelTheCar.mappers;

import refuelTheCar.models.internal.Price;

public class PriceMapper {

    public static Price toPrice(String id, Double value, String fuelId, String azsId) {
        Price price = new Price();

        price.setId(id);
        price.setAzsId(azsId);
        price.setFuelId(fuelId);
        price.setValue(value);

        return price;

    }

}
