package superhero.dao;

import superhero.model.Power;
import superhero.model.Super;

import java.util.List;

public interface SuperDao {
    Super getSuperById(int superId);

    List<Super> getAllSupers();

    Super addSuper(Super superHero);

    void updateSuper(Super superHero);

    void deleteSuperById(int superId);

    List<Power> getPowersForSuper(Super superHero);

}
