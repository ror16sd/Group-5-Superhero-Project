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

public class LocationDaoDb implements LocationDao {
    @Override
    public Location getLocationById(int locationId) {
        return null;
    }

    @Override
    public List<Location> getAllLocations() {
        return null;
    }

    @Override
    public Location addLocation(Location location) {
        return null;
    }

    @Override
    public void updateLocation(Location location) {

    }

    @Override
    public void deleteLocationById(int locationId) {

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
