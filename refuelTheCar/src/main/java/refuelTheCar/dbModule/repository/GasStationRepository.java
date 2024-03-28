package refuelTheCar.dbModule.repository;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import refuelTheCar.adapter.HttpAdapter;
import refuelTheCar.config.Config;
import refuelTheCar.exception.GetDataByHttpException;
import refuelTheCar.models.external.station.Station;

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

    public List<Station> getAll() throws GetDataByHttpException {
        String url = String.format("%s/station?apikey=%s", config.getStationUrl(), config.getApiKey());

        try {
            return httpAdapter.sendGetRequest(url, new TypeReference<List<Station>>() {
            });
        } catch (Exception e) {
            throw new GetDataByHttpException(url);
        }
    }

}
