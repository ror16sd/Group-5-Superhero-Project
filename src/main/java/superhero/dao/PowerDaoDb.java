package superhero.dao;

//<<<<<<< Updated upstream
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import superhero.model.Location;
//=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
//>>>>>>> Stashed changes
import superhero.model.Power;
import superhero.model.Super;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PowerDaoDb implements PowerDao {

    @Autowired
    JdbcTemplate jdbc;


    @Override
    public Power getPowerById(int powerId) {
        try {
            final String SELECT_POWER_BY_ID = "Select * from power where powerId = ?";
            return jdbc.queryForObject(SELECT_POWER_BY_ID, new PowerMapper(), powerId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        final String SELECT_ALL_POWERS = "SELECT * FROM power";
        return jdbc.query(SELECT_ALL_POWERS, new PowerMapper());
    }

    @Override
    public Power addPower(Power power) {
        final String INSERT_POWER = "INSERT INTO power(powerDescription) "
                + "VALUES(?)";
        jdbc.update(INSERT_POWER, power.getPowerDescription());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setPowerId(newId);
        return power;
    }

    @Override
    public void updatePower(Power power) {
        final String UPDATE_POWER = "UPDATE power SET powerDescription = ? "
                + "WHERE powerId = ?";
        jdbc.update(UPDATE_POWER, power.getPowerDescription(), power.getPowerId());
    }

    @Override
    public void deletePowerById(int powerId) {
        final String DELETE_HERO_POWER = "UPDATE superPerson SET powerId = null WHERE powerId = ?";
        jdbc.update(DELETE_HERO_POWER, powerId);

        final String DELETE_SUPERPOWER = "DELETE FROM power WHERE powerId = ?";
        jdbc.update(DELETE_SUPERPOWER, powerId);
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