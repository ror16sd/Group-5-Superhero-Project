/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import java.time.format.DateTimeFormatter;
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
    
    @PostMapping
    public String createSuperSighting(HttpServletRequest request) {
        String sightingSuperId = request.getParameter("superId");
        String locationId = request.getParameter("locationId");
        String date = request.getParameter("sight_date");

        Sighting sighting = new Sighting();
        sighting.setSightingSuper(superDao.getSuperById(Integer.parseInt(sightingSuperId)));
        sighting.setSightingLocation(locationDao.getLocationById(Integer.parseInt(locationId)));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(date, formatter);
        sighting.setSightingDate(ld);
        sightingDao.addSighting(sighting);
        return "redirect:/super-sightings";
    }
    
//    @GetMapping("/{id}")
//    public String getSuperSighting(@PathVariable int id) {
//        return "NOT IMPLEMENTED: Get specific super sighting";
//    }
//

    @GetMapping("editSighting")
    public String updateSuperSighting(Integer sightingId, Model model) {

        Sighting sighting = sightingDao.getSightingById(sightingId);
        List<Super> superheroes = superDao.getAllSupers();
        List<Location> locations = locationDao.getAllLocations();
        final String sightingDate = sighting.getSightingDate().toString();
        model.addAttribute("sighting", sighting);
        model.addAttribute("superheroes", superheroes);
        model.addAttribute("locations", locations);
        model.addAttribute("sightingDate", sightingDate);

        return "editSighting";
    }


    @PostMapping("editSighting")
    public String performEditSighting(@Valid Sighting sighting, BindingResult result,
                                      HttpServletRequest request, Model model) {
        String superId = request.getParameter("superId");
        String locationId = request.getParameter("locationId");
        String date = request.getParameter("date");

        System.out.println(sighting.toString());


        if (superId == null) {
            FieldError error = new FieldError("sighting", "super", "Must include a super");
            result.addError(error);
        }

        if (locationId == null) {
            FieldError error = new FieldError("sighting", "location","Must include a location");
            result.addError(error);
        }

        if (date == null) {
            FieldError error = new FieldError("sighting", "date", "Must include a date");
            result.addError(error);
        }




        sighting.setSightingSuper(superDao.getSuperById(Integer.parseInt(superId)));
        sighting.setSightingLocation(locationDao.getLocationById(Integer.parseInt(locationId)));
        sighting.setSightingDate(LocalDate.parse(date));

        sightingDao.updateSighting(sighting);

        if (result.hasErrors()) {
            model.addAttribute("sighting", sighting);

            return "redirect:/super-sightings/editSighting?sightingId=" + sighting.getSightingId();
        }
        sightingDao.updateSighting(sighting);

        return "redirect:/super-sightings";

    }
    
    @GetMapping("deleteSighting")
    public String deleteSuperSighting(Integer sightingId) {
        sightingDao.deleteSightingById(sightingId);
        return "redirect:/super-sightings";
    }
}
