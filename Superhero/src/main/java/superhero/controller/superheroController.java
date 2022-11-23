package superhero.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class superheroController {

    @GetMapping
    public String getHomePage() {
        return "NOT IMPLEMENTED: Send homepage";
    }
    
    @GetMapping("/super-people")
    public String getSuperPeople() {
        return "NOT IMPLEMENTED: View super people";
    }
    
    @PostMapping("/super-people")
    public String createSuperPerson() {
        return "NOT IMPLEMENTED: Create super person";
    }
    
    @GetMapping("/super-people/{id}")
    public String getSuperPerson(@PathVariable int id) {
        return "NOT IMPLEMENTED: Get specific super person";
    }
    
    @PutMapping("/super-people/{id}")
    public String updateSuperPerson(@PathVariable int id) {
        return "NOT IMPLEMENTED: Update specific super person";
    }
    
    @DeleteMapping("/super-people/{id}")
    public String deleteSuperPerson(@PathVariable int id) {
        return "NOT IMPLEMENTED: Delete specific super person";
    }
    
    @GetMapping("/super-powers")
    public String getSuperPowers() {
        return "NOT IMPLEMENTED: View super powers";
    }
    
    @PostMapping("/super-powers")
    public String createSuperPower() {
        return "NOT IMPLEMENTED: Create super powers";
    }
    
    @GetMapping("/super-powers/{id}")
    public String getSuperPower(@PathVariable int id) {
        return "NOT IMPLEMENTED: Get specific super power";
    }
    
    @PutMapping("/super-powers/{id}")
    public String updateSuperPower(@PathVariable int id) {
        return "NOT IMPLEMENTED: Update specific super power";
    }
    
    @DeleteMapping("/super-powers/{id}")
    public String deleteSuperPower(@PathVariable int id) {
        return "NOT IMPLEMENTED: Delete specific super power";
    }
    
    //ADD LOCATION ENPOINTS HERE IF NEEDED
    
    @GetMapping("/super-organizations")
    public String getSuperOrganizations() {
        return "NOT IMPLEMENTED: View super organizations";
    }
    
    @PostMapping("/super-organizations")
    public String createSuperOrganization() {
        return "NOT IMPLEMENTED: Create super organization";
    }
    
    @GetMapping("/super-organizations/{id}")
    public String getSuperOrganization(@PathVariable int id) {
        return "NOT IMPLEMENTED: Get specific super organization";
    }
    
    @PutMapping("/super-organizations/{id}")
    public String updateSuperOrganization(@PathVariable int id) {
        return "NOT IMPLEMENTED: Update specific super organization";
    }
    
    @DeleteMapping("/super-organizations/{id}")
    public String deleteSuperOrganization(@PathVariable int id) {
        return "NOT IMPLEMENTED: Delete specific super organization";
    }
    
    @GetMapping("/super-sightings")
    public String getSuperSightings() {
        return "NOT IMPLEMENTED: view super sightings";
    }
    
    @PostMapping("/super-sightings")
    public String createSuperSighting() {
        return "NOT IMPLEMENTED: Create super sighting";
    }
    
    @GetMapping("/super-sightings/{id}")
    public String getSuperSighting(@PathVariable int id) {
        return "NOT IMPLEMENTED: Get specific super sighting";
    }
    
    @PutMapping("/super-sightings/{id}")
    public String updateSuperSighting(@PathVariable int id) {
        return "NOT IMPLEMENTED: Update specific super sighting";
    }
    
    @DeleteMapping("/super-sightings/{id}")
    public String deleteSuperSighting(@PathVariable int id) {
        return "NOT IMPLEMENTED: Delete specific super sighting";
    }
}
