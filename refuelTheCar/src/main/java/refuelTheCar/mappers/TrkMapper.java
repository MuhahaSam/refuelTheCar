package refuelTheCar.mappers;

import refuelTheCar.models.external.station.ExternalFuel;
import refuelTheCar.models.internal.Fuel;
import refuelTheCar.models.internal.TRK;
import refuelTheCar.models.internal.TrkFuel;

public class TrkMapper {
    public static Fuel toFuel(ExternalFuel externalFuel, String orgId, String id) {
        Fuel fuel = new Fuel();

        fuel.setBrand(externalFuel.getBrand());
        fuel.setExternalId(externalFuel.getId());
        fuel.setId(id);
        fuel.setName(externalFuel.getName());
        fuel.setOrganizationId(orgId);
        fuel.setType(externalFuel.getType());
        fuel.setTypeId(externalFuel.getTypeId());

        return fuel;
    }

    public static TRK toTrk(String id, String azsId, Integer number) {
        TRK trk = new TRK();

        trk.setId(id);
        trk.setAzsId(azsId);
        trk.setNumber(number);

        return trk;
    }

    public static TrkFuel toTrkFuel(String id, String fuelId, String trkId) {
        TrkFuel trkFuel = new TrkFuel();

        trkFuel.setId(id);
        trkFuel.setFuelId(fuelId);
        trkFuel.setTrkId(trkId);

        return trkFuel;
    }
}
