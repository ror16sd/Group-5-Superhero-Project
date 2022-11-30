/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import superhero.dao.LocationDao;
import superhero.dao.SightingDao;
import superhero.dao.SuperDao;
import superhero.model.Location;
import superhero.model.Power;
import superhero.model.Sighting;
import superhero.model.Super;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ciruf
 */
@Controller
@RequestMapping("/super-sightings")
public class SuperSightingController {

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperDao superDao;

    @Autowired
    LocationDao locationDao;
    
    @GetMapping
    public String getSuperSightings(Model model) {

//        List<Sighting> sightings = sightingDao.getAllSightings();
//        model.addAttribute("sightings", sightings);

        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Super> superheroes = superDao.getAllSupers();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("locations", locations);

        return "Sighting";
    }
    
    @PostMapping("addSighting")
    public String createSuperSighting(Sighting sighting, HttpServletRequest request) {
        String sightingSuperId = request.getParameter("superId");
        String locationId = request.getParameter("locationId");
        String date = request.getParameter("date");

        sighting.setSightingSuper(superDao.getSuperById(Integer.parseInt(sightingSuperId)));
        sighting.setSightingLocation(locationDao.getLocationById(Integer.parseInt(locationId)));

        return "redirect:/Sighting";
    }
    
    @GetMapping("/{id}")
    public String getSuperSighting(@PathVariable int id) {
        return "NOT IMPLEMENTED: Get specific super sighting";
    }
    
    @PutMapping("/{id}")
    public String updateSuperSighting(@PathVariable int id) {
        return "NOT IMPLEMENTED: Update specific super sighting";
    }
    
    @DeleteMapping("/{id}")
    public String deleteSuperSighting(@PathVariable int id) {
        return "NOT IMPLEMENTED: Delete specific super sighting";
    }
}
