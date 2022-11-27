package superheroDao;

import superheroEntities.Super;
import superheroEntities.SuperOrganization;

import java.util.List;

public class SuperOrganizationDaoDb implements SuperOrganizationDao{
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
}
