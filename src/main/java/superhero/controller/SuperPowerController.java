/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import superhero.dao.PowerDao;
import superhero.dao.SuperDao;
import superhero.model.Power;

import java.util.List;

/**
 *
 * @author ciruf
 */
@Controller
@RequestMapping("/super-powers")
public class SuperPowerController {

    @Autowired
    public PowerDao powerDao;

//    @Autowired
//    SuperDao superDao;
    
    @GetMapping("/powers")
    public String getSuperPowers(Model model) {

        List<Power> powers = powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        return "SuperPower";
    }
    
    @PostMapping
    public String createSuperPower(int powerId, String powerDescription) {
        Power power = new Power();
        power.setPowerId(powerId);
        power.setPowerDescription(powerDescription);
        powerDao.addPower(power);
        return "redirect:/SuperPower";

    }
    
    @GetMapping("/{id}")
    public String getSuperPower(@PathVariable int id) {

        return "NOT IMPLEMENTED: Get specific super power";
    }
    
    @PutMapping("/{id}")
    public String updateSuperPower(@PathVariable int id, @RequestBody Power power) {
        Power updatePower = powerDao.getPowerById(id);
        updatePower.setPowerId(power.getPowerId());
        updatePower.setPowerDescription(power.getPowerDescription());
        powerDao.updatePower(power);
        return "editPower";
    }
    
    @DeleteMapping("/{id}")
    public String deleteSuperPower(@PathVariable int id) {
        Power deletePower = powerDao.getPowerById(id);
        powerDao.deletePowerById(id);
        return "redirect:/SuperPower";

    }
    
}
