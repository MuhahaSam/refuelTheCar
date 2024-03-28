package refuelTheCar.models.internal;

import java.util.UUID;

import lombok.Data;
import refuelTheCar.enums.TakeOffModeEnum;

@Data
public class AZS {
    private UUID id;
    private UUID externalId;
    private String name;
    private String brand;
    private String brandId;
    private UUID organizationId;
    private Double lon;
    private Double lat;
    private TakeOffModeEnum takeOffMode;
    private boolean postPay;
    private boolean enable;

}
