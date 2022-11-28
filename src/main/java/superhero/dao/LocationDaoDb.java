package superhero.dao;

import superhero.model.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LocationDaoDb implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int locationId) {
        try{
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE locationId = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), locationId);
        } catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM location";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location(locationName, locationDescription, locationAddress, locationState, locationCity, locationZip, locationLatLat, locationLong) "
                + "VAlUES(?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLocationState(),
                location.getLocationCity(),
                location.getLocationZip(),
                location.getLocationLat(),
                location.getLocationLong());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET locationName = ?, locationDescription = ?, locationAddress = ?, locationState = ?, locationCity = ?, locationZip = ?, locationLatLat = ?, locationLong = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLocationState(),
                location.getLocationCity(),
                location.getLocationZip(),
                location.getLocationLat(),
                location.getLocationLong(),
                location.getLocationId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int locationId) {
        //quick delete written for testing, if issue with location deleting too much information START HERE***
        final String DELETE_LOCATION = "DELETE FROM location WHERE locationId = ?";
        jdbc.update(DELETE_LOCATION, locationId);
    }

    public static final class LocationMapper implements RowMapper<Location>{
        @Override
        public Location mapRow (ResultSet rs, int index) throws SQLException{
            Location location = new Location();
            location.setLocationId(rs.getInt("locationId"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setLocationAddress(rs.getString("locationAddress"));
            location.setLocationState(rs.getString("locationState"));
            location.setLocationCity(rs.getString("locationCity"));
            location.setLocationZip(rs.getInt("locationZip"));
            location.setLocationLat(rs.getDouble("locationLatLat"));
            location.setLocationLong(rs.getDouble("locationLong"));
            return location;
        }
    }
}
