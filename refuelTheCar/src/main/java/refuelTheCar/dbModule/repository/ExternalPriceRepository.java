package refuelTheCar.dbModule.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;

import refuelTheCar.adapter.HttpAdapter;
import refuelTheCar.config.Config;
import refuelTheCar.dbModule.interfacees.ExternalRespositoryInterface;
import refuelTheCar.exception.GetDataByHttpException;
import refuelTheCar.models.external.price.ExternalPrice;

public class ExternalPriceRepository implements ExternalRespositoryInterface<ExternalPrice> {
    private static ExternalPriceRepository INSTANCE;

    private Config config = Config.getInstance();;
    private HttpAdapter httpAdapter = HttpAdapter.getInstance();

    Logger log = Logger.getLogger(ExternalPriceRepository.class.getName());

    public static ExternalPriceRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExternalPriceRepository();
        }
        return INSTANCE;
    }

    public List<ExternalPrice> findAll() throws GetDataByHttpException {
        String url = String.format("%s/price?apikey=%s", config.getStationUrl(), config.getApiKey());

        try {
            return httpAdapter.sendGetRequest(url, new TypeReference<List<ExternalPrice>>() {
            });
        } catch (Exception e) {
            log.logp(Level.OFF, ExternalPriceRepository.class.getName(), "findAll", e.getMessage());
            throw new GetDataByHttpException(url);
        }

    }
}
