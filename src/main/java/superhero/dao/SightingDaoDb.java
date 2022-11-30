package superhero.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import superhero.model.Location;
import superhero.model.Sighting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class SightingDaoDb implements SightingDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightingLocation WHERE id = ?";
            Sighting sighting = jdbcTemplate.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingId);
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Location getLocationForSighting(int locationId) {
        final String SELECT_LOCATION_FOR_SIGHTING_BY_ID = "SELECT l * FROM location l " +
                "JOIN sightingLocation s ON s.locationId = l.locationId WHERE s.sightingId = ?";
        return jdbcTemplate.queryForObject(SELECT_LOCATION_FOR_SIGHTING_BY_ID, new LocationDaoDb.LocationMapper(), locationId);

    }

    @Override
    public List<Sighting> getAllSightings() {
        final String GET_ALL_SIGHTINGS = "SELECT * FROM sightinglocation";
        return jdbcTemplate.query(GET_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        //TODO: should the model be changed?
        final String INSERT_SIGHTING = "INSERT INTO sightinglocation (sightingDate, locationId, superId) "
                + "VALUES(?,?,?)";
        jdbcTemplate.update(INSERT_SIGHTING,
                sighting.getSightingDate(),
                sighting.getSightingLocation(),
                sighting.getSightingSuper());

        int newSightingId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newSightingId);
        return sighting;
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sightinglocation SET sightingDate = ?, locationId = ?, superId = ?)"
                + "WHERE sightingId = ?";
        jdbcTemplate.update(UPDATE_SIGHTING,
                sighting.getSightingDate(),
                sighting.getSightingLocation().getLocationId(),
                sighting.getSightingSuper().getSuperId(),
                sighting.getSightingId());
    }

    @Override
    @Transactional
    public void deleteSightingById(int sightingId) {
        final String DELETE_SIGHTING = "DELETE from sightinglocation WHERE sightingId = ?";
        jdbcTemplate.update(DELETE_SIGHTING, sightingId);

    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        final String SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM sightinglocation WHERE locationId = ?";
        List<Sighting> sighting = jdbcTemplate.query(SELECT_SIGHTINGS_FOR_LOCATION, new SightingMapper(), location.getLocationId());
        associateLocationsForSighting(sighting);
        return sighting;
    }

    void associateLocationsForSighting(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setSightingLocation(getLocationForSighting(sighting.getSightingId()));
        }
    }

    public static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setSightingDate(rs.getTimestamp("sightingDate"));
            return sighting;
        }
    }
}

