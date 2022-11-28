package superhero.model;

//Create Table superOrganization(
//        organizationId int primary key auto_increment,
//        organizationName varchar(20) not null,
//        locationId int,
//        Constraint foreign key (locationId) references location(locationId)
//        );

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SuperOrganization {

    private int organizationId;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 20, message = "Name must be fewer than 20 characters")
    private String organizationName;

    private Location location;

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.organizationId;
        hash = 61 * hash + Objects.hashCode(this.organizationName);
        hash = 61 * hash + Objects.hashCode(this.location);
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
        final SuperOrganization other = (SuperOrganization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }

        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }
}
