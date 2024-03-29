package refuelTheCar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import refuelTheCar.dbModule.repository.TrkFuelRepository;
import refuelTheCar.dbModule.repository.TrkRepository;
import refuelTheCar.mappers.TrkMapper;
import refuelTheCar.models.external.station.ExternalColumn;
import refuelTheCar.models.internal.TRK;
import refuelTheCar.models.internal.TrkFuel;

public class TrkService {
    private static TrkService INSTANCE;

    public static TrkService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrkService();
        }
        return INSTANCE;
    }

    private TrkRepository trkRepository = TrkRepository.getInstance();
    private TrkFuelRepository trkFuelRepository = TrkFuelRepository.getInstance();

    Logger log = Logger.getLogger(TrkService.class.getName());

    public void create(Map<String, ExternalColumn> columns, Map<String, String> fuelExtIdToIdMap, String azsId,
            HashMap<Class<?>, List<String>> processedTrkIdListMap) {
        for (Map.Entry<String, ExternalColumn> column : columns.entrySet()) {

            Integer number = Integer.valueOf(column.getKey());
            UUID trkId = getExistedOrNewtrkId(azsId, number);

            TRK newTrk = TrkMapper.toTrk(trkId.toString(), azsId, number);
            TRK exisTrk = trkRepository.findOneByAzsIdAndNumber(azsId, number);

            if (!newTrk.equals(exisTrk)) {
                trkRepository.save(newTrk);
            }
            processedTrkIdListMap.get(TRK.class).add(trkId.toString());

            log.logp(Level.INFO, FuelPriceService.class.getName(), "create",
                    String.format("processed TRK data with number: %d and with azs id: %s", newTrk.getNumber(), azsId));

            for (String extFuelId : column.getValue().getFuels()) {
                UUID trkFuelId = getExistedOrNewTrkFuelId(trkId.toString(), fuelExtIdToIdMap.get(extFuelId));

                TrkFuel newTrkFuyFuel = TrkMapper.toTrkFuel(trkFuelId.toString(), fuelExtIdToIdMap.get(extFuelId),
                        trkId.toString());

                TrkFuel existedTrkFuel = trkFuelRepository.findONeByTrkIdAndFuelId(trkId.toString(),
                        fuelExtIdToIdMap.get(extFuelId));

                if (!newTrkFuyFuel.equals(existedTrkFuel)) {
                    trkFuelRepository
                            .save(newTrkFuyFuel);
                }
                processedTrkIdListMap.get(TrkFuel.class).add(trkFuelId.toString());

                log.logp(Level.INFO, FuelPriceService.class.getName(), "create",
                        String.format("processed TrkFuel data with id: %s", newTrkFuyFuel.getId(),
                                azsId));

            }
        }
    }

    private UUID getExistedOrNewtrkId(String azsId, Integer number) {
        TRK existedTrk = trkRepository.findOneByAzsIdAndNumber(azsId, number);

        if (existedTrk == null) {
            return UUID.randomUUID();
        }

        return UUID.fromString(existedTrk.getId());
    }

    private UUID getExistedOrNewTrkFuelId(String trkId, String fuelId) {
        TrkFuel trkFuel = trkFuelRepository.findONeByTrkIdAndFuelId(trkId, fuelId);
        if (trkFuel == null) {
            return UUID.randomUUID();
        }

        return UUID.fromString(trkFuel.getId());
    }

    public void deleteUnprocessedData(HashMap<Class<?>, List<String>> processedTrkIdListMap) {
        List<String> processedTrkIds = processedTrkIdListMap.get(TRK.class);
        List<String> processedTrkFuelIds = processedTrkIdListMap.get(TrkFuel.class);

        trkRepository.deleteNotInArray(processedTrkIds);
        trkFuelRepository.deleteNotInArray(processedTrkFuelIds);

        log.logp(Level.INFO, FuelPriceService.class.getName(), "deleteUnprocessedData", "deleted unprocessed TRK data");
    }
}
