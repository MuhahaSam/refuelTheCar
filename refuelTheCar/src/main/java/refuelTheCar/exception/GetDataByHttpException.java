package refuelTheCar.exception;

public class GetDataByHttpException extends Exception {
    public GetDataByHttpException(String url) {
        super(String.format("failed get data from %s", url));
    }
}
