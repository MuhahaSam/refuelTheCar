package refuelTheCar.dbModule.repository;

import java.util.List;
import java.util.Map;

import refuelTheCar.dbModule.FakeDbModule;
import refuelTheCar.dbModule.interfacees.InternalRepositoryInterface;
import refuelTheCar.models.internal.TRK;

public class TrkRepository implements InternalRepositoryInterface<TRK> {
    private static TrkRepository INSTANCE;

    private FakeDbModule dbModule = FakeDbModule.getInstance();

    public static TrkRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrkRepository();
        }
        return INSTANCE;
    }

    public TRK findOneByAzsIdAndNumber(String azsId, Integer number) {
        String key = String.format("%s:%d", azsId, number);
        return dbModule.getTrkTable().get(key);
    }

    public void save(TRK trk) {
        String key = String.format("%s:%d", trk.getAzsId(), trk.getNumber());
        dbModule.getTrkTable().put(key, trk);
    }

    public void deleteNotInArray(List<String> ids) {
        for (Map.Entry<String, TRK> trk : dbModule.getTrkTable().entrySet()) {
            if (!ids.contains(trk.getValue().getId())) {
                dbModule.getTrkTable().remove(trk.getKey());
            }
        }
    }

}
