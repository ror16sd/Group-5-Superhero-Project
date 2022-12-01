package superhero.dao;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import superhero.model.Location;
import superhero.model.Sighting;
import superhero.model.Super;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public class SightingDaoDb implements SightingDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    SuperDao superDao;

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightingLocation WHERE sightingId = ?";
            Sighting sighting = jdbcTemplate.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingId);
            sighting.setSightingLocation(getLocationForSighting(sightingId));
            sighting.setSightingSuper(getSuperForSighting(sightingId));
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Super getSuperForSighting(int sightId) {
        //WHERE s.sightingId needed to be changed to WHERE m.sightingId since
        //m is the alias for sightingLocation
        //This change fixed testAddGetSighting and testDeleteSightingById
        final String SELECT_SUPER_FOR_SIGHTING_BY_ID = "SELECT s.superId FROM superPerson s " +
                "JOIN sightingLocation m ON m.superId = s.superId WHERE m.sightingId = ?";
        final Integer idOfSuper = jdbcTemplate.queryForObject(SELECT_SUPER_FOR_SIGHTING_BY_ID, Integer.class, sightId);
        return superDao.getSuperById(idOfSuper);

    }
    
//    private List<Sighting> setSuperForSightings(List<Sighting> sightingsList) {
//        for (Sighting sighting: sightingsList) {
//            sighting.setSightingSuper(getSuperForSighting(sighting.getSightingId()));
//        }
//        
//        return sightingsList;
//    }
    
    //Changed to int sightingId instead of int locationId which was
    //causing the error with this method
    private Location getLocationForSighting(int sightingId) {
        final String SELECT_LOCATION_FOR_SIGHTING_BY_ID = "SELECT l.* FROM location l " +
                "JOIN sightingLocation s ON s.locationId = l.locationId WHERE s.sightingId = ?";
        return jdbcTemplate.queryForObject(SELECT_LOCATION_FOR_SIGHTING_BY_ID, new LocationDaoDb.LocationMapper(), sightingId);

    }
    
    private List<Sighting> setLocationForSightings(List<Sighting> sightingsList) {
        for (Sighting sighting: sightingsList) {
            sighting.setSightingLocation(getLocationForSighting(sighting.getSightingId()));
        }
        return sightingsList;
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String GET_ALL_SIGHTINGS = "SELECT * FROM sightinglocation";
        List<Sighting> sightings = jdbcTemplate.query(GET_ALL_SIGHTINGS, new SightingMapper());
        //Needed to assocaite super and location for sightings
        //This addition fixed testGetAllSightings()
        associateLocationsForSighting(sightings);
        return sightings;
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        //TODO: should the model be changed?
        //date needed to be sightingDate since this is how it appears in the 
        //database
        final String INSERT_SIGHTING = "INSERT INTO sightinglocation (sightingDate , locationId, superId) "
                + "VALUES(?,?,?)";
        jdbcTemplate.update(INSERT_SIGHTING,
                Date.valueOf(sighting.getSightingDate()),
                sighting.getSightingLocation().getLocationId(),
                sighting.getSightingSuper().getSuperId());

        int newSightingId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newSightingId);
        return sighting;
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        //Used to be superId = ?) causing the error in SQL syntax
        //Also, date needed to be locationDate
        //Lastly, the date needed to be parsed to a MySQL Date
        final String UPDATE_SIGHTING = "UPDATE sightinglocation SET sightingDate = ?, locationId = ?, superId = ? "
                + "WHERE sightingId = ?";
        jdbcTemplate.update(UPDATE_SIGHTING,
                Date.valueOf(sighting.getSightingDate()),
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
        List<Sighting> sightings = jdbcTemplate.query(SELECT_SIGHTINGS_FOR_LOCATION, new SightingMapper(), location.getLocationId());
        associateLocationsForSighting(sightings);
        return sightings;
    }
    
    void associateLocationsForSighting(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setSightingLocation(getLocationForSighting(sighting.getSightingId()));
            sighting.setSightingSuper(getSuperForSighting(sighting.getSightingId()));
        }
    }

    public static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            //changed date to sightingDate since this is how date column
            //appears in the database
            sighting.setSightingDate(rs.getDate("sightingDate").toLocalDate());
            return sighting;
        }
    }
}

