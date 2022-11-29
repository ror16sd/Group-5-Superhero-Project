package superhero.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import superhero.model.Super;
import superhero.model.SuperOrganization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuperOrganizationDaoDb implements SuperOrganizationDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public SuperOrganization getSuperOrganizationById(int superOrgId) {
        try {
            final String GET_SUPERORGANIZATION_BY_ID = "Select * superOrganization WHERE id = ?;";
            return jdbcTemplate.queryForObject(GET_SUPERORGANIZATION_BY_ID, new SuperOrganizationMapper(), superOrgId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperOrganization> getAllSuperOrganizations() {
        //TODO: Implement this method
        final String GET_ALL_SUPERORGANIZATIONS = "SELECT * FROM superOrganization;";
        List<SuperOrganization> organizations = jdbcTemplate.query(GET_ALL_SUPERORGANIZATIONS, new SuperOrganizationMapper());
        associateOrganizationAndSuper(organizations);
        return organizations;
    }

    private void associateOrganizationAndSuper(List<SuperOrganization> organization) {
        for (SuperOrganization superOrganization : organization){
            //TODO: implement below, why not working??
//            superOrganization.setSupers(getSuperForSuperOrganization(superOrganization.getOrganizationId()));
        }
    }


    public SuperOrganization getSuperForSuperOrganization (int superId){
        final String SELECT_SUPERPERSON_FOR_SUPERORGANIZATION = "SELECT s.* FROM superPerson s "
                +"JOIN superOrganization so ON so.superId = s.superId WHERE so.organizationId = ?";
        return jdbcTemplate.queryForObject(SELECT_SUPERPERSON_FOR_SUPERORGANIZATION, new SuperOrganizationMapper(),superId);
    }
        @Override
        @Transactional
        public SuperOrganization addSuperOrganization (SuperOrganization superOrganization){
            final String INSERT_SUPERORGANIZATION =
                    "INSERT INTO superOrganization(organizationId, organizationName, organizationDescription,locationId)" +
                            "VALUES(?,?,?,?);";
            jdbcTemplate.update(INSERT_SUPERORGANIZATION,
                    superOrganization.getOrganizationId(),
                    superOrganization.getOrganizationName(),
                    superOrganization.getOrganizationDescription(),
                    superOrganization.getLocation());

            int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            superOrganization.setOrganizationId(newId);
            return superOrganization;

        }

        //TODO: implement this method so can complete implementation of updateSuperOrganization
//    private void insertSuper_Organization(SuperOrganization superOrganization){
//        final String INSERT_SUPER_ORGANIZATION = "INSERT INTO " +
//                "super_organization(organizationId, superPersonId) VALUES(?,?);";
//        for(Super supers: superOrganization.getSupers();{
//            jdbcTemplate.update(INSERT_SUPER_ORGANIZATION,
//                    getSuperOrganizationForSuper().get(int)
//
//        }
//    }

        @Override
        public void updateSuperOrganization (SuperOrganization superOrganization){
            final String UPDATE_SUPERORGANIZATION = "UPDATE superOrganization SET organizationName = ?, orgnaizationDescription = ?, locationId = ?" +
                    "WHERE organizationId = ?";
            jdbcTemplate.update(UPDATE_SUPERORGANIZATION,
                    superOrganization.getOrganizationId(),
                    superOrganization.getOrganizationName(),
                    superOrganization.getOrganizationDescription(),
                    superOrganization.getLocation());

            final String DELETE_SUPERORGANIZATION = "DELETE FROM super_organziation WHERE organizationId = ?;";
            jdbcTemplate.update(DELETE_SUPERORGANIZATION, superOrganization.getOrganizationId());
            //TODO: fix below
            //insertSuper_Organization(superOrganization);
        }

        @Override
        @Transactional
        public void deleteSuperOrganizationById ( int superOrganizationId){
            final String DELETE_HERO_ORGANIZATION_BY_ID = "DELETE FROM super_Organization WHERE organizationId = ?;";
            jdbcTemplate.update(DELETE_HERO_ORGANIZATION_BY_ID, superOrganizationId);

            final String DELETE_ORGANIZATION = "DELETE FROM superOrganization organizationId = ?;";
            jdbcTemplate.update(DELETE_ORGANIZATION);


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

