package refuelTheCar.domain.gasStation;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class GasStation {
    private String Id;
    private String Name;
    private String Address;
    private String Brand;
    private String BrandId;
    private Organization Organization;
    private Location Location;
    private String TakeOffMode;
    private boolean PostPay;
    private boolean Enable;
    private List<Fuel> Fuels;
    private Map<String, Column> Columns;
}
