package refuelTheCar.models.internal;

import java.util.UUID;

import lombok.Data;

@Data
public class TRK {
    private UUID id;
    private Integer number;
    private UUID azsId;
    private UUID trkFuelId;
}
