/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import superhero.dao.PowerDao;
import superhero.dao.SuperDao;
import superhero.model.Power;

import java.net.BindException;
import java.util.List;

/**
 *
// * @author ciruf
 */
@Controller
@RequestMapping("/super-powers")
public class SuperPowerController {

    @Autowired
    public PowerDao powerDao;

//    @Autowired
//    SuperDao superDao;

    @GetMapping("powers")
    public String getSuperPowers(Model model) {

        List<Power> powers = powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        return "SuperPower";
    }

    @PostMapping
    public String createSuperPower(Power power, HttpServletRequest request) {
//        powerDao.addPower(power);
        return "redirect:/SuperPower";

    }

    @GetMapping("/{id}")
    public String getSuperPower(@PathVariable int id) {
//        Power power = powerDao.getPowerById(id);
        return "SuperPower";
    }


    @GetMapping("edit")
    public String editPower(Integer id, Model model) {
//        Power power = powerDao.getPowerById(id);
//        model.addAttribute("power", power);
        return "editPower";
    }



//    @PutMapping("/{id}")
//    public String updateSuperPower(@PathVariable int id, @RequestBody Power power) {
//        Power updatePower = powerDao.getPowerById(id);
//        updatePower.setPowerId(power.getPowerId());
//        updatePower.setPowerDescription(power.getPowerDescription());
//        powerDao.updatePower(power);
//        return "editPower";
//    }
//
    @PostMapping("edit")
    public String performEditPower(@Valid Power power, BindingResult result){
        if(result.hasErrors()) {
            return "editPower";
        }
//        powerDao.updatePower(power);
        return "redirect:/SuperPower";

    }


    @GetMapping("deletePower")
    public String deleteSuperPower(@PathVariable int powerId) {
//        powerDao.deletePowerById(powerId);
        return "redirect:/SuperPower";

    }

}