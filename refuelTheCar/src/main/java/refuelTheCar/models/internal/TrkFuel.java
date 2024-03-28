package refuelTheCar.models.internal;

import java.util.UUID;

import lombok.Data;

@Data
public class TrkFuel {
    private UUID id;
    private UUID fuelId;
    private UUID trkId;
}
