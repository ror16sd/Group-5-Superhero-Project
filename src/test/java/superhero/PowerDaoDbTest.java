package superhero;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import superhero.model.*;
import superhero.dao.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PowerDaoDbTest {

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
    @Autowired
    PowerDao PowerDao;

    public PowerDaoDbTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Super> SuperPerson = superDao.getAllSupers();
        for (Super Super : SuperPerson) {
            superDao.deleteSuperById(Super.getSuperId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationById(location.getLocationId());
        }

        List<SuperOrganization> superOrganization = superOrganizationDao.getAllSuperOrganizations();
        for (SuperOrganization SuperOrganization : superOrganization) {
            superOrganizationDao.deleteSuperOrganizationById(SuperOrganization.getOrganizationId());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSightingId());
        }

        List<Power> powers = PowerDao.getAllPowers();
        for (Power power : powers) {
            PowerDao.deletePowerById(power.getPowerId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetPowerByPowerId() {
    }

    @Test
    public void testGetAndAddPower() {

        Power power = new Power();
        //power.setPowerId("Test Id");
        power.setPowerDescription("Test description");
        power = PowerDao.addPower(power);

        Power fromDao = PowerDao.getPowerById(power.getPowerId());
        assertEquals(power, fromDao);
    }

    @Test
    public void testGetAllPowers() {

        Power power = new Power();
        //power.setPowerId("Test Id");
        power.setPowerDescription("Test description");
        power = PowerDao.addPower(power);

        Power power2 = new Power();
        //power2.setPowerId("Test Id2");
        power2.setPowerDescription("Test description2");
        power2 = PowerDao.addPower(power2);

        List<Power> powers = PowerDao.getAllPowers();
        assertEquals(2, powers.size());
        assertTrue(powers.contains(power));
        assertTrue(powers.contains(power2));
    }

    @Test
    public void testAddPower() {
    }

    @Test
    public void testUpdatePower() {
        Power power = new Power();
        //power.setPowerId("Test Id");
        power.setPowerDescription("Test description");
        power = PowerDao.addPower(power);

        Power fromDao = PowerDao.getPowerById(power.getPowerId());
        assertEquals(power, fromDao);

        //power.setPowerId("Test Id2");
        power.setPowerDescription("Test description2");

        PowerDao.updatePower(power);
        assertNotEquals(power, fromDao);

        fromDao = PowerDao.getPowerById(power.getPowerId());
        assertEquals(power, fromDao);
    }

    @Test
    public void testDeletePowerByPowerId() {
        Power power = new Power();
        //power.setPowerId("Test PowerId");
        power.setPowerDescription("Test description");
        power = PowerDao.addPower(power);

        Power fromDao = PowerDao.getPowerById(power.getPowerId());
        assertEquals(power, fromDao);

        PowerDao.deletePowerById(power.getPowerId());

        fromDao = PowerDao.getPowerById(power.getPowerId());
        assertNull(fromDao);
    }
}