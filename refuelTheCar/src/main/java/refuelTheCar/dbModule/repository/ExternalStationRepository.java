package refuelTheCar.dbModule.repository;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import refuelTheCar.adapter.HttpAdapter;
import refuelTheCar.config.Config;
import refuelTheCar.dbModule.interfacees.ExternalRespositoryInterface;
import refuelTheCar.exception.GetDataByHttpException;
import refuelTheCar.models.external.station.ExternalStation;

public class ExternalStationRepository implements ExternalRespositoryInterface<ExternalStation> {
    private static ExternalStationRepository INSTANCE;

    private Config config = Config.getInstance();
    private HttpAdapter httpAdapter = HttpAdapter.getInstance();

    public static ExternalStationRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExternalStationRepository();
        }
        return INSTANCE;
    }

    public List<ExternalStation> findAll() throws GetDataByHttpException {
        String url = String.format("%s/station?apikey=%s", config.getStationUrl(), config.getApiKey());

        try {
            return httpAdapter.sendGetRequest(url, new TypeReference<List<ExternalStation>>() {
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new GetDataByHttpException(url);
        }
    }

}
