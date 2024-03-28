package refuelTheCar;

import refuelTheCar.dbModule.repository.GasStationRepository;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {

        GasStationRepository gasStationRepository = GasStationRepository.getInstance();
        System.out.println(gasStationRepository.getAll().get(0).getAddress());
        System.out.println("Hello World!");
    }
}
