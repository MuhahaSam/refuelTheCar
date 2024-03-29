package refuelTheCar.dbModule.repository;

import java.util.List;
import java.util.Map;

import refuelTheCar.dbModule.FakeDbModule;
import refuelTheCar.dbModule.interfacees.InternalRepositoryInterface;
import refuelTheCar.models.internal.Price;

public class PriceRepository implements InternalRepositoryInterface<Price> {
    private static PriceRepository INSTANCE;

    private FakeDbModule dbModule = FakeDbModule.getInstance();

    public static PriceRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PriceRepository();
        }
        return INSTANCE;
    }

    public Price findOneByFuelIdAndAzsId(String fuelId, String azsId) {
        String key = String.format("%s:%s", fuelId, azsId);
        return dbModule.getPriceTable().get(key);
    }

    public void save(Price price) {
        String key = String.format("%s:%s", price.getFuelId(), price.getAzsId());
        dbModule.getPriceTable().put(key, price);
    }

    public void updateByFuelIdAndAzsId(String fuelId, String azsId, Double value) {
        String key = String.format("%s:%s", fuelId, azsId);
        dbModule.getPriceTable().get(key).setValue(value);
    }

    public void deleteNotInArray(List<String> ids) {
        for (Map.Entry<String, Price> price : dbModule.getPriceTable().entrySet()) {
            if (!ids.contains(price.getValue().getId())) {
                dbModule.getPriceTable().remove(price.getKey());
            }
        }
    }

}
