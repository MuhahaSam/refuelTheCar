package refuelTheCar.models.internal;

import java.util.Objects;

import lombok.Data;

@Data
public class Fuel {
    private String id;
    private String externalId;
    private String type;
    private Integer typeId;
    private String brand;
    private String name;
    private String organizationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fuel fuel = (Fuel) o;
        return Objects.equals(externalId, fuel.externalId) &&
                Objects.equals(type, fuel.type) &&
                Objects.equals(typeId, fuel.typeId) &&
                Objects.equals(brand, fuel.brand) &&
                Objects.equals(name, fuel.name) &&
                Objects.equals(organizationId, fuel.organizationId);
    }
}
