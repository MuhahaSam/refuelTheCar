package refuelTheCar.dbModule.repository;

import java.util.List;
import java.util.Map;

import refuelTheCar.dbModule.FakeDbModule;
import refuelTheCar.dbModule.interfacees.InternalRepositoryInterface;
import refuelTheCar.models.internal.TrkFuel;

public class TrkFuelRepository implements InternalRepositoryInterface<TrkFuel> {
    private static TrkFuelRepository INSTANCE;

    private FakeDbModule dbModule = FakeDbModule.getInstance();

    public static TrkFuelRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrkFuelRepository();
        }
        return INSTANCE;
    }

    public TrkFuel findONeByTrkIdAndFuelId(String trkId, String fuelId) {
        String key = String.format("%s:%s", trkId, fuelId);
        return dbModule.getTrkFueltable().get(key);
    }

    public void save(TrkFuel trkFuel) {
        String key = String.format("%s:%s", trkFuel.getTrkId(), trkFuel.getFuelId());
        dbModule.getTrkFueltable().put(key, trkFuel);
    }

    public void deleteNotInArray(List<String> ids) {
        for (Map.Entry<String, TrkFuel> trkFuel : dbModule.getTrkFueltable().entrySet()) {
            if (!ids.contains(trkFuel.getValue().getId())) {
                dbModule.getTrkFueltable().remove(trkFuel.getKey());
            }
        }
    }
}
