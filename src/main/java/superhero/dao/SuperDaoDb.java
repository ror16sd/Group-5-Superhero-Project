package superhero.dao;

import org.springframework.jdbc.core.RowMapper;
import superhero.model.Sighting;
import superhero.model.Super;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperDaoDb
{

    public static final class SuperMapper implements RowMapper<Super> {
        @Override
        public Super mapRow (ResultSet rs, int index) throws SQLException {
            Super mapSuper = new Super();
            mapSuper.setSuperId(rs.getInt("superId"));
            mapSuper.setSuperName(rs.getString("superName"));
            mapSuper.setSuperDescription(rs.getString("superDescription"));
            mapSuper.setIsSuper(rs.getBoolean("isSuper"));
            return mapSuper;
        }
    }
}
