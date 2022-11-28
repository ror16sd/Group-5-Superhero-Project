package superhero.dao;

import org.springframework.jdbc.core.RowMapper;
import superhero.model.Location;
import superhero.model.Sighting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SightingDaoDb {


    public static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow (ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setSightingDate(rs.getTimestamp("sightingDate"));
            return sighting;
        }
    }
}
