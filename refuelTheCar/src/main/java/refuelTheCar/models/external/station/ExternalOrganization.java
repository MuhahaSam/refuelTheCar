package refuelTheCar.models.external.station;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExternalOrganization {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Inn")
    private String inn;

    @JsonProperty("Kpp")
    private String kpp;

}
