package refuelTheCar.dbModule.repository;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import refuelTheCar.adapter.HttpAdapter;
import refuelTheCar.config.Config;
import refuelTheCar.exception.GetDataByHttpException;
import refuelTheCar.models.external.price.PriceInfo;

public class PriceRepository {
    private static PriceRepository INSTANCE;

    private Config config = Config.getInstance();;
    private HttpAdapter httpAdapter = HttpAdapter.getInstance();

    public static PriceRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PriceRepository();
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
