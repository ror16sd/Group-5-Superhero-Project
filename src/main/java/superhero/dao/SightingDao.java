package superhero.dao;

import superhero.model.Location;
import superhero.model.Sighting;

import java.util.List;

public interface SightingDao {
    Sighting getSightingById(int sightingId);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);

    void updateSighting(Sighting sighting);

    void deleteSightingById(int sightingId);

    List<Sighting> getSightingsForLocation(Location location);
}
