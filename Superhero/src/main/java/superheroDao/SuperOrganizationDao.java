package superheroDao;

import superheroEntities.Super;
import superheroEntities.SuperOrganization;

import java.util.List;

public interface SuperOrganizationDao {

    SuperOrganization getSuperOrganizationById(int superOrgId);

    List<SuperOrganization> getAllSuperOrganizations();

    SuperOrganization addSuperOrganization(SuperOrganization superOrganization);

    void updateSuperOrganization(SuperOrganization superOrganization);

    void deleteSuperOrganizationById(int superOrganizationId);

    List<Super> getSuperOrganizationForSuper(Super superHero);


}
