package refuelTheCar.models.internal;

import java.util.UUID;

import lombok.Data;

@Data
public class Fuel {
    private UUID id;
    private String externalId;
    private String type;
    private Integer typeId;
    private String brand;
    private String name;
    private UUID organizationId;
}
