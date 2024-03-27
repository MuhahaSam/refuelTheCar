package refuelTheCar;

import refuelTheCar.config.Config;
import refuelTheCar.dbModule.repository.GasStationRepository;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {

        Config appConfig = Config.getInstance();

        GasStationRepository gasStationRepository = GasStationRepository.getInstance(appConfig);
        System.out.println(gasStationRepository.getAll().get(0).getAddress());
        System.out.println("Hello World!");
    }
}
