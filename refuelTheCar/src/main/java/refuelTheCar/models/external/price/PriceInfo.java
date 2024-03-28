package refuelTheCar.models.external.price;

import lombok.Data;

@Data
public class PriceInfo {
    private String stationId;
    private String productId;
    private double price;
}
