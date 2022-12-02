package superhero.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

public class Location {

    private int locationId;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 30, message = "Name must be fewer than 30 characters")
    private String locationName;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 50, message = "Description must be fewer than 50 characters")
    private String locationDescription;

    @NotBlank(message = "Address must not be blank")
    @Size(max = 30, message = "Address must be fewer than 30 characters")
    private String locationAddress;

    @NotBlank(message = "State must not be blank")
    @Size(max = 20, message = "State must be fewer than 20 characters")
    @Pattern(regexp="(A[KLRZ]|C[AOT]|D[CE]|FL|GA"
            + "|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEH"
            + "JMVY]|O[HKR]|PA|RI|S[CD]|T[NX]|UT|V[AT]|W[AIV"
            + "Y])", message = "Must input a valid state abbreviation")
    private String locationState;

    @NotBlank(message = "City must not be blank")
    @Size(max = 30, message = "City name must be fewer than 30 characters")
    private String locationCity;

    @Digits(integer = 5, fraction = 0, message = "Zip code must be 5 digits")
    private int locationZip;
    @Digits(integer = 12, fraction = 8, message = "Long can only be represented in 12 digits maximum, "
            + "8 of those being decimal points")
    private double locationLong;
    @Digits(integer = 12, fraction = 8, message = "Lat can only be represented in 12 digits maximum, "
            + "8 of those being decimal points")
    private double locationLat;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public int getLocationZip() {
        return locationZip;
    }

    public void setLocationZip(int locationZip) {
        this.locationZip = locationZip;
    }

    public double getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(double locationLong) {
        this.locationLong = locationLong;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.locationId;
        hash = 61 * hash + Objects.hashCode(this.locationName);
        hash = 61 * hash + Objects.hashCode(this.locationDescription);
        hash = 61 * hash + Objects.hashCode(this.locationAddress);
        hash = 61 * hash + Objects.hashCode(this.locationState);
        hash = 61 * hash + Objects.hashCode(this.locationCity);
        hash = 61 * hash + Objects.hashCode(this.locationZip);
        hash = 61 * hash + Objects.hashCode(this.locationLong);
        hash = 61 * hash + Objects.hashCode(this.locationLat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.locationAddress, other.locationAddress)) {
            return false;
        }
        if (!Objects.equals(this.locationCity, other.locationCity)) {
            return false;
        }
        if (!Objects.equals(this.locationState, other.locationState)) {
            return false;
        }
        if (!Objects.equals(this.locationZip, other.locationZip)) {
            return false;
        }
        if (!Objects.equals(this.locationLong, other.locationLong)) {
            return false;
        }
        if(!Objects.equals(this.locationLat, other.locationLat)) {
            return false;
        }
        return true;
    }
}
