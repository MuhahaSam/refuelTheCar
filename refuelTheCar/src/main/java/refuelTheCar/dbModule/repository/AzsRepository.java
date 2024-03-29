package refuelTheCar.dbModule.repository;

import refuelTheCar.dbModule.FakeDbModule;
import refuelTheCar.dbModule.interfacees.InternalRepositoryInterface;
import refuelTheCar.models.internal.AZS;

public class AzsRepository implements InternalRepositoryInterface<AZS> {

    private static AzsRepository INSTANCE;

    private FakeDbModule dbModule = FakeDbModule.getInstance();

    public static AzsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AzsRepository();
        }
        return INSTANCE;
    }

    public AZS findOneBygetExternalId(String externalId) {
        return dbModule.getAzsTable().get(externalId);
    }

    public void save(AZS azs) {
        dbModule.getAzsTable().put(azs.getExternalId(), azs);
    }

}
