package superhero.dao;

import superhero.model.Location;

import java.util.List;

public interface LocationDao {

    Location getLocationById(int locationId);

    List<Location> getAllLocations();

    Location addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocationById(int locationId);
}
