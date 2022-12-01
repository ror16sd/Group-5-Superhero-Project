package superhero.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import superhero.dao.PowerDaoDb.PowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import superhero.model.Power;
import superhero.model.Sighting;
import superhero.model.Super;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuperDaoDb implements SuperDao
{

    @Autowired
    JdbcTemplate jdbc;
    @Override
    public Super getSuperById(int superId) {
        try{
            final String SELECT_SUPER_BY_ID = "SElECT * FROM superPerson WHERE superId = ?";
            Super superPerson = jdbc.queryForObject(SELECT_SUPER_BY_ID, new SuperMapper(), superId);
            superPerson.setPower(getPowerForSuper(superId));
            return superPerson;
        } catch (DataAccessException ex){
            return null;
        }
    }

    private Power getPowerForSuper(int superId) {
        final String SELECT_POWER_FOR_SUPER = "SELECT p.* FROM power p "
                + "JOIN superPerson s ON s.powerId = p.powerId WHERE s.superId = ?";
        return jdbc.queryForObject(SELECT_POWER_FOR_SUPER, new PowerMapper(), superId);
    }

    @Override
    public List<Super> getAllSupers() {
       final String SELECT_ALL_SUPERS = "SELECT * FROM superPerson";
       List<Super> supers = jdbc.query(SELECT_ALL_SUPERS, new SuperMapper());
        associateSuperAndPowers(supers);
        return supers;
    }

    private void associateSuperAndPowers(List<Super> supers) {
        for (Super currentSuper : supers){
            currentSuper.setPower(getPowerForSuper(currentSuper.getSuperId()));
        }
    }

    @Override
    @Transactional
    public Super addSuper(Super superHero) {
        final String INSERT_SUPER = "INSERT INTO superPerson(superName, powerId, superDescription, isSuper) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_SUPER,
                superHero.getSuperName(),
                superHero.getPower().getPowerId(),
                superHero.getSuperDescription(),
                superHero.getIsSuper());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superHero.setSuperId(newId);
//        insertSuper(superHero);
        return superHero;
    }

/*
    private void insertSuper(Super superHero) {

    }
*/

    @Override
    public void updateSuper(Super superHero) {
        final String UPDATE_SUPER = "UPDATE superPerson SET superName = ?, powerId = ?, superDescription = ?, "
                + "isSuper = ? WHERE superId = ?";
        jdbc.update(UPDATE_SUPER,
                superHero.getSuperName(),
                superHero.getPower().getPowerId(),
                superHero.getSuperDescription(),
                superHero.getIsSuper(),
                superHero.getSuperId());
    }


    @Override
    public void deleteSuperById(int superId) {
        final String DELETE_SUPER_ORG = "DELETE FROM super_organization WHERE superId = ?";
        jdbc.update(DELETE_SUPER_ORG, superId);

        final String DELETE_SUPER = "DELETE FROM superPerson WHERE superId = ?";
        jdbc.update(DELETE_SUPER, superId);
    }

    @Override
    public List<Power> getPowersForSuper(Super superHero) {
        return null;
    }

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
