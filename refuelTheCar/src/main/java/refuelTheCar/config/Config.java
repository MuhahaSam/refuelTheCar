package refuelTheCar.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import refuelTheCar.exception.ConfigNoneExistException;
import refuelTheCar.exception.ConfigValidateFailedException;

@Data
public class Config {
    private static Config INSTANCE;
    private static final Path configFilePath = Paths.get(".", "resources", "application.yaml");

    @URL(message = "stationUrl must be url")
    @NotNull(message = "stationUrl cannot be null")
    private String stationUrl;

    @NotNull(message = "apiKey cannot be null")
    private String apiKey;

    public static Config getInstance() throws ConfigNoneExistException, ConfigValidateFailedException {
        if (INSTANCE == null) {
            Config appConfig = readConfigFile();
            validateConfig(appConfig);
            INSTANCE = appConfig;
        }

        return INSTANCE;
    }

    private static Config readConfigFile() throws ConfigNoneExistException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(new File(configFilePath.toString()), Config.class);
        } catch (IOException e) {
            throw new ConfigNoneExistException();
        }
    }

    private static void validateConfig(Config appConfig) throws ConfigValidateFailedException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Config>> violations = validator.validate(appConfig);

        if (violations.size() > 0) {
            violations.stream().forEach(x -> System.err.println(x.getMessage()));
            throw new ConfigValidateFailedException();
        }

    }
}
