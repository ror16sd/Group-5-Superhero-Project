/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
import superhero.dao.PowerDao;
import superhero.dao.SuperDao;
import superhero.model.Power;
import superhero.model.Super;

/**
 *
 * @author ciruf
 */
@Controller
@RequestMapping("/super-people")
public class SuperPersonController {
   @Autowired
   SuperDao superDao;
   
   @Autowired
   PowerDao powerDao;
   
   @GetMapping
    public String getSuperPeople(Model model) {
        List<Super> supers = superDao.getAllSupers();
        List<Power> powers = powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        model.addAttribute("superPeople", supers);
        return "Who";
    }
    
    @PostMapping
    public String createSuperPerson(Super superPerson, HttpServletRequest request, Model model) {
        final boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
        final Power power = powerDao.getPowerById(Integer.parseInt(request.getParameter("powerId")));
        return "redirect:/Who";
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
    
    //TEST METHODS
    private List<Power> testPowerList() {
        List<Power> powers = new ArrayList<>();
        Power testPow1 = new Power();
        testPow1.setPowerId(1);
        testPow1.setPowerDescription("Super Strength");
        Power testPow2 = new Power();
        testPow2.setPowerId(2);
        testPow2.setPowerDescription("Invisibility");
        Power testPow3 = new Power();
        testPow3.setPowerId(3);
        testPow3.setPowerDescription("Omniscence");
        powers.add(testPow1);
        powers.add(testPow2);
        powers.add(testPow3);
        return powers;
    }
}
