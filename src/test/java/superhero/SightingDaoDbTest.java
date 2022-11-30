package superhero;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import superhero.dao.*;
import superhero.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SightingDaoDbTest {

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

    public SightingDaoDbTest(){

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
    void testAddAndGetSighting() {
        List<Location> locations = new ArrayList<>();
        List<Super> supers = new ArrayList<>();

        Sighting sighting = new Sighting();
        sighting.setSightingLocations(locations);
        sighting.setSightingDate(Timestamp.valueOf("2022-11-11 03:25:45"));
        sighting.setSupers(supers);
        sighting = sightingDao.addSighting(sighting);

    }

    @Test
    void getAllSightings() {
        List<Location> locations = new ArrayList<>();
        List<Super> supers = new ArrayList<>();

        Sighting sighting1 = new Sighting();
        sighting1.setSightingLocations(locations);
        sighting1.setSightingDate(Timestamp.valueOf("2022-11-29 12:45:55"));
        sighting1.setSupers(supers);
        sighting1 = sightingDao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingLocations(locations);
        sighting2.setSightingDate(Timestamp.valueOf("2022-11-28 10:30:45"));
        sighting2.setSupers(supers);
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting1));
        assertTrue(sightings.contains(sighting2));
    }

//    @Test
//    void updateSighting() {
//        List<Location> locations = new ArrayList<>();
//        List<Super> supers = new ArrayList<>();
//
//        Sighting sighting1 = new Sighting();
//        sighting1.setSightingLocations(locations);
//        sighting1.setSightingDate(Timestamp.valueOf("2022-11-29 12:45:55"));
//        sighting1.setSupers(supers);
//        sighting1 = sightingDao.addSighting(sighting1);
//
//        Sighting fromDao = sightingDao.getSightingById(sighting1.getSightingId());
//        assertEquals(sighting1, fromDao);
//
//        Sighting sighting2 = new Sighting();
//        sighting2.setSightingLocations(locations);
//        sighting2.setSightingDate(Timestamp.valueOf("2022-11-28 10:30:45"));
//        sighting2.setSupers(supers);
//        sighting2 = sightingDao.addSighting(sighting2);
//
//        sightingDao.updateSighting(sighting2);
//
//        assertNotEquals(sighting2, fromDao);
//
//        fromDao = sightingDao.getSightingById(sighting2.getSightingId());
//        assertEquals(sighting2, fromDao);
//
//    }

//    @Test
//    void deleteSightingById() {
//        Power power = new Power();
//        power.setPowerDescription("Test Power");
//        power = powerDao.addPower(power);
//
//        Super superPerson = new Super();
//        superPerson.setSuperName("Superman");
//        superPerson.setPower(power);
//        superPerson.setSuperDescription("Man of Steel");
//        superPerson.setIsSuper(true);
//        superPerson = superDao.addSuper(superPerson);
//
//        Location location = new Location();
//        location.setLocationName("The Mall");
//        location.setLocationDescription("Test Location");
//        location.setLocationAddress("Test Address");
//        location.setLocationState("Test State");
//        location.setLocationCity("Test City");
//        location.setLocationZip(75234);
//        location.setLocationLat(65.362);
//        location.setLocationLong(56.362);
//        location = locationDao.addLocation(location);
//
//        Sighting sighting = new Sighting();
//        sighting.setSightingDate(Timestamp.valueOf("2022-11-15 04:30:55"));
//        sighting.setSightingLocation(location);
//        sighting.setSightingSuper(superPerson);
//        sighting = sightingDao.addSighting(sighting);
//
//        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
//        assertEquals(sighting, fromDao);
//
//        sightingDao.deleteSightingById(sighting.getSightingId());
//
//        fromDao = sightingDao.getSightingById(sighting.getSightingId());
//        assertNull(fromDao);
//
//
//    }
//
    @Test
    void getSightingsForLocation() {
        Location location = new Location();
        location.setLocationName("The Mall");
        location.setLocationDescription("Test Location");
        location.setLocationAddress("Test Address");
        location.setLocationState("Test State");
        location.setLocationCity("Test City");
        location.setLocationZip(75234);
        location.setLocationLat(65.362);
        location.setLocationLong(56.362);
        location = locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("The Mall 2");
        location2.setLocationDescription("Test Location 2");
        location2.setLocationAddress("Test Address 2");
        location2.setLocationState("Test State 2");
        location2.setLocationCity("Test City 2");
        location2.setLocationZip(10011);
        location2.setLocationLat(45.63);
        location2.setLocationLong(77.63);
        location2 = locationDao.addLocation(location2);

        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDao.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setPower(power);
        superPerson.setIsSuper(true);

        List<Super> supers = new ArrayList<>();
        supers.add(superPerson);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(Timestamp.valueOf("2022-11-15 04:30:55"));
        sighting.setSightingLocation(location);
        sighting.setSupers(supers);
        sighting = sightingDao.addSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate(Timestamp.valueOf("2022-11-15 04:30:55"));
        sighting2.setSightingLocation(location2);
        sighting2.setSupers(supers);
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getSightingsForLocation(location);
        assertEquals(1, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertFalse(sightings.contains(sighting2));

    }
}