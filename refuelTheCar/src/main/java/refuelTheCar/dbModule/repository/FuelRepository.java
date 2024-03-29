package refuelTheCar.dbModule.repository;

import java.util.List;
import java.util.Map;

import refuelTheCar.dbModule.FakeDbModule;
import refuelTheCar.dbModule.interfacees.InternalRepositoryInterface;
import refuelTheCar.models.internal.Fuel;

public class FuelRepository implements InternalRepositoryInterface<Fuel> {
    private static FuelRepository INSTANCE;

    private FakeDbModule dbModule = FakeDbModule.getInstance();

    public static FuelRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FuelRepository();
        }
        return INSTANCE;
    }

    public Fuel findOneByExtIdAndOrgId(String extId, String orgId) {
        String key = String.format("%s:%s", extId, orgId);
        return dbModule.getFuelTable().get(key);
    }

    public void save(Fuel fuel) {
        String key = String.format("%s:%s", fuel.getExternalId(), fuel.getOrganizationId());
        dbModule.getFuelTable().put(key, fuel);
    }

    public void deleteNotInArray(List<String> ids) {
        for (Map.Entry<String, Fuel> fuel : dbModule.getFuelTable().entrySet()) {
            if (!ids.contains(fuel.getValue().getId())) {
                dbModule.getFuelTable().remove(fuel.getKey());
            }
        }
    }

}
