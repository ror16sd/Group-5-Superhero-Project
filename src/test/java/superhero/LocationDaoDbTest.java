package superhero;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import superhero.dao.*;
import superhero.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationDaoDbTest {

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

    public LocationDaoDbTest(){

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
    void TestGetLocationByIdAndAdd() {
       Location location = new Location();
       location.setLocationName("Disney World");
       location.setLocationDescription("The Happiest Place on Earth");
       location.setLocationAddress("1375 E Buena Vista Dr");
       location.setLocationCity("Orlando");
       location.setLocationState("Florida");
       location.setLocationZip(32830);
       location.setLocationLat(28.37779);
       location.setLocationLong(-81.57065);

       location = locationDao.addLocation(location);
       Location retrievedLocation = locationDao.getLocationById(location.getLocationId());
       assertEquals(location,retrievedLocation);

    }

    @Test
    void getAllLocations() {
        Location location = new Location();
        location.setLocationName("Disney World");
        location.setLocationDescription("The Happiest Place on Earth");
        location.setLocationAddress("1375 E Buena Vista Dr");
        location.setLocationCity("Orlando");
        location.setLocationState("Florida");
        location.setLocationZip(32830);
        location.setLocationLat(28.37779);
        location.setLocationLong(-81.57065);

        location = locationDao.addLocation(location);

        Location location1 = new Location();
        location1.setLocationName("Space Needle");
        location1.setLocationDescription("Large observation tower");
        location1.setLocationAddress("400 Broad St");
        location1.setLocationCity("Seattle");
        location1.setLocationState("Washington");
        location1.setLocationZip(98109);
        location1.setLocationLat(47.62062);
        location1.setLocationLong(-122.34929);

        location1 = locationDao.addLocation(location1);

        List<Location> locations = locationDao.getAllLocations();
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location1));

    }


    @Test
    void updateLocation() {
        Location location1 = new Location();
        location1.setLocationName("Space Needle");
        location1.setLocationDescription("Large observation tower");
        location1.setLocationAddress("400 Broad St");
        location1.setLocationCity("Seattle");
        location1.setLocationState("Washington");
        location1.setLocationZip(98109);
        location1.setLocationLat(47.62062);
        location1.setLocationLong(-122.34929);
        location1 = locationDao.addLocation(location1);

        Location retrievedLocation = locationDao.getLocationById(location1.getLocationId());
        assertEquals(location1,retrievedLocation);
        location1.setLocationCity("Tampa");
        locationDao.updateLocation(location1);

        assertNotEquals(location1,retrievedLocation);

        retrievedLocation = locationDao.getLocationById(location1.getLocationId());
        assertEquals(location1,retrievedLocation);
    }

    @Test
    void deleteLocationById() {
        Location location1 = new Location();
        location1.setLocationName("Space Needle");
        location1.setLocationDescription("Large observation tower");
        location1.setLocationAddress("400 Broad St");
        location1.setLocationCity("Seattle");
        location1.setLocationState("Washington");
        location1.setLocationZip(98109);
        location1.setLocationLat(47.62062);
        location1.setLocationLong(-122.34929);
        location1 = locationDao.addLocation(location1);

        Location retrievedLocation = locationDao.getLocationById(location1.getLocationId());
        assertEquals(location1,retrievedLocation);

        locationDao.deleteLocationById(location1.getLocationId());
        retrievedLocation = locationDao.getLocationById(location1.getLocationId());
        assertNull(retrievedLocation);

    }
}