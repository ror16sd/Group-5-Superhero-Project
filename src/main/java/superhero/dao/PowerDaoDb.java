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


    JdbcTemplate jdbc;

    @Autowired
    public PowerDaoDb(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

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
        final String SELECT_ALL_POWERS = "Select * from powers";
        return jdbc.query(SELECT_ALL_POWERS, new PowerMapper());
    }

    @Override
    public Power addPower(Power power) {
        final String INSERT_POWER = "INSERT INTO Power(Name,Description) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_POWER, power.getPowerDescription());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setPowerId(newId);
        return power;
    }

    @Override
    public void updatePower(Power power) {
        final String UPDATE_POWER = "UPDATE Power set name = ?"
                + "WHERE powerId = ?";
        jdbc.update(UPDATE_POWER, power.getPowerDescription(), power.getPowerId());
    }

    @Override
    public void deletePowerById(int powerId) {
        final String DELETE_HERO_POWER = "DELETE FROM HeroSuperpower WHERE SuperpowerId = ?";
        jdbc.update(DELETE_HERO_POWER, powerId);

        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE SuperpowerId = ?";
        jdbc.update(DELETE_SUPERPOWER, powerId);
    }

    @Override
    public List<Power> getPowerForSuper(Super superHero) {
        return null;
    }

//<<<<<<< Updated upstream



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

