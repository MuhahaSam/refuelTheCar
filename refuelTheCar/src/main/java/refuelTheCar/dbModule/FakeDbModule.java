package refuelTheCar.dbModule;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import refuelTheCar.models.internal.AZS;
import refuelTheCar.models.internal.Fuel;
import refuelTheCar.models.internal.Organization;
import refuelTheCar.models.internal.Price;
import refuelTheCar.models.internal.TRK;
import refuelTheCar.models.internal.TrkFuel;

@Data
public class FakeDbModule {
        private static FakeDbModule INSTANCE;

        public static FakeDbModule getInstance() {
                if (INSTANCE == null) {
                        INSTANCE = new FakeDbModule();
                }
                return INSTANCE;
        }

        Logger log = Logger.getLogger(FakeDbModule.class.getName());

        private Map<String, AZS> azsTable;
        private Map<String, TRK> trkTable;
        private Map<String, Fuel> fuelTable;
        private Map<String, Organization> organizationTable;
        private Map<String, Price> priceTable;
        private Map<String, TrkFuel> trkFueltable;

        private Path azsTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule", "tableFiles",
                        "azsTable.json");

        private Path trkTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule", "tableFiles",
                        "trkTable.json");

        private Path fuelTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule", "tableFiles",
                        "fuelTable.json");

        private Path organizationTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule",
                        "tableFiles",
                        "organizationTable.json");

        private Path priceTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule",
                        "tableFiles",
                        "priceTable.json");

        private Path trkFueltableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule",
                        "tableFiles",
                        "trkFueltable.json");

        public void openConnection() {
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                        azsTable = objectMapper.readValue(azsTableFilePath.toFile(),
                                        new TypeReference<ConcurrentHashMap<String, AZS>>() {
                                        });

                        trkTable = objectMapper.readValue(trkTableFilePath.toFile(),
                                        new TypeReference<ConcurrentHashMap<String, TRK>>() {
                                        });

                        fuelTable = objectMapper.readValue(fuelTableFilePath.toFile(),
                                        new TypeReference<ConcurrentHashMap<String, Fuel>>() {
                                        });

                        organizationTable = objectMapper.readValue(organizationTableFilePath.toFile(),
                                        new TypeReference<Map<String, Organization>>() {
                                        });
                        priceTable = objectMapper.readValue(priceTableFilePath.toFile(),
                                        new TypeReference<ConcurrentHashMap<String, Price>>() {
                                        });

                        trkFueltable = objectMapper.readValue(trkFueltableFilePath.toFile(),
                                        new TypeReference<ConcurrentHashMap<String, TrkFuel>>() {
                                        });

                } catch (Exception e) {
                        log.logp(Level.OFF, FakeDbModule.class.getName(), "openConnection",
                                        e.getMessage());
                        System.exit(0);
                }

        }

        public void closeConnection() {
                ObjectMapper objectMapper = new ObjectMapper();

                try {

                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(azsTableFilePath.toFile(), azsTable);

                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(trkTableFilePath.toFile(), trkTable);

                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(fuelTableFilePath.toFile(), fuelTable);

                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(organizationTableFilePath.toFile(),
                                        organizationTable);
                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(priceTableFilePath.toFile(),
                                        priceTable);

                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(trkFueltableFilePath.toFile(),
                                        trkFueltable);

                } catch (Exception e) {
                        log.logp(Level.OFF, FakeDbModule.class.getName(), "closeConnection",
                                        e.getMessage());
                        System.exit(0);
                }

        }
}
