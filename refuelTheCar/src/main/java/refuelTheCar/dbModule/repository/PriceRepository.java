package refuelTheCar.dbModule.repository;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import refuelTheCar.adapter.HttpAdapter;
import refuelTheCar.config.Config;
import refuelTheCar.exception.GetDataByHttpException;
import refuelTheCar.models.external.price.PriceInfo;

public class PriceRepository {
    private static PriceRepository INSTANCE;

    private Config config;
    private HttpAdapter httpAdapter = HttpAdapter.getInstance();

    PriceRepository(Config config) {
        this.config = config;
    }

    public static PriceRepository getInstance(Config config) {
        if (INSTANCE == null) {
            INSTANCE = new PriceRepository(config);
        }
        return INSTANCE;
    }

    public List<PriceInfo> getAll() throws GetDataByHttpException {
        String url = String.format("%s/price?apikey=%s", config.getStationUrl(), config.getApiKey());

        try {
            return httpAdapter.sendGetRequest(url, new TypeReference<List<PriceInfo>>() {
            });
        } catch (Exception e) {
            throw new GetDataByHttpException(url);
        }

    }
}
