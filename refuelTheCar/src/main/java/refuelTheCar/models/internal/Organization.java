package refuelTheCar.models.internal;

import java.util.Objects;

import lombok.Data;

@Data
public class Organization {
    private String id;
    private String name;
    private String inn;
    private String kpp;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Organization organization = (Organization) o;
        return Objects.equals(name, organization.name) &&
                Objects.equals(inn, organization.inn) &&
                Objects.equals(kpp, organization.kpp);
    }
}
