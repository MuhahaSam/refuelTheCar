package refuelTheCar.models.internal;

import java.util.UUID;

import lombok.Data;

@Data
public class Organization {
    private UUID id;
    private String name;
    private String inn;
    private String kpp;
}
