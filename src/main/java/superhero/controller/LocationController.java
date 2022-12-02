/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import superhero.dao.LocationDao;
import superhero.model.Location;
import superhero.model.Super;

/**
 *
 * @author ciruf
 */
@Controller
@RequestMapping("/locations")
public class LocationController {
    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    
    @Autowired
    LocationDao locationDao;
    
    @GetMapping
    public String getLocations(Model model) {
        final List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations);
        return "HomeBase.html";
    }
       
    @PostMapping
    public String createLocation(Location location) {
      Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
      violations = validate.validate(location);  
      
      if (violations.isEmpty()) {
           locationDao.addLocation(location);
       }
      
      return "redirect:/locations";
    }
}
