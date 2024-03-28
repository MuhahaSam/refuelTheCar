package refuelTheCar.models.internal;

import java.util.UUID;

import lombok.Data;

@Data
public class Price {
    private UUID id;
    private UUID azsId;
    private UUID fuelId;
    private Double value;

}
