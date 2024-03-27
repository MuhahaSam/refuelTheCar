package refuelTheCar.domain.gasStation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Organization {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Inn")
    private String inn;

    @JsonProperty("Kpp")
    private String kpp;

}
