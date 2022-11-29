package superhero;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import superhero.dao.*;
import superhero.model.*;

import java.util.List;

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
    void getSuperById() {
    }

    @Test
    void getAllSupers() {
    }

    @Test
    void addSuper() {
    }

    @Test
    void updateSuper() {
    }

    @Test
    void deleteSuperById() {
    }

    @Test
    void getPowersForSuper() {
    }
}