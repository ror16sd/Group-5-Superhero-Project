package superhero.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import superhero.model.Location;
import superhero.model.Super;
import superhero.model.SuperOrganization;
import superhero.dao.LocationDaoDb.LocationMapper;
import superhero.dao.SuperDaoDb.SuperMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuperOrganizationDaoDb implements SuperOrganizationDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SuperDaoDb superDaoDb;

    @Override
    public SuperOrganization getSuperOrganizationById(int superOrgId) {
        try {
            final String GET_SUPERORGANIZATION_BY_ID = "Select * superOrganization WHERE id = ?";
            SuperOrganization superOrganization = jdbcTemplate.queryForObject(GET_SUPERORGANIZATION_BY_ID, new SuperOrganizationMapper(), superOrgId);
            superOrganization.setSupers(getSuperForSuperOrganization(superOrgId));
            superOrganization.setLocation(superOrganization.getLocation());
            return superOrganization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperOrganization> getOrganizationsForSuper(Super superPerson) {
        final String SELECT_SUPERS_FOR_ORGANIZATION = "SELECT o.* FROM superOrganization o JOIN "
                + "super_organization so ON so.organizationId = o.organizationId WHERE so.superId = ?";
        List<SuperOrganization> superOrganizations = jdbcTemplate.query(SELECT_SUPERS_FOR_ORGANIZATION, new SuperOrganizationMapper(),
                superPerson.getSuperId());
        associateOrganizationAndSuper(superOrganizations);
        return superOrganizations;

    }
    @Override
    public List<SuperOrganization> getOrganizationsForLocation(Location location) {
       final String SELECT_LOCATION_FOR_ORGANIZATION ="SELECT * FROM superOrganization WHERE locationId =?";
       List<SuperOrganization> superOrganizations = jdbcTemplate.query(SELECT_LOCATION_FOR_ORGANIZATION,
               new SuperOrganizationMapper(), location.getLocationId());
       associateOrganizationAndSuper(superOrganizations);
       return superOrganizations;
    }

    @Override
    public List<SuperOrganization> getAllSuperOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM superOrganization";
        List<SuperOrganization> superOrganizations = jdbcTemplate.query(SELECT_ALL_ORGANIZATIONS, new SuperOrganizationMapper());
        associateOrganizationAndSuper(superOrganizations);
        return superOrganizations;
    }

    private void associateOrganizationAndSuper(List<SuperOrganization> organization) {
        for (SuperOrganization superOrganization : organization) {
            superOrganization.setSupers(getSuperForSuperOrganization(superOrganization.getOrganizationId()));
        }
    }


    public List<Super> getSuperForSuperOrganization(int id) {
        final String SELECT_SUPERPERSON_FOR_SUPERORGANIZATION = "SELECT superPerson.superId FROM superPerson"
                + " JOIN super_Organization ON superPerson.superId = super_Organization.superId WHERE super_Organization.organizationId = ?";
        return jdbcTemplate.query(SELECT_SUPERPERSON_FOR_SUPERORGANIZATION, new SuperDaoDb.SuperMapper(), id);
    }

    @Override
    @Transactional
    public SuperOrganization addSuperOrganization(SuperOrganization superOrganization) {
        final String INSERT_SUPERORGANIZATION =
                "INSERT INTO superOrganization(organizationName, organizationDescription,locationId) " +
                        "VALUES(?,?,?)";
        jdbcTemplate.update(INSERT_SUPERORGANIZATION,
                superOrganization.getOrganizationName(),
                superOrganization.getOrganizationDescription(),
                superOrganization.getLocation().getLocationId());

        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superOrganization.setOrganizationId(newId);
        insertOrganizationSuper(superOrganization);
        return superOrganization;

    }    private void insertOrganizationSuper(SuperOrganization superOrganization) {
        final String INSERT_ORGANIZATION_SUPERPERSON =
                "INSERT INTO super_organization (superId, organizationId) VALUES(?,?)";
        for (Super superPerson : superOrganization.getSupers()) {
            jdbcTemplate.update(INSERT_ORGANIZATION_SUPERPERSON,
                    superPerson.getSuperId(),
                    superOrganization.getOrganizationId());
        }
    }

    @Override
    public void updateSuperOrganization(SuperOrganization superOrganization) {
        final String UPDATE_SUPERORGANIZATION =
                "UPDATE superOrganization SET organizationName = ?, organizationDescription = ?, locationId = ?" +
                "WHERE organizationId = ?";
        jdbcTemplate.update(UPDATE_SUPERORGANIZATION,
                superOrganization.getOrganizationName(),
                superOrganization.getOrganizationDescription(),
                superOrganization.getLocation());

        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM super_organziation WHERE organizationId = ?";
        jdbcTemplate.update(DELETE_SUPER_ORGANIZATION, superOrganization.getOrganizationId());
        insertOrganizationSuper(superOrganization);
    }

    @Override
    @Transactional
    public void deleteSuperOrganizationById(int superOrganizationId) {
        final String DELETE_HERO_ORGANIZATION_BY_ID = "DELETE FROM super_Organization WHERE organizationId = ?";
        jdbcTemplate.update(DELETE_HERO_ORGANIZATION_BY_ID, superOrganizationId);

        final String DELETE_ORGANIZATION = "DELETE FROM superOrganization WHERE organizationId = ?";
        jdbcTemplate.update(DELETE_ORGANIZATION, superOrganizationId);
    }




    public static final class SuperOrganizationMapper implements RowMapper<SuperOrganization> {
        @Override
        public SuperOrganization mapRow(ResultSet rs, int index) throws SQLException {
            SuperOrganization superOrganization = new SuperOrganization();
            superOrganization.setOrganizationId(rs.getInt("organizationId"));
            superOrganization.setOrganizationName(rs.getString("organizationName"));
            superOrganization.setOrganizationDescription(rs.getString("organizationDescription"));
            return superOrganization;
        }
    }
}