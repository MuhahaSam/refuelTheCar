package refuelTheCar.models.external.station;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExternalLocation {
    @JsonProperty("Lon")
    private Double lon;

    @JsonProperty("Lat")
    private Double lat;
}
