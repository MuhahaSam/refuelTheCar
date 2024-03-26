package refuelTheCar.domain.price;

import lombok.Data;

@Data
public class Price {
    private String stationId;
    private String productId;
    private double price;
}
