package refuelTheCar.domain.gasStation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Column {
    @JsonProperty("Fuels")
    private List<String> fuels;
}
