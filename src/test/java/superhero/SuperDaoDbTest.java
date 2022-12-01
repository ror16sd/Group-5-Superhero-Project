package superhero;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import superhero.dao.*;
import superhero.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SuperDaoDbTest {

    @Autowired
    LocationDao locationDao;
    @Autowired
    PowerDao powerDao;
    @Autowired
    SightingDao sightingDao;
    @Autowired
    SuperDao superDao;
    @Autowired
    SuperOrganizationDao superOrganizationDao;

    public SuperDaoDbTest(){

    }
    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations){
            locationDao.deleteLocationById(location.getLocationId());
        }
        List<Power> powers = powerDao.getAllPowers();
        for (Power power : powers){
            powerDao.deletePowerById(power.getPowerId());
        }
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings){
            sightingDao.deleteSightingById(sighting.getSightingId());
        }
        List<Super> supers = superDao.getAllSupers();
        for(Super super1 : supers){
            superDao.deleteSuperById(super1.getSuperId());
        }
        List<SuperOrganization> organizations = superOrganizationDao.getAllSuperOrganizations();
        for(SuperOrganization organization : organizations){
            superOrganizationDao.deleteSuperOrganizationById(organization.getOrganizationId());
        }
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetSuperByIdAndAdd() {
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDao.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDao.addSuper(superPerson);

        Super retrievedSuper = superDao.getSuperById(superPerson.getSuperId());
        assertEquals(superPerson,retrievedSuper);
    }

    @Test
    void getAllSupers() {
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDao.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDao.addSuper(superPerson);

        Power power1 = new Power();
        power1.setPowerDescription("Test Power1");
        power1 = powerDao.addPower(power1);

        Super superPerson1 = new Super();
        superPerson1.setSuperName("Test Name1");
        superPerson1.setSuperDescription("Test Description1");
        superPerson1.setIsSuper(true);
        superPerson1.setPower(power1);
        superPerson1 = superDao.addSuper(superPerson1);

        List<Super> supers = superDao.getAllSupers();
        assertEquals(2,supers.size());
        assertTrue(supers.contains(superPerson));
        assertTrue(supers.contains(superPerson1));
    }

    @Test
    void updateSuper() {
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDao.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDao.addSuper(superPerson);

        Super retrievedSuper = superDao.getSuperById(superPerson.getSuperId());
        assertEquals(superPerson,retrievedSuper);

        superPerson.setSuperName("New Test Name");
        superDao.updateSuper(superPerson);

        assertNotEquals(superPerson,retrievedSuper);

        retrievedSuper = superDao.getSuperById(superPerson.getSuperId());

        assertEquals(superPerson,retrievedSuper);
    }

    @Test
    void deleteSuperById() {
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDao.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDao.addSuper(superPerson);

        Super retrievedSuper = superDao.getSuperById(superPerson.getSuperId());
        assertEquals(superPerson,retrievedSuper);

        superDao.deleteSuperById(superPerson.getSuperId());

        retrievedSuper = superDao.getSuperById(superPerson.getSuperId());
        assertNull(retrievedSuper);
    }

}