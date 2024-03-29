package refuelTheCar.dbModule.repository;

import refuelTheCar.dbModule.FakeDbModule;
import refuelTheCar.dbModule.interfacees.InternalRepositoryInterface;
import refuelTheCar.models.internal.Organization;

public class OrganizationRepository implements InternalRepositoryInterface<Organization> {
    private static OrganizationRepository INSTANCE;

    private FakeDbModule dbModule = FakeDbModule.getInstance();

    public static OrganizationRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrganizationRepository();
        }
        return INSTANCE;
    }

    public Organization findOneByNameAndInnAndKpp(String name, String inn, String kpp) {
        String key = String.format("%s:%s:%s", name, inn, kpp);
        return dbModule.getOrganizationTable().get(key);
    }

    public void save(Organization organization) {
        String key = String.format("%s:%s:%s", organization.getName(), organization.getInn(), organization.getKpp());
        dbModule.getOrganizationTable().put(key, organization);
    }

}
