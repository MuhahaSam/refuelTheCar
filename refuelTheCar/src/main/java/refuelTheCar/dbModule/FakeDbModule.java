package refuelTheCar.dbModule;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    private List<AZS> azsTable;
    private List<TRK> trkTable;
    private List<Fuel> fuelTable;
    private List<Organization> organizationTable;
    private List<Price> priceTable;
    private List<TrkFuel> trkFueltable;

    private Path azsTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule", "tableFiles",
            "azsTable.json");

    private Path trkTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule", "tableFiles",
            "trkTable.json");

    private Path fuelTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule", "tableFiles",
            "fuelTable.json");

    private Path organizationTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule",
            "tableFiles",
            "organizationTable.json");

    private Path priceTableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule", "tableFiles",
            "priceTable.json");

    private Path trkFueltableFilePath = Paths.get(".", "src", "main", "java", "refuelTheCar", "dbModule", "tableFiles",
            "trkFueltable.json");

    FakeDbModule() {
        getDatas();
    }

    public static FakeDbModule getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeDbModule();
        }
        return INSTANCE;
    }

    private void getDatas() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            azsTable = objectMapper.readValue(azsTableFilePath.toFile(), new TypeReference<List<AZS>>() {
            });

            trkTable = objectMapper.readValue(trkTableFilePath.toFile(), new TypeReference<List<TRK>>() {
            });

            fuelTable = objectMapper.readValue(fuelTableFilePath.toFile(), new TypeReference<List<Fuel>>() {
            });

            organizationTable = objectMapper.readValue(organizationTableFilePath.toFile(),
                    new TypeReference<List<Organization>>() {
                    });
            priceTable = objectMapper.readValue(priceTableFilePath.toFile(), new TypeReference<List<Price>>() {
            });

            trkFueltable = objectMapper.readValue(trkFueltableFilePath.toFile(), new TypeReference<List<TrkFuel>>() {
            });

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }
}
