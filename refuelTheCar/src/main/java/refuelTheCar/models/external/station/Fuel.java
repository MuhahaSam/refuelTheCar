package refuelTheCar.models.external.station;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Fuel {
    @JsonProperty("Id")
    private String id;

    @JsonProperty("Price")
    private int price;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("TypeId")
    private int typeId;

    @JsonProperty("Brand")
    private String brand;

    @JsonProperty("Name")
    private String name;
}
