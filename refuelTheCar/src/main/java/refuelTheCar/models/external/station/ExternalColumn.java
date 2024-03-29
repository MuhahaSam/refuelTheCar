package refuelTheCar.models.external.station;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExternalColumn {
    @JsonProperty("Fuels")
    private List<String> fuels;
}
