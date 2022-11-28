/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

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
@RestController
@RequestMapping("/api/super-powers")
public class SuperPowerController {
    
    @GetMapping
    public String getSuperPowers() {
        return "NOT IMPLEMENTED: View super powers";
    }
    
    @PostMapping
    public String createSuperPower() {
        return "NOT IMPLEMENTED: Create super powers";
    }
    
    @GetMapping("/{id}")
    public String getSuperPower(@PathVariable int id) {
        return "NOT IMPLEMENTED: Get specific super power";
    }
    
    @PutMapping("/{id}")
    public String updateSuperPower(@PathVariable int id) {
        return "NOT IMPLEMENTED: Update specific super power";
    }
    
    @DeleteMapping("/{id}")
    public String deleteSuperPower(@PathVariable int id) {
        return "NOT IMPLEMENTED: Delete specific super power";
    }
    
}
