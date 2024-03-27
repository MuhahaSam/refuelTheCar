package refuelTheCar.domain.gasStation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Location {
    @JsonProperty("Lon")
    private double lon;

    @JsonProperty("Lat")
    private double lat;
}
