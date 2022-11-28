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
@RequestMapping("/api/super-organizations")
public class SuperOrganizationController {
    
    @GetMapping
    public String getSuperOrganizations() {
        return "NOT IMPLEMENTED: View super organizations";
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
