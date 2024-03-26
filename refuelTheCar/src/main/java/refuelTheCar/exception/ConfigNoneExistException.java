package refuelTheCar.exception;

public class ConfigNoneExistException extends Exception {

    public ConfigNoneExistException() {
        super("config file do not exist");
    }

}
