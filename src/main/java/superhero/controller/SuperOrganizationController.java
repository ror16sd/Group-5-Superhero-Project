/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import java.util.List;
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
import superhero.dao.SuperDao;
import superhero.dao.SuperOrganizationDao;
import superhero.model.Location;
import superhero.model.Super;
import superhero.model.SuperOrganization;

/**
 *
 * @author ciruf
 */
@Controller
@RequestMapping("/super-organizations")
public class SuperOrganizationController {
    @Autowired
    LocationDao locationDao;
    @Autowired
    SuperDao superDao;
    @Autowired
    SuperOrganizationDao organizationDao;
    @GetMapping
    public String getSuperOrganizations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        List<Super> superPeople = superDao.getAllSupers();
        List<SuperOrganization> organizations =  organizationDao.getAllSuperOrganizations();
        model.addAttribute("locations", locations);
        model.addAttribute("superPeople", superPeople);
        model.addAttribute("organizations", organizations);
        return "Organization";
    }
    
    @PostMapping
    public String createSuperOrganization() {
        return "NOT IMPLEMENTED: Create super organization";
    }
    
    @GetMapping("/{id}")
    public String getSuperOrganization(@PathVariable int id) {
        return "NOT IMPLEMENTED: Get specific super organization";
    }
    
    @PutMapping("/{id}")
    public String updateSuperOrganization(@PathVariable int id) {
        return "NOT IMPLEMENTED: Update specific super organization";
    }
    
    @DeleteMapping("/{id}")
    public String deleteSuperOrganization(@PathVariable int id) {
        return "NOT IMPLEMENTED: Delete specific super organization";
    }
    
}
