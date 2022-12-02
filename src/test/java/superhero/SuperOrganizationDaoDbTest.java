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
        List<SuperOrganization> organizations = superOrganizationDaoDb.getAllSuperOrganizations();
        for (SuperOrganization organization : organizations) {
            superOrganizationDaoDb.deleteSuperOrganizationById(organization.getOrganizationId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetSuperOrganization() {
        Super superPerson = new Super();
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDaoDb.addPower(power);

        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDaoDb.addSuper(superPerson);
//
        List<Super> supers = new ArrayList<>();
        supers.add(superPerson);
//
        List<Location> locations = locationDaoDb.getAllLocations();

        Location location = new Location();
        location.setLocationName("Disney World");
        location.setLocationDescription("The Happiest Place on Earth");
        location.setLocationAddress("1375 E Buena Vista Dr");
        location.setLocationCity("Orlando");
        location.setLocationState("Florida");
        location.setLocationZip(32830);
        location.setLocationLat(28.37779);
        location.setLocationLong(-81.57065);
        location = locationDaoDb.addLocation(location);

        SuperOrganization superOrganization = new SuperOrganization();
        superOrganization.setOrganizationName("Test Organization Name");
        superOrganization.setOrganizationDescription("Test Organization Description");
        superOrganization.setLocation(location);
        superOrganization.setSupers(supers);
//        superOrganization.setSightingLocations(locations);
        superOrganization = superOrganizationDaoDb.addSuperOrganization(superOrganization);

        SuperOrganization fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization.getOrganizationId());
        assertEquals(superOrganization, fromDao);
    }



//    @Test
//    void getAllSuperOrganizations() {
//        Power power = new Power();
//        power.setPowerDescription("Test Power");
//        power = powerDaoDb.addPower(power);
//
//        Location location = new Location();
//        location.setLocationName("Disney World");
//        location.setLocationDescription("The Happiest Place on Earth");
//        location.setLocationAddress("1375 E Buena Vista Dr");
//        location.setLocationCity("Orlando");
//        location.setLocationState("Florida");
//        location.setLocationZip(32830);
//        location.setLocationLat(28.37779);
//        location.setLocationLong(-81.57065);
//
//        location = locationDaoDb.addLocation(location);
//
//        Location location1 = new Location();
//        location1.setLocationName("Space Needle");
//        location1.setLocationDescription("Large observation tower");
//        location1.setLocationAddress("400 Broad St");
//        location1.setLocationCity("Seattle");
//        location1.setLocationState("Washington");
//        location1.setLocationZip(98109);
//        location1.setLocationLat(47.62062);
//        location1.setLocationLong(-122.34929);
//        location1 = locationDaoDb.addLocation(location1);
//
//        List<Location> locations = locationDaoDb.getAllLocations();
//
//        power.setPowerDescription("Test Power");
//        power = powerDaoDb.addPower(power);
//
//        Super superPerson = new Super();
//        superPerson.setSuperName("Test Name");
//        superPerson.setSuperDescription("Test Description");
//        superPerson.setIsSuper(true);
//        superPerson.setPower(power);
//        superPerson = superDaoDb.addSuper(superPerson);
//
//        Power power1 = new Power();
//        power1.setPowerDescription("Test Power1");
//        power1 = powerDaoDb.addPower(power1);
//
//        Super superPerson1 = new Super();
//        superPerson1.setSuperName("Test Name1");
//        superPerson1.setSuperDescription("Test Description1");
//        superPerson1.setIsSuper(true);
//        superPerson1.setPower(power1);
//        superPerson1 = superDaoDb.addSuper(superPerson1);
//
//        List<Super> supers = superDaoDb.getAllSupers();
//
//        SuperOrganization superOrganization1 = new SuperOrganization();
//        superOrganization1.setOrganizationName("Test Organization Name 1");
//        superOrganization1.setOrganizationDescription("Description 1");
//        superOrganization1.setLocation(location);
//        superOrganization1.setSightingLocations(locations);
//        superOrganization1.setSupers(supers);
//        superOrganization1 = superOrganizationDaoDb.addSuperOrganization(superOrganization1);
//
//        SuperOrganization superOrganization2 = new SuperOrganization();
//        superOrganization2.setOrganizationName("Test Organization Name 2");
//        superOrganization2.setOrganizationDescription("Description 2");
//        superOrganization2.setLocation(location1);
//        superOrganization2.setSightingLocations(locations);
//        superOrganization2.setSupers(supers);
//        superOrganization2 = superOrganizationDaoDb.addSuperOrganization(superOrganization2);
//
//
//        List<SuperOrganization> superOrganizations = superOrganizationDaoDb.getAllSuperOrganizations();
//        assertEquals(2, superOrganizations.size());
//        assertTrue(superOrganizations.contains(superOrganization1));
//        assertTrue(superOrganizations.contains(superOrganization2));
//    }
//
//
//
//    @Test
//    void updateSuperOrganization() {
//        Power power = new Power();
//        power.setPowerDescription("Test Power");
//        power = powerDaoDb.addPower(power);
//
//        Location location = new Location();
//        location.setLocationName("Test Location Name");
//        location.setLocationDescription("Test Location Description");
//        location.setLocationAddress("Test Location Address");
//        location.setLocationCity("Test Location City");
//        location.setLocationState("Test Location State");
//        location.setLocationZip(75234);
//        location.setLocationLat(85.234);
//        location.setLocationLong(98.236);
//        location = locationDaoDb.addLocation(location);
//
//        Super superPerson = new Super();
//        superPerson.setSuperName("Test Super Name");
//        superPerson.setPower(power);
//        superPerson.setSuperDescription("Test Super Description");
//        superPerson.setIsSuper(true);
//        superPerson = superDaoDb.addSuper(superPerson);
//
//        List<Super> supers = new ArrayList<>();
//        supers.add(superPerson);
//
//        SuperOrganization superOrganization = new SuperOrganization();
//        superOrganization.setOrganizationName("Test Organization Name");
//        superOrganization.setOrganizationDescription("Test Organization Description");
//        superOrganization.setLocation(location);
//        superOrganization.setSupers(supers);
//        superOrganization = superOrganizationDaoDb.addSuperOrganization(superOrganization);
//
//        SuperOrganization fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization.getOrganizationId());
//        assertEquals(superOrganization, fromDao);
//
//        superOrganization.setOrganizationName("New Test Organization Name");
//        Super super2 = new Super();
//        super2.setSuperName("Test Super 2");
//        super2.setSuperDescription("Test Super Description");
//        super2.setPower(power);
//        super2.setIsSuper(false);
//        supers.add(super2);
//        superOrganization.setSupers(supers);
//
//
//        superOrganizationDaoDb.updateSuperOrganization(superOrganization);
//
//        assertNotEquals(superOrganization, fromDao);
//
//        fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization.getOrganizationId());
//        assertEquals(superOrganization, fromDao);
//    }
//

//    @Test
//    void deleteSuperOrganizationById() {
//        Location location = new Location();
//        location.setLocationName("Test Location Name");
//        location.setLocationDescription("Test Location Description");
//        location.setLocationAddress("Test Location Address");
//        location.setLocationCity("Test Location City");
//        location.setLocationState("Test Location State");
//        location.setLocationZip(75234);
//        location.setLocationLat(45.265);
//        location.setLocationLong(75.365);
//        location = locationDaoDb.addLocation(location);
//
//        Power power = new Power();
//        power.setPowerDescription("Test Power");
//        power = powerDaoDb.addPower(power);
//
//        Super superPerson = new Super();
//        superPerson.setSuperName("Test Super Name");
//        superPerson.setPower(power);
//        superPerson.setSuperDescription("Test Super Description");
//        superPerson.setIsSuper(true);
//        superPerson = superDaoDb.addSuper(superPerson);
//
//        List<Super> supers = new ArrayList<>();
//        supers.add(superPerson);
//
//        SuperOrganization superOrganization = new SuperOrganization();
//        superOrganization.setOrganizationName("Test Organization Name");
//        superOrganization.setOrganizationDescription("Test Organization Description");
//        superOrganization.setLocation(location);
//        superOrganization.setSupers(supers);
//        superOrganization = superOrganizationDaoDb.addSuperOrganization(superOrganization);
//
//        //TODO: look at getById and add/update location in all methods
//        SuperOrganization fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization.getOrganizationId());
//        System.out.println(superOrganization.getLocation() + " " + superOrganization.getOrganizationId());
//        System.out.println(fromDao.getLocation() + " " + fromDao.getOrganizationId());
//        assertEquals(superOrganization, fromDao);
//
//        superOrganizationDaoDb.deleteSuperOrganizationById(superOrganization.getOrganizationId());
//
//        fromDao = superOrganizationDaoDb.getSuperOrganizationById(superOrganization.getOrganizationId());
//        assertNull(fromDao);
//    }
}