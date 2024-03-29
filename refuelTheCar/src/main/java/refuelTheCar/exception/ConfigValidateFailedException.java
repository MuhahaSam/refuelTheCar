package refuelTheCar.exception;

public class ConfigValidateFailedException extends Exception {
    public ConfigValidateFailedException() {
        super("config validation failed");
    }

}
