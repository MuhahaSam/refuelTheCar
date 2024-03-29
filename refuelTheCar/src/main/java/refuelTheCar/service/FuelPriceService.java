package refuelTheCar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import refuelTheCar.dbModule.repository.AzsRepository;
import refuelTheCar.dbModule.repository.FuelRepository;
import refuelTheCar.dbModule.repository.PriceRepository;
import refuelTheCar.mappers.PriceMapper;
import refuelTheCar.mappers.TrkMapper;
import refuelTheCar.models.external.price.ExternalPrice;
import refuelTheCar.models.external.station.ExternalFuel;
import refuelTheCar.models.external.station.ExternalStation;
import refuelTheCar.models.internal.AZS;
import refuelTheCar.models.internal.Fuel;
import refuelTheCar.models.internal.Price;

public class FuelPriceService {
    private static FuelPriceService INSTANCE;

    Logger log = Logger.getLogger(FuelPriceService.class.getName());

    public static FuelPriceService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FuelPriceService();
        }
        return INSTANCE;
    }

    private PriceRepository priceRepository = PriceRepository.getInstance();
    private FuelRepository fuelRepository = FuelRepository.getInstance();
    private AzsRepository azsRepository = AzsRepository.getInstance();

    public void create(ExternalStation externalStation, Map<String, String> fuelExtIdToIdMap, String orgId,
            String azsId, HashMap<Class<?>, List<String>> processedTrkIdListMap) {
        for (ExternalFuel extFuel : externalStation.getFuels()) {
            UUID fuelId = getExistedOrNewfuelId(extFuel.getId(), orgId);

            fuelExtIdToIdMap.put(extFuel.getId(), fuelId.toString());

            Fuel newFuel = TrkMapper.toFuel(extFuel, orgId, fuelId.toString());
            Fuel existedFuel = fuelRepository.findOneByExtIdAndOrgId(extFuel.getId(), orgId);

            log.logp(Level.INFO, FuelPriceService.class.getName(), "create",
                    String.format("processed fuel data with name: %s", extFuel.getName()));

            if (!newFuel.equals(existedFuel)) {
                fuelRepository.save(newFuel);
            }

            processedTrkIdListMap.get(Fuel.class).add(fuelId.toString());

            createPrice(extFuel.getPrice(), fuelId.toString(), azsId, processedTrkIdListMap);

            log.logp(Level.INFO, FuelPriceService.class.getName(), "create",
                    String.format("processed price data for fuel with name: %s", extFuel.getName()));
        }
    }

    private void createPrice(Double vlaue, String fuelId, String azsId,
            HashMap<Class<?>, List<String>> processedTrkIdListMap) {
        UUID id = getExistedOrNewPriceId(fuelId, azsId);

        Price existedPrice = priceRepository.findOneByFuelIdAndAzsId(fuelId, azsId);
        Price newPrice = PriceMapper.toPrice(id.toString(), vlaue, fuelId, azsId);

        if (!newPrice.equals(existedPrice)) {
            priceRepository.save(newPrice);
        }

        processedTrkIdListMap.get(Price.class).add(id.toString());
    }

    private UUID getExistedOrNewPriceId(String fuelId, String azsId) {
        Price price = priceRepository.findOneByFuelIdAndAzsId(fuelId, azsId);

        if (price == null) {
            return UUID.randomUUID();
        }

        return UUID.fromString(price.getId());
    }

    private UUID getExistedOrNewfuelId(String extId, String orgId) {
        Fuel fuel = fuelRepository.findOneByExtIdAndOrgId(extId, orgId);

        if (fuel == null) {
            return UUID.randomUUID();
        }

        return UUID.fromString(fuel.getId());
    }

    public void deleteUnprocessedData(HashMap<Class<?>, List<String>> processedTrkIdListMap) {
        List<String> processedFuelIds = processedTrkIdListMap.get(Fuel.class);
        List<String> processedPriceIds = processedTrkIdListMap.get(Price.class);

        fuelRepository.deleteNotInArray(processedFuelIds);
        priceRepository.deleteNotInArray(processedPriceIds);

    }

    public void updatePrice(ExternalPrice extPrice) {

        AZS azs = azsRepository.findOneBygetExternalId(extPrice.getStationId());
        if (azs == null) {
            log.logp(Level.OFF, FuelPriceService.class.getName(), "create",
                    String.format("no such AZS data with id: %s for price data update", extPrice.getStationId()));
            return;
        }

        Fuel fuel = fuelRepository.findOneByExtIdAndOrgId(extPrice.getProductId(), azs.getOrganizationId());

        if (fuel == null) {
            log.logp(Level.OFF, FuelPriceService.class.getName(), "create",
                    String.format("no such fluel data with organicationId: %s and externalId", azs.getOrganizationId(),
                            extPrice.getProductId()));
            return;
        }

        priceRepository.updateByFuelIdAndAzsId(fuel.getId(), azs.getId(), extPrice.getPrice());

        log.logp(Level.INFO, FuelPriceService.class.getName(), "create",
                String.format("processed price data for fuel with name: %s and AZS with name: %s", fuel.getName(),
                        azs.getName()));

    }

}
