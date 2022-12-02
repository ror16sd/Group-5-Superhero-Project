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
import java.util.ArrayList;
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
            final String GET_SUPERORGANIZATION_BY_ID = "Select * FROM superOrganization WHERE organizationId = ?";
            SuperOrganization superOrganization = jdbcTemplate.queryForObject(GET_SUPERORGANIZATION_BY_ID, new SuperOrganizationMapper(), superOrgId);
            superOrganization.setSupers(getSupersForOrganization(superOrgId));
            superOrganization.setLocation(getLocationForSuperOrg(superOrgId));
            return superOrganization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperOrganization> getOrganizationsForSupers(Super supers) {
        final String SELECT_SUPERS_FOR_ORGANIZATION = "SELECT o.* FROM superOrganization o JOIN "
                + "super_organization so ON so.organizationId = o.organizationId WHERE so.superId = ?";
        List<SuperOrganization> superOrganizations = jdbcTemplate.query(SELECT_SUPERS_FOR_ORGANIZATION, new SuperOrganizationMapper(),
                supers.getSuperId());
        associateSuperAndLocation(superOrganizations);
        return superOrganizations;

    }


    private Location getLocationForSuperOrg(int superOrgId) {
        final String SELECT_LOCATION_FOR_SIGHTING_BY_ID = "SELECT l.* FROM location l " +
                "JOIN superOrganization s ON s.locationId = l.locationId WHERE s.organizationId = ?";
        return jdbcTemplate.queryForObject(SELECT_LOCATION_FOR_SIGHTING_BY_ID, new LocationDaoDb.LocationMapper(), superOrgId);

    }

    @Override
    public List<SuperOrganization> getOrganizationsForLocation(Location location) {
        final String SELECT_ORGANIZATIONS_FOR_LOCATION = "SELECT * FROM superOrganization WHERE locationId = ?";
        List<SuperOrganization> superOrganizations =
                jdbcTemplate.query(SELECT_ORGANIZATIONS_FOR_LOCATION, new SuperOrganizationMapper(), location.getLocationId());
        associateSuperAndLocation(superOrganizations);
        return superOrganizations;
    }



    @Override
    public List<SuperOrganization> getAllSuperOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM superOrganization";
        List<SuperOrganization> superOrganizations = jdbcTemplate.query(SELECT_ALL_ORGANIZATIONS, new SuperOrganizationMapper());
        associateSuperAndLocation(superOrganizations);
        return superOrganizations;
    }

    private void associateSuperAndLocation(List<SuperOrganization> organization) {
        for (SuperOrganization superOrganization : organization) {
            superOrganization.setSupers(getSupersForOrganization(superOrganization.getOrganizationId()));
            superOrganization.setLocation(getLocationForSuperOrg(superOrganization.getOrganizationId()));
        }
    }


    @Override
    public List<Super> getSupersForOrganization(int superId) {
        final String SELECT_SUPERS_FOR_ORGANIZATION = "SELECT so.superId FROM superPerson s " +
                "JOIN super_organization so ON so.superId = s.superId WHERE so.superId = ?";
        List<Integer> superIds = jdbcTemplate.queryForList(SELECT_SUPERS_FOR_ORGANIZATION, Integer.class, superId);
        List<Super> supers = new ArrayList<>();
        for(Integer superId1 : superIds) {
            supers.add(superDaoDb.getSuperById(superId1));
        }
        return supers;
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

    }

    private void insertOrganizationSuper(SuperOrganization superOrganization) {
        final String INSERT_ORGANIZATION_SUPERPERSON =
                "INSERT INTO super_organization (superId, organizationId) VALUES(?,?)";
        for (Super superPerson : superOrganization.getSuperMembers()) {
            jdbcTemplate.update(INSERT_ORGANIZATION_SUPERPERSON,
                    superPerson.getSuperId(),
                    superOrganization.getOrganizationId());
        }
    }

    @Override
    @Transactional
    public void updateSuperOrganization(SuperOrganization superOrganization) {
        final String UPDATE_SUPERORGANIZATION =
                "UPDATE superOrganization SET organizationName = ?, organizationDescription = ?, locationId = ? " +
                        "WHERE organizationId = ?";
        jdbcTemplate.update(UPDATE_SUPERORGANIZATION,
                superOrganization.getOrganizationName(),
                superOrganization.getOrganizationDescription(),
                superOrganization.getLocation().getLocationId(),
                superOrganization.getOrganizationId());

        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM super_organization WHERE organizationId = ?";
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