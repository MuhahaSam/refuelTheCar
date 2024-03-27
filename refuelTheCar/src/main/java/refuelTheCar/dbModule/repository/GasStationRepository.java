package refuelTheCar.dbModule.repository;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import refuelTheCar.adapter.HttpAdapter;
import refuelTheCar.config.Config;
import refuelTheCar.domain.gasStation.GasStation;
import refuelTheCar.exception.GetDataByHttpException;

public class GasStationRepository {
    private static GasStationRepository INSTANCE;

    private Config config;
    private HttpAdapter httpAdapter = HttpAdapter.getInstance();

    GasStationRepository(Config config) {
        this.config = config;
    }

    public static GasStationRepository getInstance(Config config) {
        if (INSTANCE == null) {
            INSTANCE = new GasStationRepository(config);
        }
        return INSTANCE;
    }

    public List<GasStation> getAll() throws GetDataByHttpException {
        String url = String.format("%s/station?apikey=%s", config.getStationUrl(), config.getApiKey());

        try {
            return httpAdapter.sendGetRequest(url, new TypeReference<List<GasStation>>() {
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new GetDataByHttpException(url);
        }
    }

}
