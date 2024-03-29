package refuelTheCar.mappers;

import java.util.UUID;

import refuelTheCar.models.external.station.ExternalOrganization;
import refuelTheCar.models.external.station.ExternalStation;
import refuelTheCar.models.internal.AZS;
import refuelTheCar.models.internal.Organization;

public class AzsMapper {
    public static AZS toAZS(ExternalStation station, UUID id, UUID organizationId) {
        AZS azs = new AZS();

        azs.setId(id.toString());
        azs.setExternalId(station.getId());
        azs.setBrand(station.getBrand());
        azs.setBrandId(station.getBrandId());
        azs.setEnable(station.isEnable());
        azs.setName(station.getName());
        azs.setLat(station.getLocation().getLat());
        azs.setLon(station.getLocation().getLon());
        azs.setPostPay(station.isPostPay());
        azs.setTakeOffMode(station.getTakeOffMode());
        azs.setOrganizationId(organizationId.toString());

        return azs;
    }

    public static Organization toOrganization(ExternalOrganization extOrganization, UUID id) {
        Organization organization = new Organization();

        organization.setId(id.toString());
        organization.setInn(extOrganization.getInn());
        organization.setKpp(extOrganization.getKpp());
        organization.setName(extOrganization.getName());

        return organization;
    }

}
