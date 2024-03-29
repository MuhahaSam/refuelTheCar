package refuelTheCar.models.internal;

import java.util.Objects;

import lombok.Data;

@Data
public class AZS {
    private String id;
    private String externalId;
    private String name;
    private String brand;
    private String brandId;
    private String organizationId;
    private Double lon;
    private Double lat;
    private String takeOffMode;
    private boolean postPay;
    private boolean enable;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AZS azs = (AZS) o;
        return postPay == azs.postPay &&
                enable == azs.enable &&
                Objects.equals(externalId, azs.externalId) &&
                Objects.equals(name, azs.name) &&
                Objects.equals(brand, azs.brand) &&
                Objects.equals(brandId, azs.brandId) &&
                Objects.equals(organizationId, azs.organizationId) &&
                Objects.equals(lon, azs.lon) &&
                Objects.equals(lat, azs.lat) &&
                Objects.equals(takeOffMode, azs.takeOffMode);
    }

}
