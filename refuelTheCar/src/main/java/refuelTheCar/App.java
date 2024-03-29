package refuelTheCar;

import refuelTheCar.dbModule.FakeDbModule;
import refuelTheCar.service.AzsFacade;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws Exception {

        FakeDbModule fakeDbModule = FakeDbModule.getInstance();
        AzsFacade azsFacade = AzsFacade.getInstance();

        fakeDbModule.openConnection();

        azsFacade.process();

        fakeDbModule.closeConnection();

    }
}
