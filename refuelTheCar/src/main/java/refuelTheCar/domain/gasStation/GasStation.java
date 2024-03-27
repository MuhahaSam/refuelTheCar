package refuelTheCar.domain.gasStation;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GasStation {
    @JsonProperty("Id")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("Brand")
    private String brand;

    @JsonProperty("BrandId")
    private String brandId;

    @JsonProperty("Organization")
    private Organization organization;

    @JsonProperty("Location")
    private Location location;

    @JsonProperty("TakeOffMode")
    private String takeOffMode;

    @JsonProperty("PostPay")
    private boolean postPay;

    @JsonProperty("Enable")
    private boolean enable;

    @JsonProperty("Fuels")
    private List<Fuel> fuels;

    @JsonProperty("Columns")
    private Map<String, Column> columns;
}
