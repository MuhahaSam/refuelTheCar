package refuelTheCar.dbModule.repository;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import refuelTheCar.adapter.HttpAdapter;
import refuelTheCar.config.Config;
import refuelTheCar.exception.GetDataByHttpException;
import refuelTheCar.models.external.station.Station;

public class GasStationRepository {
    private static GasStationRepository INSTANCE;

    private Config config = Config.getInstance();
    private HttpAdapter httpAdapter = HttpAdapter.getInstance();

    public static GasStationRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GasStationRepository();
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
