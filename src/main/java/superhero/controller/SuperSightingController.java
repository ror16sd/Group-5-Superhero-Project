/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ciruf
 */
@Controller
@RequestMapping("/super-sightings")
public class SuperSightingController {
    
    @GetMapping
    public String getSuperSightings() {
        return "Sighting";
    }
    
    @PostMapping
    public String createSuperSighting() {
        return "NOT IMPLEMENTED: Create super sighting";
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
