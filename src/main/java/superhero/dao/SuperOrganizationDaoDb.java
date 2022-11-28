package superhero.dao;

import org.springframework.jdbc.core.RowMapper;
import superhero.model.Super;
import superhero.model.SuperOrganization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SuperOrganizationDaoDb implements SuperOrganizationDao {
    @Override
    public SuperOrganization getSuperOrganizationById(int superOrgId) {
        return null;
    }

    @Override
    public List<SuperOrganization> getAllSuperOrganizations() {
        return null;
    }

    @Override
    public SuperOrganization addSuperOrganization(SuperOrganization superOrganization) {
        return null;
    }

    @Override
    public void updateSuperOrganization(SuperOrganization superOrganization) {

    }

    @Override
    public void deleteSuperOrganizationById(int superOrganizationId) {

    }

    @Override
    public List<Super> getSuperOrganizationForSuper(Super superHero) {
        return null;
    }

    public static final class SuperOrganizationMapper implements RowMapper<SuperOrganization> {
        @Override
        public SuperOrganization mapRow (ResultSet rs, int index) throws SQLException {
            SuperOrganization superOrganization = new SuperOrganization();
            superOrganization.setOrganizationId(rs.getInt("organizationId"));
            superOrganization.setOrganizationName(rs.getString("organizationName"));
            superOrganization.setOrganizationDescription(rs.getString("organizationDescription"));
            return superOrganization;
        }
    }
}
