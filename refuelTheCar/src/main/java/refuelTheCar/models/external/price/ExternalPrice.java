package refuelTheCar.models.external.price;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExternalPrice {
    @JsonProperty("StationId")
    private String stationId;

    @JsonProperty("ProductId")
    private String productId;

    @JsonProperty("Price")
    private double price;
}
