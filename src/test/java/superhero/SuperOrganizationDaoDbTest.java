package superhero;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import superhero.dao.*;
import superhero.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;







@SpringBootTest
class SuperOrganizationDaoDbTest {

    @Autowired
    SuperOrganizationDaoDb superOrganizationDaoDb;

    @Autowired
    SuperDaoDb superDaoDb;

    @Autowired
    LocationDaoDb locationDaoDb;

    @Autowired
    PowerDaoDb powerDaoDb;

    @Autowired
    SightingDaoDb sightingDaoDb;

    public SuperOrganizationDaoDbTest(){

    }


    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<SuperOrganization> organizations = superOrganizationDaoDb.getAllSuperOrganizations();
        for (SuperOrganization organization : organizations) {
            superOrganizationDaoDb.deleteSuperOrganizationById(organization.getOrganizationId());
        }
        List<Location> locations = locationDaoDb.getAllLocations();
        for (Location location : locations) {
            locationDaoDb.deleteLocationById(location.getLocationId());
        }
        List<Power> powers = powerDaoDb.getAllPowers();
        for (Power power : powers) {
            powerDaoDb.deletePowerById(power.getPowerId());
        }
        List<Sighting> sightings = sightingDaoDb.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDaoDb.deleteSightingById(sighting.getSightingId());
        }
        List<Super> supers = superDaoDb.getAllSupers();
        for (Super super1 : supers) {
            superDaoDb.deleteSuperById(super1.getSuperId());
        }

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetSuperOrganization() {
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDaoDb.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDaoDb.addSuper(superPerson);

        Super retrievedSuper = superDaoDb.getSuperById(superPerson.getSuperId());
        List<Super> supers = new ArrayList<>();
        supers.add(superPerson);

        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDescription("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLocationCity("Test Location City");
        location.setLocationState("Test Location State");
        location.setLocationZip(75234);
        location.setLocationLat(location.getLocationLat());
        location.setLocationLong(location.getLocationLong());
        location = locationDaoDb.addLocation(location);

        List<Location> locations = new ArrayList<>();
        locations.add(location);

        SuperOrganization superOrganization1 = new SuperOrganization();
        superOrganization1.setOrganizationName("Test Organization Name 1");
        superOrganization1.setOrganizationDescription("Description 1");
        superOrganization1.setLocation(location);
        superOrganization1.setSupers(supers);
        superOrganization1 = superOrganizationDaoDb.addSuperOrganization(superOrganization1);

        SuperOrganization superOrganization = superOrganizationDaoDb.getSuperOrganizationById(superOrganization1.getOrganizationId());
        assertEquals(superOrganization,superOrganization1);

    }



    @Test
    void getAllSuperOrganizations() {

        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDaoDb.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Super Name");
        superPerson.setPower(power);
        superPerson.setSuperDescription("Test Super Description");
        superPerson.setIsSuper(superPerson.getIsSuper());
        superPerson = superDaoDb.addSuper(superPerson);

        List<Super> supers = new ArrayList<>();
        supers.add(superPerson);

        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDescription("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLocationCity("Test Location City");
        location.setLocationState("Test Location State");
        location.setLocationZip(75234);
        location.setLocationLat(location.getLocationLat());
        location.setLocationLong(location.getLocationLong());
        location = locationDaoDb.addLocation(location);

        List<Location> locations = new ArrayList<>();
        locations.add(location);

        SuperOrganization superOrganization1 = new SuperOrganization();
        superOrganization1.setOrganizationName("Test Organization Name 1");
        superOrganization1.setOrganizationDescription("Description 1");
        superOrganization1.setLocation(location);
        superOrganization1.setSupers(supers);
        superOrganization1 = superOrganizationDaoDb.addSuperOrganization(superOrganization1);

        SuperOrganization superOrganization2 = new SuperOrganization();
        superOrganization2.setOrganizationName("Test Organization Name 2");
        superOrganization2.setOrganizationDescription("Description 2");
        superOrganization2.setLocation(location);
        superOrganization2.setSupers(supers);
        superOrganization2 = superOrganizationDaoDb.addSuperOrganization(superOrganization2);


        List<SuperOrganization> superOrganizations = superOrganizationDaoDb.getAllSuperOrganizations();
        assertEquals(2, superOrganizations.size());
        assertTrue(superOrganizations.contains(superOrganization1));
        assertTrue(superOrganizations.contains(superOrganization2));
    }



    @Test
    void updateSuperOrganization() {
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDaoDb.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDaoDb.addSuper(superPerson);

        Super retrievedSuper = superDaoDb.getSuperById(superPerson.getSuperId());
        List<Super> supers = new ArrayList<>();
        supers.add(superPerson);

        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDescription("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLocationCity("Test Location City");
        location.setLocationState("Test Location State");
        location.setLocationZip(75234);
        location.setLocationLat(location.getLocationLat());
        location.setLocationLong(location.getLocationLong());
        location = locationDaoDb.addLocation(location);

        List<Location> locations = new ArrayList<>();
        locations.add(location);

        SuperOrganization superOrganization1 = new SuperOrganization();
        superOrganization1.setOrganizationName("Test Organization Name 1");
        superOrganization1.setOrganizationDescription("Description 1");
        superOrganization1.setLocation(location);

        superOrganization1.setSupers(supers);
        superOrganization1 = superOrganizationDaoDb.addSuperOrganization(superOrganization1);

        SuperOrganization superOrganization = superOrganizationDaoDb.getSuperOrganizationById(superOrganization1.getOrganizationId());
        assertEquals(superOrganization,superOrganization1);

        superOrganization1.setOrganizationName("NEW TEST NAME");

        superOrganizationDaoDb.updateSuperOrganization(superOrganization1);

        SuperOrganization fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization.getOrganizationId());

        assertNotEquals(superOrganization, fromDao);

        fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization1.getOrganizationId());
        assertEquals(superOrganization1, fromDao);
    }


    @Test
    void deleteSuperOrganizationById() {
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDaoDb.addPower(power);

        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDescription("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLocationCity("Test Location City");
        location.setLocationState("Test Location State");
        location.setLocationZip(75234);
        location.setLocationLat(45.265);
        location.setLocationLong(75.365);
        location = locationDaoDb.addLocation(location);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Super Name");
        superPerson.setPower(power);
        superPerson.setSuperDescription("Test Super Description");
        superPerson.setIsSuper(true);
        superPerson = superDaoDb.addSuper(superPerson);

        Super superPerson1 = new Super();
        superPerson1.setSuperName("Test Super Name1");
        superPerson1.setPower(power);
        superPerson1.setSuperDescription("Test Super Description1");
        superPerson1.setIsSuper(true);
        superPerson1 = superDaoDb.addSuper(superPerson1);

        List<Super> supers = new ArrayList<>();
        supers.add(superPerson);
        supers.add(superPerson1);

        SuperOrganization superOrganization = new SuperOrganization();
        superOrganization.setOrganizationName("Test Organization Name");
        superOrganization.setOrganizationDescription("Test Organization Description");
        superOrganization.setSupers(supers);
        superOrganization.setLocation(location);
        superOrganization = superOrganizationDaoDb.addSuperOrganization(superOrganization);

        SuperOrganization fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization.getOrganizationId());
        assertEquals(superOrganization, fromDao);

        superOrganizationDaoDb.deleteSuperOrganizationById(superOrganization.getOrganizationId());

        fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization.getOrganizationId());
        assertNull(fromDao);
    }
}