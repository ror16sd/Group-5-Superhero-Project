package superheroDao;

import superheroEntities.Power;
import superheroEntities.Super;

import java.util.List;

public interface PowerDao {

    Power getPowerById(int powerId);

    List<Power> getAllPowers();

    Power addPower(Power power);

    void updatePower(Power power);

    void deletePowerById(int powerId);

    List<Power> getPowerForSuper(Super superHero);
}
