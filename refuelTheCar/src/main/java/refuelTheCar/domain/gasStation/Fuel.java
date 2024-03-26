package refuelTheCar.domain.gasStation;

import lombok.Data;

@Data
public class Fuel {
    private String Id;
    private double Price;
    private String Type;
    private int TypeId;
    private String Brand;
    private String Name;
}
