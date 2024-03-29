package refuelTheCar.models.internal;

import java.util.Objects;

import lombok.Data;

@Data
public class Price {
    private String id;
    private String azsId;
    private String fuelId;
    private Double value;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        return Objects.equals(azsId, price.azsId) &&
                Objects.equals(fuelId, price.fuelId) &&
                Objects.equals(value, price.value);
    }

}
