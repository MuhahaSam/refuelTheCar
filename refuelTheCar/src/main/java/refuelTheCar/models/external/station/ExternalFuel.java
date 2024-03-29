package refuelTheCar.models.external.station;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExternalFuel {
    @JsonProperty("Id")
    private String id;

    @JsonProperty("Price")
    private Double price;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("TypeId")
    private Integer typeId;

    @JsonProperty("Brand")
    private String brand;

    @JsonProperty("Name")
    private String name;
}
