package refuelTheCar.models.internal;

import java.util.Objects;

import lombok.Data;

@Data
public class TrkFuel {
    private String id;
    private String fuelId;
    private String trkId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrkFuel trkFuel = (TrkFuel) o;
        return Objects.equals(fuelId, trkFuel.fuelId) &&
                Objects.equals(trkId, trkFuel.trkId);
    }
}
