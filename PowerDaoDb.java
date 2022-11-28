package superhero.dao;

import org.springframework.jdbc.core.RowMapper;
import superhero.model.Location;
import superhero.model.Power;
import superhero.model.Super;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PowerDaoDb implements PowerDao {
    @Override
    public Power getPowerById(int powerId) {
        return null;
    }

    @Override
    public List<Power> getAllPowers() {
        return null;
    }

    @Override
    public Power addPower(Power power) {
        return null;
    }

    @Override
    public void updatePower(Power power) {

    }

    @Override
    public void deletePowerById(int powerId) {

    }

    @Override
    public List<Power> getPowerForSuper(Super superHero) {
        return null;
    }

    public static final class PowerMapper implements RowMapper<Power> {
        @Override
        public Power mapRow (ResultSet rs, int index) throws SQLException {
            Power power = new Power();
            power.setPowerId(rs.getInt("powerId"));
            power.setPowerDescription(rs.getString("powerDescription"));

            return power;
        }
    }
}
