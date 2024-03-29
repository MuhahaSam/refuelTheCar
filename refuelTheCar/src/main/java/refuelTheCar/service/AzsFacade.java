package refuelTheCar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import refuelTheCar.dbModule.repository.ExternalPriceRepository;
import refuelTheCar.dbModule.repository.ExternalStationRepository;
import refuelTheCar.exception.GetDataByHttpException;
import refuelTheCar.models.external.price.ExternalPrice;
import refuelTheCar.models.external.station.ExternalOrganization;
import refuelTheCar.models.external.station.ExternalStation;
import refuelTheCar.models.internal.Fuel;
import refuelTheCar.models.internal.Price;
import refuelTheCar.models.internal.TRK;
import refuelTheCar.models.internal.TrkFuel;

public class AzsFacade {
    private static AzsFacade INSTANCE;

    public static AzsFacade getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AzsFacade();
        }
        return INSTANCE;
    }

    private AzsSerice stationSerice = AzsSerice.getInstance();
    private TrkService trkService = TrkService.getInstance();
    private FuelPriceService fuelService = FuelPriceService.getInstance();
    private ExternalStationRepository extStationRepository = ExternalStationRepository.getInstance();
    private ExternalPriceRepository externalPriceRepository = ExternalPriceRepository.getInstance();

    Logger log = Logger.getLogger(AzsFacade.class.getName());

    public void process() throws GetDataByHttpException {

        List<String> processedTrkIds = new ArrayList<>();
        List<String> processedTrkFuelIds = new ArrayList<>();
        List<String> processedFuelIds = new ArrayList<>();
        List<String> processedPriceIds = new ArrayList<>();

        HashMap<Class<?>, List<String>> processedTrkIdListMap = new HashMap<>();

        processedTrkIdListMap.put(TRK.class, processedTrkIds);
        processedTrkIdListMap.put(TrkFuel.class, processedTrkFuelIds);
        processedTrkIdListMap.put(Fuel.class, processedFuelIds);
        processedTrkIdListMap.put(Price.class, processedPriceIds);

        log.logp(Level.INFO, AzsFacade.class.getName(), "process", "starting AZS data process");

        for (ExternalStation station : extStationRepository.findAll()) {
            ExternalOrganization externalOrganization = station.getOrganization();

            if (externalOrganization.getInn() == "") {
                continue;
            }

            UUID azsId = stationSerice.getExistOrNewAzsId(station);
            UUID orgId = stationSerice.getExistOrNewOrgId(station);

            stationSerice.create(station, azsId, orgId);

            HashMap<String, String> fuelExtIdToIdMap = new HashMap<>();
            fuelService.create(station, fuelExtIdToIdMap, orgId.toString(), azsId.toString(),
                    processedTrkIdListMap);

            if (station.getColumns() == null || station.getColumns().size() == 0) {
                log.logp(Level.INFO, AzsFacade.class.getName(), "process", "AZH do not have columns");
            } else {
                trkService.create(station.getColumns(), fuelExtIdToIdMap, azsId.toString(), processedTrkIdListMap);
            }

        }

        log.logp(Level.INFO, AzsFacade.class.getName(), "process", "starting delete none processed data");

        fuelService.deleteUnprocessedData(processedTrkIdListMap);
        trkService.deleteUnprocessedData(processedTrkIdListMap);

        log.logp(Level.INFO, AzsFacade.class.getName(), "process", "starting update price data");

        for (ExternalPrice extPrice : externalPriceRepository.findAll()) {
            fuelService.updatePrice(extPrice);
        }

        log.logp(Level.INFO, AzsFacade.class.getName(), "process", "process ended");

    }

}
