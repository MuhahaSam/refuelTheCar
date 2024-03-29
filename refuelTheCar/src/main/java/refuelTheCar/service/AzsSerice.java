package refuelTheCar.service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import refuelTheCar.dbModule.repository.AzsRepository;
import refuelTheCar.dbModule.repository.OrganizationRepository;
import refuelTheCar.mappers.AzsMapper;
import refuelTheCar.models.external.station.ExternalOrganization;
import refuelTheCar.models.external.station.ExternalStation;
import refuelTheCar.models.internal.AZS;
import refuelTheCar.models.internal.Organization;

public class AzsSerice {
    private static AzsSerice INSTANCE;

    public static AzsSerice getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AzsSerice();
        }
        return INSTANCE;
    }

    Logger log = Logger.getLogger(AzsSerice.class.getName());

    private AzsRepository azsRepository = AzsRepository.getInstance();
    private OrganizationRepository organizationRepository = OrganizationRepository.getInstance();

    public void create(ExternalStation station, UUID azsId, UUID orgId) {
        ExternalOrganization externalOrganization = station.getOrganization();
        createOrganization(externalOrganization, azsId, orgId);
        createAzs(station, azsId, orgId);
    }

    private void createOrganization(ExternalOrganization extOrganizaiton, UUID azsId, UUID orgId) {
        Organization exisOrganization = organizationRepository
                .findOneByNameAndInnAndKpp(extOrganizaiton.getName(), extOrganizaiton.getInn(),
                        extOrganizaiton.getKpp());
        Organization newOrganization = AzsMapper.toOrganization(extOrganizaiton, orgId);

        if (!newOrganization.equals(exisOrganization)) {
            organizationRepository.save(newOrganization);
        }

        log.logp(Level.INFO, AzsFacade.class.getName(), "create",
                String.format("processed organization data with name: %s", newOrganization.getName()));

    }

    private void createAzs(ExternalStation station, UUID azsId, UUID orgId) {
        AZS existedAzs = azsRepository.findOneBygetExternalId(station.getId());
        AZS newAzs = AzsMapper.toAZS(station, azsId, orgId);

        if (!newAzs.equals(existedAzs)) {
            azsRepository.save(newAzs);
        }
        log.logp(Level.INFO, AzsFacade.class.getName(), "create",
                String.format("processed AZS data with name: %s", newAzs.getName()));

    }

    public UUID getExistOrNewOrgId(ExternalStation station) {
        Organization organization = organizationRepository.findOneByNameAndInnAndKpp(
                station.getOrganization().getName(),
                station.getOrganization().getInn(), station.getOrganization().getKpp());
        if (organization == null) {
            return UUID.randomUUID();
        }

        return UUID.fromString(organization.getId());
    }

    public UUID getExistOrNewAzsId(ExternalStation station) {
        AZS azs = azsRepository.findOneBygetExternalId(station.getId());

        if (azs == null) {
            return UUID.randomUUID();
        }

        return UUID.fromString(azs.getId());
    }

}
