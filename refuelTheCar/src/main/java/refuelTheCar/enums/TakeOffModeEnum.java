package refuelTheCar.enums;

public enum TakeOffModeEnum {
    BOTH("both"),
    BEFORE("before"),
    AFTER("after");

    private String mode;

    TakeOffModeEnum(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

}
