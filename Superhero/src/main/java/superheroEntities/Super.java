package superheroEntities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Super {

    private int superId;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 30, message = "Name must be fewer than 30 characters")
    private String superName;

    private Power power;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 30, message = "Description must be fewer than 30 characters")
    String superDescription;

    @NotBlank(message = "Is a hero must not be blank")
    @Size( message = "Is a hero must be True(1) or False(0).")
    boolean isSuper;

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public String getSuperDescription() {
        return superDescription;
    }

    public void setSuperDescription(String superDescription) {
        this.superDescription = superDescription;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void isSuper(boolean hero) {
        isSuper = hero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.superId;
        hash = 61 * hash + Objects.hashCode(this.superName);
        hash = 61 * hash + Objects.hashCode(this.power);
        hash = 61 * hash + Objects.hashCode(this.superDescription);
        hash = 61 * hash + Objects.hashCode(this.isSuper);
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
        final Super other = (Super) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (!Objects.equals(this.superName, other.superName)) {
            return false;
        }
        if (!Objects.equals(this.superDescription, other.superDescription)) {
            return false;
        }
        if (!Objects.equals(this.power, other.power)) {
            return false;
        }

        return true;
    }
}
