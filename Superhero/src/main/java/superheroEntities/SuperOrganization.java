package superheroEntities;

//Create Table superOrganization(
//        organizationId int primary key auto_increment,
//        organizationName varchar(20) not null,
//        locationId int,
//        Constraint foreign key (locationId) references location(locationId)
//        );

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SuperOrganization {

    private int organizationId;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 20, message = "Name must be fewer than 20 characters")
    private String organizationName;

    private Location location;



}
