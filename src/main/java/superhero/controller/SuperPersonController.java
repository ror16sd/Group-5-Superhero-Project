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
@RequestMapping("/super-people")
public class SuperPersonController {
   @GetMapping
    public String getSuperPeople() {
        return "Who";
    }
    
    @PostMapping
    public String createSuperPerson() {
        return "NOT IMPLEMENTED: Create super person";
    }
    
    @GetMapping("/{id}")
    public String getSuperPerson(@PathVariable int id) {
        return "NOT IMPLEMENTED: Get specific super person";
    }
    
    @PutMapping("/{id}")
    public String updateSuperPerson(@PathVariable int id) {
        return "NOT IMPLEMENTED: Update specific super person";
    }
    
    @DeleteMapping("/{id}")
    public String deleteSuperPerson(@PathVariable int id) {
        return "NOT IMPLEMENTED: Delete specific super person";
    }
}
