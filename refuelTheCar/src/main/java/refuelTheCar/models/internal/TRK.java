package refuelTheCar.models.internal;

import java.util.Objects;

import lombok.Data;

@Data
public class TRK {
    private String id;
    private Integer number;
    private String azsId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TRK trk = (TRK) o;
        return Objects.equals(number, trk.number) &&
                Objects.equals(azsId, trk.azsId);
    }
}
