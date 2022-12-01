package superhero;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import superhero.dao.*;
import superhero.model.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SightingDaoDbTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDao.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDao.addSuper(superPerson);

        List<Location> locations = locationDao.getAllLocations();
        List<Super> supers = superDao.getAllSupers();

        Sighting sighting = new Sighting();
        sighting.setSightingLocation(location);
        sighting.setSightingLocations(locations);
        sighting.setSightingDate(LocalDate.parse("2022-11-11",formatter));
        sighting.setSightingSuper(superPerson);
        sighting.setSupers(supers);
        sighting = sightingDao.addSighting(sighting);

        Sighting retrievedSighting = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(retrievedSighting,sighting);

    }

    @Test
    void getAllSightings() {
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

        Sighting sighting1 = new Sighting();
        sighting1.setSightingLocation(location);
        sighting1.setSightingLocations(locations);
        sighting1.setSightingDate(LocalDate.parse("2022-11-29",formatter));
        sighting1.setSightingSuper(superPerson);
        sighting1.setSupers(supers);
        sighting1 = sightingDao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingLocation(location1);
        sighting2.setSightingLocations(locations);
        sighting2.setSightingDate(LocalDate.parse("2022-11-28",formatter));
        sighting2.setSightingSuper(superPerson1);
        sighting2.setSupers(supers);
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting1));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    void updateSighting() {

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

        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDao.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Test Name");
        superPerson.setSuperDescription("Test Description");
        superPerson.setIsSuper(true);
        superPerson.setPower(power);
        superPerson = superDao.addSuper(superPerson);

        List<Location> locations = locationDao.getAllLocations();
        List<Super> supers = superDao.getAllSupers();

        Sighting sighting = new Sighting();
        sighting.setSightingLocation(location);
        sighting.setSightingLocations(locations);
        sighting.setSightingDate(LocalDate.parse("2022-11-11",formatter));
        sighting.setSightingSuper(superPerson);
        sighting.setSupers(supers);
        sighting = sightingDao.addSighting(sighting);

        Sighting retreievedSighting = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting,retreievedSighting);
        //After the fixes to the updateSuperSighting method the 2043 years was
        //being truncated in MySQL because it
        sighting.setSightingDate(LocalDate.parse("2023-04-01",formatter));
        sightingDao.updateSighting(sighting);

        assertNotEquals(sighting,retreievedSighting);
        retreievedSighting = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting,retreievedSighting);
    }

    @Test
    void deleteSightingById() {
        Power power = new Power();
        power.setPowerDescription("Test Power");
        power = powerDao.addPower(power);

        Super superPerson = new Super();
        superPerson.setSuperName("Superman");
        superPerson.setPower(power);
        superPerson.setSuperDescription("Man of Steel");
        superPerson.setIsSuper(true);
        superPerson = superDao.addSuper(superPerson);

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

        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2022-11-15",formatter));
        sighting.setSightingLocation(location);
        sighting.setSightingSuper(superPerson);
        sighting = sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);

        sightingDao.deleteSightingById(sighting.getSightingId());

        fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertNull(fromDao);


    }

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
        //UPDATED SO SUPER PERSON CAN EXIST
        //Before this superId of superPerson would be 0
        //This fixed this test
        superPerson = superDao.addSuper(superPerson);
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2022-11-15",formatter));
        sighting.setSightingLocation(location);
        sighting.setSightingSuper(superPerson);
        sighting.setSupers(supers);
        sighting = sightingDao.addSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate(LocalDate.parse("2022-11-15",formatter));
        sighting2.setSightingLocation(location2);
        sighting2.setSightingSuper(superPerson);
        sighting2.setSupers(supers);
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getSightingsForLocation(location);
        assertEquals(1, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertFalse(sightings.contains(sighting2));

    }
}