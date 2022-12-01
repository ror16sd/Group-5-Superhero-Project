/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superhero.controller;

import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import superhero.dao.PowerDao;
import superhero.dao.SuperDao;
import superhero.model.Location;
import superhero.model.Power;
import superhero.model.Sighting;
import superhero.model.Super;

import javax.validation.Valid;
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

    @Autowired
    SuperDao superDao;

    @GetMapping
    public String getSuperPowers(Model model) {

        List<Power> powers = powerDao.getAllPowers();
        model.addAttribute("powers", powers);
        return "SuperPower";
    }

    // Get power by ID
    // Need to check this again, null error, powerId return null value
    @GetMapping("/{id}")
    public String getSuperPower(Model model, Integer powerId) {
        Power power = powerDao.getPowerById(powerId);

        model.addAttribute("power", power);

        return "redirect:/super-powers";
    }


    @PostMapping
    public String createSuperPower(HttpServletRequest request) {
        String powerDescription = request.getParameter("powerDescription");
        Power power = new Power();
        power.setPowerDescription(powerDescription);
        powerDao.addPower(power);
        return "redirect:/super-powers";
    }


    @GetMapping("editPower")
    public String editPower(Integer powerId, Model model) {
        Power power = powerDao.getPowerById(powerId);
        model.addAttribute("power", power);
        return "EditPower";
    }

    @PostMapping("editPower")
    public String performEditPower(@Valid Power power, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "EditPower";
        }

        powerDao.updatePower(power);
        return "redirect:/super-powers" + power.getPowerId();

    }

//    public String editPower(Integer powerId, Model model) {
//
//        Power power = powerDao.getPowerById(powerId);
//        List<Super> superheroes = superDao.getAllSupers();
//        model.addAttribute("superheroes", superheroes);
//        model.addAttribute("power", power);
//
//
//        return "EditPower";
//
//    }
//
//    @PostMapping("editPower")
//    public String performEditPower(@Valid Power power, BindingResult result){
//        if(result.hasErrors()) {
//            return "EditPower";
//        }
//        powerDao.updatePower(power);
//        return "redirect:/super-powers";
//
//    }

    @GetMapping("deletePower")
    public String deleteSuperPower(Integer powerId) {
        powerDao.deletePowerById(powerId);
        return "redirect:/super-powers";

    }

}