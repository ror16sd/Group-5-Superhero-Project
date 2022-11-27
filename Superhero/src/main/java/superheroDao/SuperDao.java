package superheroDao;

import superheroEntities.Power;
import superheroEntities.Super;
import superheroEntities.SuperOrganization;

import java.util.List;

public interface SuperDao {
    Super getSuperById(int superId);

    List<Super> getAllSupers();

    Super addSuper(Super superHero);

    void updateSuper(Super superHero);

    void deleteSuperById(int superId);

    List<Power> getPowersForSuper(Super superHero);

}
